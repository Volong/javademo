> 来源：[Pro Git book](https://git-scm.com/book/zh/v2)

## Git 基础

### 三种状态

**已提交 (committed)**：数据已经安全保存在本地数据库中。

**已修改 (modified)**：已经修改了文件，但是还没有保存到数据库中。

**已暂存 (staged)**：对一个已修改文件的当前版本做了标记，让它可以包含在下次提交的快照中。

Git 三个工作区域的概念：

**Git 仓库**：用来保存项目的元数据和对象数据库的地方。

**工作目录**：对项目的某个版本独立提出出来的内容。

**暂存区域**：一个文件，保存了下次将提交到文件列表信息，一般在 Git 仓库目录中。

## 初次运行 Git 前的配置

使用 `git config` 来对 Git 的配置变量进行设置。这些变量分别存储在三个地方：

1. `/etc/gitconfig` 文件：包含系统上每一个用户以及他们仓库的通用配置。使用 `--system` 选项时会从这里读取配置。
2. `~/.gitconfig` 或 `~/.config/git/config` 文件：只针对当前用户。使用 `--global` 选项时会从这里读取配置。
3. 当前仓库中 `.git/config` 文件：针对该仓库。

每一个级别覆盖上一个级别的配置。所以，`.git/config` 的配置变量会覆盖 `/etc/gitconfig` 中的配置变量。

在 Windows 系统中，Git 会查找 \$HOME 目录 (`C:\Users\$USER`) 中的 `.gitconfig` 文件。

### 用户信息

设置用户名称与邮箱。Git 的每一次提交都会使用这些信息，并且会将这些信息写入到每次的提交中，不可更改。

```git
git config --global user.name "Volong"
git config --global user.email "Volong@example.com"
```

如果使用了 `--global` 信息，那么只需要运行一次，之后 Git 都会使用这些信息。但是如果想要针对不同使用不同的用户名与邮箱时，可以在项目的目录下执行没有 `--global` 选项的命令来进行设置。

# Git 基础

## 获取 Git 仓库

获取 Git 仓库有两种方法：

1. 在现有项目或目录下导入所有文件到 Git 中
2. 从服务器上克隆一个现有的 Git 仓库

### 在现有目录中初始化仓库

在该目录下输入命令：

```git
git init
```

该命令将创建一个名为 `.git` 的子目录。这个子目录包含初始化 Git 仓库中所有必须的文件。

### 克隆现有的仓库

使用如下命令进行克隆：

```git
git clone [url]
```

例：

```bash
git clone https://github.com/libgit2/libgit2
```

该命令会在当前目录下创建一个名为 "libgit2" 的文件夹。

```bash
git clone https://github.com/libgit2/libgit2 mylibgit
```

该命令会在当前目录下创建一个名为 "mylibgit" 的文件夹。

### 状态简览

使用 `git status -s` 可以看到更加简单的状态输出信息。

> `-s` 为 `--short` 的缩写

```bash
$ git status -s
M README
MM Rakefile
A lib/git.rb
M lib/simplegit.rb
?? LICENSE.txt
```

新添加的未跟踪文件前面有 `??` 标记。
新添加到暂存区的文件前面有 `A` 标记。
修改过的文件前面有 `M` 标记。
有两个 `MM` 的地方，右边的 `M` 表示该文件被修改了但是还没有放入暂存区，左边的 `M` 表示该文件被修改了并放入了暂存区。

> 虽然在工作区被修改后提交到了暂存区，但是之后又在工作区被修改了并且还没有被提交到暂存区

### 忽略文件

