package com.thoughtworks.xstream.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Designates that the type and its derived types will serialize
 * as the specified type. This is useful if you want an entire
 * family of types serialize as the base type with its special converter.
 *
 * @author Kohsuke Kawaguchi
 */
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface XStreamSerializeAs {
    /**
     * Use {@code void.class} to cancel out the annotation defined in the super type.
     */
    Class<?> value();
}
