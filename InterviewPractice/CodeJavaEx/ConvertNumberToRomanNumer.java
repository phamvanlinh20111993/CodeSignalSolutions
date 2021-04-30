package InterviewPractice.CodeJavaEx;

import java.util.ArrayList;
import java.util.Arrays;

import javax.activation.UnsupportedDataTypeException;
/**
 * 
 * @author PhamVanLinh
 *
 */
class ConvertNumberToRomanNumer implements ConvertDashBoardToRomanNumber {
	private static final Long MAX_RANGE_ROMAN_NUMBER = 3999999999L;
	private static final Long MIN_RANGE_ROMAN_NUMBER = 1L;
	private static ArrayList<KeyPairValue> LIST_CONVERT_BASE = 
			new ArrayList<>(Arrays.asList(new KeyPairValue(1, "I"),
			new KeyPairValue(5, "V"), 
			new KeyPairValue(10, "X"), 
			new KeyPairValue(50, "L"), 
			new KeyPairValue(100, "C"),
			new KeyPairValue(500, "D"), 
			new KeyPairValue(1000, "M"), 
			// Convention wiki
			new KeyPairValue(5000, "V-"),
			new KeyPairValue(10000, "X-"), 
			new KeyPairValue(50000, "L-"), 
			new KeyPairValue(100000, "C-"),
			new KeyPairValue(500000, "D-"), 
			new KeyPairValue(1000000, "M-"),
			// Convention wiki
			new KeyPairValue(5000000, "V_"), 
			new KeyPairValue(10000000, "X_"), 
			new KeyPairValue(50000000, "L_"),
			new KeyPairValue(100000000, "C_"), 
			new KeyPairValue(500000000, "D_"), 
			new KeyPairValue(1000000000, "M_")));

	/**
	 * 
	 * @param number
	 * @return
	 */
	private static boolean checkIntegerNumber(String number) {
		return number.trim().matches("^\\d+$");
	}

	/**
	 * Allow max number is 3.999.999.999 representation in roman number
	 * 
	 * @param number
	 * @return
	 */
	private static boolean checkRangeNumber(String number) {
		return number.matches("^0*(?:[1-9]|\\d{2,8}|[1-3]\\d{8})$");
	}

	/**
	 * 
	 * @param number
	 * @return
	 * @throws UnsupportedDataTypeException
	 * @throws OutOfRangeException
	 */
	private static <T> String convertToNumber(T number) throws UnsupportedDataTypeException, OutOfRangeException {
		String tmp;
		if (number instanceof Integer) {
			tmp = Integer.toString((Integer) number);
		} else if (number instanceof Long) {
			tmp = Long.toString((Integer) number);
		} else if (number instanceof String) {
			if (number instanceof String && !checkIntegerNumber((String) number)) {
				throw new NumberFormatException("Is not Integer Number.");
			}
			tmp = (String) number;
		} else {
			throw new UnsupportedDataTypeException("Not support this type.");
		}

		if (!checkRangeNumber(tmp)) {
			throw new OutOfRangeException(MIN_RANGE_ROMAN_NUMBER, MAX_RANGE_ROMAN_NUMBER, "Number not in range");
		}

		return tmp.trim();
	}

	/**
	 * 
	 * @param number
	 * @param index
	 * @return
	 */
	private String calRomanNumber(Long number, int index) {
		int i = index;
		String value = "";

		while (i > 0 && number < LIST_CONVERT_BASE.get(i).getKey()) {
			i--;
		}
		/**
		 * check value if number is start with 5
		 */
		Integer positionVal = i > 0 && LIST_CONVERT_BASE.get(i).getKey() / LIST_CONVERT_BASE.get(i - 1).getKey() == 5
				? LIST_CONVERT_BASE.get(i - 1).getKey()
				: LIST_CONVERT_BASE.get(i).getKey();
		Long spitNumber = number / positionVal | 0;

		if (spitNumber == 9) {
			value = LIST_CONVERT_BASE.get(i - 1).getValue() + LIST_CONVERT_BASE.get(i + 1).getValue();
		} else if (spitNumber >= 5 && spitNumber <= 8) {
			value = LIST_CONVERT_BASE.get(i).getValue();
			for (int j = 0; j < spitNumber - 5; j++) {
				value += LIST_CONVERT_BASE.get(i - 1).getValue();
			}
		} else if (spitNumber == 4) {
			value = LIST_CONVERT_BASE.get(i).getValue() + LIST_CONVERT_BASE.get(i + 1).getValue();
		} else {
			for (int j = 0; j < spitNumber; j++) {
				value += LIST_CONVERT_BASE.get(i).getValue();
			}
		}
		number -= spitNumber * positionVal;

		return i == 0  ? value : value + calRomanNumber(number, i);
	}

	/**
	 * 
	 */
	@Override
	public <T> String converNumberToRomanNumber(T number) {

		try {
			String num = convertToNumber(number);
			num = calRomanNumber(Long.parseLong(num), LIST_CONVERT_BASE.size() - 1);
			return num;
		} catch (UnsupportedDataTypeException e) {
			System.out.println(e.getMessage());
		} catch (OutOfRangeException e1) {
			System.out.println(e1.getMessage());
		}

		return null;
	}

}
