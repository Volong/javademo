12

依赖声明三要素：

`groupId`、`artifactId`、`version`

maven有三种类型的classpath：

- 编译
- 测试
- 运行

依赖范围：

`<scope>`

- `compile`：默认。在编译、测试、运行时都有效

- `test`：仅在测试时有效

- `provided`：在编译、测试时有效

  > 例如：`servlet-api`，一般容器会提供这个依赖

- `runtime`：在测试、运行时有效

  > 例如：`mysql-connector-java`。在运行时进行数据库的连接就可以了，写代码基本上是基于`javax.sql`包下的标准接口

  依赖调解

  - `就近原则`：选择依赖路径最短的
  - `第一声明原则`：谁先声明用谁

  可选依赖

  `<optional>`

  依赖传递失效，不会向上传递。如果项目中需要使用这种依赖，需要自己显式声明。

  

  ---

  

  13

  查看依赖树：`mvn dependency:tree`

  排除依赖：

  ```xml
  <exclusions>
    <exclusion>
    	<groupId></groupId>
      <artifactId></artifactId>
    </exclusion>
  </exclusions>
  ```

  

  

  

  

  

