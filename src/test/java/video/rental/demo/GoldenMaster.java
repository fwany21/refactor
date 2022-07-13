package video.rental.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class GoldenMaster {

	private String goldenMasterFile = "./goldenmaster/goldenmaster.txt";

	private String simulatedInput = "1\n" // List customer
			+ "2\n" // List video
			+ "3\n" // Register customer
			+ "Peter\n" + "3\n" + "1980-07-11\n" + "1\n" // List customer
			+ "4\n" // Register video
			+ "V4\n" + "1\n" + "2\n" + "3\n" + "2\n" // List video
			+ "5\n" // Rent video
			+ "2\n" + "V3\n" + "1\n" // List customer
			+ "6\n" // Return video
			+ "0\n" + "V1\n" + "7\n" // Show customer report
			+ "0\n" + "6\n" // Return video
			+ "0\n" + "V2\n" + "8\n" // Show customer and clear rentals
			+ "0\n" + "1\n" // List customers
			+ "0\n"; // Bye

	public String getCurrentRun() {
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		PrintStream pstream = new PrintStream(ostream);
		System.setOut(pstream);

		ByteArrayInputStream istream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(istream);

		CmdUI ui = new CmdUI();
		ui.start();

		return ostream.toString();
	}

	public void saveGoldenMaster() {
		try {
			Files.write(Paths.get(goldenMasterFile), getCurrentRun().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

// abc\ndef\nghi\n
// abc\ndef\nghi

    //@formatter:off
	public String getGoldenMaster() {
		try {
			return Files.readAllLines(
				Paths.get(goldenMasterFile)).stream()
					 .collect(Collectors.joining("\n", "", "\n")
			);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
    //@formatter:on

}














