package com.thoughtworks.xstream.decorators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specifies what decorators should be invoked during marshalling/unmarshalling the given field.
 *
 * @author Kohsuke Kawaguchi
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface XStreamDecorator {
    Class<? extends Decorator> value();
}
