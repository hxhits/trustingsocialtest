package trustingsocial.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static Date parse(String dateString) {
		try {
			if (!dateString.isEmpty()) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				return format.parse(dateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateByFormat(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateFormat = formatter.format(date);
		return dateFormat;
	}

	public static Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(new Date(1514136735000L)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
