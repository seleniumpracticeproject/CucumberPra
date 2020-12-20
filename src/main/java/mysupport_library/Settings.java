package mysupport_library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static Properties properties = loadFromPropertiesFile();
	//static Logger log = Logger.getLogger(Settings.class);

	private Settings() {
		
	}

	public static Properties getInstance() {
		return properties;
	}

	private static Properties loadFromPropertiesFile() {
		Properties properties = new Properties();
		String relativepath = new File(System.getProperty("user.dir")).getAbsolutePath();
		relativepath = relativepath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
				+ Util.getFileSeparator() + "resources";
		try {
			properties.load(new FileInputStream(relativepath + Util.getFileSeparator() + "Global Setting.properties"));
		} catch (FileNotFoundException e) {
			//log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			//log.error(e.getMessage());
			e.printStackTrace();
		}
		return properties;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
