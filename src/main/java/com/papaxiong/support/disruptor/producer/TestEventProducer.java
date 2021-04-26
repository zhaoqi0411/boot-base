package com.papaxiong.support.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import com.papaxiong.support.disruptor.event.TestEvent;

/**
 * @author zhaoqi
 * @since 2021-03-30
 */
public class TestEventProducer {

    private final RingBuffer<TestEvent> ringBuffer;


    public TestEventProducer(RingBuffer<TestEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void produce(Long value){

        long sequence  = ringBuffer.next();


        TestEvent testEvent = ringBuffer.get(sequence);
        testEvent.setValue(value);

        ringBuffer.publish(sequence);

    }
}
