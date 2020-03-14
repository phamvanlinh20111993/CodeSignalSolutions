package ConstrucArray;

public class BitWise_Example {

	public static void main(String[] args) {
		BitWiseFunctional test = new BitWiseFunctionalImlement();
		
		System.out.println("Get hightest bit on number 23423: " + test.getHightestBit(23423) );
		
		System.out.println("Get lowest bit on number 12: " + test.getLowestBit(12) );
		
		System.out.println("Get reverse bit on number 34: " + test.reverseBitNumber(34));
		
		Integer[] reverseBit = test.reverseBitArr(34);
		System.out.println("Get reverse bit array on number: ");
		for(Integer element : reverseBit) {
			System.out.print(element + " ");
		}
		System.out.println();
		
		System.out.println("Up value one 30: " + test.ValueUpOne(30));
		
		System.out.println("Down value one 30: " + test.ValueDownOne(30));
		
		System.out.println("Check 342 is even or odd: " + test.IsEvenOrOddNumber(342));
	}

}
