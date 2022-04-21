"""
You are creating a new website about HTML parsing. You don't want your page to be too simple, so you would like to estimate the complexity of the main page of your site. In order to measure the complexity of the page, you need to find a set of all tags located on the deepest level of a tree correponsing to HTML document. Given a valid HTML document, find all distinct tags located on the deepest level.

Example

For
document = "<!DOCTYPE html><html> <body> <h1>The best heading ever</h1> <p>The worst paragraph ever.</p> </body></html>"
the output should be
solution(document) = ["h1", "p"].

Input/Output

[execution time limit] 4 seconds (py3)

[input] string document

Correct HTML document.

Guaranteed constraints:
10 ? document.length ? 900.

[output] array.string

List of all distinct tags on the deepest level sorted lexicographically.
"""


from html.parser import HTMLParser

class MyHTMLParser(HTMLParser):
    #Initializing lists
    tags = list()
    level = 0
    def handle_starttag(self, tag, attrs):
        self.level += 1
        self.tags.append([self.level, tag])
        
    def handle_endtag(self, tag):
        self.level -= 1
        
    def returnListTag(self):
        return self.tags

def solution(document):
    parser = MyHTMLParser()
    parser.feed(document)
    listTag = sorted(parser.returnListTag(), key=lambda x: x[0], reverse=True)
    listReturn = set()
    print(listTag)
    if len(listTag) > 0:
        maxLevel = listTag[0][0]
        index = 0
        #i fix bug on hidden test 17 by using more command: index < len(listTag)
        while index < len(listTag) and listTag[index][0] == maxLevel:
            listReturn.add(listTag[index][1])
            index += 1
    
    return sorted(list(listReturn))

