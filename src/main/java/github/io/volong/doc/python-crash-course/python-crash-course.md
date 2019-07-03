> 以`.`开头的方法表示被参数调用，否则就是将参数传入

.title()`：以首字母大写的方式显示每个单词，即将每个单词的首字母都改为大写。

`.upper()`：将字符串变为大写

`.lower()`：将字符串变为小写

`.rstrip()`：删除字符串末尾的空白

`.lstrip()`：删除字符串开头的空白

`.strip()`：删除字符串两端的空白

使用两个`**`表示乘方运算

> 3 ** 2 = 9

`str()`：将非字符表示为字符串

使用`[]`表示列表，并用逗号来分隔其中的元素

> bicycles = ['trek', 'cannondale', 'redline', 'specialized']

访问列表中元素的方法：`bicycles [3]`

修改列表中的元素：`bicycles[2] = 'honda'`

在列表的末尾添加元素：`bicycles.append('ducati')`

在列表中插入元素：`bicycles.insert(2, 'yamaha')`

从列表中删除元素：`del bicycles[2]`

从列表中删除并返回删除的元素：

> 删除末尾的元素：`bicycle = bicycle.pop()`
>
> 删除任意位置的元素：`bicycle = bicycle.pop(3)`

根据值删除列表中的元素：`bicycles.remove('ducati')`

> 如果列表中有多个相同的值，`remove()`只能删除第一个出现的值

对列表进行排序：`bicycles.sort()`

> 永久性排序：`.sort()`按照自然顺序、`.sort(reverse=True)`逆序
>
> 临时性排序：`sorted()`、`sorted(reverse=True)`

反转列表元素：`reverse()`

> 会永久性的修改原列表

获取列表的长度：`len()`

遍历列表：`for bicycle in bicycles:`

`range()`：生成一组数字

> `list(range(1, 5))`：[1, 2, 3, 4]
>
> `list(range(1, 5, 2))`：[1, 3]

`min()`：找出数字列表中的最大值

`max()`：找出数字列表中的最小值

`sum()`：计算数字列表的总和

列表解析：

> ```python
> squares = [value**2 for value in range(1, 3)]
> print(squares) # [1, 4]
> ```

切片：

```python
players = ['charles', 'martina', 'michael', 'florence', 'eli']
# 含头不含尾
print(players[0:3]) # ['charles', 'martina', 'michael'] 
# 没有指定起始索引，从列表开头开始
print(players[:3]) # ['charles', 'martina', 'michael'] 
# 没有指定终止索引，从列表末尾终止
print(players[2:]) # ['michael', 'florence', 'eli']
# 复制列表
copy_players = players[:]
```

使用`()`表示元祖，使用逗号分隔。定义后就不能改变，被称为不可变的列表，所以操作与列表相似。

使用`and`检查多个条件

```python
age > 20 and age < 30
```

使用`or`检查多个条件

```python
age > 20 or age < 30
```

判断特定的值是否在列表中：`in`

判断特定的值是否不在列表中：`not in`

`if-elif-else`结构：

```python
if age < 4:
  print(age)
elif age < 10:
  print(age)
else:
  print(age)
```

判断列表是否为空：

```python
if players:
  print('players is not empty')
```

使用`{}`表示字典。字典表示一系列的键值对。

```python
alien = {'color': 'green', 'points': 5}
```

访问字典中的值：

```python
alien('color')
```

在字典中添加值：

```python
alien['position'] = 25
```

修改字典中的值：

```python
alien['color'] = 'yellow'
```

删除字典中的值：

```python
del alien['points']
```

遍历字典中的值：

```python
for key, value in alien.items():
  print(key)
  print(value)
```

遍历字典中的键：

```python
for key in alien.keys():
  print(key)
# 按照顺序输出key
for key in sorted(alien.keys()):
  print(key)
```

遍历字典中的值：

```python
for value in alien.values():
  print(value)
# 通过对包含重复元素的列表调用set()，可以找出列表中独一无二的元素，并使用这些元素来创建一个集合。
for value in set(alien.values()):
  print(value)
```









