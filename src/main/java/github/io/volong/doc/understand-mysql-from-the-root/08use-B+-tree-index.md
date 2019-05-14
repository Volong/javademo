> 来源：[B+树索引的使用](https://juejin.im/book/5bffcbc9f265da614b11b731/section/5bffdbf06fb9a049f570dc4f)

```mysql
CREATE TABLE person_info(
    id INT NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    phone_number CHAR(11) NOT NULL,
    country varchar(100) NOT NULL,
    PRIMARY KEY (id),
    KEY idx_name_birthday_phone_number (name, birthday, phone_number)
);
```

#### 全值匹配

搜索条件中的列和索引列一致。例：

```mysql
SELECT * FROM person_info WHERE name = 'Ashburn' AND birthday = '1990-09-27' AND phone_number = '15123983239';
```

> `WHERE` 子句中查询条件的顺序对查询的执行过程没有影响

#### 匹配左边的列

查询语句中也可以不用包含全部联合索引中的列，只包含左边的就行。

```mysql
SELECT * FROM person_info WHERE name = 'Ashburn';
```

或者包含多个左边的列也行。

```mysql
SELECT * FROM person_info WHERE name = 'Ashburn' AND birthday = '1990-09-27';
```

那为什么查询条件中必须出现左边的列才可以使用到这个 `B+` 树索引呢？

因为 `B+` 树的数据页和记录先是按照 `name` 列的值排序的，在 `name` 列的值相同的情况下才使用 `birthday` 列进行排序，也就是说 `name` 列的值不同的记录中 `birthday` 的值可能是无序的。所以跳过 `name` 列直接根据`birthday ` 的值去查找是用不到 `B+` 树索引的。

#### 匹配列前缀

对于字符串类型的索引列来说，只匹配它的前缀也是可以快速定位记录的。

```mysql
SELECT * FROM person_info WHERE name LIKE 'As%';
```

因为在索引中，字符串的字符也是按照顺序排列的。

#### 匹配范围值

因为在 `B+` 树索引中，所有记录都是按照索引列的值从小到大的顺序排好序的，所以可以查找索引列的值在某个范围内的记录。

```mysql
SELECT * FROM person_info WHERE name > 'Asa' AND name < 'Barlow';
```

由于 `B+` 树中的数据页和记录是先按 `name` 列排序的，所以上边的查询过程如下所示：

- 找到 `name` 值为 `Asa` 的记录
- 找到 `name` 值为 `Barlow` 的记录
- 由于所有记录都是由链表连起来的（记录之间用单链表，数据页之间用双链表），所以他们之间的记录都可以很容易的取出来
- 找到这些记录的主键值，再到`聚簇索引`中`回表`查找完整的记录

在使用联合进行范围查找的时候需要注意，如果对多个列同时进行范围查找的话，只有对索引最左边的那个列进行范围查找的时候才能用到 `B+` 树索引：

```mysql
SELECT * FROM person_info WHERE name > 'Asa' AND name < 'Barlow' AND birthday > '1980-01-01';
```

上面这个查询只能用到 `name` 列的部分，而用不到 `birthday` 列的部分，因为只有 `name` 值相同的情况下才能用 `birthday` 列的值进行排序，而这个查询中通过 `name` 进行范围查找的记录中可能并不是按照 `birthday` 列进行排序的。

#### 精确匹配某一列并范围匹配另外一列

对于同一个联合索引来说，如果左边的列是精确查找，则右边的列可以进行范围查找的：

```mysql
SELECT * FROM person_info WHERE name = 'Ashburn' AND birthday > '1980-01-01' AND birthday < '2000-12-31' AND phone_number > '15100000000';
```

- 对 `name` 列进行精确查找，可以使用`B+`树索引
- 通过 `name = 'Ashburn'` 条件查找后得到的结果的 `name` 值都是相同的，它们会再按照 `birthday` 的值进行排序。所以对 `birthday` 列进行范围查找是可以用到 `B+` 树索引的
- 通过 `birthday` 的范围查找到的记录的 `birthday` 的值可能不同，所以 `phone_number` 这个条件无法再利用  `B+` 树索引了，只能遍历上一步查询得到的记录

#### 用于排序

有时需要对查询出来的记录通过 `ORDER BY` 子句按照某种规则进行排序。一般情况下，我们只能把记录都加载到内存中，再用一些排序算法在内存中对这些记录进行排序，有时候可能查询的结果集太大以至于不能在内存中进行排序，需要暂时借助磁盘的空间来存放中间结果，排序操作完成后再把排好序的结果集返回到客户端。

在 MySQL 中，把在内存中或者磁盘上进行排序的方式统称为文件排序（filesort）。但是如果 `ORDER BY` 子句里使用到了索引列，就有可能省去在内存或文件中排序的步骤：

```mysql
SELECT * FROM person_info ORDER BY name, birthday, phone_number LIMIT 10;
```

对于这个查询来说，`B+ `树索引本身就是按照上述规则排好序的，所以直接从索引中提取数据，然后进行`回表`操作取出该索引中不包含的列就好了。

##### 使用联合索引进行排序注意事项

对于`联合索引`需要注意的是，`ORDER BY` 子句后边的列的顺序也必须按照索引列的顺序给出。

> 如果给出 `ORDER BY phone_number, birthday, name` 的顺序，是用不了 `B+` 树索引的

`ORDER BY name` 、`ORDER BY name, birthday` 这种匹配索引左边的列的形式可以使用部分的 `B+` 树索引。

当联合索引左边列的值为常量，也可以使用后边的列进行排序：

```mysql
SELECT * FROM person_info WHERE name = 'A' ORDER BY birthday, phone_number LIMIT 10;
```

> 因为 `name` 列的值相同的记录是按照 `birthday` , `phone_number` 排序的

##### 不可以使用索引进行排序的几种情况

###### ASC、DESC 混用

对于使用联合索引进行排序的场景，要求各个排序列的排序顺序是一致的，也就是要么各个列都是 `ASC` 规则排序，要么都是 `DESC` 规则排序。

```mysql
SELECT * FROM person_info ORDER BY name, birthday DESC LIMIT 10;
```

如果使用索引排序的话过程就是这样的：

- 先从索引的最左边确定 `name` 列最小的值，然后找到 `name` 列等于该值的所有记录，然后从 `name` 列等于该值的最右边的那条记录开始往左找10条记录
- 如果 `name` 列等于最小的值的记录不足10条，再继续往右找 `name` 值第二小的记录，重复上边那个过程，直到找到 10 条记录为止

这样不能高效使用索引，而要采取更复杂的算法去从索引中取数据，所以 MySQL 规定使用联合索引的各个排序列的排序顺序必须是一致的。

###### WHERE 子句中出现非排序使用到的索引列

```mysql
SELECT * FROM person_info WHERE country = 'China' ORDER BY name LIMIT 10;
```

这个查询只能先把符合搜索条件 `country = 'China'` 的记录提取出来后再进行排序，是使用不到索引。

###### 排序列包含非同一个索引的列

```mysql
SELECT * FROM person_info ORDER BY name, country LIMIT 10;
```

`name` 和 `country` 并不属于一个联合索引中的列，所以无法使用索引进行排序。

###### 排序列使用了复杂的表达式

要想使用索引进行排序操作，必须保证索引列是以单独列的形式出现，而不是修饰过的形式。

```mysql
SELECT * FROM person_info ORDER BY UPPER(name) LIMIT 10;
```

#### 用于分组

```mysql
SELECT name, birthday, phone_number, COUNT(*) FROM person_info GROUP BY name, birthday, phone_number;
```

使用 `B+` 树索引进行排序是一个道理，分组列的顺序也需要和索引列的顺序一致，也可以只使用索引列中左边的列进行分组。

### 回表的代价

```mysql
SELECT * FROM person_info WHERE name > 'Asa' AND name < 'Barlow';
```

在使用 `idx_name_birthday_phone_number` 索引进行查询时步骤大致如下：

1. 从联合索引的 `B+` 树中取出 `name` 值在 `Asa ~ Barlow` 之间的记录
2. 由于联合索引的对应的记录中只包含 `name`、`birthday`、`phone_number`、`id` 这 4 个字段，而查询列表是 `*`，也就是需要查询表中所有的字段。这时需要把从上一步中获取到的每一条记录的 `id` 字段都到聚簇索引对应的 `B+` 树中找到完整的用户记录，即`回表`，然后把完整的记录返回给用户

