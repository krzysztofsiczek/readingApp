package readingProject;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ZonedTime {

	public ZonedTime() {
	}

	public static LocalDateTime checkCurrentDateTimeBeforePassingToDatabase() {
		return generateLocalDateTime();
	}

	private static LocalDateTime generateLocalDateTime() {

		LocalDateTime finishedBookDateTime = LocalDateTime.now();

		finishedBookDateTime = checkDateTimeAgainstTimeZone(finishedBookDateTime);

		return finishedBookDateTime;
	}
	
	private static LocalDateTime checkDateTimeAgainstTimeZone (LocalDateTime generatedBySystem){
		
		ZoneId currentTimeZone = ZoneId.systemDefault();
		ZoneId standardTimeZone = ZoneId.of("Europe/Warsaw");
		
		if ((currentTimeZone.equals(standardTimeZone)) != true) {
			generatedBySystem = LocalDateTime.now(standardTimeZone);
		}

		return generatedBySystem;
	}
	
}