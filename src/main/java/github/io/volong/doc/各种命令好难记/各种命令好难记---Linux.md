
1.实时查看日志信息
​	tail -f log_name.log

2.查看前一百行的日志信息
​	tail -n 100 log_name.log

3.清空日志信息
​	echo > log_name.log

4.打开日志文件
​	cat log_name.log

5.创建.sh文件
​	-新建 touch test.sh
  	-编辑 vi test.sh
  	-保存并退出	:wq 强制性写入文件并退出。即使文件没有被修改也强制写入，并更新文件的修改时间(可执行文件将会被重新编译)
  				:x 	写入文件并退出。仅当文件被修改时才写入，并更新文件修改时间，否则不会更新文件修改时间
  	-给执行权限 chmod +x test.sh	
  	-执行 ./test.sh

6.创建目录
​	mkdir test

7.移动文件
​	sudo mv 源文件路径 目标文件夹

8.删除文件
​	-删除文件 rm -f 文件名 
​			  将会强行删除文件，且无提示
​	-删除文件夹以及文件夹中的所有文件 rm -rfiv 目录名字
​			  -r:向下递归删除,不带这个参数不能删除目录
​			  -f:直接强行删除,且没有任何提示
​			  -i:确认是否删除,y——是,n——否
​			  -v:显示结果信息

9.获取root权限
​	su root

10.configure --prefix=/
​	指定安装路径
​	不指定prefix，则可执行文件默认放在/usr/local/bin
​				    库文件默认放在/usr/local/lib
​				    配置文件默认放在/usr/local/etc
​				    其它的资源文件放在/usr/local/share。
​		当卸载这个程序
​			要么在原来的make目录下用一次make uninstall(前提是make文件指定过uninstall)要么去上述目录里面把相关的文件一个个手工删掉。
​	指定prefix，直接删掉一个文件夹就够了

11.关闭linux防火墙
​	service iptables stop
​	chkconfig iptables off

12.启动ssh服务
​	service sshd start

13.下载文件
​	wget 下载地址

14.解压zip
​	unzip 文件名 


15.修改文件夹的名字
​	mv 旧的名字 新的名字

16.pm -ql XXX 就可以看到XXX在系统的哪些位置放了文件（不要带.rpm的扩展名）。
​	针对RPM包
​	一般情况下
​		命令放在/usr/bin或/usr/sbin下
​		库在/usr/lib下
​		数据文件在/usr/share下


​	
​	rpm -pql [rpm文件名]，来查看一个rpm包里有哪些文件，即安装的路径


17.source /etc/profile
   使环境变量生效

18.-bash: make: command not found的解决办法
​	一般出现这个-bash: make: command not found提示，是因为安装系统的时候使用的是最小化mini安装，系统没有安装make、vim等常用命令，直接yum安装下即可。
​	yum -y install gcc automake autoconf libtool make

19.解压缩命令安装
​	yum install -y unzip zip

20.解压并指定目录
​	举例：unzip /home/kms/kms.zip -d /home/kms/server/kms
​			  tar zxvf xxx.tar.gz -C /usr/local/xxxx
​		  -z：有gzip属性的
​		  -x：解压
​		  -v：显示所有过程
​		  -f: (必须)。使用档案名字，切记，这个参数是最后一个参数，后面只能接档案名。


21.将test01复制到test2文件夹下
​	cp /TEST/test1/test01  /TEST/test2

   将test1文件夹复制到test2文件夹下
   	cp -r  /TEST/test1 /TEST/test2

   把test1中的文件夹及文件复制到test2中
   	cp -r /TEST/test1/. /TEST/test2
   	或者
   	cp -r /TEST/test1/* /TEST/test2

   	当有很多提示「是否覆盖」时，可以在 cp 前加上 \ 
   	
22.rm -rf *.*
​	在某个文件夹下面执行,表示删除该文件夹中的所有内容

23.rpm -ivh jdk-8u91-Linux-x64.rpm 

23.rpm安装到指定文件夹
​	rpm -ivh --prefix=路径 xxx.rpm

24.执行一个文件夹内所有的文件:
```bash
for i in `ls /usr/local/solrconfig`;do /usr/local/solr-6.3.0/bin/solr create -c $i -s 3 -rf 3 -d /usr/local/solrconfig/$i -force;done
```



   执行多个文件：
```bash
for i in 'file1 file2 file3';do /usr/local/solr-6.3.0/bin/solr create -c $i -s 3 -rf 3 -d /usr/local/solrconfig/$i -force;done
```

25.安装rz、sz命令
​	yum install lrzsz

26.启动mysql
​	service msyql start

27.查看mysql下的所有用户
​	select user from mysql.user

28.重启nginx
​	/usr/nginx/sbin/nginx -s reload  

29.查看nginx是否正常
​	ps -ef | grep nginx

30.sz/rz命令安装包
​	yum install -y lrzsz

31.查看磁盘的空间信息
​	df -h

   查看目录的大小   
   du -sh dir

32. node 后台启动
	nohup node app.js &

	杀掉 node 进程
	pkill node

33. 赋予文件可执行权限
	chmod +x startup.sh

34. 查看某个端口号的占用情况
	netstat -apn | grep 8080

35. 查看字体
	fc-list

	查看中文字体
	fc-list :lang=zh

36. 查看版本号
	cat /etc/issue
	
37. 杀掉 JAVA 进程
	pkill -9 java

38. JavaMelody 图片显示中文乱码
	yum groupinstall chinese-support

	fc-cache -fv 

	关掉 tomcat 再启动

39. sftp 连接远程服务器
    sftp -o port=port user@ip

40. 将 xxx.sql.gz 文件导入数据库
    gunzip -f < xxx.sql.gz | mysql -u usename -ppassword databaseName

41. rar 压缩包
    wget http://www.rarlab.com/rar/rarlinux-x64-5.3.0.tar.gz
    tar -xzpvf rarlinux-x64-5.3.0.tar.gz
    cd rar
    make
    unrar x  filename.rar  指定目录名


42. du - Disk Usage
    计算每个文件的大小然后累加

43. df - Disk Free
    通过文件系统来获取文件的大小。
    具体参考: https://mp.weixin.qq.com/s/oCK0chpOmJcCnPdMhcketw