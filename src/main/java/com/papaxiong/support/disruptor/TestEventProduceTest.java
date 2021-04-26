package com.papaxiong.support.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.papaxiong.support.disruptor.event.TestEvent;
import com.papaxiong.support.disruptor.event.TestEventFactory;
import com.papaxiong.support.disruptor.handler.TestEventHandler;
import com.papaxiong.support.disruptor.producer.TestEventProducer;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zhaoqi
 * @since 2021-03-30
 */
public class TestEventProduceTest {


    public static void main(String[] args) {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(12);

        EventFactory<TestEvent> testEventEventFactory = new TestEventFactory();

        Disruptor<TestEvent> eventDisruptor = new Disruptor<>(testEventEventFactory, 1024 * 1024, poolExecutor);

        eventDisruptor.handleEventsWith(new TestEventHandler());


        eventDisruptor.start();


        RingBuffer<TestEvent> ringBuffer = eventDisruptor.getRingBuffer();

        TestEventProducer eventProducer = new TestEventProducer(ringBuffer);
        
        for (int i = 1; i <= 1000; i++) {
            eventProducer.produce(Long.valueOf(i));
        }
        eventDisruptor.shutdown();

    }
}
