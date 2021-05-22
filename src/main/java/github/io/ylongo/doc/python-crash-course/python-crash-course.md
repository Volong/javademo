> 来自：[Python编程 从入门到实践](https://www.amazon.cn/dp/B0719GSVJB)



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

`input()`：获取用户的文本输入

`int()`：获取数值输入

定义函数：

```python
def function_name():
  do something...
```

关键字实参：

```python
def function_name(a, b):
  do something...
  
#函数调用
function_name(b='bbb', a='aaa')
```

形参默认值：

```python
def function_name(a, b='ccc'):
  do something...
```

定义任意形参的函数：

```python
def function_name(*a):
  do something...
 
# 如果要让函数接受不同类型的实参，必须在函数定义中将接纳任意数量实参的形参放在最后。
# 先匹配位置实参和关键字实参，再将余下的实参都收集到最后一个形参中。
def function_name(a, *b):
  do something...
```

定义任意数量的关键字形参：

```python
def function_name(first, second, **a):
    print(first)
    print(second)
    for key, value in a.items():
        print(key)
        print(value)
```

创建模块：模块是扩展名为`.py`的文件，包含要导入到程序中的代码。

导入模块：

```python
import module_name
```

使用模块中的函数：

```python
module_name.function_name()
```

导入模块中特定的函数：

```python
from module_name import function_name
```

导入模块中任意数量的函数：

```python
# 使用逗号分割函数名
from module_name import function_name_0, function_name_1, function_name_2...
```

> 导入特定的函数之后，则可以直接通过函数名来进行调用

给函数指定别名：

```python
from module_name import function_name as another_name
```

给模块指定别名：

```python
import module_name as another_name
```

导入模块中的所有函数：

```python
from module_name import *
```

> 由于是导入了所有的函数，所以可以直接通过函数名来进行调用
>
> 但是会造成的问题是，如果有多个相同名称的函数，会进行函数覆盖

创建类：

```python
# 类型一般是首字母大写
class ClassName():
    # 相当于Java中的构造方法，并且self必须存在，且位于其它形参的前面
    def __init(self, first_argument, second_argument):
        self.first = first_argument
        self.second = second_argument
       
    def function_name():
        print()
```

创建实例：

```python
instance_name = ClassName('a', 'b')
print(instance_name.first) # 访问属性
print(instance_name.second)
print(instance_name.function_name()) # 访问方法
```

继承：创建子类时，父类必须包含在当前文件中，且位于子类前面。

```python
class ChileClassName(SuperClassNam):
    def __init__(self, first_argument, second_argument):
        super.__init__(first_argument, second_argument)
```

导入单个类：

```python
from module_name import ClassName
```

在一个模块中定义多个类：

```python
class ClassName_0():
	print("do something...")
  
class ClassName_1():
	print("do something...")
  
class ClassName_2():
	print("do something...")
```

从一个模块中导入多个类：

```python
from module_name from ClassName_0, ClassName_1
```

导入整个模块：

```python
import module_name
```

> 使用 `module_name.class_name` 访问需要的类

导入模块中的所有类：

```python
from module_name import *
```

> 不推荐使用，因为会导致名称冲突

从文件中读取数据：

```python
with open('file_path') as file_object:
  content = file_object.read()
  print(content)
```

从文件逐行读取数据：

```python
with open('file_path') as file_object:
  for line in file_object:
    print(line)
```

> 因为每行的末尾都有一个看不见的换行符，而print语句也会加上一个换行符，因此每行末尾都有两个换行符。可以使用`rstrip()`去掉多余的空白行

写入文件：

```python
with open('file_path', 'w') as file_object:
  file_object.write("some text")
```

> `r`：读取模式。默认是该模式
>
> `w`：写入模式，会先清除文件中的内容。如果文件不存在，则新建
>
> `a`：附加模式
>
> `r+`：读取与写入

处理异常：

```python
try:
  print('do something ...')
except ErrorName:
  print('do something ...') # 发生异常是才会执行
else:
  print('do something ...') # try里面的代码块执行成功了才会执行
finally:
  print('do something ...') # 无论如何都会执行
```

存储/读取数据：

```python
import json # 导入模块

data = [2, 3, 5, 7, 11, 13]

# 存储数据
with open('file_path', 'w') as file_object:
  json.dump(data, file_object)
  
# 读取数据
with open('file_path') as file_object:
  data = json.load(file_object)
```















