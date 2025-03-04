import java.util.ArrayList;

public abstract class Phone implements Cloneable, Comparable<Phone> {
// Data members
	private String processor;
	private int cache;
	private int storage;
	private ArrayList<Character> IMEI;

//Constructors
	public Phone(String processor, int cache, int storage, String iMEI) {
		this.processor = processor;
		this.cache = cache;
		this.storage = storage;
		this.IMEI = new ArrayList<>();
		for (char c : iMEI.toCharArray()) {
			this.IMEI.add(c);
		}
	}

//Getters and Setters
	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public ArrayList<Character> getIMEI() {
		return IMEI;
	}

	public void setIMEI(ArrayList<Character> iMEI) {
		IMEI = iMEI;
	}

//Methods
	public Phone clone() {
		try {
			Phone cloned = (Phone) super.clone();
			cloned.IMEI = new ArrayList<>(this.IMEI);
			return cloned;
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new AssertionError();
		}
	}

	@Override
	public String toString() {
		return "Phone processor=" + processor + ", cache=" + cache + ", storage=" + storage + ", IMEI=" + IMEI + ".\n";
	}

	@Override
	public int compareTo(Phone o) {
		if (this.cache != o.cache) {
			return Integer.compare(this.cache, o.cache);
		}
		return Integer.compare(this.storage, o.storage);
	}
}
