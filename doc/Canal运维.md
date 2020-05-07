# 一. 安装

## 1.1 Canal Server

1. 解压
2. 目录

## 1.2

## 1.3



# 二. 配置

​		properties配置分为两部分：

- canal.properties （系统根配置文件）
- instance.properties（instance级别的配置文件，每个instance一份）

## 2.1 canal

### 2.1.1 基本

```properties
#可选项: tcp(默认), kafka, rocketmq
canal.serverMode=kafka

```

### 2.1.2 des

```properties
canal.destinations=example
# conf root dir
canal.conf.dir=../conf
# auto scan instance dir add/remove and start/stop instance
canal.auto.scan=true
canal.auto.scan.interval=5
```

​		定义了canal.destinations后，需要在canal.conf.dir对应的目录下建立同名的文件。如果canal.properties未定义instance列表，但开启了canal.auto.scan时 server第一次启动时，会自动扫描conf目录下，将文件名做为instance name，启动对应的instance server运行过程中，会根据canal.auto.scan.interval定义的频率，进行扫描：

- 发现目录有新增，启动新的instance
- 发现目录有删除，关闭老的instance
- 发现对应目录的instance.properties有变化，重启instance



### 2.1.3 MQ

```properties
canal.mq.servers=127.0.0.1:6667
canal.mq.retries=0
```









## 2.2 实例配置（instance.properties）

### 2.2.1 基本

```properties
#
canal.instance.mysql.slaveId=1234
#
canal.instance.gtidon=false
```



### 2.2.2 数据库

```properties
#地址
canal.instance.master.address=127.0.0.1:3306
canal.instance.master.journal.name= mysql-bin.000001
canal.instance.master.position= 
canal.instance.master.timestamp=
#
canal.instance.master.gtid=

#canal.instance.standby.address =
#canal.instance.standby.journal.name =
#canal.instance.standby.position =
#canal.instance.standby.timestamp =
#canal.instance.standby.gtid=

#用户名密码
canal.instance.dbUsername=root
canal.instance.dbPassword=root
#连接字符编码
canal.instance.connectionCharset=UTF-8
#默认数据库名称
canal.instance.defaultDatabaseName=ds_ms
canal.instance.enableDruid=false
```



### 2.2.3 过滤

```properties
canal.instance.filter.regex=.*\\..*\ 
canal.instance.filter.field=
#黑名单
canal.instance.filter.black.regex=
canal.instance.filter.black.field=
```

mysql 数据解析关注的表，Perl正则表达式.多个正则之间以逗号(,)分隔，转义符需要双斜杠(\\) 

-  所有表：.* or .*\\..*
-  canal schema下所有表： canal\\..*
-  canal下的以canal打头的表：canal\\.canal.*
- canal schema下的一张表：canal.test1
  多个规则组合使用：canal\\..*,mysql.test1,mysql.test2 (逗号分隔)
  注意：此过滤条件只针对row模式的数据有效(ps. mixed/statement因为不解析sql，所以无法准确提取tableName进行过滤)

### 2.2.4 MQ

```properties
#
canal.mq.topic=example
#针对库名或者表名发送动态topic
#canal.mq.dynamicTopic=mytest,.*,mytest.user,mytest\\..*,.*\\..*
canal.mq.partition=0
#hash partition config
canal.mq.partitionsNum=3
#库名.表名: 唯一主键，多个表之间用逗号分隔
canal.mq.partitionHash=mytest.person:id,mytest.role:id
```



