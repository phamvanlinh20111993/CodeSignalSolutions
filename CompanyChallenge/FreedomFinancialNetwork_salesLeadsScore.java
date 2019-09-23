package CompanyChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SalesLeadsScore {

	private String name;
	private int time;
	private int netValue;

	private float lead;

	/**
	 * @param name
	 * @param time
	 * @param netValue
	 */
	public SalesLeadsScore(String name, int time, int netValue, float lead) {
		super();
		this.name = name;
		this.time = time;
		this.netValue = netValue;
		this.lead = lead;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the netValue
	 */
	public int getNetValue() {
		return netValue;
	}

	/**
	 * @param netValue
	 *            the netValue to set
	 */
	public void setNetValue(int netValue) {
		this.netValue = netValue;
	}

	/**
	 * @return the lead
	 */
	public float getLead() {
		return lead;
	}

	/**
	 * @param lead
	 *            the lead to set
	 */
	public void setLead(float lead) {
		this.lead = lead;
	}

}

public class FreedomFinancialNetwork_salesLeadsScore {
	static String[] salesLeadsScore(String[] names, int[] time, int[] netValue, boolean[] isOnVacation) {

		List<SalesLeadsScore> salesLeadsSc = new ArrayList<>();
		for (int index = 0; index < names.length; index++) {
			if (!isOnVacation[index]) {
				salesLeadsSc.add(new SalesLeadsScore(names[index], time[index], netValue[index],
						netValue[index] * time[index] / (float) 365));
			}
		}
		salesLeadsSc.sort(new Comparator<SalesLeadsScore>() {
			@Override
			public int compare(SalesLeadsScore o1, SalesLeadsScore o2) {
				if (o1.getLead() > o2.getLead()) {
					return -1;
				} else if (o1.getLead() < o2.getLead()) {
					return 1;
				} else {
					if (o1.getTime() > o2.getTime()) {
						return -1;
					} else if (o1.getTime() < o2.getTime()) {
						return 1;
					} else {
						return o1.getName().compareToIgnoreCase(o2.getName());
					}
				}
			}

		});

		List<String> response = new ArrayList<>();
		for(SalesLeadsScore score : salesLeadsSc) {
			response.add(score.getName());
		}

		return response.toArray( new String[response.size()]);
	}

	public static void main(String[] args) {

		String[] names = { "lead1", "lead2", "lead3", "lead4", "lead5" };
		int[] time = { 250, 300, 250, 260, 310 };
		int[] netValue = { 1000, 800, 1100, 1200, 1000 };
		boolean[] isOnVacation = { true, false, true, false, false };
		System.out.println("####################### test 1 ####################");
		salesLeadsScore(names, time, netValue, isOnVacation);
	}

}
