"""
You've got tired of fixing your relatives' PCs after they visited some phishing website so you decided to implement a special plug-in for their browsers which will check if the page they are trying to visit is similar to the one in the blacklist.

For that, you've thought of the special algorithm that for two URLs url1 and url2 computes their similarity as following:

Initially their similarity is 0
Then, it is increased by the following rules:
+5, if the same protocol is used in both URLs.
+10, if url1 and url2 have the same address.
+k, if the first k components of path (delimited by /) are exactly the same (and in the same order) between the two URLs.
+1 if for each varNames common between them. Additional +1 if the respective values are equal too.
URLs are given in the following format: protocol://address[(/path)*][?query] (where query = varName=value(&varName=value)*, parts given in [] are optional, and parts in ()* may be repeated several times). Each of the named elements (i.e. protocol, address, path, varName and value) are guaranteed to contain only alphanumeric characters and periods.

Given the two URLs url1 and url2, compute their similarity using the algorithm described above.

Example

For

url1 = "https://codesignal.com/home/test?param1=42&param3=testing&login=admin"
and

url2 = "https://codesignal.com/home/secret/test?param3=fish&param1=42&password=admin"
the output should be
solution(url1, url2) = 19.

Because these URLs have the same protocols, addresses, first path component (home) and two varNames, with one also having the same value in both of them.
So the resulting similarity is thus 5 + 10 + 1 + 1 + 1 + 1 = 19.

Input/Output

[execution time limit] 4 seconds (py)

[input] string url1

First URL. It can only contain lowercase English letters, digits and characters '.', '/', ':', '?', '&' and '='.
It's guaranteed that each varName is unique.

Guaranteed constraints:
5 ? url1.length ? 100.

[input] string url2

Second URL with the same format as url1.

Guaranteed constraints:
5 ? url2.length ? 100.

[output] integer

The computed similarity.
"""

import re

def getUrlComponents(url):
    getUrlComponent = re.findall(r'^([a-zA-Z0-9.]+):\/\/([a-zA-Z0-9.]*)((?:\/[a-zA-Z0-9.]+)*)\??((?:[a-zA-Z0-9.]+=[a-zA-Z0-9.]*&?)*)$', url)
    
    protocol = getUrlComponent[0][0]
    
    address = None
    if len(getUrlComponent[0]) > 0:
        address = getUrlComponent[0][1]
    
    path = None
    if len(getUrlComponent[0]) > 1:
        path = getUrlComponent[0][2].split("/")
    query = []
    if len(getUrlComponent[0]) > 2:
        query = getUrlComponent[0][3].split("&")
        index = 0
        while index < len(query):
            query[index] = query[index].split("=")
            index += 1
    return [protocol, address, path, query]

def solution(url1, url2):
    similarity = 0
    getUrlComponentUrl1 = getUrlComponents(url1)
    getUrlComponentUrl2 = getUrlComponents(url2)
    
    print getUrlComponentUrl1
    
    # check protocol
    if getUrlComponentUrl1[0] == getUrlComponentUrl2[0]:
        similarity += 5
        
    # check address
    if getUrlComponentUrl1[1] != "" and getUrlComponentUrl2[1] != "" and getUrlComponentUrl1[1] == getUrlComponentUrl2[1]:
        similarity += 10
        
    # check path
    if getUrlComponentUrl1[2] is not None and getUrlComponentUrl2[2] is not None:
        index = 1
        leng = min(len(getUrlComponentUrl1[2]), len(getUrlComponentUrl2[2]))
        while index < leng and getUrlComponentUrl1[2][index] == getUrlComponentUrl2[2][index]:
            similarity += 1
            index += 1
            
    #check query
    queryList1 = getUrlComponentUrl1[3]
    queryList2 = getUrlComponentUrl2[3]
    if len(queryList1) > 0 and len(queryList2) > 0:
        
        isVisted = [False for _ in range(len(getUrlComponentUrl2[3]))]
        index = 0
        
        while index < len(queryList1) and len(queryList1[0]) == 2:    
            pos = 0
            while pos < len(queryList2) and len(queryList2[0]) == 2:
                if isVisted[pos] == False and queryList1[index][0] == queryList2[pos][0]:
                    similarity += 1
                    isVisted[pos] = True
                    if queryList1[index][1] ==  queryList2[pos][1]:
                        similarity += 1
                pos += 1
            index += 1
        
    return similarity
