package com.papaxiong.config.extra;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "support.redis.cluster")
@Setter
@Getter
public class JedisClusterConfig {

    private int expireSeconds;
    private String nodes;
    private String password;
    private int commandTimeout;


    @Bean
    @ConditionalOnProperty(value = "support.redis.cluster.enable", havingValue = "true")
    public JedisCluster getJedisCluster() {
        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = this.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        return new JedisCluster(nodes, commandTimeout, 1000, 1, password, new GenericObjectPoolConfig());


    }
}