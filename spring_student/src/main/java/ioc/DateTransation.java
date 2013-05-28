package ioc;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransation extends PropertyEditorSupport {
	
	public DateTransation(){
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = sdf.parse(text);
			this.setValue(new Timestamp(date.getTime()));
		} catch (java.text.ParseException e) {

			e.printStackTrace();
		}
	}
}