package com.papaxiong.model.po;

import lombok.Data;

/**
 * null
 * @TableName id_generator
 */
@Data
public class IdGeneratorDO {
    /**
     * 
     *
     * @mbg.generated 2021-04-13 15:52:10
     */
    private Long id;

    /**
     * 
     *
     * @mbg.generated 2021-04-13 15:52:10
     */
    private Long maxId;

    /**
     * 
     *
     * @mbg.generated 2021-04-13 15:52:10
     */
    private Integer incrementStep;

    /**
     * 
     *
     * @mbg.generated 2021-04-13 15:52:10
     */
    private Integer version;

}