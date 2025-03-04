
public class FlipModel extends Phone implements Repairable, Comparable<Phone> {
	private String type = "Flip Model";
	//Constructors
	public FlipModel(String iMEI) {
		super("HorseRacer 300", 3, 16, iMEI);
	}

	
public String getType() {
		return type;
	}

	//Methods
	@Override
	public String toString() {
		return getType() + ", Repairable: Yes, Processor: " + getProcessor() + ", Cache:" + getCache() + "MB, Storage:" + getStorage()
				+ "GB, IMEI" + getIMEI() + "\n";
	}

	@Override
	public String howToRepair() {
		return "Repair the FlipModel by fixing it";
	}

	@Override
	public double costToRepair() {
		return 700.00;
	}
}
