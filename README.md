# 故事

- [产品故事，只当是一些闲言碎语吧](./docs/story.md)
- [驾驶预约产品文档](./docs/驾驶预约产品文档.md)
- [驾驶预约产品设计](./docs/驾驶预约产品设计.md)

# 技术选型

- gradle-7.5.1-bin
- [spring mvc 6.0.9](https://spring.io/projects/spring-framework#learn)
- [rabbitmq 3.11.15](https://github.com/rabbitmq/rabbitmq-server/tags)
- redis 只是集成，实际未用到，可在 app 模块的 TestRedisController.java 查看 demo
- nginx
- [shiro](https://shiro.apache.org/reference.html)
- [jwt](https://github.com/auth0/java-jwt)
- [MariaDB 10.11.2](https://mariadb.com/)
- [Tomcat 11.0.0-M3](https://archive.apache.org/dist/tomcat/tomcat-11/v11.0.0-M3/bin/apache-tomcat-11.0.0-M3.zip)
- [jdk 18.0.2.1](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

# 项目启动

> 此项目是基于 java config 方式的 spring mvc，非 xml config 方式

**后端**

初始化 MariaDB 数据库

- [安装 MariaDB，此处仅提供 CentOS7.6 安装方式](./docs/MariadbInstall.md)
- 创建 sql 库，打开 `docs/sql/drive.sql` 文件，复制第一行建库语句，粘贴 MariaDB 并执行该语句
- 选择刚创建的库 `use drive`
- 创建 sql 表，打开 `docs/sql/drive.sql` 和 `docs/sql/admin.sql` 文件，复制建表语句，粘贴并执行
- 初始化表数据，打开 `docs/sql/drivetemp.sql` 文件，复制，粘贴，执行

初始化 Rabbitmq 消息队列

- [安装 Rabbitmq，此处仅提供 CentOS7.6 安装方式](./docs/RabbitmqInstall.md)

启动服务

- intellij idea 导入 drive-ssm-gradle，等待依赖加载，gradle 加载可能有点慢。
- 加载完后注意查看 file -> project structure 里的各项配置，尤其是 artifacts。
- 修改项目配置文件 `drive-ssm-gradle/sys-core/src/main/resources/application-core.yml`
- 选择 tomcat 启动，端口 8086

**前端 通用管理后台**

- node v16.20.2
- vue 3.3.4
- element-plus 2.3.6

```sh
cd eleadmin-vueiii
npm install
npm run dev
```

**小程序 用户端**

- [下载微信小程序开发者工具](https://developers.weixin.qq.com/miniprogram/dev/devtools/stable.html)
- 更换自己的 appID
- 导入到微信开发者工具里

# 其它说明

1. 只所以将 shiro 直接放入代码里，而不是放入 gradle dependencies，  
是因为 Tomcat 11 使用的是 jakarta.servlet 包，当时 shiro-web 还没有适配，  
不知道现在有没有适配，好累，不想去验证了，  
另一方面，想仿写一个，了解其具体原理。

2. [当部署项目完成时，会发现上传图片后无显示？使上传的文件，其他用户拥有可读权限](https://www.cnblogs.com/leepandar/p/16741380.html)

```
vim /tomcat/bin/catalina.sh

搜索 UMASK 将 0027 改为 0022
```

# 产品体验

![guide01](./docs/images/guide01.PNG)
![guide02](./docs/images/guide02.PNG)


