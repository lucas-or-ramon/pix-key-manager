package dev.canoa.pixkeymanager.domain.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        Class<? extends Enum<?>> enumSelected = annotation.enumClass();
        acceptedValues = Stream.of(enumSelected.getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.name());
    }
}
