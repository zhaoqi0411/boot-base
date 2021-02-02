package com.papaxiong.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhaoqi
 * @since 2021-02-02
 */
@Getter
@Setter
public class BasePageQueryDTO {

    private Integer pageNo;

    private Integer pageSize;

    private String sortName;

    /**
     * @see SortConstant
     */
    private Integer sortOrder;

}
