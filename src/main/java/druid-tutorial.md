>   原文：http://druid.io/docs/latest/tutorials/tutorial-ingestion-spec.html

## 定义 schema

核心元素：`dataSchema`，定义了怎么将数据解析成一个列的集合存储到 Druid 中。
新建一个空的 `dataSchema`：

```json
"dataSchema" : {}
```

### 数据库的名字

通过 `dataSource` 来指定数据库的名字。

### 选择一个解析器

通过 `parser` 定义一个解析器来指定 Druid 如何解析输入的数据。
例：

```json
"dataSchema" : {
    "dataSource" : "ingestion-tutorial",
    "parser" : {
        "type" : "string",
        "parseSpec" : {
            "format" : "json"
        }
    }
}
```

## 时间列

`parser` 需要知道怎么从输入的数据中抽取时间字段。当使用 `json` 类型的 `parseSpec` 时，时间戳字段定义在 `timestampSpec` 中。
例：

```json
"dataSchema" : {
    "dataSource" : "ingestion-tutorial",
    "parser" : {
        "type" : "string",
        "parseSpec" : {
            "format" : "json",
            "timestampSpec" : {
                "format" : "iso", // 输入数据时间字段的格式
                "column" : "ts"   // 输入数据时间字段的名字
            }
        }
    }
}
```

## 列的类型

Druid 支持的列类型为：`String`、`Long`、`Float`、`Double`。

## 汇总 (Rollup)

-   如果需要汇总，那么我们需要将输入的列分成两类，维度列与度量列。在汇总的时候会将维度列进行分组，会将度量列进行聚合。
-   如果不需要汇总，那么所有的列都会被当作度量列，并且不会进行预聚合。

通过如下方式指定：

```json
"dataSchema" : {
  "dataSource" : "ingestion-tutorial",
  "parser" : {
    "type" : "string",
    "parseSpec" : {
      "format" : "json",
      "timestampSpec" : {
        "format" : "iso",
        "column" : "ts"
      }
    }
  },
  "granularitySpec" : {
    "rollup" : true
  }
}
```

#### 维度列

在 `parseSpec` 内通过 `dimensionsSpec` 指定维度列。
例：

```json
"dataSchema" : {
  "dataSource" : "ingestion-tutorial",
  "parser" : {
    "type" : "string",
    "parseSpec" : {
      "dimensionsSpec" : {
        "dimensions": [
          "srcIP",
          { "name" : "srcPort", "type" : "long" },
          { "name" : "dstIP", "type" : "string" },
          { "name" : "dstPort", "type" : "long" },
          { "name" : "protocol", "type" : "string" }
        ]
      }
    }
  }
}
```

每个维度都有 `name`、`type`。`type` 的值可以为 `long`，`float`，`double`，`string`。
对于 `string` 类型的列，仅仅只需要指定名字就可以了，因为默认的类型为 `string`。

>   `srcIP` 是 `string` 维度

##### Strings vs. Numerics

输入的数据为数值应该被当作数值列还是字符列？
数值维度列相对字符维度列有如下的优缺点：

-   优：数值类型可以降低存储在磁盘上列的大小；读取数据时会降低开销。
-   缺：数值类型没有索引，所以在过滤的时候比字符类型 (有位图索引) 要慢。

#### 度量列

在 `dataSchema` 里通过 `metricsSpec` 指定度量列。
例：

```json
"dataSchema" : {
  "dataSource" : "ingestion-tutorial",
  "metricsSpec" : [
    { "type" : "count", "name" : "count" },
    { "type" : "longSum", "name" : "packets", "fieldName" : "packets" },
    { "type" : "longSum", "name" : "bytes", "fieldName" : "bytes" },
    { "type" : "doubleSum", "name" : "cost", "fieldName" : "cost" }
  ]
}
```

在定义度量列时，需要指定汇总时对该列执行什么类型的聚合。
如上所示：`packets` 与 `bytes` 为 `long` 类型。`cost` 为 `double` 类型。
还定义了一个 `count` 的聚合字段。该字段会统计汇总时原始数据的行数。

### 不要汇总

如果你不需要进行汇总，那么可以将所有的列都定义在 `dimensionsSpec` 中。例：

```json
 "dimensionsSpec" : {
        "dimensions": [
          "srcIP",
          { "name" : "srcPort", "type" : "long" },
          { "name" : "dstIP", "type" : "string" },
          { "name" : "dstPort", "type" : "long" },
          { "name" : "protocol", "type" : "string" },
          { "name" : "packets", "type" : "long" },
          { "name" : "bytes", "type" : "long" },
          { "name" : "srcPort", "type" : "double" }
        ]
      }
```

### 定义粒度

通过 `granularitySpec` 指定粒度，类型为：`uniform` 与 `arbitrary`。





