package com.example.util.validator;

import com.example.service.UserService;
import com.example.util.annotation.UniqueEmail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String gitAddress, ConstraintValidatorContext constraintValidatorContext) {
        return userService.emailExists(gitAddress);
    }
}
