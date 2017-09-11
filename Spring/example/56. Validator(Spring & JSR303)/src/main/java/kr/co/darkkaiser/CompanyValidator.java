package kr.co.darkkaiser;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompanyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (Company.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		Company company = (Company)target;
		if(company.getName() == null || company.getName().length() == 0) {
	        errors.rejectValue("name", "fail.common.company.msg");
	    }
		// ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
	}

}
