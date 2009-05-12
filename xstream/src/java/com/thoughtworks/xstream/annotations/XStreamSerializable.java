package com.thoughtworks.xstream.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * @author Kohsuke Kawaguchi
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface XStreamSerializable {
}
