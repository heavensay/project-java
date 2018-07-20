package test.syntax.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Repeatable(value = Roles.class)
@Retention(RUNTIME)
public @interface Role {
    String value();
}


