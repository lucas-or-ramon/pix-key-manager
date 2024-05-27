package dev.canoa.pixkeymanager.domain.model.validation;

import dev.canoa.pixkeymanager.domain.model.Key;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PixKeyValueValidator implements ConstraintValidator<PixKeyValue, Key> {

    @Override
    public boolean isValid(Key value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.type().isValid(value.value());
    }
}
