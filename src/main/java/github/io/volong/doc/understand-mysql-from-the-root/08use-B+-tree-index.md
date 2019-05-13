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

> `WHERE` 子句中搜索条件的顺序对查询的执行过程没有影响

