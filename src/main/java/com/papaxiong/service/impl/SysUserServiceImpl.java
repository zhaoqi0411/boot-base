package com.papaxiong.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUserDTO user) {
//        AssertUtils.nonNull(user.getUserName(), "登录账号不能为空");
//        AssertUtils.nonNull(user.getPassword(), "密码不能为空");
//        AssertUtils.nonNull(user.getConfirmPassword(), "确认密码不能为空");
//        AssertUtils.nonNull(user.getUserCode(), "用户编码不能为空");
//        if (!user.getConfirmPassword().equals(user.getPassword())) {
//            throw new BusinessBaseException("请确认两次输入的密码是否一致！");
//        }
//
//        LambdaQueryWrapper<SysUserDO> distinctUserQuery =
//                Wrappers.<SysUserDO>lambdaQuery()
//                        .eq(SysUserDO::getIsDel, 0)
//                        .and(i -> i.eq(SysUserDO::getUserCode, user.getUserCode())
//                                .or()
//                                .eq(SysUserDO::getUserName, user.getUserName()));
//
//        List<SysUserDO> distinctUsers = super.list(distinctUserQuery);
//        if (!CollectionUtils.isEmpty(distinctUsers)) {
//            throw new BusinessBaseException("已存在登录名/用户编码 的用户！");
//        }
        SysUserDO sysUser = new SysUserDO();
        BeanUtils.copyProperties(user, sysUser);
        sysUser.setPassword(DigestUtils.md5Hex(user.getPassword()+ LoginConstant.LOGIN_SALT));
        // sysUser.setUserStatus(UserStatusEnum.NORMAL.getStatus());
        sysUser.setIsDel(0);
        sysUser.setCtime(new Date());
        sysUser.setVersion(1);
        sysUserMapper.insert(sysUser);
        //role
    }

    @Override
    public void updateUser(SysUserDTO user) {

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
