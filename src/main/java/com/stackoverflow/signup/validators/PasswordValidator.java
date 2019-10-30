package com.stackoverflow.signup.validators;

import com.stackoverflow.signup.annotations.Password;
import com.stackoverflow.signup.exceptions.UserExistException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,String> {
    private String password;
    @Override
    public void initialize(Password constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //System.out.println(value);
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher m = p.matcher(value);
//        if(m.matches())
        return m.matches();
       // throw new UserExistException("Password is invalid",400);
    }
}
