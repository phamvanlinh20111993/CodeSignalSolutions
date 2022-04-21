
"""
You're creating your own website for Harry Potter fans. As you all believe in magic, you're waiting for your personal letter from Hogwarts, that will definitely arrive to you some day with a magnificent owl. To be prepared for this exciting moment you decided to embed a calendar to your website on which you will mark the days when Hogwarts owls can bring letters.

Given a month and a year, return an HTML table representing the desired calendar. Follow the same format as provided in the example but omit all whitespace characters (i.e. tabs, newlines and whitespaces) where it is possible, because you care about space efficiency.

Example

For month = 11 and year = 2016, the output should be

solution(month, year) =
"<table border="0" cellpadding="0" cellspacing="0" class="month">
  <tr>
    <th colspan="7" class="month">November 2016</th>
  </tr>
  <tr>
    <th class="mon">Mon</th>
    <th class="tue">Tue</th>
    <th class="wed">Wed</th>
    <th class="thu">Thu</th>
    <th class="fri">Fri</th>
    <th class="sat">Sat</th>
    <th class="sun">Sun</th>
  </tr>
  <tr>
    <td class="noday">&nbsp;</td>
    <td class="tue">1</td>
    <td class="wed">2</td>
    <td class="thu">3</td>
    <td class="fri">4</td>
    <td class="sat">5</td>
    <td class="sun">6</td>
  </tr>
  <tr>
    <td class="mon">7</td>
    <td class="tue">8</td>
    <td class="wed">9</td>
    <td class="thu">10</td>
    <td class="fri">11</td>
    <td class="sat">12</td>
    <td class="sun">13</td>
  </tr>
  <tr>
    <td class="mon">14</td>
    <td class="tue">15</td>
    <td class="wed">16</td>
    <td class="thu">17</td>
    <td class="fri">18</td>
    <td class="sat">19</td>
    <td class="sun">20</td>
  </tr>
  <tr>
    <td class="mon">21</td>
    <td class="tue">22</td>
    <td class="wed">23</td>
    <td class="thu">24</td>
    <td class="fri">25</td>
    <td class="sat">26</td>
    <td class="sun">27</td>
  </tr>
  <tr>
    <td class="mon">28</td>
    <td class="tue">29</td>
    <td class="wed">30</td>
    <td class="noday">&nbsp;</td>
    <td class="noday">&nbsp;</td>
    <td class="noday">&nbsp;</td>
    <td class="noday">&nbsp;</td>
  </tr>
</table>"
Here is how this calendar would look on your website:

November 2016
Mon	Tue	Wed	Thu	Fri	Sat	Sun
 	1	2	3	4	5	6
7	8	9	10	11	12	13
14	15	16	17	18	19	20
21	22	23	24	25	26	27
28	29	30	 	 	 	 
Input/Output

[execution time limit] 4 seconds (py)

[input] integer month

1-based number of a month (i.e. 1 stands for January, 2 stands for February, and so on).

Guaranteed constraints:
1 ≤ month ≤ 12.

[input] integer year

4-digit number of a year. Please don't forget that in leap years February has 29 days.

Guaranteed constraints:
1900 ≤ year ≤ 2100.

[output] string

An HTML table corresponding to the given month and the given year.
"""

import datetime, math 
from calendar import monthrange

def table(month, year, data):
    months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
    monthYear = months[month - 1] + " " + str(year)
    tableContent = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"month\"><tr><th colspan=\"7\" class=\"month\">"+monthYear+"</th></tr><tr><th class=\"mon\">Mon</th><th class=\"tue\">Tue</th><th class=\"wed\">Wed</th><th class=\"thu\">Thu</th><th class=\"fri\">Fri</th><th class=\"sat\">Sat</th><th class=\"sun\">Sun</th></tr>"+data+"</table>"
    return tableContent

def tdContent(className, content):
    return  "<td class=\""+className+"\">"+content+"</td>"
    
def renderTrContent(weekDay, dayOfMonth):
    data = ""
    weekDays = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"] 
    arrMonthPresent = []
    index = 1
    p = 0
    dayPresent = int(weekDay)
    #change to my format weekdays
    dayPresent =  6 if dayPresent == 0 else dayPresent - 1
    arrMonthPresent.append([None for _ in range(7)])
    while index <= dayOfMonth:
        arrMonthPresent[p][dayPresent] = [weekDays[dayPresent], index]
        dayPresent = (dayPresent+1) % 7
        # fix one bug on test 14 :( im so unlucky
        if dayPresent == 0 and index != dayOfMonth:
            p = p + 1
            arrMonthPresent.append([None for _ in range(7)])
        index += 1
        
    print arrMonthPresent
    
    index = 0
    while index < len(arrMonthPresent):
        data += "<tr>"
        for v in range(7):
            if arrMonthPresent[index][v] == None:
                data += tdContent("noday", "&nbsp;")
            else: 
                data += tdContent(arrMonthPresent[index][v][0], str(arrMonthPresent[index][v][1])) 
        data += "</tr>"
        index += 1
    
    return data

def solution(month, year):
    dateti = datetime.datetime(year, month, 1)
    weekDay = dateti.strftime("%w")
    dayOfMonth = monthrange(year, month)[1]
    return table(month, year, renderTrContent(weekDay, dayOfMonth))