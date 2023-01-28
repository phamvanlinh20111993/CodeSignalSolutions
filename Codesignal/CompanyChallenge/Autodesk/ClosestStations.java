package Codesignal.CompanyChallenge.Autodesk;

/**
 * https://app.codesignal.com/company-challenges/autodeskasia/EReoR836k4T8eqaxN
 * 
 * When Autodesk was designing a new rail tunnel, they enlisted the help of architecture, engineering, and design consultancy Norconsult, which helped reduce the risk by applying advanced technology to a critical-yet-cumbersome design requirement - the railway signaling system. To prevent costly construction delays due to inadequate signs and signals, Norconsult used 3D modeling and VR gamification in the infrastructure design, in order to help foresee potential design problems.

Now it's time to test the system, and your task is to find the best stations for testing. You've received the stations list from your co-workers, which contains the names of at least 2 different stations, and you've been instructed to find the closest pair of stations from the list. Return the distance between these stations in the form of a floating-point numerical value.

We assume that the distance between two stations is the Euclidian distance between their coordinates (latitude and longitude).

Useful APIs

For this task, you can use the following APIs or any other public APIs that provide the necessary functionality.

Transport API.
Note that you are not allowed to access resources created specifically for solving this task (like your own service).

You can access the Transport API with the following parameters.

App ID: 8b9cd4d9, Key: 69eaf54e2f7d2f279a03077acab2721b.
App ID: ae6d5a90, Key: 5c6b8d92f7c423c588ab1ef7a03c1bce.
App ID: b6b818ca, Key: 03e4aec30ef27c673a08ea02d4567e28.
Example

For stations = ["Euston", "Mossley Hill", "Glasgow Central"], the output should be solution(stations) = 2.82054384773.

The closest stations are "Mossley Hill", and "Glasgow Central". The distance between them is 2.82054384773.

Input/Output

[execution time limit] 20 seconds (java)

[input] array.string stations

The names of the stations that can be chosen. All station names contain only English letters and spaces.

Guaranteed constraints:
2 ≤ stations.length ≤ 10,
1 ≤ stations[i].length ≤ 100.

[output] float

The minimal distance between any two of the given stations. Your answer will be considered correct if the absolute error doesn't exceed 10-5.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ClosestStations {

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "https://transportapi.com/v3/uk/places.json?query=euston&type=train_station&app_id=b6b818ca&app_key=03e4aec30ef27c673a08ea02d4567e28";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	double solution(String[] stations) throws Exception {
		ClosestStations http = new ClosestStations();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		return 1D;
	}

}
