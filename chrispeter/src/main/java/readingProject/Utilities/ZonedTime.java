package readingProject.Utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ZonedTime {

	public ZonedTime() {
	}

	public LocalDateTime checkCurrentDateTimeBeforePassingToDatabase() {
		return generateLocalDateTime();
	}

	private LocalDateTime generateLocalDateTime() {

		LocalDateTime finishedBookDateTime = LocalDateTime.now();

		finishedBookDateTime = checkDateTimeAgainstTimeZone(finishedBookDateTime);

		return finishedBookDateTime;
	}
	
	private LocalDateTime checkDateTimeAgainstTimeZone (LocalDateTime generatedBySystem){
		
		ZoneId currentTimeZone = ZoneId.systemDefault();
		ZoneId standardTimeZone = ZoneId.of("Europe/Warsaw");
		
		if ((currentTimeZone.equals(standardTimeZone)) != true) {
			generatedBySystem = LocalDateTime.now(standardTimeZone);
		}

		return generatedBySystem;
	}
	
}