
public class BarModel extends Phone implements Repairable, Comparable<Phone>{
	private String type = "Bar Model";


	public BarModel(String iMEI) {
		super("DragonSlayer 600", 8, 32, iMEI);
	}

	public String getType() {
		return type;
	}

	@Override
	public String howToRepair() {
		return "Repair the BarModel by buying a new phone";
	}

	@Override
	public double costToRepair() {
		return 650;
	}
		@Override
	public String toString() {
			return getType() + ", Repairable: Yes, Processor:" + getProcessor() + ", Cache:" + getCache() + "MB, Storage:" + getStorage()
			+ "GB, IMEI" + getIMEI() + "\n";
	}

}
