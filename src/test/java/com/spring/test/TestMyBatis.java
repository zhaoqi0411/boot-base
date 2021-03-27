package com.spring.test;

import com.papaxiong.Application;
import com.papaxiong.config.SpringUtils;
import com.papaxiong.mapper.SysUserMapper;
import com.papaxiong.model.po.SysUserDO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * @author zhaoqi
 * @since 2021-03-15
 */
public class TestMyBatis {


    public static void main(String[] args) {
        Application application = new Application();
        application.main(new String[]{});


        SqlSessionTemplate sqlSessionTemplate = SpringUtils.getBean(SqlSessionTemplate.class);
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);

    }
}
