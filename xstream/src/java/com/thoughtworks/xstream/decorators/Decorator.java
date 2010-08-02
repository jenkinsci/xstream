package com.thoughtworks.xstream.decorators;

import java.lang.reflect.Field;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class Decorator {
    public abstract Object decorateUnmarshal(Object parent, Object value, Field field);
    public abstract Object decorateMarshal(Object parent, Object value, Field field);
}
