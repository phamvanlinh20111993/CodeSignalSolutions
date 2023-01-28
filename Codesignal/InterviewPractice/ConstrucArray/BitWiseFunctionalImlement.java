package Codesignal.InterviewPractice.ConstrucArray;

import java.util.ArrayList;
import java.util.List;

public class BitWiseFunctionalImlement implements BitWiseFunctional {

	@Override
	public Integer getHightestBit(Integer number) {
		int tmp = number, pos = 0, location = 0;
		while (tmp > 0) {
			location = (tmp & 1) == 1 ? pos : location;
			tmp >>= 1;
			pos++;
		}
		return 2 << location-1;
	}

	public Integer getLowestBit(Integer number) {

		return number & -number;
	}

	@Override
	public Integer[] reverseBitArr(Integer number) {
		List<Integer> reverseBitNumberArr = new ArrayList<>();
		while(number > 0) {
			reverseBitNumberArr.add((number & 1)); 
			number >>= 1;
		}
		Integer [] res = new Integer[reverseBitNumberArr.size()];
		for(int i = 0; i < res.length; i++) {
			res[i] = reverseBitNumberArr.get(i);
		}
		return res;
	}

	@Override
	public Integer reverseBitNumber(Integer number) {

		int reverseBitNumber = 0;
		while(number > 0) {
			reverseBitNumber <<= 1;
			reverseBitNumber = reverseBitNumber | (number & 1);
			number >>= 1;
		}
		return reverseBitNumber;
	}

	@Override
	public Integer ValueUpOne(Integer number) {
		return -~number;
	}

	@Override
	public Integer ValueDownOne(Integer number) {
		return ~-number;
	}

	@Override
	public String IsEvenOrOddNumber(Integer number) {
		return (number & 1) == 1 ? "Odd" : "Even";
	}

	@Override
	public Integer Add(Integer numberA, Integer numberB) {
		Integer res = 0;
		return res;
	}

	@Override
	public Integer Minus(Integer numberA, Integer numberB) {
	
		Integer res = 0;
		return res;
	}
	
	
}
