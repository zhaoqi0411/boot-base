package com.papaxiong.support.sentienl;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * @author zhaoqi
 * @since 2021-04-15
 */
@Configuration
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

    @PostConstruct
    public void init(){
        ParamFlowRule rule = new ParamFlowRule("key1");


        //針對某個參數 自定義限流
        rule.setParamIdx(0);
        rule.setCount(2);
        rule.setDurationInSec(60);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);


        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));

    }

}
