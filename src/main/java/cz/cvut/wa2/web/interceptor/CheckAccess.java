package cz.cvut.wa2.web.interceptor;

import cz.cvut.wa2.entity.Role;

import java.lang.annotation.*;

/**
 * Vlastni anotace pro kontrolu pristupu k metodam.
 *
 * @author jakubchalupa
 * @since 03.03.15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Documented
public @interface CheckAccess {

    /**
     * nutne role pro pristup
     */
    Role.Type[] value() default {Role.Type.USER};

}
