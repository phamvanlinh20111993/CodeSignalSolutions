package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * url: https://leetcode.com/problems/count-mentions-per-user/description/?envType=daily-question&envId=2025-12-12
 * 
 * You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.

Each events[i] can be either of the following two types:

Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
This event indicates that a set of users was mentioned in a message at timestampi.
The mentions_stringi string can contain one of the following tokens:
id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple ids separated by a single whitespace and may contain duplicates. This can mention even the offline users.
ALL: mentions all users.
HERE: mentions all online users.
Offline Event: ["OFFLINE", "timestampi", "idi"]
This event indicates that the user idi had become offline at timestampi for 60 time units. The user will automatically be online again at time timestampi + 60.
Return an array mentions where mentions[i] represents the number of mentions the user with id i has across all MESSAGE events.

All users are initially online, and if a user goes offline or comes back online, their status change is processed before handling any message event that occurs at the same timestamp.

Note that a user can be mentioned multiple times in a single message event, and each mention should be counted separately.

 

Example 1:

Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]

Output: [2,2]

Explanation:

Initially, all users are online.

At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]

At timestamp 11, id0 goes offline.

At timestamp 71, id0 comes back online and "HERE" is mentioned. mentions = [2,2]

Example 2:

Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]

Output: [2,2]

Explanation:

Initially, all users are online.

At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]

At timestamp 11, id0 goes offline.

At timestamp 12, "ALL" is mentioned. This includes offline users, so both id0 and id1 are mentioned. mentions = [2,2]

Example 3:

Input: numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]

Output: [0,1]

Explanation:

Initially, all users are online.

At timestamp 10, id0 goes offline.

At timestamp 12, "HERE" is mentioned. Because id0 is still offline, they will not be mentioned. mentions = [0,1]

 

Constraints:

1 <= numberOfUsers <= 100
1 <= events.length <= 100
events[i].length == 3
events[i][0] will be one of MESSAGE or OFFLINE.
1 <= int(events[i][1]) <= 105
The number of id<number> mentions in any "MESSAGE" event is between 1 and 100.
0 <= <number> <= numberOfUsers - 1
It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs.
 */

class MentionPerUser {

	private boolean isOnline;
	public int mentionAmount;
	public int timestamp;

	public MentionPerUser(boolean isOnline, int mentionAmount, int timestamp) {
		this.isOnline = isOnline;
		this.mentionAmount = mentionAmount;
		this.timestamp = timestamp;
	}

	public boolean getIsOnline(int atTimeStamp) {
		if (isOnline)
			return true;

		if (atTimeStamp - this.timestamp >= 60) {
			this.isOnline = true;
			// update timestamp
			this.timestamp = atTimeStamp;
			return true;
		}

		return false;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}

public class CountMentionsPerUser_3433 {

	public List<Integer> getUserIds(String mentionsString) {
		List<Integer> res = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(mentionsString);
		while (matcher.find()) {
			res.add(Integer.valueOf(matcher.group()));
		}

		return res;
	}

	public int[] countMentions(int numberOfUsers, List<List<String>> events) {
		events = events.stream().sorted((e1, e2) -> {
			int t1 = Integer.valueOf(e1.get(1));
			int t2 = Integer.valueOf(e2.get(1));
			if (t1 == t2) {
				return e2.get(0).charAt(0) - e1.get(0).charAt(0);
			}
			return t1 - t2;
		}).collect(Collectors.toList());

		MentionPerUser[] metionPerUsers = new MentionPerUser[numberOfUsers];
		for (int ind = 0; ind < numberOfUsers; ind++) {
			metionPerUsers[ind] = new MentionPerUser(true, 0, 0);
		}

		int allOnline = 0;

		for (List<String> event : events) {
			Integer timestamp = Integer.valueOf(event.get(1));
			if (event.get(0).equals("OFFLINE")) {
				Integer userId = Integer.valueOf(event.get(2));
				metionPerUsers[userId].setIsOnline(false);
				metionPerUsers[userId].timestamp = timestamp;
				continue;
			}
			
			// handle message type MESSAGE
			String mentionsString = event.get(2);
			if (mentionsString.equals("ALL")) {
				allOnline++;
			} else if (mentionsString.equals("HERE")) {
				for (int ind = 0; ind < numberOfUsers; ind++) {
					if (metionPerUsers[ind].getIsOnline(timestamp)) {
						metionPerUsers[ind].mentionAmount++;
					}
				}
			} else {
				List<Integer> userIds = getUserIds(mentionsString);
				for (int ind = 0; ind < userIds.size(); ind++) {
					metionPerUsers[userIds.get(ind)].mentionAmount++;
				}
			}
		}

		int[] mention = new int[numberOfUsers];
		for (int ind = 0; ind < numberOfUsers; ind++) {
			mention[ind] = metionPerUsers[ind].mentionAmount + allOnline;
		}

		return mention;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
