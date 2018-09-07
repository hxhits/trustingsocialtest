package trustingsocial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecordProcessing record = new RecordProcessing("resources\\random_1.csv");
		record.process();
	}

}
