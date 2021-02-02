package com.papaxiong.support;

import com.papaxiong.support.model.BusinessBaseException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author zhaoqi
 * @since 2020-11-11
 */
public class AssertUtils {


    public static void nonNull(Object obj, String errMsg) {
        if (Objects.isNull(obj)) {
            throw new BusinessBaseException(errMsg);
        }
    }

    public static void notEmpty(Collection<?> collection, String errMsg) {
        if (Objects.isNull(collection)||CollectionUtils.isEmpty(collection)) {
            throw new BusinessBaseException(errMsg);
        }

    }
}
