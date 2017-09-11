package kr.co.darkkaiser;

import java.util.regex.Matcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]\\d{2}-(\\d{3}|\\d{4})-\\d{4}$");
	
	@Override
	public void initialize(Telephone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
	}

}
