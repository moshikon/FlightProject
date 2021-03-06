package demo.jxmapviewerdemo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Color gradient map from blue to red.
 * Returns a Color for a double value. 
 */
public class ColorHelper {

	private final static int LOW = 0;
	private final static int HIGH = 255;
	private final static int HALF = (HIGH + 1) / 2;

	private final static Map<Integer, Color> map = initNumberToColorMap();
	private static int factor;

	/**
	 * 
	 * @param value
	 *            should be from 0 until 100
	 */
	public static Color numberToColor(final double value) {
		if (value < 0 || value > 100) {
			return null;
		}
		return numberToColorPercentage(value / 100);
	}

	/**
	 * @param value
	 *            should be from 0 until 1
	 * @return
	 */
	public static Color numberToColorPercentage(final double value) {
		if (value < 0 || value > 1) {
			return null;
		}
		Double d = value * factor;
		int index = d.intValue();
		if (index == factor) {
			index--;
		}
		return map.get(index);
	}

	/**
	 * @return
	 */
	private static Map<Integer, Color> initNumberToColorMap() {
		HashMap<Integer, Color> localMap = new HashMap<Integer, Color>();
		int r = LOW;
		int g = LOW;
		int b = HALF;

		// factor (increment or decrement)
		int rF = 0;
		int gF = 0;
		int bF = 1;

		int count = 0;
		// 1276 steps
		while (true) {
			localMap.put(count++, new Color(r, g, b, 150)); // last value is the shade
			if (b == HIGH) {
				gF = 1; // increment green
			}
			if (g == HIGH) {
				bF = -1; // decrement blue
				// rF = +1; // increment red
			}
			if (b == LOW) {
				rF = +1; // increment red
			}
			if (r == HIGH) {
				gF = -1; // decrement green
			}
			if (g == LOW && b == LOW) {
				rF = -1; // decrement red
			}
			if (r < HALF && g == LOW && b == LOW) {
				break; // finish
			}
			r += rF;
			g += gF;
			b += bF;
			r = rangeCheck(r);
			g = rangeCheck(g);
			b = rangeCheck(b);
		}
		initList(localMap);
		return localMap;
	}

	/**
	 * @param localMap
	 */
	private static void initList(final HashMap<Integer, Color> localMap) {
		List<Integer> list = new ArrayList<Integer>(localMap.keySet());
		Collections.sort(list);
		Integer max = list.get(list.size() - 1);
		factor = max + 1;
	}

	/**
	 * @param value
	 * @return
	 */
	private static int rangeCheck(final int value) {
		if (value > HIGH) {
			return HIGH;
		} else if (value < LOW) {
			return LOW;
		}
		return value;
	}
}