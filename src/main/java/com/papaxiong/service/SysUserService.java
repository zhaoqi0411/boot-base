package com.papaxiong.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.papaxiong.model.dto.SysUserDTO;
import com.papaxiong.model.dto.SysUserQueryDTO;
import com.papaxiong.model.po.SysUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoqi
 * @since 2021-02-02
 */
public interface SysUserService extends IService<SysUserDO> {


    void saveUser(SysUserDTO user);

    void updateUser(SysUserDTO user);

    SysUserDO getUser(SysUserQueryDTO query);

    Page<SysUserDO> pageUser(SysUserQueryDTO page);

    void delUser(SysUserDTO dto);

    void batchDelUser(SysUserDTO dto);

    void exportSysUser(SysUserQueryDTO dto, HttpServletResponse response);
}
