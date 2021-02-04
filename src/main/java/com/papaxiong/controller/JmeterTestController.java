package com.papaxiong.controller;

import com.papaxiong.mapper.SysUserMapper;
import com.papaxiong.model.po.SysUserDO;
import com.papaxiong.service.SysUserService;
import com.papaxiong.support.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaoqi
 * @since 2021-02-04
 */
@Slf4j
@RestController
public class JmeterTestController {

   private  CyclicBarrier barrier;

   private CountDownLatch cl ;

   private Integer requestSize= 1000 ;

   private ExecutorService executorService;

   private AtomicInteger failedCount=new AtomicInteger(0);



    @PostConstruct
    public void initBarrier() {

        System.out.println("cores="+Runtime.getRuntime().availableProcessors());
        barrier = new CyclicBarrier(requestSize+1);   //有一个守护线程
        cl=new CountDownLatch(requestSize);
        executorService= Executors.newFixedThreadPool(32);

        //起一个守护线程 用来检测处理性能

        new Thread(() -> {

            try {
                barrier.await();


                //数据库连接池为10个核心线程 最大也是10个

                //开始等待计时器完成

                long totalStart = System.currentTimeMillis();
                log.info("@@@@@@@@@所有线程就绪,开始计算,million={}",totalStart);

                cl.await();
                log.info("@@@@@@@@@所有线程完成计算,cost={}",System.currentTimeMillis()-totalStart);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }).start();
    }



    @Autowired
    private SysUserService sysUserService;



    AtomicInteger aCount = new AtomicInteger(1000);
    @RequestMapping("/test/threadPool")
    public Wrapper<String> testThreadPool() throws BrokenBarrierException, InterruptedException {


        System.out.println("@剩余"+aCount.decrementAndGet());
        barrier.await();


        //数据库连接池为10个核心线程 最大也是10个


        executorService.submit(() -> {
            try {
                long tStart = System.currentTimeMillis();


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long ioStart = System.currentTimeMillis();
                log.info("@@@@@@@@@start,time={}",ioStart);

                List<SysUserDO> list = sysUserService.list();
                log.info("@@@@@@@@@线程计算IO,计算完成,cost={}",System.currentTimeMillis()-ioStart);

                log.info("@@@@@@@@@线程执行任务,计算完成,cost={}",System.currentTimeMillis()-tStart);
            } catch (Exception e) {
                e.printStackTrace();
                failedCount.incrementAndGet();
            }finally {
                cl.countDown();
            }
        });

        return Wrapper.ok("ok");

    }
}
