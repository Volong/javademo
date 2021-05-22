
>   翻译自：[Index File Formats](https://lucene.apache.org/core/3_6_2/fileformats.html#Index%20File%20Formats)

# Apache Lucene - Index File Formats

## 简介

这篇文档说明了这个 Lucene 版本的索引格式。如果你使用的是其他版本的 Lucence，请考虑参考你所使用版本 Lucene 的 `docs/fileformats.html` 文件。

Lucene 是用 Java 写的，但是还有[其它语言](http://wiki.apache.org/lucene-java/LuceneImplementations)写的。如果这些版本想要跟 Lucene 兼容，那需要需要定义与语言无关的 Lucene 索引格式。这篇文档因此提供了关于 Lucene 文件索引完整且独立的定义。

随着 Lucene 的不断更新迭代，这篇文档也会不断迭代。不同编程语言的 Lucene 版本应该在索引文件格式上保持一致，并不断形成新的文档。

本文档提供了兼容性说明，说明了文件格式跟之前版本不一样的地方。

>   关于不同版本，文件格式的差异性就不翻译了。翻译这篇文档主要是为了了解 Lucene 的索引格式长什么样，以及如果自定义索引格式。

## 定义

Lucene 的基本概念是 index、document、field 以及 term。

>   这几个名词不打算翻译出来。因为我觉得这样更贴切

index 包含一系列的文档：

-   document 包含一系列的 field
-   field 包含一系列的 term
-   term 就是字符串

相同的字符串在不同的 field 上被认为是不同的 term。因为 term 通过一对字符串来表示，第一个表示 field 的名字，第二个表示字段中的文本。

### 倒排索引 (inverted Indexing)

为了让基于 term 的搜索更加的有效率，index 存储了有关 term 的统计。Lucene 的 index 在 index 分类中被称为 *倒排索引*。这是因为 term 可以列出包含它的文档。也就是文档可以列出 term 的自然关系反转。

>   说白一点就是：以前是通过文档去找某个词，现在是通过词可以找到文档。

### field 的类型

在 Lucene 中，field 可以被 *stored*，在这种情况下，它们的文本是按照字面意思被直接存储在 index 中，而不是通过倒排的方式。field 可以被倒排则称为 *indexed*。field 可以既被 *stroed*，也可以被 *indexed*。

field 中的文本可以被 *tokenized* 变成 term，然后被索引。或者 field 中的文本按照字面意思直接被索引。大部分字段都可以被 tokenized，但是有些时候，一些具有标识性的字段按照字面意思直接被索引是有用的。

>   关于 field 的更多信息查看 [Field]([Field](https://lucene.apache.org/core/3_6_2/api/core/org/apache/lucene/document/Field.html) ) java doc

### 段 (segment)

Lucene 的索引由多个子索引或者*段* 组成，每个段都是一个完全独立的 index，所以可以单独进行搜索。索引的处理流程为：

1.  对新添加的 document 创建新的段
2.  合并已经存在的段

搜索可能涉及多个段或者多个索引，每个索引由多个段组成。

### 文档编号 (document numbers)

在 Lucene 内部，通过一个整数 *文档编号* 来标识一个 document。第一个被添加到 index 中的 document 编号为 0，下一个为 1，每次编号自增 1。

注意：文档编号可能会变化。所以当把编号存储在 Lucene 之外的地方需要注意这一点。一下几种情况可能会导致编号变化：

-   每个段中存储的编号在这个段中是唯一的，并且将它们应用在更大的上下文中时需要进行转换。标准的做法是基于段中编号的范围，为每个段分配一系列的值。为了将段中文档编号转为外部值，需要添加段的基础文档编号。为了将外部值转换回段指定的值，通过外部值所在的范围减去段的基本文档编号来标识段。例如两个包含五个段的文档在合并的时候，第一个段的基础编号为 0，第二个为 5，第二段中的第三个文档的外部值为 8。
-   当文档被删除时，在编号中会产生空隙。在合并的时候，它们会被最终移除掉。在合并段的时候，删除的文档将会被删除。一个新合并的段在它的编号中不会产生间隙。

## 简介

 每个段的索引包含一下部分：

-   Field names. 在索引中包含字段名字的集合

-   Stored Filed values. 在每个文档中，包含一个属性-值的键值对的列表，属性为字段的名字。它们被用来存储文档的额外信息，例如 title，url 或者一个访问数据库的标识。在搜索的时候被命中，会返回一个存储字段集。它们的键为文档编号。

    >   译者注：存储字段表示 stored=true

-   Term dictionary. 词典包含所有文档中的所有索引域中使用的词。词典也包含了包含这个词的文档数量以及指向词频与距离的指针。

-   Term Frequency data. 对于词典中的每个词，包含了包含这个词的文档数，以及每个文档中该词出现的频次。除非通过 IndexOptions.DOCS_ONLY 忽略的频次信息。

-   Term Proximity data. 对于词典中的每个词，包含了每个文档中词的位置信息。注意，如果所有文档中的所有字段都忽略了位置信息，那么该数据就不会存在。

-   Normalization factors. 对于每个文档中的每个字段，包含一个用来乘以命中字段时得分的值。

-   Term Vectors. 对于每个文档中的每个字段，词向量（有时也叫文档向量） 可能会被存储。一个词向量由词与词频组成。要将词向量添加到索引，参见 [Field](https://lucene.apache.org/core/3_6_2/api/core/org/apache/lucene/document/Field.html) 构造函数。

-   Deleted documents. 通过一个可选的文件，用来表明哪些文档已经被删除了。

详细的信息将会在后续的章节中介绍。

## 文件命名

所有属于同一个段 (segment) 的文件有相同的名字，但是扩展名不同。扩展名对应接下来要说的文件不同格式。当使用混合文件格式 (Compound File fomat) 时 (默认在 1.4 或者更高的版本)，这些文件会被聚合成一个 .cfs 文件 (详情见下面)。

通常，一个索引中所有的段都会被存储到单独文件夹中，虽然这不是必须的。

在版本 2.1 中 (无锁提交)，文件名不会被重复使用 (有一个例外 "segments.gen"，详情如下)。也就是说，任何被保存到文件夹中的文件使用一个以前没有用过的文件名。这通过一个简单的生成方法来实现。例如，第一段文件为 segments_1，那么第二个为 segments_2，依此类推。生成的是以阿拉伯数字 (基于 36) 表示的连续的长整型。

## 文件扩展名总结

下表总结了 Lucene 中的文件的名字以及扩展名

| 名字                                                         | 扩展名                   | 描述                                                         |
| ------------------------------------------------------------ | ------------------------ | ------------------------------------------------------------ |
| [Segments File](https://lucene.apache.org/core/3_6_2/fileformats.html#Segments%20File) | segments.gen, segments_N | 存储段相关的信息                                             |
| [Lock File](https://lucene.apache.org/core/3_6_2/fileformats.html#Lock%20File) | write.lock               | 写入锁可以阻止多个 IndexWriter 往同一个文件中写              |
| [Compound File](https://lucene.apache.org/core/3_6_2/fileformats.html#Compound%20Files) | .cfs                     | 一个可选的"虚拟"文件。由其他经常耗尽文件句柄的系统的其它索引文件组成。 |
| [Compound File Entry table](https://lucene.apache.org/core/3_6_2/fileformats.html#Compound%20File) | .cfe                     | 一个 "虚拟" 的混合文件条目表。包含 .cfs 文件中相应的所有条目 (3.4 版本) |
| [Fields](https://lucene.apache.org/core/3_6_2/fileformats.html#Fields) | .fnm                     | 存储 field 相关信息                                          |
| [Field Index](https://lucene.apache.org/core/3_6_2/fileformats.html#field_index) | .fdx                     | 包含指向 field 数据的指针                                    |
| [Field Data](https://lucene.apache.org/core/3_6_2/fileformats.html#field_data) | .fdt                     | 存储 document 的 field                                       |
| [Term Infos](https://lucene.apache.org/core/3_6_2/fileformats.html#tis) | .tis                     | 部分 term 的词典，存储 term 的信息                           |
| [Term Info Index](https://lucene.apache.org/core/3_6_2/fileformats.html#tii) | .tii                     | Term Infos 文件包含的 index                                  |
| [Frequencies](https://lucene.apache.org/core/3_6_2/fileformats.html#Frequencies) | .frq                     | 包含文档列表中每个 term 与它出现的频次                       |
| [Positions](https://lucene.apache.org/core/3_6_2/fileformats.html#Positions) | .prx                     | 存储 term 在 index 中的位置信息                              |
| [Norms](https://lucene.apache.org/core/3_6_2/fileformats.html#Normalization%20Factors) | .nrm                     | 为 doc 以及 field 编码长度以及加权因子                       |
| [Term Vector Index](https://lucene.apache.org/core/3_6_2/fileformats.html#tvx) | .tvx                     | 存储 doc 中数据的偏移量                                      |
| [Term Vector Documents](https://lucene.apache.org/core/3_6_2/fileformats.html#tvd) | .tvd                     | 包含每个 doc 中的 term 向量信息                              |
| [Term Vector Fields](https://lucene.apache.org/core/3_6_2/fileformats.html#tvf) | .tvf                     | term 向量中的 field 级别信息                                 |
| [Deleted Documents](https://lucene.apache.org/core/3_6_2/fileformats.html#Deleted%20Documents) | .del                     | 删除的文件信息                                               |

## 原始类型

### Byte

大部分的原始类型都是 8 位字节。文件通过连续的字节被访问。所有其他类型的数据都被定义为字节顺序，因此文件格式跟字节顺序是无关的。

### UInt32

32 位无符号整数被当作 4 个字节，高位字节在前。

$UInt32 --> <Byte>^4$

### Uint64

64 位无符号整数被当作 8 个字节，高位字节在前。

$UInt64 --> <Byte>^8 $

### VInt

为正整数定义的可变长度的格式。每个字节的高位表示是否还有多余的字节需要读取。低 7 位可以通过增加有效位数来表示整数的值。因此 0 到 127 可以被存储在一个字节中，128 到 16,383 可以被存储在两个字节中，以此类推。

**VInt 编码示例**

| 值     | 第一字节 | 第二字节 | 第三字节 |
| ------ | -------- | -------- | -------- |
| 0      | 00000000 |          |          |
| 1      | 00000001 |          |          |
| 2      | 00000010 |          |          |
| ...    |          |          |          |
| 127    | 01111111 |          |          |
| 128    | 10000000 | 00000001 |          |
| 129    | 10000001 | 00000001 |          |
| 130    | 10000010 | 00000001 |          |
| ...    |          |          |          |
| 16,383 | 11111111 | 01111111 |          |
| 16,384 | 10000000 | 10000000 | 00000001 |
| 16,385 | 10000001 | 10000000 | 00000001 |
| ...    |          |          |          |

这样不但提供了压缩，还能够有效的解码。

>   举个例子：对于 129，第一字节 10000001 中，高位的 1 表示还需要读取一个字节；第二字节 00000001 中，高位的 0 表示没有需要读取的字节了，所以最终的表示为第二个字节的后七位加上第一个字节的后七位，即：00000010000001 => 129

### Chars

Lucene 将 unicode 字符序列当作 UTF-8 编码的字节。

### String

Lucene 将 unicode 字符序列当作 UTF-8 编码的字节。

String --> VInt, Chars

## 复合类型

### Map<String,String>

Lucene 在一些地方存储了一个 Map String -> String

$Map<String,String> --> Count<String,String>^{Count}$

## 每个 index 文件

本节中的文件都是单独的 index。

### 段 (Segments)

Lucene 的索引由多个子索引或者段组成。每个段都是完全独立的索引，能够单独的进行搜索。
