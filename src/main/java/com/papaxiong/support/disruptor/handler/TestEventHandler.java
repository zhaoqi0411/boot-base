package com.papaxiong.support.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.papaxiong.support.disruptor.event.TestEvent;

/**
 * @author zhaoqi
 * @since 2021-03-30
 */
public class TestEventHandler implements EventHandler<TestEvent> {
    @Override
    public void onEvent(TestEvent testEvent, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getId() + "消费" + testEvent.getValue());
    }
}
