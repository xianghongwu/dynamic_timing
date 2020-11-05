package com.xhw.dynamictiming.controller;

import com.xhw.dynamictiming.mapper.UserMapper;
import com.xhw.dynamictiming.model.SysJob;
import com.xhw.dynamictiming.timing.CronTaskRegistrar;
import com.xhw.dynamictiming.timing.SchedulingRunnable;
import com.xhw.dynamictiming.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping
@Validated
public class UserController {

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/job")
    public String addJob(SysJob sysJob){
        SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        String key = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        sysJob.setJobId(key);

        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(rule.getEndTime());
        calendar.add(Calendar.MINUTE, 2);//加10分钟后运行
        String dataStr = DateUtils.FORMAT_TIME_STANDARD.format(calendar.getTime());
        String[] arry = dataStr.split(":");
        String cronExpression = Integer.valueOf(arry[2]) + " " + Integer.valueOf(arry[1]) + " " + Integer.valueOf(arry[0]) + " * * ?";*/

        cronTaskRegistrar.addCronTask(key, task, sysJob.getCronExpression());
        sysJob.setCreateTime(DateUtils.getCurrentTime());
        sysJob.setUpdateTime(DateUtils.getCurrentTime());
        int i = userMapper.addSysJob(sysJob);
        return "添加成功"+i;
    }

    @DeleteMapping("/job")
    public String delJob(@NotNull String jobId){
        cronTaskRegistrar.removeCronTask(jobId);
        int i = userMapper.delSysJob(jobId);
        return "删除成功"+i;
    }

    /**
     * 修改任务状态
     * @param jobId
     * @param status 1开启   0暂停
     * @return
     */
    @PutMapping("/job")
    public String updateJob(@NotNull String jobId,@Max(1) @Min(0) Integer status){
        if(status==0){
            cronTaskRegistrar.removeCronTask(jobId);
        }else if(status==1){
            SysJob sysJob = userMapper.getSysJobById(jobId);
            SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            cronTaskRegistrar.addCronTask(jobId, task, sysJob.getCronExpression());
        }
        int i = userMapper.updateSysJob(jobId, status);
        return "修改成功"+i;
    }
}
