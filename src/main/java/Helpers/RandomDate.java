package main.java.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @return Random Generated Date
 */

public class RandomDate {
	public static String GetRandomDate() {
		final String NEW_FORMAT = "dd/MM/yyyy";
		final String OLD_FORMAT = "yyyy-MM-dd";
		Random random = new Random();
		int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2025, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = null;
		try {
			d = sdf.parse(randomBirthDate.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf.applyPattern(NEW_FORMAT);
		return sdf.format(d);
	}
}
