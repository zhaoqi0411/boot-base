package com.spring.test;

import com.alibaba.fastjson.JSONObject;
import com.papaxiong.Application;
import com.papaxiong.config.SpringUtils;
import com.papaxiong.mapper.IdGeneratorMapper;
import com.papaxiong.mapper.SysUserMapper;
import com.papaxiong.model.po.IdGeneratorDO;
import com.papaxiong.model.po.SysUserDO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhaoqi
 * @since 2021-03-15
 */
public class TestMyBatis {


    public static void main(String[] args) throws IOException, SQLException {


//        //db config
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        hikariConfig.setUsername("mysql_rw");
//        hikariConfig.setJdbcUrl("jdbc:mysql://rm-bp1086fgg6d76f4yqco.mysql.rds.aliyuncs.com:3306/service_base");
//        hikariConfig.setPassword("3301994411ZQzqZQ");
//        DataSource dataSource = new HikariDataSource(hikariConfig);
//
//
//        //transaction
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        //init Environment  =transaction+datasource
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(IdGeneratorMapper.class); //添加对mapper class的扫描   顺便加载同目录下的mapper.xml     也可以现在configuration里加载
//        //configuration.addInterceptor();    plugin


        InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();


//        List<Object> objects = sqlSession.selectList("com.papaxiong.mapper.IdGeneratorMapper.getOneBySimple");
        IdGeneratorMapper mapper = sqlSession.getMapper(IdGeneratorMapper.class);
        IdGeneratorDO oneBySimple = mapper.getOne(1L);

        System.out.println(oneBySimple);


        //JDBC


//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        hikariConfig.setUsername("mysql_rw");
//        hikariConfig.setJdbcUrl("jdbc:mysql://rm-bp1086fgg6d76f4yqco.mysql.rds.aliyuncs.com:3306/service_base");
//        hikariConfig.setPassword("3301994411ZQzqZQ");
//        DataSource dataSource = new HikariDataSource(hikariConfig);
//
//        Connection connection = dataSource.getConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from id_generator where id =?");
//        preparedStatement.setLong(1,1L);
//        preparedStatement.execute();
//
//        System.out.println(JSONObject.toJSONString(preparedStatement.getResultSet()));


    }
}
