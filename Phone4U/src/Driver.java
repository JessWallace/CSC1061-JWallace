import java.util.Random;

public class Driver {
	public static void main(String[] args) {

		FlipModel flip = new FlipModel(genIMEI());
		FoldModel fold = new FoldModel(genIMEI());
		BarModel bar = new BarModel(genIMEI());

		System.out.println(flip);
		System.out.println(fold);
		System.out.println(bar);
		
		FlipModel cFlip = (FlipModel) flip.clone();
		cFlip.getIMEI().set(0, 'X');
		System.out.println("Cloned: " + cFlip.getIMEI());
		System.out.println("Original: " + flip.getIMEI());
		
		System.out.println("Flip vs Fold: " + flip.compareTo(fold));
		System.out.println("Fold vs Bar: " + fold.compareTo(bar));
		System.out.println("Bar vs Flip: " + bar.compareTo(flip));
		
		System.out.println(flip.howToRepair() + " $" + flip.costToRepair());
		System.out.println(bar.howToRepair() + " $" + bar.costToRepair());
	}

	public static String genIMEI() {
		Random rand = new Random();
		boolean iMEILength = true;
		String IMEI = "";
		while (iMEILength == true) {
			if (IMEI.length() < 15) {
				String hold = rand.nextInt(9) + "";
				IMEI += hold + "";
			} else {
				iMEILength = false;
			}
		}
		return IMEI;
	}
}
