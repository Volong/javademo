> 来源：[Basic Linux Commands](https://www.reddit.com/r/linux/comments/b4khut/basic_linux_commands/)

**文件 & 浏览**

`ls` - 文件列表（列出当前文件夹中的所有文件 / 文件夹）

`ls -l` - 格式化列表

`ls -la` - 格式化列表，包括隐藏的文件

`cd dir` 将目录更改为 `dir`（`dir` 为目录的名字）

`cd ..` - 将目录更改为上级目录

`cd ../dir` - 将目录更改为上级目录中的 `dir`

`cd` - 将目录更改为根目录

`pwd` - 展示当前的目录

`mkdir dir` 创建一个的目录

`rm file` - 删除文件（`file` 为文件名）

`rm -f file` 强制删除文件

`rm -r dir` - 删除目录

`rm -rf dir` - 强制删除目录

`rm -rf /` - 系统原地爆照

`cp file1 file2` - 将文件 1 复制到文件 2

`mv file1 file2` - 将文件 1 更名为文件 2

`mv file1 dir/file2` 将文件 1 移动到指定文件夹，并改名为文件 2

`touch file` 创建或更新文件

`cat file` - 输出文件中的内容

`cat > file` - 输入内容到文件，会覆盖原有内容（按 `Ctrl + D` 结束输入）

`cat >> file` - 追加内容到文件

`tail -f file` - 实时输出文件的内容

**网络**

`ping host` - ping 指定主机

`whois domain` - 查看指定域名的信息

`dig domain` - 获取指定域名的 DNS

`dig -x host` - 获取指定主机的信息

`wget file` - 下载文件

`wget -c file` - 继续之前暂停的下载

`wget -r url` - 递归下载 url 中的所有文件

`curl url` - 输出 url 的网页信息

`curl -o meh.html url` - 将 url 的网页信息写入到 meh.html 中

`ssh user@host` - 使用 user 连接到 host

`ssh -p port user@host` - 通过指定端口连接

`ssh -D user@host` 使用绑定的端口进行连接

**进程**

`ps` - 显示当前活跃的进程

`ps aux` - 详细的输出

`kill pid` - 通过进程 id 杀掉进程

`killall proc` - 杀掉所有名为 proc 的进程

**系统信息**

`date` - 输出当前时间

`uptime` - 当前系统运行时间

`whoami` - 查看当前登录的用户名

`w` - 查看当前谁在线

`cat /proc/cpuinfo` - 查看 cpu 信息

`cat /proc/meminfo` - 查看内存信息

`free` - 查看内存与 `swap` 区的使用情况

`du` - 查看目录的使用情况

`du -sh` - 以 `GB` 形式展示可读大小

`df` - 查看磁盘使用情况

`uname -a` - 显示内核的配置信息

**压缩**

`tar cf file.tar files` - 将所有文件打包到 file.tar 中

`tar xf file.tar` - 将 file.tar 解压到当前文件夹中

`tar tf file.tar` - 查看归档中的内容

选项：

`c` - 创建归档                                           `j` - bzip2 压缩

`t` - 目录                                                  `w` - 询问确认信息

`x` - 抽取                                                  `k` - 不要重写

`z` - 使用 zip/gzip                                   `T` - 指定需要打包的文件

`f` - 指定文件名                                      `v` - 详细输出

**权限**

`4` - 可读（`r`）

`2` - 可写（`w`）

`1` - 可执行（`x`）

顺序：所有者 / 组 / 其他人

`chmod 777` 任何人都可读、可写、可执行

`chmod 755` 所有者可读可写可执行，组内用户可读可执行，其它用户可读可执行

> 7 = 4 + 2 + 1，5 = 4 + 1

**其它**

`grep pattern files` - 在文件中通过指定模式进行搜索

`grep -r pattern dir` - 在目录中通过指定模式进行递归搜索

`locate file` - 找出所有名字为 file 的文件

`whereis app` 查看 app 所有可能的位置

`man command` - 查看指定命令的帮助手册