package com.papaxiong.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.papaxiong.config.LoginConstant;
import com.papaxiong.model.dto.SysUserDTO;
import com.papaxiong.model.dto.SysUserQueryDTO;
import com.papaxiong.model.po.SysUserDO;
import com.papaxiong.mapper.SysUserMapper;
import com.papaxiong.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoqi
 * @since 2021-02-02
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserService sysUserService;



    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void saveUser(SysUserDTO user) {



        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUserCode(RandomUtil.randomString("userCode",6));
        sysUserDO.setUserName(RandomUtil.randomString("userName",6));

        sysUserDO.setPassword(RandomUtil.randomString(12));
        sysUserDO.setRealName(RandomUtil.randomString(5));
        sysUserDO.setMobile(RandomUtil.randomString(11));
        sysUserDO.setUserStatus(1);
        sysUserDO.setIsDel(0);

        sysUserDO.setVersion(1);
        sysUserMapper.insert(sysUserDO);


        List<SysUserDO> userDOS = sysUserMapper.queryAll();


    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void updateUser(SysUserDTO user) {

        SysUserDO sysUser = new SysUserDO();
        BeanUtils.copyProperties(user, sysUser);
        sysUser.setPassword(DigestUtils.md5Hex(user.getPassword()+ LoginConstant.LOGIN_SALT));
        // sysUser.setUserStatus(UserStatusEnum.NORMAL.getStatus());
        sysUser.setIsDel(0);
        sysUser.setCtime(new Date());
        sysUser.setVersion(1);
        sysUserMapper.insert(sysUser);

    }

    @Override
    public SysUserDO getUser(SysUserQueryDTO query) {
        return null;
    }



    @Override
    public void delUser(SysUserDTO dto) {

    }

    @Override
    public void batchDelUser(SysUserDTO dto) {

    }

    @Override
    public void exportSysUser(SysUserQueryDTO dto, HttpServletResponse response) {

    }
}
