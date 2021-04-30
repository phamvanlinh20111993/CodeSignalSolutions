package CompanyChallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KeyValue {
	private String value;
	private Integer key;

	KeyValue(String value, Integer key) {
		this.key = key;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

public class Autodesk_AnalyzingLogs {
	static final String FILE_PATH = "/root/devops/logs/";
	static final String PATTERN = "(?:\\d+)\\|([A-Z]+)\\|([a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+)\\|(?:\\d+)(?:\\|[a-zA-Z ]+){2}-{5}";

	static List<String> readFilePath(String rootPath) {
		File root = new File(rootPath);
		List<String> listFiles = new ArrayList<String>();

		for (final File fileEntry : root.listFiles()) {
			listFiles.add(fileEntry.getName());
		}
		return listFiles;
	}

	static String readDataInFile(String fileName) {
		try {

			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String res = new String();
			String line;

			while ((line = br.readLine()) != null) {
				res = res.concat("-----" + line);
			}
			br.close();
			return res;
		} catch (FileNotFoundException ex1) {
		} catch (IOException ex) {
		}

		return "";
	}

	static List<KeyValue> getLoggingInfor(String data) {
		List<KeyValue> getLog = new ArrayList<KeyValue>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(data);

		while (m.find()) {
			// System.out.println(m.group(0));
			if (m.group(1).toString().equals("ERROR")) {
				// System.out.println("Data " + m.group(2));
				if (map.containsKey(m.group(2).toString())) {
					map.put(m.group(2).toString(), map.get(m.group(2).toString()) + 1);
				} else {
					map.put(m.group(2).toString(), 1);
				}
			}
		}

		for (String key : map.keySet()) {
			// System.out.println(key + " " + map.get(key));
			getLog.add(new KeyValue(key, map.get(key)));
		}

		Collections.sort(getLog, (a, b) -> {
			if (a.getKey() > b.getKey()) {
				return -1;
			} else if (a.getKey() < b.getKey()) {
				return 1;
			} else {
				return a.getValue().compareTo(b.getValue());
			}
		});

		return getLog;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> filePath = readFilePath(FILE_PATH);
		String res = new String();
		for (String dataEachFile : filePath) {
			String[] split = dataEachFile.split("\\.");
			if (split[1].equals("log")) {
				res = res.concat(readDataInFile(FILE_PATH + "/" + dataEachFile));
			}
		}

		// System.out.println(res);
		List<KeyValue> response = getLoggingInfor(res);
		for (int i = 0; i < response.size(); i++) {
			System.out.println(response.get(i).getValue() + " " + response.get(i).getKey());
		}

	}

}
