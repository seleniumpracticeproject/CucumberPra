package stepDefinations;

import java.io.IOException;

import Library.DriverFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeeeHooooks {
	
	static DriverFactory strDriver = new DriverFactory();
	@Before
	public static void BeforeActions() throws IOException, Exception {
		System.out.println("This method is used to start the driver");
		strDriver.initializeDriver();
	}
	@After
	public static void AfterActions() {
		System.out.println("This method is used to quit the driver");
		strDriver.closeDriver();
	}
}
