package CompanyChallenge.FreedomFinancialNetwork;

import java.util.Arrays;

class CoverDebt implements Comparable<CoverDebt> {
	private int debt;
	private int interest;

	/**
	 * @param debt
	 * @param interest
	 */
	public CoverDebt(int debt, int interest) {
		super();
		this.debt = debt;
		this.interest = interest;
	}

	/**
	 * @return the debt
	 */
	public int getDebt() {
		return debt;
	}

	/**
	 * @param debt
	 *            the debt to set
	 */
	public void setDebt(int debt) {
		this.debt = debt;
	}

	/**
	 * @return the interest
	 */
	public int getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            the interest to set
	 */
	public void setInterest(int interest) {
		this.interest = interest;
	}

	@Override
	public int compareTo(CoverDebt o) {
		return this.interest > o.getInterest() ? -1 : this.interest == o.getInterest() ? 0 : 1;
	}

}

public class FreedomFinancialNetwork_coverDebts {

	static double coverDebts(int s, int[] debts, int[] interests) {
		CoverDebt[] coverDebtArr = new CoverDebt[interests.length];
		double response = 0, salary10 = s*0.1;
		
		for (int index = 0; index < debts.length; index++)
			coverDebtArr[index] = new CoverDebt(debts[index], interests[index]);
		Arrays.sort(coverDebtArr);
		
		for(int index = 0; index <= debts.length;) {
			if(debts[index] - salary10 > 0) {
				debts[index] -= salary10;
			}else {
				index++;
			}
		}

		return response;
	}

	public static void main(String[] args) {
		System.out.println("################# test 1 #####################");
		int s = 50;
		int[] debts = { 2, 2, 5 }, interests = { 200, 100, 150 };
		coverDebts(s, debts, interests);

		System.out.println("################# test 2 #####################");
		int s1 = 40;
		int[] debts1 = { 2, 2, 5 }, interests1 = { 75, 25, 25 };
		coverDebts(s1, debts1, interests1);
	}

}
