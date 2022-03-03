package CompanyChallenge.Verkada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verkada_AnalyzeIpAddresses {
	static List<String> listFile;

	private static void getALlFile(File folder) {
		File[] fileName = folder.listFiles();
		for (int i = 0; i < fileName.length; i++) {
			if (fileName[i].isDirectory()) {
				getALlFile(fileName[i]);
			} else {
				if (fileName[i].isFile()) {
					listFile.add(fileName[i].toString());
				}
			}
		}
	}

	private static List<String> getAllStringFromFile(String pathFile) {

		List<String> listString = new ArrayList<String>();

		try {
			String line = null;
			FileReader fileReader = new FileReader(pathFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				listString.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}

		return listString;
	}

	private static List<String> isIp(String sequence) {
		// String PATTERN_IP =
		// "(?:(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		String PATTERN_IP = "(?:(?:25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)";

		Set<String> distinctIP = new TreeSet<String>();
		Pattern r = Pattern.compile(PATTERN_IP);
		Matcher m = r.matcher(sequence);

		while (m.find()) {
			distinctIP.add(m.group(0));
		}

		return new ArrayList<String>(distinctIP);
	}

	public static List<String> AnalyzeIpAddresses(List<String> listFile) {
		List<String> listIP = new ArrayList<String>(), listIpTmp = new ArrayList<String>();
		String strContainIp = "";
		for (String pathFile : listFile) {
			listIpTmp = getAllStringFromFile(pathFile);
			listIP.addAll(listIpTmp);
		}

		for (String ip : listIP) {
			strContainIp = strContainIp.concat(ip);
		}

		return isIp(strContainIp);
	}

	public static void main(String[] args) {
		listFile = new ArrayList<String>();
		getALlFile(new File("/root"));

		List<String> listDistinctIp = new ArrayList<String>();
		listDistinctIp = AnalyzeIpAddresses(listFile);
		for (int i = 0; i < listDistinctIp.size(); i++) {
			System.out.println(listDistinctIp.get(i));
		}

	}

}
