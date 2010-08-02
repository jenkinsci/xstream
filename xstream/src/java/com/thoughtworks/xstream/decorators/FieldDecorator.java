package com.thoughtworks.xstream.decorators;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Aggregated {@link Decorator}s to the given field.
 *
 * @author Kohsuke Kawaguchi
 */
public final class FieldDecorator extends Decorator {
    public final Field target;
    private final Decorator[] decorators;

    public FieldDecorator(Field target) {
        this.target = target;
        // by far the common case is there's no decorators
        this.decorators = EMPTY_DECORATORS;
    }

    public FieldDecorator(Field target, List<Decorator> decorators) {
        this.target = target;
        // by far the common case is there's no decorators
        this.decorators = decorators.isEmpty() ? EMPTY_DECORATORS : decorators.toArray(new Decorator[decorators.size()]);
    }

    @Override
    public Object decorateUnmarshal(Object parent, Object value, Field field) {
        for (Decorator decorator : decorators)
            value = decorator.decorateUnmarshal(parent, value, field);
        return value;
    }

    @Override
    public Object decorateMarshal(Object parent, Object value, Field field) {
        for (int i=decorators.length-1; i>=0; i--)
            value = decorators[i].decorateMarshal(parent,value,field);
        return value;
    }

    private static final Decorator[] EMPTY_DECORATORS = new Decorator[0];
}
