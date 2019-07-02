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

