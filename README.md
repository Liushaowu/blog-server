# blog-server
博客系统

先创建一个数据库 db_blog

然后运行db文件夹内的sql脚本

每次在更改数据库字段时将sql脚本上传至github

开发时使用develop分支进行开发 
切换分支的教程链接 : https://www.cnblogs.com/milan-123/p/10301265.html

需要安装rabbitmq 教程链接:  https://www.cnblogs.com/saryli/p/9729591.html

需要安装redis 教程链接: https://blog.csdn.net/qq_29291085/article/details/77489342

修改redis的密码

在redis的根目录下用notepad++打开redis.windows.conf  找到 # requirepass foobared，去掉#号 修改foobared为 root 


redis自启动 

1、在redis的根目录下创建logs文件夹  

2、在redis的根目录下打开powerShell运行该命令 redis-server --service-install redis.windows-service.conf --loglevel verbose

3、右键我的电脑 -> 管理 -> 服务 ->找到redis 启动


                                      



