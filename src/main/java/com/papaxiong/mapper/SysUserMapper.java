package com.papaxiong.mapper;

import com.papaxiong.model.po.SysUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface SysUserMapper extends BaseMapper<SysUserDO> {

}
