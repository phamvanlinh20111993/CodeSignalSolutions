"""
While migrating to a new operation system, you faced an unexpected problem: now you need to create a new build command for your favorite text editor plugin. The build command is stored as a JSON file that you should now update. In order to make the transition simpler, you decided to write a program that will create a template of the build command by replacing everything but dictionaries in given jsonFile with their default values: numbers with 0, strings with "" and lists with [].

It is guaranteed that there are only aforementioned types in the given JSON file.

Example

For

jsonFile =
"""
{
    "version": "0.1.0",
    "command": "c:python",
    "args": ["app.py"],
    "problemMatcher": {
        "fileLocation": ["relative", "${workspaceRoot}"],
        "pattern": {
            "regexp": "^(.*)+s$",
            "message": 1
        }
    }
}
"""
the output should be

solution(jsonFile) =
"""
{
    "version": "",
    "command": "",
    "args": [],
    "problemMatcher": {
        "fileLocation": [],
        "pattern": {
            "regexp": "",
            "message": 0
        }
    }
}
"""
Input/Output

[execution time limit] 4 seconds (py)

[input] string jsonFile

A correct JSON file of build command for your favorite plugin. It is guaranteed that it contains only lists, dictionaries, strings and numbers. It is also guaranteed that the entire file consists of a single dictionary.

Guaranteed constraints:
2 ≤ jsonFile.length ≤ 1000.

[output] string

Modified jsonFile.
"""

import re, unicodedata

def ignoreSquareBracket(jsonF):
    jsonFTmp = ""
    isInSquareBracket = []
    isInString = False
    index = 0
    while index < len(jsonF):
        if (index == 0 or (index > 0 and jsonF[index-1] != "\\")) and (jsonF[index] == "\"" or jsonF[index] == "'"):
           isInString = not isInString
        if not isInString:
            if jsonF[index] == "[":
                isInSquareBracket.append(1)
            if jsonF[index] == "]":
                isInSquareBracket.pop()
                # error on test 7
                if len(isInSquareBracket) == 0:
                    jsonFTmp += "["
                    
        if len(isInSquareBracket) == 0:
             jsonFTmp += jsonF[index]
        index += 1
    return jsonFTmp


def returnType(val):
    if not val or val.strip() == "":
        return ["", val]
    
    val = val.strip()
    #convert unicode to str
    val = unicodedata.normalize('NFKD', val).encode('ascii','ignore')
    result = re.findall(r'^([^}]*)(\}*)$', val)
    
    if len(result) == 0:
        return val
    tmp = result[0][1]
    val = result[0][0]
    if re.match(r'^-?\d+(?:\.\d+)?$', val) is not None:
        return ("0" + tmp)
    if re.match(r'^\[\s*\]$', val) is not None:
        return "[]" + tmp
    return "\"\"" + tmp


def splitBySignal(jsonF, signal):
    arr = []
    isInString = False
    start = 0
    ind = 0
    while ind < len(jsonF):
        if (ind == 0 or (ind > 0 and jsonF[ind-1] != "\\")) and (jsonF[ind] == "\"" or jsonF[ind] == "'"):
            isInString = not isInString
        if not isInString and jsonF[ind] == signal:
            arr.append(jsonF[start:ind])
            start = ind + 1
        ind += 1
    arr.append(jsonF[start:ind])
        
    return arr


def solution(jsonFile):
    jsonFile = ignoreSquareBracket(jsonFile) 
    print jsonFile
    jsonFileTmp = ""
    splitArray = splitBySignal(jsonFile, ",")
    
    index = 0
    while index < len(splitArray):
        splitArray[index] = splitBySignal(splitArray[index], ":")
        index += 1
        
    index = 0
    while index < len(splitArray): 
        
        lenSplitArr = len(splitArray[index])
        pos = 0
        while pos < lenSplitArr - 1:
            jsonFileTmp += splitArray[index][pos].strip() + ": "
            pos +=1
        
        if lenSplitArr > 1:
            value = splitArray[index][lenSplitArr-1]
            value = returnType(value)
            jsonFileTmp += value
        else:
            jsonFileTmp += splitArray[index][lenSplitArr-1]
            
        if index < len(splitArray) - 1:
            jsonFileTmp += ", "
        index += 1
    
    return jsonFileTmp

   # return re.findall( r'(?<=:\s+)\[?.*', jsonFile)
   # return json.loads(jsonFile)

