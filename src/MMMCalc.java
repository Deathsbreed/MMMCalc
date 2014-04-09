import java.lang.Math;
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
	private static boolean verbose = false;
	private static float[] numArray;
	private static float mean = 0;
	private static float median = 0;
	private static float mode = 0;
	private static float range = 0;
	private static float stdDev = 0;
	private static float varience = 0;

	public static void main(String[] args) {
		System.out.println("Welcome to MMMCalc v0.2, a simple tool for basic statistics calculations.\n" +
			"This software is licensed under the GNU GPLv3 license and comes WITHOUT WARRANTY.\n");
		if(args.length > 0) {
			if(args[0].equals("-h")) {
				System.out.println("Usage:\n" +
					"  MMMCalc [options] [variables]\n\n" +
					"Options:\n" +
					"  -h -- Show this help information.\n" +
					"  -v | -V -- Be verbose (show the work)\n");
			} else if(args[0].equals("-v") || args[0].equals("-V")) {
				verbose = true;
				numArray = new float[args.length - 1];

				for(int i = 0; i < numArray.length; i++) {
					numArray[i] = Float.parseFloat(args[i+1]);
				}
				sortArray();

				calcMean();
				calcMedian();
				calcMode();
				calcRange();
				calcStdDev();
				calcVarience();
			} else {
				numArray = new float[args.length];

				for(int i = 0; i < args.length; i++) {
					numArray[i] = Float.parseFloat(args[i]) - 0f;
				}
				sortArray();

				calcMean();
				calcMedian();
				calcMode();
				calcRange();
				calcStdDev();
				calcVarience();
			}
		} else {
			System.out.println("You did not mention any variables. Use the -h argument for help.");
		}
	}

	private static void sortArray() {
		int nL = numArray.length;
		float tmp = 0;
		for(int i = 0; i < nL; i++) {
			for(int j = (nL-1); j >= (i+1); j--) {
				if(numArray[j] < numArray[j-1]) {
					tmp = numArray[j];
					numArray[j] = numArray[j-1];
					numArray[j-1] = tmp;
				}
			}
		}
	}

	private static void calcMean() {
		float sum = 0;

		for(float i: numArray) {
			sum += i;
		}

		mean = sum / (float)numArray.length;

		System.out.println("Mean: " + mean);
	}

	private static void calcMedian() {
		int midVar = numArray.length / 2;

		median = numArray[midVar];

		System.out.println("Median: " + median);
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

	private static void calcRange() {
		int l = numArray.length -1;
		range = numArray[l] - numArray[0];

		System.out.println("Range: " + range);

		if(verbose) {
			System.out.println(numArray[l] + " - " + numArray[0] + " = " + range + "\n");
		}
	}

	private static void calcStdDev() {
		float difSum = 0;
		for(int i = 0; i < numArray.length; i++) {
			difSum += numArray[i] - mean;
		}

		stdDev = difSum / (float)Math.sqrt((double)numArray.length);

		System.out.println("Standard Deviation: " + stdDev);
	}

	private static void calcVarience() {
		// NOTE: I'm doing it this way so I don't have to convert the variables to doubles and lose precision.
		varience = stdDev * stdDev;

		System.out.println("Varience: " + varience);
	}
}
