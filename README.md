
### mee-multi-api
```
  MEE api 作为一个纯后端api项目,该包含常用springboot组件,是一套适合生产使用的开发项目
  技术组件大致包含:
    - springboot2.3
    - postgresql
    - MySQL
    - oracle
    - mybatis3.5
    - 简单mybatis封装
    - 分布式序列生成器
    - 分布式事务管理
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
+ One method: ` java -jar oem-api.jar --server.port=8000 `
+ Two method: run [MeeApiApplication->main Method](#)

>>> test(linux) deploy (just example)
+ `echo 正在启动mee模块.....`
+ `ps -ef|grep mee-multi-api.jar|grep java|awk '{print $2}'|xargs kill -9`
+ `cd /mnt/app/8000-mee && nohup /usr/local/java/jdk1.8.0_261/bin/java -jar /mnt/app/8000-mee/mee-multi-api.jar --server.port=8000 --spring.profiles.active=test  1>/mnt/app/8000-mee/logs/mee_ALL.log 2>/mnt/app/8000-mee/logs/mee_ALL.log &`
