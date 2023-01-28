"""
You decided to create a malicious program to play a joke on your friend. He's now struggling with a report for his work, and you want to scare him by spoiling some dates in it (of course you will change them back after you have your moment of joy). However, you don't want him to discover the errors until he starts double-checking the report, so all spoiled dates should be valid.

Now your goal is to write a program that modifies the curDate according to the rules that specify the changes that should be made. However, if the changes result in an incorrect date, the program should leave the date as is.

Given the changes and the curDate, return the spoiled date or don't change it if the result appears to be invalid.

Example

For curDate = "01 Jul 2016 16:53:24" and
changes = [2, 3, -1, 0, 5, 4], the output should be
solution(curDate, changes) = "03 Oct 2015 16:58:28";

For curDate = "15 Mar 1998 12:09:59" and
changes = [-2, 0, 9, 1, 3, 1], the output should be
solution(curDate, changes) = "15 Mar 1998 12:09:59".

After changing the date will look like "13 Mar 2007 13:12:60", which is incorrect, because the number of seconds cannot be equal to 60.

Input/Output

[execution time limit] 4 seconds (py)

[input] string curDate

The current date in the format "DD MMM YYYY HH:MM:SS", where DD stands for day of the month (1-based), MMM stands for the name of month cut to 3 letters (i.e. "Jan" for January, "Feb" for February and so on), YYYY - for the year, HH - for hour (your friend uses 24-hour clock), MM - for minutes and SS - for seconds. It's guaranteed that given date is correct.

Guaranteed constraints:
01 ? DD ? 31,
MMM ? ["Jan", "Feb", ..., "Dec"],
1900 ? YYYY ? 2100,
00 ? HH ? 23,
00 ? MM ? 59,
00 ? SS ? 59.

[input] array.integer changes

An array that describes how each component of curDate should be updated. changes[i] is equal to the value that should be added to the ith component, where i stands for:

0: for the day;
1: for the month;
2: for the year;
3: for the hour;
4: for the minute;
5: for the second.
Guaranteed constraints:
-30 ? changes[0] ? 30,
-11 ? changes[1] ? 12,
-100 ? changes[2] ? 100,
-23 ? changes[3] ? 23,
-59 ? changes[4] ? 59,
-59 ? changes[5] ? 59.

[output] string

The modified date if it's correct and the given date otherwise (the output should follow the same format as the input). The date is considered to be incorrect if at least one of its components is invalid.
"""

import re

import datetime

def checkDate(year, month, day, h, m, s):
    correctDate = None
    try:
        newDate = datetime.datetime(year, month, day, h, m, s)
        correctDate = True
    except ValueError:
        correctDate = False
    return correctDate

def solution(curDate, changes):
    dateArr = re.findall(r'^(\d{2})\s(\w{3})\s(\d{4})\s(\d{2}):(\d{2}):(\d{2})$', curDate)
    day = int(dateArr[0][0]) + int(changes[0])
    month = 0
    year = int(dateArr[0][2]) + int(changes[2]) 
    h = int(dateArr[0][3]) + int(changes[3])
    m = int(dateArr[0][4]) + int(changes[4])
    s = int(dateArr[0][5]) + int(changes[5])
    
    monthPresents = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
    
    for i in range(12):
        if dateArr[0][1] == monthPresents[i]:
            month = i + int(changes[1])
    
    c = lambda convert: "0" + str(convert) if convert < 10 else str(convert)
    cd = lambda year, month, day, h, m, s: c(day) + " " + monthPresents[month] + " " +  c(year) + " " + c(h) + ":" +  c(m) + ":" + c(s)
    
    if checkDate(year, month+1, day, h, m, s):
        return cd(year, month, day, h, m, s)
    else:
        return curDate