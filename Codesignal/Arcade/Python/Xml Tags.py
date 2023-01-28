"""
You want to create your local database containing information about the things you find the coolest. You used to store this information in xml documents, so now you need to come up with an algorithm that will convert the existing format into the new one. First you decided to choose a structure for your scheme, and to do it you want to represent xml document as a tree, i.e. gather all the tags and print them out as follows:

tag1()
 --tag1.1(attribute1, attribute2, ...)
 ----tag1.1.1(attribute1, attribute2, ...)
 ----tag1.1.2(attribute1, attribute2, ...)
 --tag1.2(attribute1, attribute2, ...)
 ----tag1.2.1(attribute1, attribute2, ...)
...
where attributes of each tag are sorted lexicographically.

You are a careful person, so the structure of the xml is neatly organized is such a way that:

there is a single tag at the root level;
each tag has a single parent tag (i.e. if there are several occurrences of tag a, and in one occurrence it's a child of tag b and in the other one it's a child of tag c, then b = c);
each appearance of the same tag belongs to the same level.
Given an xml file, return its structure as shown above. The tags of the same level should be sorted in the order they appear in xml, and the attributes should be sorted lexicographically.

Note: you are given xml represantation in one line.

Example

For

xml =
"<data>
    <animal name="cat">
    	<genus>Felis</genus>
        <family name="Felidae" subfamily="Felinae"/>
        <similar name="tiger" size="bigger"/>
    </animal>
    <animal name="dog">
        <family name="Canidae" member="canid"/>
        <order>Carnivora</order>
        <similar name="fox" size="similar"/>
    </animal>
</data>"
the output should be

solution(xml) =
["data()",
 "--animal(name)",
 "----genus()",
 "----family(member, name, subfamily)",
 "----similar(name, size)",
 "----order()"]
Input/Output

[execution time limit] 4 seconds (py)

[input] string xml

xml file as a string.

Guaranteed constraints:
7 ≤ xml.length ≤ 1000.

[output] array.string

A list representing the structure of the xml file as described above.
"""

import re

class XmlObject:
  def __init__(self, data, level, order):
    self.data = data
    self.level = level
    self.order = order
    
  def set_data(self, other): 
    self.data = other 
    
  def __str__(self):
    return str(self.data) + ", " + str(self.level) + ", " + str(self.order)

  def __gt__(self, obj):
    return self.order > obj.order
    
def solution(xml): 
    getAttXmlTag = lambda tag: re.findall( r'(^\w+|\s+\w+(?==))', tag)
    res = []
    stackGetLevel = []
    getData = []
    position = 0
    index = 0
    
    while index < len(xml):
        if xml[index] == "<":
            if xml[index+1] == "/":
                getData.append(stackGetLevel.pop())
            else:
                position += 1
                j = index + 1
                data = ""
                isEndNow = False
                
                while j < len(xml) and xml[j] != ">":
                    if xml[j] == "/" and xml[j+1] == ">":
                        isEndNow = True
                        break
                    data += xml[j]
                    j += 1     
                if len(stackGetLevel) == 0:
                    stackGetLevel.append(XmlObject(getAttXmlTag(data), 0, position))
                else:
                    level = stackGetLevel[len(stackGetLevel)-1].level + 1
                    if isEndNow == False:
                        stackGetLevel.append(XmlObject(getAttXmlTag(data), level, position))
                    else:
                        getData.append(XmlObject(getAttXmlTag(data), level, position))
                index = j - 1
        index += 1
    getData.sort()
    
    mapFilter = OrderedDict()
    for v in getData:
      #  print v
        if v.data[0] not in mapFilter:
            key = v.data[0]
            dt = filter(lambda x: x != key, v.data)
            dt.sort()
            v.set_data(dt)
            mapFilter[key] = v
        else:
            getOnlyAtt = set()
            obj = mapFilter[v.data[0]]
            for dt in obj.data + v.data:
                getOnlyAtt.add(dt)
            dt = filter(lambda x: x != v.data[0], list(getOnlyAtt))
            dt.sort()
            obj.set_data(dt)
            mapFilter[v.data[0]] = obj
    
    
    for key in mapFilter:
        value = mapFilter[key]
        string = "--" * mapFilter[key].level + key + "("
        
        for ind in range(0, len(value.data)-1):
             string += value.data[ind].strip() + ", "
                
        if len(value.data) > 0:
            string += value.data[len(value.data)-1].strip()
        string += ")"

        res.append(string)
        
    return res
