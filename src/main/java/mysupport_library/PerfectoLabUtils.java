package mysupport_library;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.*;

import sun.net.www.protocol.http.HttpURLConnection;

public class PerfectoLabUtils {

	//private static final Logger logger = Logger.getLogger(PerfectoLapUtils.class);
	private static final String HTTPS = "https://";
	private static final String MEDIA_REPOSITORY = "/services/repositories/media";
	private static final String UPLOAD_OPERATION = "operation = upload&overwrite = true";
	private static final String UTF_8 = "UTF-8";

	public static void downloadReport(RemoteWebDriver driver, String type, String fileName) throws IOException {
		try {
			String command = " mobile:report:download";
			Map<String, Object> params = new HashMap<>();
			params.put("type", type);
			String report = (String) driver.executeScript(command, params);
			File reportFile = new File(fileName + "." + type);
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile));
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
			output.write(reportBytes);
			output.close();
		} catch (Exception ex) {
			System.out.println("Got exception " + ex);
		}
	}

	public static void downloadAttachment(RemoteWebDriver driver, String type, String fileName, String suffix)
			throws IOException {
		try {
			String command = "mobile:report:attachment";
			boolean done = false;
			int index = 0;
			while (!done) {
				Map<String, Object> params = new HashMap<>();
				params.put("type", type);
				params.put("index", Integer.toString(index));
				String attachment = (String) driver.executeScript(command, params);
				if (attachment == null) {
					done = true;
				} else {
					File file = new File(fileName + index + "." + suffix);
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
					byte[] bytes = OutputType.BYTES.convertFromBase64Png(attachment);
					output.write(bytes);
					output.close();
					index++;
				}

			}
		} catch (Exception ex) {
			System.out.println("Got excepton" + ex);
		}
	}

	public static void uploadMedia(String host, String user, String password, String path, String repositoryKey)
			throws IOException {
		File file = new File(path);
		byte[] content = readFile(file);
		uploadmedia(host, user, password, content, repositoryKey);
	}

	public static void uploadMedia(String host, String user, String password, URL mediaURL, String repositoryKey)
			throws IOException {
		byte[] content = readURL(mediaURL);
		uploadmedia(host, user, password, content, repositoryKey);
	}

	public static void uploadmedia(String host, String user, String password, byte[] content, String repositoryKey)
			throws UnsupportedEncodingException, MalformedURLException, IOException {
		if (content != null) {
			String encodedUser = URLEncoder.encode(user, "UTF-8");
			String encodedPassword = URLEncoder.encode(password, "UTF-8");
			String urlStr = HTTPS + host + MEDIA_REPOSITORY + repositoryKey + "?" + UPLOAD_OPERATION + "&user="
					+ encodedUser + "&password=" + encodedPassword;
			URL url = new URL(urlStr);
			sendRequest(content, url);
		}
	}

	private static void sendRequest(byte[] content, URL url) throws IOException {
		// TODO Auto-generated method stub
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/octet-stream");
		connection.connect();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		outStream.write(content);
		outStream.writeTo(connection.getOutputStream());
		outStream.close();
		int code = connection.getResponseCode();
		if (code > HttpURLConnection.HTTP_OK) {
			handleError(connection);

		}
	}

	private static void handleError(HttpURLConnection connection) throws IOException {
		// TODO Auto-generated method stub
		String msg = "Failed to upload media.";
		InputStream errorStream = connection.getErrorStream();
		if (errorStream != null) {
			InputStreamReader inputStreamReader = new InputStreamReader(errorStream, UTF_8);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			try {
				StringBuilder builder = new StringBuilder();
				String OutputString;
				while ((OutputString = bufferReader.readLine()) != null) {
					if (builder.length() != 0) {
						builder.append("\n");
					}
					builder.append(OutputString);
				}
				String response = builder.toString();
				msg += "Response:" + response;
			} finally {
				bufferReader.close();
			}
		}
		throw new RuntimeException(msg);
	}

	private static byte[] readFile(File path) throws FileNotFoundException, IOException {
		int length = (int) path.length();
		byte[] content = new byte[length];
		InputStream inStream = new FileInputStream(path);
		try {
			inStream.read(content);
		} finally {
			inStream.close();
		}
		return content;
	}

	private static byte[] readURL(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		int code = connection.getResponseCode();
		if (code > HttpURLConnection.HTTP_OK) {
			handleError(connection);
		}
		
		InputStream stream = connection.getInputStream();
		if (stream == null) {
			throw new RuntimeException("Failed to get content from url" + url + "no response stream");
			//byte[] content = read(stream);
		}
		return null;
		
}

	private static byte[] read(InputStream input) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int nBytes = 0;
			while ((nBytes = input.read(buffer)) > 0) {
				output.write(buffer, 0, nBytes);
			}
			byte[] result = output.toByteArray();
			return result;
		} finally {
			try {
				input.close();
			} catch (IOException e) {

			}
		}
	}
}
