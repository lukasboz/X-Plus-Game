import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {

	private LinkedList<Data>[] linkedListArr;
	private int size;

	public HashDictionary(int size) {
		this.size = size;
		this.linkedListArr = new LinkedList[size];
		
		for (int i = 0; i < size; i++) {
			linkedListArr[i] = new LinkedList<Data>();
			
		}

	}

	public int put(Data record) throws DictionaryException {

		LinkedList<Data> currentRecord = linkedListArr[computeHashCode(record.getConfiguration())];

		if (currentRecord != null && currentRecord.isEmpty() == false) {
			
			for (int i = 0; i < currentRecord.size(); i++) {
				
				if (currentRecord.get(i).getConfiguration().equals(record.getConfiguration())) {
					
					throw new DictionaryException();
				}
			}

			currentRecord.add(record);
			return 1;

		}

		currentRecord.add(record);
		return 0;

	}

	public void remove(String config) throws DictionaryException {

		
		LinkedList<Data> currentRecord = linkedListArr[computeHashCode(config)];

		if (currentRecord != null && currentRecord.isEmpty() == false) {
			for (int i = 0; i < currentRecord.size(); i++) {
				if (currentRecord.get(i).getConfiguration().equals(config)) {
					currentRecord.remove(i);
				}
			}
		} else {
			throw new DictionaryException();
		}

	}

	public int get(String config) {
		
		LinkedList<Data> currentRecord = linkedListArr[computeHashCode(config)];

		if (currentRecord != null && currentRecord.isEmpty() == false) {
			for (int i = 0; i < currentRecord.size(); i++) {
				if (currentRecord.get(i).getConfiguration().equals(config)) {
					return currentRecord.get(i).getScore();
				}
			}
		}
		return -1;
	}

	public int numRecords() {
		int total = 0;
		for (int i = 0; i < size; i++) {
			total++;
		}
		return total;
	}

	// because we're not allowed to use .hashcode() we need to design our own
	private int computeHashCode(String configuration) {
		int hashToReturn = 0;
		char[] configArr = configuration.toCharArray();

		for (int i = 0; i < configArr.length; i++) {
			hashToReturn += (int) (configArr[i] * (Math.pow(31, (configArr.length - i)))); //too large?
		}

		return Math.abs(hashToReturn % size); // use Math.abs so we don't get a negative value that would
															// break our hashdictionary
	}

}
