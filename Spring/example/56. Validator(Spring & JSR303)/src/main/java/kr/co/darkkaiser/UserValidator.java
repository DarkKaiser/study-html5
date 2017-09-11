package kr.co.darkkaiser;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (User.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if(user.getName() == null || user.getName().length() == 0) {
			// fail.common.user.msg
	        errors.rejectValue("name", "fail.common.user.msg");
	    }
		
		// ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
	}

}
