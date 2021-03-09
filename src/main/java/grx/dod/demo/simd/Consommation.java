package grx.dod.demo.simd;

import java.util.List;

public class Consommation {

	public void consume(List<String> formes) {
		formes.stream().forEach(
			forme -> {
				System.out.println("  - "+forme);
			}
		);
	}

}
