package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * second, minute, hour, day of month, month and day of week.
     * (0 * * * * MON-FRI)
     * , 代表枚举
     * - 代表区间
     * / 代表步长 每隔多少时间执行一次
     * ? 日/星期冲突匹配
     * L 最后
     * 注意：
     *  0,7都代表周日 1-6代表周一到周六
     * 【0 0/5 14,18 * * ?】每天14点整和18点整，每隔五分钟执行一次
     * 【0 15 10 ? * 1-6】每个月的周一至周六10：15分执行一次
     * 【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     */
//    @Scheduled(cron = "0 * * * * MON-SAT") 每个月周一到周六，每分钟执行一次
//    @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT") 每个月周一到周六每分钟的0，1，2，3，4 这几秒都执行
//    @Scheduled(cron = "0-4 * * * * MON-SAT")
    @Scheduled(cron = "0/4 * * * * MON-SAT") //从0秒启动，每隔4秒执行一次
    public void hello(){
        System.out.println("hello ... ");
    }
}
