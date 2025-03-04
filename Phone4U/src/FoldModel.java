
public class FoldModel extends Phone implements Comparable<Phone> {
	private String type = "Fold Model";
	public FoldModel(String iMEI) {
		super("SpeedRacer 800", 6, 64, iMEI);
	}
	public String getType() {
		return type;
	}
	@Override
	public String toString() {
		return getType() + " Processor: " + getProcessor() + ", Cache:" + getCache() + "MB, Storage:" + getStorage()
		+ "GB, IMEI" + getIMEI() + "\n";
	}

}
