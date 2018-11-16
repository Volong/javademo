>   翻译自：https://lucidworks.com/2018/06/20/solr-and-optimizing-your-index-take-ii/

优化 (optimize) 与删除不再跟之前一样糟糕。但是也不应该随便使用，因为它所需要的成本依然很高。也就是说，这些操作不再那么容易受[这篇文章](https://lucidworks.com/2017/10/13/segment-merging-deleted-documents-optimize-may-bad/)中列出的问题的影响。如果你对 Solr/Lucene 中的段合并操作不那么熟悉，那篇文章可以给你一些背景知识。

## 摘要

-   从 Solr 7.5 开始，删除与优化 / 强制合并的的默认实现 `TieredMergePolicy (TMP) ` 将有完全不同的表现
-   `TieredMergePolicy` 将有一些额外的选项用来控制在一个索引中删除文档的百分比。 见 [LUCENE-8263](https://issues.apache.org/jira/browse/LUCENE-8263)
-   `TMP` 对于强制合并与删除有一个默认的参数 *maxMergedSegmentMB* 
-   如果想要执行以前强制合并 (优化) 的操作，可以通过命令指定 `maxSegments`
-   删除不会超过 `maxMergedSegmentMB` 指定的值
-   如果创建了非常大的段，随着删除的文档在段中的堆积。这个段将会"单独合并"，用于清理掉那些已经被删除的文档。注意：只有当索引中有接近 50% 的文档被删除时，这种情况才会发生。有可能后续会对这种情况进行调节

## 介绍

不久之前，我写了一篇关于使用 Solr 的 [优化以及删除的提交选项](https://lucidworks.com/2017/10/13/segment-merging-deleted-documents-optimize-may-bad/) 的文章。随着 Solr 7.5 的到来，文章中提到的最坏情况将不会出现。如果你想要知道更多的细节，可以参考 [LUCENE-7976](https://issues.apache.org/jira/browse/LUCENE-7976) 以及相关的 JIRA。

在 Solr 7.5 中，优化 (又名强制删除) 以及删除使用 `TieredMergePolicy` 作为默认以及推荐的合并策略，并且遵循 *maxMergedSegmentMB* 参数的配置。

这样一个简短的介绍，涉及到一些重要的方面，因此将这篇文章发了出来。

## 快速回顾 Solr 7.5 之前的合并以及删除

首先快速回顾一下，通过命令去执行优化或者删除操作，默认的行为是任何合并的段将会被合并成一个段，*而不管最终的结果段会变成多大*。

-   对于优化操作，整个索引将会被合并到 `maxSegments` 参数 (默认为 1) 指定的个数的段中。
-   对于删除操作，所有删除的文档超过 10% 的段会被合并到一个段中。

对于"自然"合并，当索引被更新时，每次硬提交都会进行如下的初始化操作：

-   所有"活着"的文档小于 `maxMergedSegmentMB` 50% 的段都会被检查，被选择的段会进行合并。
-   "被选择的段"意味着使用启发式算法来尝试选择最少工作量的合并，并且依然会遵循 `maxMergedSegmentMB`。

关键的不同在于优化/强制合并与删除*并不遵循 maxMergedSegmentMB*。

### 之前为什么要实现 maxMergedSegmentMB?

关于这点，有过很长的讨论，但是我并不想谈论这个。因为保持索引更新需要去解决一些相互竞争，而 `maxMergedSegmentMB` 是解决这些问题的一部分。需要权衡的点包括：

-   控制 I/O，因为索引以及搜索对 I/O 瓶颈敏感
-   控制段数，防止耗尽文件句柄数等
-   控制内存消耗，仅仅为了索引而要求分配 5G 的内存在堆上是不可接受的
-   