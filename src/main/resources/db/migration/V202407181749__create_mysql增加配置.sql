在MySQL配置文件中my.conf或my.ini中添加:

[mysqld]
group_concat_max_len=102400


然后重启mysql服务