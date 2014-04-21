package demo.service;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for transactional methods. Managed by Interceptor
 *
 * @author Erofeev Danil
 * @see demo.service.impl.TransactionInterceptor
 */
@InterceptorBinding
@Inherited
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface Transactional {

}
