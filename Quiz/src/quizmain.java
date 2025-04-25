import java.util.HashMap;
import java.util.Map;

public class quizmain {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("123",  "John Smith");
		map.put("111",  "George Smith");
		map.put("123",  "Steve Yao");
		map.put("222",  "Steve Yao");

		System.out.println(map.get("222"));
		
	}

}
