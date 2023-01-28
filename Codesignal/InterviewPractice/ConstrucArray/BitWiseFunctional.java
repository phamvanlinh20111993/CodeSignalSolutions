/**
 * 
 */
package Codesignal.InterviewPractice.ConstrucArray;

/**
 * @author PhamVanLinh
 *
 */
public interface BitWiseFunctional {
	/**
	 * 
	 * @param number
	 * @return hightest bit 1 of number. Ex: 16 = 10000 => bit 1 hightest is 2^4 = 16
	 */
	public Integer getHightestBit(Integer number);

	/**
	 * 
	 * @param number
	 * @return lowest bit 1 of number. Ex: 16 = 10000 => bit 1 lowest is 2^4 = 16
	 */
	public Integer getLowestBit(Integer number);

	/**
	 * 
	 * @param number
	 * @return reverse
	 */
	public Integer[] reverseBitArr(Integer number);

	/**
	 * 
	 * @param number
	 * @return
	 */
	public Integer reverseBitNumber(Integer number);
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public Integer ValueUpOne(Integer number);
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public Integer ValueDownOne(Integer number);
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public String IsEvenOrOddNumber(Integer number);
	
	/**
	 * 
	 * @param numberA
	 * @param numberB
	 * @return
	 */
	public Integer Add(Integer numberA, Integer numberB);
	
	/**
	 * 
	 * @param numberA
	 * @param numberB
	 * @return
	 */
	public Integer Minus(Integer numberA, Integer numberB);
}
