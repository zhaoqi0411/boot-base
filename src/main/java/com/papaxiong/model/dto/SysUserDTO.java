package com.papaxiong.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class SysUserDTO {

    private Long id;

    private List<Long> idList;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    private String confirmPassword ;


    private String realName;

    private String workPhone;

    /**
     * 邮箱
     */
    private String email;

    //部门id
    private Long deptId;

    private Integer userStatus;

    private List<Long> roleIdList;




}
