package junit.test;

import static junit.test.TestUtils.assertReaders;

import org.junit.jupiter.api.Test;

import trustingsocial.RecordProcessing;

class RecordTest {
	@Test
	public void test_default() {
		new RecordProcessing("resources\\random_2.csv").process();
		assertReaders("resources\\random_2_result.csv", "resources\\output.csv");
	}

}
