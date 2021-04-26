package com.papaxiong.support.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhaoqi
 * @since 2021-03-30
 */
public class TestEventFactory implements EventFactory<TestEvent> {
    @Override
    public TestEvent newInstance() {
        return new TestEvent();
    }
}
