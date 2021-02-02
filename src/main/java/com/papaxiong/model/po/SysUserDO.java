package com.papaxiong.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Accessors(chain = true)
public class SysUserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
