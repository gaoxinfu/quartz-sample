# 主题：如何启动和如何停止调度器

## 调度器的启动

**举例**
```java
        System.out.println(currentClassName+",初始化Scheduler开始");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        System.out.println(currentClassName+",初始化Scheduler完成");

        //绑定job与trigger：设置job使用的trigger信息，即job调用的频率
        scheduler.scheduleJob(jobDetail,trigger);
        //启动schedule
        scheduler.start();
        System.out.println(currentClassName+",Started Scheduler ");
```
## 调度器的停止

**举例**

```java
        System.out.println(currentClassName+",停止schedule开始");
        scheduler.shutdown();
        System.out.println(currentClassName+",停止schedule完成");
```


