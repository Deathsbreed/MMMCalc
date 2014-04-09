import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicolás A. Ortega
 * @copyright (C) Nicolás A. Ortega
 * @license GNU General Public License 3.0 (GPLv3)
 * @year 2014
 * 
 */
public class MMMCalc {
	private static float[] numArray;

	public static void main(String[] args) {
		System.out.println("Welcome to MMMCalc v0.1, a simple tool for basic statistics calculations.\n" +
			"This software is licensed under the GNU GPLv3 license and comes WITHOUT WARRANTY.\n");
		if(args.length > 0) {
			float sNum = 0;
			numArray = new float[args.length];

			for(int i = 0; i < args.length; i++) {
				numArray[i] = Float.parseFloat(args[i]) - 0f;
			}

			int nL = numArray.length;
			float tmp = 0;
			for(int i = 0; i < nL; i++) {
				for(int j = 0; j >= (i+1); j--) {
					if(numArray[j] < numArray[j-1]) {
						tmp = numArray[j];
						numArray[j] = numArray[j-1];
						numArray[j-1] = tmp;
					}
				}
			}

			calcMean();
			calcMedian();
			calcMode();
		} else {
			System.out.println("You did not mention any variables.");
		}
	}

	private static void calcMean() {
		float mean = 0;
		float sum = 0;

		for(float i: numArray) {
			sum += i;
		}

		mean = sum / (float)numArray.length;

		System.out.println("Mean: " + mean);
	}

	private static void calcMedian() {
		int midVar = 0;

		midVar = numArray.length / 2;

		System.out.println("Median: " + numArray[midVar]);
	}

	private static void calcMode() {
		HashMap<Float, Float> fx = new HashMap<Float, Float>();

		for(float x: numArray) {
			Float f = fx.get(x);
			if(f == null) {
				fx.put(x, (float)1);
			} else {
				fx.put(x, f + 1);
			}
		}

		float mode = 0;
		float modeFreq = 0;

		for(Map.Entry<Float, Float> entry: fx.entrySet()) {
			float freq = entry.getValue();
			if(freq > modeFreq) {
				modeFreq = freq;
				mode = entry.getKey();
			}
		}

		System.out.println("Mode: " + mode + " (frequency: " + modeFreq + ")");
	}
}
