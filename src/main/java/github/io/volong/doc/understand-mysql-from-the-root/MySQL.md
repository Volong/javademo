##### 02 初识MySQL

- 查看当前服务器程序支持的存储引擎

  `show engines;`

- 创建表时指定存储引擎

  ```mysql
  CREATE TABLE 表名(
      建表语句;
  ) ENGINE = 存储引擎名称;
  ```

- 修改表的存储引擎

  ```mysql
  ALTER TABLE 表名 ENGINE = 存储引擎名称;
  ```

---

##### 03 启动选项和配置文件

- 类Unix操作系统中的配置文件

  <div align="center">
    <img src="images/mysql配置文件路径.png" height="500px" />
  </div>

  

- 查看系统变量的命令

  `show variables [like 匹配的模式];`

  例：`show variables like 'max_connections';`

---

##### 04 字符集和比较规则

- `ASCII`字符集

  共收录128个字符，包括空格、标点符号、数字、大小写字母和一些不可见字符。

  由于总共才128个字符，所以可以使用1个字节来进行编码

- `ISO 8859-1`字符集

  共收录256个字符，是在`ASCII`字符集的基础上又扩充了128个西欧常用字符(包括德法两国的字母)，也可以使用1个字节来进行编码。

  它的别名叫`latin1`

- `GB2312`字符集

  收录了汉字以及拉丁字母、希腊字母、日文平假名及片假名字母、俄语西里尔字母。

  其中收录汉字6763个，其他文字符号682个。

  同时这种字符集又兼容`ASCII`字符集，所以在编码方式上显得有些奇怪：

  - 如果该字符在`ASCII`字符集中，则采用1字节编码。
  - 否则采用2字节编码。

- `GBK`字符集

  `GBK`字符集只是在收录字符范围上对`GB2312`字符集作了扩充，编码方式上兼容`GB2312`。

- `utf8`字符集

  收录所有的字符，而且还在不断扩充。

  这种字符集兼容`ASCII`字符集，采用变长编码方式，编码一个字符需要使用1～4个字节。

  > utf8只是Unicode字符集的一种编码方案，Unicode字符集可以采用utf8、utf16、utf32这几种编码方案，utf8使用1～4个字节编码一个字符，utf16使用2个或4个字节编码一个字符，utf32使用4个字节编码一个字符。

- MySQL中的`utf8`和`utf8mb4`

  - `utf8mb3`：阉割过的`utf8`字符集，只使用1～3个字节表示字符。

    我们常用的字符使用1～3个字节就可以表示了

  - `utf8mb4`：正宗的`utf8`字符集，使用1～4个字节表示字符

  在`MySQL`中`utf8`是`utf8mb3`的别名。

- 查看字符集的命令

  `show (character set | charset) [like 匹配的模式];`

  > `CHARACTER SET`和`CHARSET`是同义词，用任意一个都可以

- 查看比较规则的命令

  `show collation [like 匹配的模式]`

- 比较规则的规律

  - 比较规则名称以与其关联的字符集的名称开头

  - 后边紧跟着该比较规则主要作用于哪种语言

    比如`utf8_polish_ci`表示以波兰语的规则比较

    `utf8_general_ci`是一种通用的比较规则

- 名称后缀意味着该比较规则是否区分语言中的重音、大小写等

  |  后缀  |       英文释义       |       描述       |
  | :----: | :------------------: | :--------------: |
  | `_ai`  | `accent insensitive` |    不区分重音    |
  | `_as`  |  `accent sensitive`  |     区分重音     |
  | `_ci`  |  `case insensitive`  |   不区分大小写   |
  | `_cs`  |   `case sensitive`   |    区分大小写    |
  | `_bin` |       `binary`       | 以二进制方式比较 |

- 4个级别的字符集和比较规则分别是

  - 服务器级别
  - 数据库级别
  - 表级别
  - 列级别

- 服务器级别的字符集和比较规则的系统变量分别是

  - `character_set_server` - 服务器级别的字符集
  - `collation_server` - 服务器级别的比较规则

- 创建数据库时指定数据库的字符集和比较规则

  `create database 数据库名 character set 字符集名称 collate 比较规则名称`

- 修改数据库字符集和比较规则

  `alter database 数据库名 character set 字符集名称 collate 比较规则名称`

- 当前数据库使用的字符集和比较规则的系统变量分别是

  - `character_set_database` - 当前数据库的字符集
  - `collation_database` - 当前数据库的比较规则

- 

