
### mee-multi-api
```
  MEE-multi-api 作为一个纯后端api项目,与 mee-api 不用的是它包含多个数据源
  技术组件大致包含:
    - springboot2.3
    - postgresql
    - MySQL5.7
    - oracle11
    - mybatis3.5
    - 简单mybatis封装
    - 分布式序列生成器
    - 分布式事务管理
    - 分页插件
```

#### How to visit
+ local `http://127.0.0.1/mee-multi-api/echo?reqData=?`
+ test `--NONE--`

#### how to start in IDE with environment cfg
+ --spring.profiles.active=dev

#### How to packaging
+ development environment
    - `mvn clean -Dmaven.test.skip=true package -Pdev`
    
+ test environment
    - `mvn clean -Dmaven.test.skip=true package -Ptest`

#### how to deploy
>>> local(windows) deploy
+ One method: ` java -jar mee-multi-api.jar --server.port=8000 `
+ Two method: run [MeeApiApplication->main Method](#)

>>> test(linux) deploy (just example)
+ `echo 正在启动mee模块.....`
+ `ps -ef|grep mee-multi-api.jar|grep java|awk '{print $2}'|xargs kill -9`
+ `cd /mnt/app/8001-mee && nohup /usr/local/java/jdk1.8.0_261/bin/java -jar /mnt/app/8001-mee-multi-api/mee-multi-api.jar --server.port=8000 --spring.profiles.active=test  1>/mnt/app/8001-mee-multi-api/logs/mee_ALL.log 2>/mnt/app/8001-mee-multi-api/logs/mee_ALL.log &`
