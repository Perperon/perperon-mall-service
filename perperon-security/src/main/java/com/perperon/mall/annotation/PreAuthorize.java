package com.perperon.mall.annotation;

import java.lang.annotation.*;

/**
 * @author dupengcheng
 * @date 2024-07-03
 * @apiNote
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuthorize {
    String value();
}

