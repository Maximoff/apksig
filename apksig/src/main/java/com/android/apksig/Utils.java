package com.android.apksig;
import java.nio.charset.Charset;

public class Utils {
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	
	public static int toIntExact(long value) {
		if ((int) value != value) {
			throw new ArithmeticException("Integer overflow (" + value + ")!");
		}
		return (int) value;
	}
}
