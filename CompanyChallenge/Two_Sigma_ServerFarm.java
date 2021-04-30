package CompanyChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class JobIndex {
	private Integer job;
	private Integer index;

	public JobIndex(Integer job, Integer index) {
		super();
		this.job = job;
		this.index = index;
	}

	public Integer getJob() {
		return job;
	}

	public void setJob(Integer job) {
		this.job = job;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}

public class Two_Sigma_ServerFarm {
	
	static final Integer MAX_TIME_ZERO = 9;//000000000
	
	static String createZero(int amount) {
		String res = "";
		int i = 0;
		while(i < amount) {
			res += "0";
			i++;
		}
		
		return res;
	}
	
	static int[][] serverFarm2(int[] jobs, int servers) {

		List<JobIndex> jobIndex = new ArrayList<>();
		int i;
		for (i = 0; i < jobs.length; i++) {
			jobIndex.add(new JobIndex(jobs[i], i));
		}
		jobIndex = jobIndex.stream().sorted(new Comparator<JobIndex>() {
			@Override
			public int compare(JobIndex o1, JobIndex o2) {
				if (o2.getJob().equals(o1.getJob())) {
					return o2.getIndex() > o1.getIndex() ? -1 : 1;
				} else {
					return o2.getJob() > o1.getJob() ? 1 : -1;
				}
			}
		}).collect(Collectors.toList());

		List<Integer> listTmp;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int serverNum = 0;
		boolean runACircle = false;
		Map<String, Integer> minTimeAvailable = new TreeMap<>();

		for (i = 0; i < jobIndex.size(); i++) {
			// for (String key : minTimeAvailable.keySet()) {
			// System.out.print(key + "--" + serverNum);
			// }
			// System.out.println();

			if (serverNum < result.size()) {
				listTmp = result.get(serverNum);
				String getKey = null;
				for (String key : minTimeAvailable.keySet()) {
					if (minTimeAvailable.get(key) != -1) {
						getKey = key;
						serverNum = minTimeAvailable.get(key);
						break;
					}
				}

				listTmp.add(jobIndex.get(i).getIndex());
				result.set(serverNum, listTmp);
				minTimeAvailable.put(getKey, -1);
				String[] splitKey = getKey.split(";");
				int amount = MAX_TIME_ZERO
						- Integer.toString(jobIndex.get(i).getJob() + Integer.parseInt(splitKey[0])).length();
				minTimeAvailable.put(createZero(amount) + (jobIndex.get(i).getJob() + Integer.parseInt(splitKey[0]))
						+ ";" + serverNum, serverNum);
			} else {
				listTmp = new ArrayList<>();
				listTmp.add(jobIndex.get(i).getIndex());
				result.add(listTmp);

				int amount = MAX_TIME_ZERO - Integer.toString(jobIndex.get(i).getJob()).length();
				minTimeAvailable.put(createZero(amount) + Integer.toString(jobIndex.get(i).getJob()) + ";" + serverNum,
						serverNum);
			}

			if (!runACircle) {
				serverNum = (serverNum + 1) % servers;
				if (serverNum == servers - 1) {
					runACircle = true;
				}
			} else {
				for (String key : minTimeAvailable.keySet()) {
					if (minTimeAvailable.get(key) != -1) {
						serverNum = minTimeAvailable.get(key);
						break;
					}
				}
			}
		}

		i = 0;
		for (List<Integer> val : result) {
			listTmp = val;
			System.out.print(i + " ");
			for (Integer v : listTmp) {
				System.out.print(v + " ");
			}
			System.out.println();
			i++;
		}

		int[][] res = new int[servers][];
		for (i = 0; i < servers; i++) {
			if (i < result.size()) {
				res[i] = new int[result.get(i).size()];
				int j = 0;
				for (Integer val : result.get(i)) {
					res[i][j++] = val;
				}
			} else {
				res[i] = new int[0];
			}
		}

		return res;
	}
	
	static int[][] serverFarm1(int[] jobs, int servers) {

		List<JobIndex> jobIndex = new ArrayList<>();
		int i;
		for (i = 0; i < jobs.length; i++) {
			jobIndex.add(new JobIndex(jobs[i], i));
		}
		jobIndex = jobIndex.stream().sorted(new Comparator<JobIndex>() {
			@Override
			public int compare(JobIndex o1, JobIndex o2) {
				if (o2.getJob().equals(o1.getJob())) {
					return o2.getIndex() > o1.getIndex() ? -1 : 1;
				} else {
					return o2.getJob() > o1.getJob() ? 1 : -1;
				}
			}
		}).collect(Collectors.toList());

		List<Integer> listTmp;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int serverNum = 0;
		boolean runACircle = false;
		List<JobIndex> listAvailableTime = new LinkedList<>();

		for (i = 0; i < jobIndex.size(); i++) {

			if (serverNum < result.size()) {
				listTmp = result.get(serverNum);
				listTmp.add(jobIndex.get(i).getIndex());
				result.set(serverNum, listTmp);
				
				for (int j = 0; j < listAvailableTime.size(); j++) {
					if (listAvailableTime.get(j).getIndex() == serverNum) {
						listAvailableTime.set(j,
								new JobIndex(listAvailableTime.get(j).getJob() + jobIndex.get(i).getJob(), serverNum));
						break;
					}
				}
			} else {
				listTmp = new ArrayList<>();
				listTmp.add(jobIndex.get(i).getIndex());
				result.add(listTmp);
				
				listAvailableTime.add(new JobIndex(jobIndex.get(i).getJob(), serverNum));
			}

			if (!runACircle) {
				serverNum = (serverNum + 1) % servers;
				if (serverNum == servers - 1) {
					runACircle = true;
				}
			} else {
				listAvailableTime = listAvailableTime.stream().sorted(new Comparator<JobIndex>() {
					@Override
					public int compare(JobIndex o1, JobIndex o2) {
						if (o2.getJob().equals(o1.getJob())) {
							return o1.getIndex() > o2.getIndex() ? 1 : -1;
						} else {
							return o1.getJob() > o2.getJob() ? 1 : -1;
						}
					}
				}).collect(Collectors.toList());
				
				serverNum = listAvailableTime.get(0).getIndex();
			}
		}

		i = 0;
		for (List<Integer> val : result) {
			listTmp = val;
			System.out.print(i + " ");
			for (Integer v : listTmp) {
				System.out.print(v + " ");
			}
			System.out.println();
			i++;
		}

		int[][] res = new int[servers][];
		for (i = 0; i < servers; i++) {
			if (i < result.size()) {
				res[i] = new int[result.get(i).size()];
				int j = 0;
				for (Integer val : result.get(i)) {
					res[i][j++] = val;
				}
			} else {
				res[i] = new int[0];
			}
		}

		return res;
	}
	
	static int[][] serverFarm(int[] jobs, int servers) {

		List<JobIndex> jobIndex = new ArrayList<>();
		int i;
		for (i = 0; i < jobs.length; i++) {
			jobIndex.add(new JobIndex(jobs[i], i));
		}
		jobIndex = jobIndex.stream().sorted(new Comparator<JobIndex>() {
			@Override
			public int compare(JobIndex o1, JobIndex o2) {
				if (o2.getJob().equals(o1.getJob())) {
					return o2.getIndex() > o1.getIndex() ? -1 : 1;
				} else {
					return o2.getJob() > o1.getJob() ? 1 : -1;
				}
			}
		}).collect(Collectors.toList());

		List<Integer> listTmp;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int serverNum = 0;
		boolean runACircle = false;
		List<JobIndex> listAvailableTime = new ArrayList<>();

		for (i = 0; i < jobIndex.size(); i++) {
			if (serverNum < result.size()) {
				listTmp = result.get(serverNum);
				listTmp.add(jobIndex.get(i).getIndex());
				result.set(serverNum, listTmp);	
				for (int j = listAvailableTime.size() - 1; j >= 0 ; j--) {
					if (listAvailableTime.get(j).getIndex() == serverNum) {
						listAvailableTime.set(j,
								new JobIndex(listAvailableTime.get(j).getJob() + jobIndex.get(i).getJob(), serverNum));
					//	System.out.println("wtf " + serverNum + "  " + listAvailableTime.get(j).getJob());
						break;
					}
				}
			}else {
				listTmp = new ArrayList<>();
				listTmp.add(jobIndex.get(i).getIndex());
				result.add(listTmp);
				listAvailableTime.add(new JobIndex(jobIndex.get(i).getJob(), serverNum));
			}
			
			if (!runACircle) {
				serverNum = (serverNum + 1) % servers;
				if (serverNum == servers - 1) {
					runACircle = true;
				}
			} else {
				int maxJob = Integer.MAX_VALUE;
				int selectServerNum = serverNum;
//				System.out.println("value");
////				for(JobIndex job : listAvailableTime) {
////					System.out.println(job.getIndex() + "  " + job.getJob());
////				}
				
				for (int j = listAvailableTime.size()-1; j >= 0 ; j--) {
					if(listAvailableTime.get(j).getJob() < maxJob) {
						maxJob = listAvailableTime.get(j).getJob();
						selectServerNum = listAvailableTime.get(j).getIndex();
					}else if(listAvailableTime.get(j).getJob() == maxJob) {
						if(listAvailableTime.get(j).getIndex() < selectServerNum) {
							selectServerNum = listAvailableTime.get(j).getIndex();
						}
					}
				}
			//	System.out.println("val " + selectServerNum);
				serverNum = selectServerNum;
			}
		}

		i = 0;
		for (List<Integer> val : result) {
			listTmp = val;
			System.out.print(i + " ");
			for (Integer v : listTmp) {
				System.out.print(v + " ");
			}
			System.out.println();
			i++;
		}

		int[][] res = new int[servers][];
		for (i = 0; i < servers; i++) {
			if (i < result.size()) {
				res[i] = new int[result.get(i).size()];
				int j = 0;
				for (Integer val : result.get(i)) {
					res[i][j++] = val;
				}
			} else {
				res[i] = new int[0];
			}
		}

		return res;
	}

	public static void main(String[] args) {
		/**
		 * [[1],          
		 * [0, 4],          
		 * [2, 3]]
		 */
		System.out.println("############## test 1 ###############");
		int[] jobs = { 15, 30, 15, 5, 10 };
		int servers = 3;
		serverFarm(jobs, servers);
		/**
		 * [[5], [0], [2], [3], [4], [1], [], []]
		 */
		System.out.println("############## test 2 ###############");
		int[] jobs1 = { 15, 2, 14, 14, 14, 258 };
		int servers1 = 8;
		serverFarm(jobs1, servers1);

		System.out.println("############## test 3 ###############");
		int[] jobs2 = {};
		int servers2 = 8;
		serverFarm(jobs2, servers2);

		/**
		 * [[0,2,4,6,8,10,12], 
		 *  [1,3,5,7,9,11]]
		 */
		System.out.println("############## test 4 ###############");
		int[] jobs3 = { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
		int servers3 = 2;
		serverFarm(jobs3, servers3);

		/**
		 * [[16,26,22,0,40,10], 
		 * [30,2,35,49,39], 
		 * [34,3,38,5,33], 
		 * [36,13,47,15,46],
		 * [11,8,4,23,7,48], 
		 * [44,17,24,19,42,9], 
		 * [6,41,29,21,43,25], 
		 * [14,45,18,1,37,12],
		 * [31,32,27,28,20]]
		 */
		System.out.println("############## test 5 ###############");
		int[] jobs4 = { 8, 7, 15, 15, 13, 6, 18, 4, 16, 1, 2, 19, 2, 15, 18, 6, 20, 16, 10, 7, 3, 7, 9, 7, 12, 1, 16,
				15, 7, 12, 20, 17, 17, 4, 20, 15, 20, 6, 15, 3, 5, 17, 5, 5, 19, 17, 4, 15, 2, 7 };
		int servers4 = 9;
		serverFarm(jobs4, servers4);
		
		System.out.println("############## test 6 ###############");
		int[] jobs5 = { 8, 7, 15, 15, 13, 6, 18, 4, 16, 1, 2, 19, 2, 15, 18, 6, 20, 16, 10, 7, 3, 7, 9, 7, 12, 1, 16,
				15, 7, 12, 20, 17, 17, 4, 20, 15, 20, 6, 15, 3, 5, 17, 5, 5, 19, 17, 4, 15, 2, 7 };
		int servers5 = 1;
		serverFarm(jobs5, servers5);
	}

}
