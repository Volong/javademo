>   以下都是在 windows 下验证通过

在任意位置新建一个 `test` 文件夹

1.  在 `test` 文件夹内新建一个 `App.java`，如下：  

    ```java
    public class App {
        public static void main(String[] args) {
            System.out.println("hello world");
        }
    }
    ```

    在命令行运行如下命令：  

    `javac App.java`  

    `java App`

2.  更改 `App.java` 的内容为：

    ```java
    import com.alibaba.fastjson.JSONObject;
    
    public class App {
        public static void main(String[] args) {
            Object parse = JSONObject.parse("");
            System.out.println("parse:" + parse);
            System.out.println("hello world");
        }
    }
    ```

    由于用到了 `fastjson`，需要将 `fastjson.jar` 放到 `test` 文件夹中

    在命令行运行如下命令：

    `javac -cp ".;fastjson-1.2.51.jar" App.java`

    `java -cp ".;fastjson-1.2.51.jar" App"`

3.  在 `test` 文件夹内新建一个 `foo` 的文件夹，在 `foo` 里面新建一个 `Foo.java`，内容如下：  

    ```java
    package foo;
    
    public class Foo {
        public static void main(String[] args) {
            System.out.println("hello world");
        }
    }
    
    ```

    -   可以直接在 `foo` 文件夹下执行 `javac Foo.java`
        也可以在 `test` 文件夹下执行 `javac foo/Foo.java`

    -   不管是在哪个文件下执行的 `javac` 命令，都必须在 `test` 文件夹下执行 `java` 命令，如下：

        `java foo/Foo`

        因为 `Foo.java` 在 `foo` 包内。

4.  更改 `Foo.java` 的内容为：

    ```java
    package foo;
    import com.alibaba.fastjson.JSONObject;
    
    public class Foo {
        public static void main(String[] args) {
            Object parse = JSONObject.parse("");
            System.out.println("parse:" + parse);
            System.out.println("hello world");
        }
    }
    ```

    由于用到了 `fastjson`，需要将 `fastjson.jar` 放到 `foo` 文件夹中

    -   可以直接在 `foo` 文件夹下执行 `javac -cp ".;fastjson-1.2.51.jar" App.java`

        也可以在 `test` 文件夹下执行 `javac -cp ".;fastjson-1.2.51.jar" foo/Foo.java`

    -   不管是在哪个文件夹下执行的 `javac`，都必须在 `test` 文件夹下执行 `java` 命令，如下：

        `java -cp ".;fastjson-1.2.51.jar" foo/Foo`

>   `jar` 包的导入，可以使用相对路径或绝对路径，我这里使用的都是相对路径。