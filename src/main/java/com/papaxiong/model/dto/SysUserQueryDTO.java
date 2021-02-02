package com.papaxiong.model.dto;

import com.papaxiong.model.BasePageQueryDTO;
import lombok.Data;

@Data
public class SysUserQueryDTO  extends BasePageQueryDTO {

    private Long id;


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


    private String realName;

    private String workPhone;

    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 状态  -1：禁用   1：正常
     */
    private Integer userStatus;

    /**
     * 部门ID
     */
    private Long deptId;

}
