package util.tx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Propagation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Tx {
   Propagation propagation() default Propagation.REQUIRED;
}