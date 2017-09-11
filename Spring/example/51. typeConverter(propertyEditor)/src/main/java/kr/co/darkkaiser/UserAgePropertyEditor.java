package kr.co.darkkaiser;

import java.beans.PropertyEditorSupport;

public class UserAgePropertyEditor extends PropertyEditorSupport {
	
	@Override
	public String getAsText() {
		return String.valueOf((Integer)this.getValue());
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Integer val = Integer.parseInt(text.trim());
		if (val < 0) val = 0;
		else if (val > 20) val = 20;
		this.setValue(val);
	}
	
}
