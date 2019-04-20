> 来自: [字符集和比较规则](<https://juejin.im/book/5bffcbc9f265da614b11b731/section/5bffd9c651882520980229a0>)

### 字符集

- `ASCII` 字符集
  共收录 128 个字符，包括空格、标点符号、数字、大小写字母和一些不可见字符。由于总共才 128 个字符，所以可以使用 1 个字节来进行编码。

- `ISO 8859-1` 字符集
  共收录 256 个字符，是在 `ASCII` 字符集的基础上又扩充了 128 个西欧常用字符 ( 包括德法两国的字母 )，也可以使用 1 个字节来进行编码。这个字符集也有一个别名 `latin1`。

- `GB2312` 字符集
  收录了汉字以及拉丁字母、希腊字母、日文平假名及片假名字母、俄语西里尔字母。其中收录汉字 6763 个，其他文字符号 682 个。同时这种字符集又兼容 `ASCII` 字符集，所以在编码方式上显得有些奇怪：

  - 如果该字符在 `ASCII` 字符集中，则采用 1 字节编码。
  - 否则采用 2 字节编码。

  这种表示一个字符需要的字节数可能不同的编码方式称为 `变长编码方式`。

- `GBK` 字符集
  `GBK` 字符集只是在收录字符范围上对 `GB2312` 字符集作了扩充，编码方式上兼容 `GB2312`。

- `utf8` 字符集
  收录地球上能想到的所有字符，而且还在不断扩充。这种字符集兼容 `ASCII` 字符集，采用变长编码方式，编码一个字符需要使用 1～4 个字节。

#### utf8 和 utf8mb4

`utf8` 字符集表示一个字符需要使用 1～4 个字节，但是我们常用的一些字符使用 1～3 个字节就可以表示了。而在 `MySQL` 中字符集表示一个字符所用最大字节长度在某些方面会影响系统的存储和性能，所以 `MySQL` 定义了两个概念：

- `utf8mb3` : 阉割过的 `utf8` 字符集，只使用 1～3 个字节表示字符。
- `utf8mb4` : 正宗的 `utf8` 字符集，使用 1～4 个字节表示字符。

有一点需要注意的是，在 `MySQL` 中 `utf8` 是 `utf8mb3` 的别名，所以之后在 `MySQL` 中提到 `utf8` 就意味着使用 1~3 个字节来表示一个字符，如果大家有使用 4 字节编码一个字符的情况，比如存储 emoji 表情，请使用 `utf8mb4`。

#### 比较规则

- 比较规则名称以与其关联的字符集的名称开头。

- 后边紧跟着该比较规则主要作用于哪种语言。

  > `utf8_spanish_ci` 是以西班牙语的规则比较，`utf8_general_ci` 是一种通用的比较规则。

- 名称后缀意味着该比较规则是否区分语言中的重音、大小等。

|  后缀  | 英文释义             | 描述             |
| :----: | :------------------- | :--------------- |
| `_ai`  | `accent insensitive` | 不区分重音       |
| `_as`  | `accent sensitive`   | 区分重音         |
| `_ci`  | `case insensitive`   | 不区分大小写     |
| `_cs`  | `case sensitive`     | 区分大小写       |
| `_bin` | `binary`             | 以二进制方式比较 |

##### 服务器级别

设置：
- 在启动服务器时通过启动选项，使用 `SET` 修改

- 在服务器运行过程中使用 `SET` 修改

- 在配置文件中修改
  ```mysql
  [server]
  character_set_server=gbk
  collation_server=gbk_chinese_ci
  ```
  

查看字符集：`SHOW VARIABLES LIKE 'character_set_server';`
查看比较规则：`SHOW VARIABLES LIKE 'collation_server';`

##### 数据库级别
创建时指定 :
```mysql
CREATE DATABASE 数据库名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [[DEFAULT] COLLATE 比较规则名称];
```

修改时指定 :
```mysql
ALTER DATABASE 数据库名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [[DEFAULT] COLLATE 比较规则名称];
```

查看字符集：`SHOW VARIABLES LIKE 'character_set_database';`
查看比较规则：`SHOW VARIABLES LIKE 'collation_database';`

##### 表级别

创建时指定：

```mysql
CREATE TABLE 表名 (列的信息)
    [[DEFAULT] CHARACTER SET 字符集名称]
    [COLLATE 比较规则名称]]
```

修改时指定：

```mysql
ALTER TABLE 表名
    [[DEFAULT] CHARACTER SET 字符集名称]
    [COLLATE 比较规则名称]
```

##### 列级别

创建时指定：

```mysql
CREATE TABLE 表名(
    列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称],
    其他列...
);
```

修改时指定：

```mysql
ALTER TABLE 表名 MODIFY 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称];
```
> 在转换列的字符集时需要注意，如果转换前列中存储的数据不能用转换后的字符集进行表示会发生错误。

