package video.rental.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class VideoRentalTest {

	private GoldenMaster goldenMaster = new GoldenMaster();
	
	@Disabled
	@Test
	void generate_GoldenMaster() {
		goldenMaster.saveGoldenMaster();
	}
	
//	@Disabled
	@EnabledOnOs(OS.WINDOWS)
	@Test
	void checkCurrentRun_against_GoldenMaster() {
		// Given (Arrange)
		String expected = goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getCurrentRun();

		// Then (Assert)
		assertEquals(expected, actual.replaceAll("\r\n", "\n"));
	}
	
	@EnabledOnOs({OS.MAC, OS.LINUX})
	@Test
	void checkCurrentRun_against_GoldenMaster_on_Mac() {
		// Given (Arrange)
		String expected = goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getCurrentRun();

		// Then (Assert)
		assertEquals(expected, actual);
	}
}
