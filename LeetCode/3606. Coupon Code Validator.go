/**
	url: https://leetcode.com/problems/coupon-code-validator/?envType=daily-question&envId=2025-12-13
	
	You are given three arrays of length n that describe the properties of n coupons: code, businessLine, and isActive. The ith coupon has:

code[i]: a string representing the coupon identifier.
businessLine[i]: a string denoting the business category of the coupon.
isActive[i]: a boolean indicating whether the coupon is currently active.
A coupon is considered valid if all of the following conditions hold:

code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
isActive[i] is true.
Return an array of the codes of all valid coupons, sorted first by their businessLine in the order: "electronics", "grocery", "pharmacy", "restaurant", and then by code in lexicographical (ascending) order within each category.

 

Example 1:

Input: code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"], isActive = [true,true,true,true]

Output: ["PHARMA5","SAVE20"]

Explanation:

First coupon is valid.
Second coupon has empty code (invalid).
Third coupon is valid.
Fourth coupon has special character @ (invalid).
Example 2:

Input: code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"], isActive = [false,true,true]

Output: ["ELECTRONICS_50"]

Explanation:

First coupon is inactive (invalid).
Second coupon is valid.
Third coupon has invalid business line (invalid).
 

Constraints:

n == code.length == businessLine.length == isActive.length
1 <= n <= 100
0 <= code[i].length, businessLine[i].length <= 100
code[i] and businessLine[i] consist of printable ASCII characters.
isActive[i] is either true or false.
**/

func validateCoupons(code []string, businessLine []string, isActive []bool) []string {

    type CodeBussineLine struct {
		code         string
		businessLine string
	}

    var BUSINESSLINEORDER = map[string]int{ 
                                "electronics": 1, 
                                "grocery": 2, 
                                "pharmacy": 3,
                                "restaurant": 4 }

    re := regexp.MustCompile("^[a-z0-9A-Z_]+$")

    var codeBussineLine []CodeBussineLine = []CodeBussineLine{}
    for ind, v := range code {
        if isActive[ind] && re.MatchString(v) && BUSINESSLINEORDER[businessLine[ind]] != 0 {
            codeBussineLine = append(codeBussineLine, CodeBussineLine{v, businessLine[ind]})
        } 
    }

    sort.Slice(codeBussineLine, func (i, j int) bool {
        if BUSINESSLINEORDER[codeBussineLine[i].businessLine] == BUSINESSLINEORDER[codeBussineLine[j].businessLine] {
            return strings.Compare(codeBussineLine[i].code, codeBussineLine[j].code) < 0 
        }
        return BUSINESSLINEORDER[codeBussineLine[i].businessLine] < BUSINESSLINEORDER[codeBussineLine[j].businessLine]
    })

    codeOrders := []string{}

    for _, v := range codeBussineLine {
        codeOrders = append(codeOrders, v.code)
    }

    return codeOrders
}