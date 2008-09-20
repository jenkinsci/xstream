package com.thoughtworks.xstream.converters.reflection;

/**
 * Indicates that field/property didn't exist in classes (as opposed to what's implied in XML.)
 *
 * @author Kohsuke Kawaguchi
 */
public class NonExistentFieldException extends ObjectAccessException {
    private final String fieldName;

    public NonExistentFieldException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public NonExistentFieldException(String message, Throwable cause, String fieldName) {
        super(message, cause);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
