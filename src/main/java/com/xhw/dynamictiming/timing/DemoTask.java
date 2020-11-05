package com.xhw.dynamictiming.timing;

import org.springframework.stereotype.Component;

/**
 * 定时任务示例类
 */
@Component("demoTask")
public class DemoTask {

    public void taskWithParams(String params) {
        System.out.println("执行有参示例任务：" + params);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }

}