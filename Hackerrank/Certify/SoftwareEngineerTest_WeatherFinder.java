package Hackerrank.Certify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftwareEngineerTest_WeatherFinder {

    public static String getJsonResponseFrom(String URL) throws IOException {
	URL strURL = new URL(URL);
	HttpURLConnection connection = (HttpURLConnection) strURL.openConnection();
	connection.setRequestMethod("GET");
	connection.setRequestProperty("Content-type", "application/json");
	connection.setReadTimeout(3000000);
	connection.setConnectTimeout(3000000);

	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String resLine;
	StringBuffer response = new StringBuffer();
	while ((resLine = in.readLine()) != null) {
	    response.append(resLine);
	}
	in.close();

	return response.toString();
    }

    public static int getTotalPage(String jsonStr) throws IOException {

	final String totalPagePattern = "total_pages\"\\s*:\\s*(\\d+)";
	Pattern pattern = Pattern.compile(totalPagePattern, Pattern.CASE_INSENSITIVE);
	Matcher matcher = pattern.matcher(jsonStr);
	while (matcher.find()) {
	    return Integer.parseInt(matcher.group(1));
	}

	return -1;
    }

    /*
     * Complete the 'getTemperature' function below.
     *
     * URL for cut and paste https://jsonmock.hackerrank.com/api/weather?name=<name>
     *
     * The function is expected to return an Integer. The function accepts a singe
     * parameter name.
     */

    public static int getTemperature(String name) throws IOException {
	String URL = "https://jsonmock.hackerrank.com/api/weather?name=" + name;

	String jsonStr = getJsonResponseFrom(URL);

	final String degreePATTERN = "weather\"\\s*:\\s*\"(-?\\d*)(?:\\.\\d+)?\\s*degree\"";
	Pattern pattern = Pattern.compile(degreePATTERN, Pattern.CASE_INSENSITIVE);
	Matcher matcher = pattern.matcher(jsonStr);
	while (matcher.find()) {
	    return Integer.parseInt(matcher.group(1));
	}

	return 0;
    }

    public static void main(String[] args) {

    }

}
