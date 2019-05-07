package CodeJavaEx;

import javax.activation.UnsupportedDataTypeException;
/**
 * 
 * @author PhamVanLinh
 *
 */
class ConvertNumberToBinaryNumber implements ConvertDashboardNumberToBase {
	static final Integer IEEE744_STANDARD_SINGLE = (int) Math.pow(2, 23);
	static final Long IEEE744_STANDARD_DOUBLE = (long) Math.pow(2, 53);

	/**
	 * Check a string is number or not
	 * 
	 * @param number
	 * @return
	 */
	private boolean checkNumber(String number) {
		return number.trim().matches("^-?\\d+(?:\\.\\d+)?$");
	}

	/**
	 * Get range of number in binary bit. Ex: 120 is represent by 8 bit in binary,
	 * 65000 is represent by 16 bit in binary
	 * 
	 * @param number
	 * @return
	 */
	private Integer getMaxRangeNumberBytes(String number) throws IllegalArgumentException {
		Integer range = 0;
		Long num = Long.parseLong(number), value = 0L;
		Boolean isSigned = num < 0L ? true : false;
		num = Math.abs(num);
		
		while (value < num && range <= 64) {
			range += 8;
			value = (long) Math.pow(2, range - 1) + (isSigned ? 0L : -1L);
		}
		
		return range;
	}

	/**
	 * 
	 * @param number
	 * @return An array string [0] is Mark, [1] is Raw parts [2] is Decimal parts.
	 *         Ex: -1.323 => [-, 1, 323]
	 * @throws UnsupportedDataTypeException
	 */
	private <T> String[] convertNumberToArray(T number) throws UnsupportedDataTypeException {
		String[] res = new String[3];
		String tmp;
		if (number instanceof Integer) {
			tmp = Integer.toString((Integer) number);
		} else if (number instanceof Long) {
			tmp = Long.toString((Long) number);
		} else if (number instanceof Float) {
			tmp = Float.toString((Float) number);
		} else if (number instanceof Double) {
			tmp = Double.toString((Double) number);
		} else if (number instanceof String) {
			if (number instanceof String && !checkNumber((String) number)) {
				throw new NumberFormatException("Is not Number.");
			}
			tmp = (String) number;
		} else {
			throw new UnsupportedDataTypeException("Not support this type.");
		}

		tmp = tmp.trim();
		if (tmp.charAt(0) == '-') {
			res[0] = "-";
			tmp = tmp.substring(1, tmp.length());
		}
		res[1] = tmp.split("\\.")[0];
		res[2] = tmp.split("\\.").length > 1 ? tmp.split("\\.")[1] : "";

		return res;
	}

	/**
	 * Ex: 12.323123 in that, 12 is raw path, 323123 is decimal path
	 * 
	 * @param number
	 * @param type
	 * @return
	 */
	private String convertDecimalPartToBase(String number, String type) {
		number = "0.".concat(number);
		Double num = Double.parseDouble(number), value, tmp, minus = 0D;
		Integer[] res;
		Integer base;
		/**
		 * Real numbers are represented as IEEE 754 with single precision (represented
		 * by 32 bits - 1 sign bit - 8 bit caps - 23 bits odd value)
		 */
		if (type == "1") {
			value = num * IEEE744_STANDARD_SINGLE;
			base = 22;
			res = new Integer[23];
			while (base >= 0) {
				tmp = Math.pow(2, base);
				minus += tmp;
				if (value - minus >= 0) {
					res[base] = 1;
				} else {
					res[base] = 0;
					minus -= tmp;
				}
				base--;
			}
		} else {
			/**
			 * Real numbers are represented as IEEE 754 with double precision (represented
			 * by 64 bits - 1 sign bit - 11 bit caps - 52 bits odd value)
			 */
			value = num * IEEE744_STANDARD_DOUBLE;
			base = 52;
			res = new Integer[53];
			while (base >= 0) {
				tmp = Math.pow(2, base);
				minus += tmp;
				if (value - minus >= 0) {
					res[base] = 1;
				} else {
					minus -= tmp;
					res[base] = 0;
				}
				base--;
			}
		}
		StringBuffer resp = new StringBuffer();
		for (int i = res.length - 1; i >= 0; i--) {
			resp.append(res[i]);
		}

		return resp.toString();
	}

	/**
	 * Ex: 12.323123 in that, 12 is raw path, 323123 is decimal path
	 * 
	 * @param number
	 * @param range
	 * @param base
	 * @return
	 * @throws IllegalArgumentException
	 */
	private String convertRawPartToBase(String number, Integer range, Integer base) throws IllegalArgumentException {
		Long num = Long.parseLong(number);
		StringBuilder res = new StringBuilder(num == 0L ? "0" : "");

		while (num > 0) {
			res.append(Long.toString(num % base));
			num = num / base;
		}
		for (int i = 0, length = res.length(); i < range - length; i++) {
			res.append("0");
		}
		return res.reverse().toString();

	}

	/**
	 * Presentation number in Base -2. In base -2 we invert the bit and add 1 to the
	 * result Only use this when number is integer
	 * 
	 * @param number
	 * @return
	 */
	private String signedNumgerPresentation(String number) {
		StringBuffer res = new StringBuffer();
		// invert the bit
		for (int i = 0; i < number.length(); i++) {
			res.append(number.charAt(i) == '1' ? "0" : "1");
		}
		// add 1 to the result
		int length = res.length() - 1;
		if(res.charAt(length) == '0') {
			res.setCharAt(length, '1');
		}else {
			while(length > 0 && res.charAt(length) == '1') {
				res.setCharAt(length, '0');
				length --;
			}
			res.setCharAt(length, '1');
		}

		return res.toString();
	}

	/**
	 * 
	 */
	@Override
	public <T> String converNumberToBaseBinary(T number) {
		try {
			String[] numberArr = convertNumberToArray(number);
			//get number is represent by amount of bit
			Integer range = getMaxRangeNumberBytes(numberArr[0] != null ? "-" + numberArr[1] : numberArr[1]);
			String rawPathString = convertRawPartToBase(numberArr[1], range, 2);
			String decimalPathString = "";
			
			if (!numberArr[2].isEmpty()) {
				decimalPathString = convertDecimalPartToBase(numberArr[2], "2");
			}
			// when number is signed integer
			if (numberArr[0] != null) {
				// when number is integer number we represent by base -2. If not we using bit
				// signed
				if (numberArr[2].isEmpty()) {
					rawPathString =  signedNumgerPresentation(rawPathString);
				} else {
					rawPathString = rawPathString.charAt(0) == '0'
							? "1" + rawPathString.substring(1, rawPathString.length())
							: rawPathString;
				}
			}

			return rawPathString + (!decimalPathString.isEmpty() ? ".".concat(decimalPathString) : "");
		} catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		} catch (UnsupportedDataTypeException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
}
