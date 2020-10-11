# CORBA_helloworld
使用Java语言实现CORBA服务器端、客户端输出helloworld

需安装并配置：jdk、Apache Ant、jacORB、eclipse sdk
开发工具：eclipse

开发环境搭建好后先写idl文件，即HelloWorld.idl，接着在cmd命令行中定位到idl文件所在的目录，输入命令“idlj.exe -fall HelloWorld.idl”，则将自动生成sample文件夹，拷贝至src文件夹下。
再分别编写服务器端、客户端代码。
在cmd命令行窗口中输入命令“tnameserv -ORBInitialPort”且不关闭，在eclipse中先后运行HelloWorld_server和HelloWorld_client即可。
