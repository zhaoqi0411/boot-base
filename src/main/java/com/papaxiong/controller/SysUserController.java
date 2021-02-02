package com.papaxiong.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.papaxiong.model.dto.SysUserDTO;
import com.papaxiong.model.dto.SysUserQueryDTO;
import com.papaxiong.model.po.SysUserDO;
import com.papaxiong.service.SysUserService;
import com.papaxiong.support.Wrapper;
import com.papaxiong.support.anno.SysLogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhaoqi
 * @since 2021-02-02
 */
@RestController
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    @SysLogAnno(operateCode =100501,operateName = "系统用户：新增")
    @RequestMapping("/sys/user/save")
    public Wrapper<String> saveUser(@RequestBody SysUserDTO user){
        sysUserService.saveUser(user);
        return Wrapper.ok("ok");
    }

    @SysLogAnno(operateCode =100502,operateName = "系统用户：更新")
    @RequestMapping("/sys/user/update")
    public Wrapper<String> updateUser(@RequestBody SysUserDTO user){
        sysUserService.updateUser(user);
        return Wrapper.ok("ok");
    }
    @RequestMapping("/sys/user/get")
    public Wrapper<SysUserDO> getUser(@RequestBody SysUserQueryDTO query){
        return Wrapper.ok(sysUserService.getUser(query));
    }
    @RequestMapping("/sys/user/page")
    public Wrapper<Page<SysUserDO>> pageUser(@RequestBody SysUserQueryDTO query){
        return Wrapper.ok(sysUserService.pageUser(query));
    }

    @SysLogAnno(operateCode =100503,operateName = "系统用户：删除")
    @RequestMapping("/sys/user/del")
    public Wrapper<String> delUser(@RequestBody SysUserDTO dto){
        sysUserService.delUser(dto);
        return Wrapper.ok("ok");
    }
    @SysLogAnno(operateCode =100504,operateName = "系统用户：批量删除")
    @RequestMapping("/sys/user/batchDel")
    public Wrapper<String> batchDelUser(@RequestBody SysUserDTO dto){
        sysUserService.batchDelUser(dto);
        return Wrapper.ok("ok");
    }


    @RequestMapping("/sys/user/export")
    public void exportUser(HttpServletResponse response) {
        sysUserService.exportSysUser(new SysUserQueryDTO(), response);
    }



}
