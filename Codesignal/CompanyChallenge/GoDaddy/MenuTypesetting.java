package Codesignal.CompanyChallenge.GoDaddy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author PhamLinh
 *
 *         Ref:
 *         https://app.codesignal.com/company-challenges/godaddy/SbhzgFSahMqFxPiSH
 *
 *         In GoDaddy’s Get Found product, small business customers can input
 *         their price list or menu and have it distributed to Yelp, Google and
 *         other online listing sites. As a convenience, Get Found customers can
 *         also get their menus typeset onto letter paper so it can be printed
 *         for in-store use.
 * 
 *         A menu is composed of an array of items that have distinct labels and
 *         are of type either "Section Header" or "Menu Item". The typesetting
 *         algorithm must bucket these individual section headers and menu items
 *         into multiple pages while avoiding abnormalities. Two of the most
 *         common abnormalities are widowed menu items and orphaned section
 *         headers. The former occurs when the last menu item in a section
 *         appears on a new page while the latter occurs when a section header
 *         appears as the last items on the bottom of a page without any menu
 *         items underneath it on the same page.
 * 
 *         Your job is to write a typesetting algorithm that splits up a given
 *         menu into multiple pages while pushing menu items and section headers
 *         to the next page whenever a widowed menu item or orphaned section
 *         header occurs. Each page can contain no more than numberOfItems items
 *         on it.
 * 
 *         Example
 * 
 *         For
 * 
 *         menu = [["Breakfast", "Section Header"], ["Eggs and Toast", "Menu
 *         Item"], ["Waffles", "Menu Item"], ["Orange juice", "Menu Item"],
 *         ["Dinner", "Section Header"], ["Steak", "Menu Item"], ["Merlot",
 *         "Menu Item"]] and numberOfItems = 3, the output should be
 * 
 *         solution(menu, numberOfItems) = [["Breakfast", "Eggs and Toast"],
 *         ["Waffles", "Orange juice"], ["Dinner", "Steak", "Merlot"]] There can
 *         be no more than 3 items on each page so to avoid abnormalities we
 *         end-up with a 3 page menu. It's impossible to add "Waffles" on the
 *         first page, since it will leave "Orange juice" orphaned on the second
 *         page. It's also impossible to add "Dinner" on the second page, since
 *         it will make it widowed.
 * 
 *         Check out the image below for better understanding:
 * 
 * 
 * 
 *         Input/Output
 * 
 *         [execution time limit] 3 seconds (java)
 * 
 *         [input] array.array.string menu
 * 
 *         Array of items given in the same order as they should appear in the
 *         printed menu. "Section Header" type items mark the start of sections
 *         and each section has at least one "Menu Item" in it. Each item is
 *         represented as an array of two elements. menu[i][0] is the label of
 *         the ith object, and menu[i][1] is its type. It is guaranteed that the
 *         first item has "Section Header" type.
 * 
 *         Guaranteed constraints: 4 ≤ menu.length ≤ 15, 2 ≤ menu[i][j].length ≤
 *         30.
 * 
 *         [input] integer numberOfItems
 * 
 *         The maximum number of items on each page.
 * 
 *         Guaranteed constraints: 3 ≤ numberOfItems ≤ 15.
 * 
 *         [output] array.array.string
 * 
 *         The list of items' labels by page.
 */
public class MenuTypesetting {

	/**
	 * 
	 * - Requirement: Array of items given in the same order as they should appear
	 * in the printed menu. "Section Header" type items mark the start of sections
	 * and each section has at least one "Menu Item" in it.
	 * 
	 * ) widowed menu item : the last menu item in a section appears on a new page
	 * 
	 * ) orphaned section header: a section header appears as the last items on the
	 * bottom of a page without any menu items underneath it on the same page
	 * 
	 **/

	static String[][] solution(String[][] menu, int numberOfItems) {

		Map<String, Integer> categoryMenu = new LinkedHashMap<>();
		//
		int ind = 0;
		String key = "";
		while (ind < menu.length) {
			if (menu[ind][1].equals("Section Header")) {
				key = menu[ind][0];
				categoryMenu.put(key, 0);
			}
			Integer menuItemsC = categoryMenu.get(key);
			categoryMenu.put(key, menuItemsC + 1);
			ind++;
		}

		//
		List<List<String>> res = new ArrayList<>();
		List<String> divides = new ArrayList<>();
		int amountItems = 0;
		for (ind = 0; ind < menu.length; ind++) {
			String item = menu[ind][0];
			String type = menu[ind][1];

			if (type.equals("Section Header")) {
				amountItems = categoryMenu.get(item);
			}
			// normal condition
			if (divides.size() == numberOfItems) {
				res.add(divides);
				divides = new ArrayList<>();
			}
			// avoid "widowed menu items" abnormalities
			if (amountItems == 2 && divides.size() == numberOfItems - 1) {
				res.add(divides);
				divides = new ArrayList<>();
			}
			// avoid "orphaned section headers" abnormalities
			if (type.equals("Section Header") && divides.size() == numberOfItems - 1) {
				res.add(divides);
				divides = new ArrayList<>();
			}

			divides.add(item);
			amountItems--;
		}

		if (divides.size() > 0) {
			res.add(divides);
		}

		//
		return res.stream().map(l -> l.stream().toArray(String[]::new)).toArray(String[][]::new);
	}

}
