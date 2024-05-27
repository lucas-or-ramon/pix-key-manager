package dev.canoa.pixkeymanager.domain.model.validation;

import jakarta.validation.ConstraintValidator;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
        @Override
        public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
            return value == null || !value.trim().isEmpty();
        }
}
