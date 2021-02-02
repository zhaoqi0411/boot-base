package com.papaxiong.support;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

/**
 * @author zhaoqi
 * @since 2020-10-10
 */
@Builder
@Getter
public class PageHelper {


    private Integer offset ;

    private Integer pageSize;

    public static com.papaxiong.support.PageHelper check(Integer pageNo, Integer pageSize) {
        if (Objects.isNull(pageNo) || pageNo <= 1) {
            pageNo = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 20;
        }
        return com.papaxiong.support.PageHelper
                .builder()
                .offset((pageNo - 1))
                .pageSize(pageSize)
                .build();

    }

}
