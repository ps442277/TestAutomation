package Reusables;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.Range;

/*
 * Rest assured reusable funtions used for Mathematical calculation
 */
public class RestReuse {
	/*
	 * The method humidity_range helps to compare humidity from UI page and API output using Range class.
	 */
	public Range<Double> humidity_range(double d, int specified_range) {
		Range<Double> humidity_range = Range.between(d-specified_range, d+specified_range);
		return humidity_range;
	}
	/*
	 * The method c_to_k_range method convert the Celcius into Kelvin using standard formulae
	 * This also compare the value using Range class to compare in specified range
	 */
	public Range<Double> c_to_k_range(double d, int specified_range) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		double trim_down = Double.parseDouble(df.format(d+273.15));
		Range<Double> c_to_k_range = Range.between(trim_down-specified_range, trim_down+specified_range);
		return c_to_k_range;
	}
	/*
	 * The method f_to_k_range method convert the Fahrenheit into Kelvin using standard formulae
	 * This also compare the value using Range class to compare in specified range
	 */
	public Range<Double> f_to_k_range(double d, int specified_range) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		double trim_down = Double.parseDouble(df.format((d+459.67)*(0.56)));
		Range<Double> f_to_k_range = Range.between(trim_down-specified_range, trim_down+specified_range);
		return f_to_k_range;
	}
}
