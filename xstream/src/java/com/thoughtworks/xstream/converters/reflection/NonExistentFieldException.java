package com.thoughtworks.xstream.converters.reflection;

/**
 * Indicates that field/property didn't exist in classes (as opposed to what's implied in XML.)
 *
 * @author Kohsuke Kawaguchi
 */
public class NonExistentFieldException extends ObjectAccessException {
    public NonExistentFieldException(String message) {
        super(message);
    }

    public NonExistentFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
