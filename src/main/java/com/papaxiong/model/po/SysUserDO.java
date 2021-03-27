package com.papaxiong.model.po;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoqi
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userCode;

    private String userName;

    private String mobile;

    private String password;

    private String realName;

    private String workPhone;

    private String email;

    private String salt;

    private Integer userStatus;

    private Integer isDel;

    private Integer version;

    private Date ctime;

    private Date mtiem;

    private Date dtime;


}
