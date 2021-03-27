package com.papaxiong.mapper;

import com.papaxiong.model.po.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoqi
 * @since 2021-02-02
 */
@Repository
@Mapper
public interface SysUserMapper {


     void insert(SysUserDO sysUser);


      List<SysUserDO> queryAll();



}
