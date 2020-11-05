package com.xhw.dynamictiming.timing;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务注册类，用来增加、删除定时任务。
 */
@Component
public class CronTaskRegistrar  implements DisposableBean {
    private final Map<String, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    /**
     * 新增定时任务
     * @param task
     * @param cronExpression
     */
    public void addCronTask(String key,Runnable task, String cronExpression) {
        addCronTask(key,new CronTask(task, cronExpression));
    }

    public void addCronTask(String key,CronTask cronTask) {
        if (cronTask != null) {
            if (this.scheduledTasks.containsKey(key)) {
                removeCronTask(key);
            }
            this.scheduledTasks.put(key, scheduleCronTask(cronTask));
        }
    }
    /**
     * 移除定时任务
     * @param key
     */
    public void removeCronTask(String key) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(key);
        if (scheduledTask != null){
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }

        this.scheduledTasks.clear();
    }
}
