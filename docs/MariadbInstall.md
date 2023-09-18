> 仅供参考，因为我比较折腾，windows, 树莓派raspberry, macOS, debian, CentOS7.6, archlinux,
这几个系统都折腾过，安装流程有些混乱。[捂脸]

# 安装

`sudo yum install mariadb-bench mariadb-embedded mariadb-libs mariadb-server mariadb`

# 初始化配置

```sh
sudo mysql_secure_installation
sudo mariadb-secure-installation # 无需mysql -u root -p

enter current password for root(enter for none) # 此时没有密码，按回车进行下一步

Enable unix_socket authentication? [Y/n] # n 设置根密码或使用unix_socket可确保任何人在没有适当授权的情况下都无法登录MariaDB根用户

set root password      # Y
new password:          # *******
re-enter new password: # ****

remove anonymous users? # Y 删除匿名用户

Disallow root login remotely? # Y 禁止root用户远程登录

remove test database and access to it? # Y 删除测试数据库

reload privilege tables now? # Y 重新装载授权表
```

# 启动与停止

```sh
mysql --version
systemctl status mariadb.service
systemctl start mariadb.service
systemctl stop mariadb.service
```

# 常见问题

## ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/var/lib/mysql/mysql.sock' (2)

- 安装包下载不完整
- mysql server 未启动

## mysql.user plug 为 mysql_native_password 时空密码仍然可以登录
 
- [Debian](https://blog.csdn.net/yimaoya/article/details/125353223)

```sh
select * from mysql.global_priv \G
ALTER USER root@localhost IDENTIFIED VIA mysql_native_password USING PASSWORD("root password");
flush privileges;
```
