package com.xhw.dynamictiming.timing;


import com.xhw.dynamictiming.mapper.UserMapper;
import com.xhw.dynamictiming.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**系统启动运行task
 * @author wgq
 * @since 2020-07-17 09:31:21
 */

@Component
public class TaskApplicationRunner implements ApplicationRunner {

    @Autowired
    UserMapper userMapper;
    @Autowired
    CronTaskRegistrar cronTaskRegistrar;
    /**
     * 启动添加数据库定时任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SysJob> sysJobAll = userMapper.getSysJobAll();
        for (SysJob sysJob : sysJobAll) {
            if(sysJob.getJobStatus()==1){
                //添加到任务中
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(sysJob.getJobId(), task, sysJob.getCronExpression());
            }
        }
        System.out.println("---------------------------启动运行,添加数据库中的任务");
    }
}
