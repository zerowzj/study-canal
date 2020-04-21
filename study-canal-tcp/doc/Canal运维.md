# 一. 安装

## 1.1 Canal Server

1. 解压
2. 目录

## 1.2

## 1.3



# 二. 配置



## 2.1 properties配置文件

​		properties配置分为两部分：

- canal.properties （系统根配置文件）
- instance.properties（instance级别的配置文件，每个instance一份）

## 2.1 canal配置

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

​		定义了canal.destinations后，需要在canal.conf.dir对应的目录下建立同名的文件。如果canal.properties未定义instance列表，但开启了canal.auto.scan时 server第一次启动时，会自动扫描conf目录下，将文件名做为instance name，启动对应的instanceserver运行过程中，会根据canal.auto.scan.interval定义的频率，进行扫描：

- 发现目录有新增，启动新的instance
- 发现目录有删除，关闭老的instance
- 发现对应目录的instance.properties有变化，重启instance



### 2.1.3 MQ

```properties
canal.mq.servers=127.0.0.1:6667
canal.mq.retries=0
```









## 2.2 instance配置

```properties
#
canal.instance.mysql.slaveId=

#数据库配置
canal.instance.master.address=
canal.instance.master.journal.name = 
canal.instance.master.position = 
canal.instance.master.timestamp =

#
canal.instance.dbUsername=root
canal.instance.dbPassword=root
canal.instance.defaultDatabaseName=
canal.instance.connectionCharset=UTF-8

#过滤配置
canal.instance.filter.regex=.*\\..*\

#MQ配置
canal.mq.topic=example
#针对库名或者表名发送动态topic
canal.mq.dynamicTopic=mytest,.*,mytest.user,mytest\\..*,.*\\..*
canal.mq.partition=0
#hash partition config
canal.mq.partitionsNum=3
#库名.表名: 唯一主键，多个表之间用逗号分隔
canal.mq.partitionHash=mytest.person:id,mytest.role:id
```

