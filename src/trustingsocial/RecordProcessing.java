package trustingsocial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import trustingsocial.utils.DateUtils;

public class RecordProcessing {
	
	private String fileUrl;
	
	public RecordProcessing(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public void process() {
		// read file
		List<Record> records = readFromFile(this.fileUrl);
		// sort
		Collections.sort(records);
		// initial
		Map<String, String> results = new HashMap<>();
		Map<String, List<Record>> recordMaps = records.stream().collect(Collectors.groupingBy(record -> record.getPhone()));
		// handle
		for (Entry<String, List<Record>> entry : recordMaps.entrySet()) {
			Iterator<Record> recordIterator = entry.getValue().iterator();
			Record previousRecord = recordIterator.next();
			if(previousRecord.isActive()) {
				if(!recordIterator.hasNext()) {
					results.put(previousRecord.getPhone(),DateUtils.getDateByFormat(previousRecord.getActivationDate(), "yyyy-MM-dd"));
				} else {
					while (recordIterator.hasNext()) {
						Record currentRecord = recordIterator.next();
						int rs = previousRecord.getActivationDate().compareTo(currentRecord.getDeactivationDate());
						if (rs == 0) {
							previousRecord = currentRecord;
							results.put(previousRecord.getPhone(),DateUtils.getDateByFormat(previousRecord.getActivationDate(), "yyyy-MM-dd"));
						} else {
							break;
						}
					}
				}
			}
		}
		// result
		for (Entry<String, String> entry : results.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			System.out.println(k + " | " + v);
		}
	}
	
	public static List<Record> readFromFile(String fileName) {
		InputReader sc = null;
		List<Record> records = new ArrayList<>();
		try {
			sc = new InputReader(new FileInputStream(new File(fileName)));
			String line;
			Record record;
			
			sc.next();
			while ((line = sc.next()) != null) {
				String[] tokens = line.split(",");
				if (tokens.length == 3) {
					record = new Record(tokens[0], DateUtils.parse(tokens[1]), DateUtils.parse(tokens[2]));
				} else {
					record = new Record(tokens[0], DateUtils.parse(tokens[1]));
				}
				records.add(record);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return records;
	}

}
