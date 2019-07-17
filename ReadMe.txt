
reate database testDB default character set utf8;

mysql> create user test@'%' identified by '123456@';
Query OK, 0 rows affected (0.01 sec)

mysql> grant all privileges on testdb.* to test@'%' identified by '123456@';
Query OK, 0 rows affected, 1 warning (0.00 sec)


mysql> SELECT Host,User,plugin,authentication_string FROM mysql.user;
+-----------+-----------+-----------------------+-------------------------------------------+
| Host      | User      | plugin                | authentication_string                     |
+-----------+-----------+-----------------------+-------------------------------------------+
| localhost | root      | mysql_native_password | *4F4F4B73650585B1F34FCC38F7B0E2B682B244C3 |
| localhost | mysql.sys | mysql_native_password | *THISISNOTAVALIDPASSWORDTHATCANBEUSEDHERE |
| %         | hive      | mysql_native_password | *4DF1D66463C18D44E3B001A8FB1BBFBEA13E27FC |
| %         | test      | mysql_native_password | *CE0EA76FAD42864F235C20E38F26070AFF6BB5B9 |
+-----------+-----------+-----------------------+-------------------------------------------+
4 rows in set (0.00 sec)