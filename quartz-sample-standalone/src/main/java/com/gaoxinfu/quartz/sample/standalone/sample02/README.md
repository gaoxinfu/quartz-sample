# 通过一个简单的使用样例，来认识一下 quartz 
## Job 【业务逻辑处理类】

```java
通过实现quartz模块中额job接口类，来自定义我们自己的业务逻辑处理
```

## JobDetail【job信息】

```sql
JobDetail对象里封装一些业务数据，比如定时任务执行的业务参数等等
```
**举例**
```java
JobDetail jobDetail = JobBuilder.newJob(FirstJob.class)
                .withIdentity("firstJob","my-group")
                .usingJobData("name","gaoxinfu")
                .usingJobData("sex","man")
                .build();
```

## Trigger 【触发器】

```sql
Trigger对象 主要是定义定时任务执行的时间频率问题，可以是某个时间，可以是每天，每小时，每周等等
都可以进行构建Trigger对象来定义我们的定时任务的调用频率
```

**举例**
```java
    Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("firstTrigger","my-group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();
```

## Scheduler 【调度器】

```java
SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
```
