package com.ahmet.helpers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class VariableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<VariableSource> {

    private String variableName;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return extensionContext.getTestClass()
                .map(this::getField)
                .map(this::getValue)
                .orElseThrow(() ->
                        new IllegalArgumentException("Failed load test arguments"));
    }

    private Field getField(Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField(variableName);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Stream<Arguments> getValue(Field field) {
        Object value = null;
        try {
            value = field.get(null);
        } catch (IllegalAccessException ignored) { }

        return value == null ? null : (Stream<Arguments>) value;
    }

    @Override
    public void accept(VariableSource variableSource) {
        variableName = variableSource.value();
    }
}
