package com.papaxiong.support.idgenerate;

import com.papaxiong.mapper.IdGeneratorMapper;
import com.papaxiong.model.po.IdGeneratorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhaoqi
 * @since 2021-04-13
 */
@Component

public class NumberSegment {

    @Autowired
    public IdGeneratorMapper idGeneratorMapper;

    AtomicLong incrementId = new AtomicLong();

    volatile Long maxThreadHold;




    @Transactional
    public void init(){
        //如果重启 需要刷新本地的maxId

        IdGeneratorDO one = idGeneratorMapper.getOne(1L);
        Long maxId = one.getMaxId();


        //cas
        Long updateMaxId = maxId + one.getIncrementStep();
        one.setMaxId(updateMaxId);
        one.setVersion(one.getVersion() + 1);
        Integer oldVersion = one.getVersion();

        int effectRows = idGeneratorMapper.updateByVersion(one, oldVersion);
        if (effectRows == 0) {
            //更新失败 重试
        }

        //更新成功
        incrementId.set(maxId);

        maxThreadHold= updateMaxId;//极限值

        //也可以将这两值持久化到本地的mysql中 ，服务器挂掉也不用重新获取一个号段

    }


    public Long getNextId() {
        if (maxThreadHold == incrementId.get()) {
            synchronized (this) {
                if (maxThreadHold == incrementId.get()) {
                    init();
                }
            }
        }

        //获取key
        long nextId = incrementId.incrementAndGet();
        //如果占用超过 50% 开始异步预加载 缓存下一个号段
        //todo
        return nextId;

    }
}
