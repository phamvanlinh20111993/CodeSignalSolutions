/**

1. Pick starting point = max X
2. Choose second point = smallest angle from a custom direction vector
3. Repeatedly pick next point = largest angle relative to last edge
4. Tie-breaking by shorter distance
5. Stop when encountering a duplicate point

**/
/*
func angleBetweenLines(A , B [] int) float64 {
    var numerator float64 = float64(A[0]*B[0] + A[1]*B[1])
    var denominator float64 = math.Sqrt(float64(A[0]*A[0] + A[1]*A[1]))*math.Sqrt(float64(B[0]*B[0] + B[1]*B[1]))
    if areFloatsEqual(denominator, 0) {
        return 0
    }
    return math.Acos(numerator/denominator)
}

func distance(A [] int, B[] int) int {
    return (B[0]-A[0])*(B[0]-A[0]) + (B[1]-A[1])*(B[1]-A[1])
}

// refer: chatGPT
func areFloatsEqual(a, b float64) bool {
	if a == b {
		return true
	}
	diff := math.Abs(a - b)
	return diff < 0.000001
}

func outerTrees(trees [][]int) [][]int {
    if len(trees) < 4 {
        return trees
    }
  // get the first point
    firstPoint := []int{0, 0} 
    for _, tree := range trees {
        if firstPoint[0] < tree[0] {
            firstPoint[0] = tree[0]
            firstPoint[1] = tree[1]
        }
    }
    listOrderedPoint := [][]int{}
    listOrderedPoint = append(listOrderedPoint, firstPoint)

     // get the second point
    directionVector := []int {0, -firstPoint[1]}
    minAngle := math.Pi
    var mDistance int = 0
    listOrderedPoint = append(listOrderedPoint, []int {0, 0})
    
    for _, tree := range trees {
        if firstPoint[0] == tree[0] && firstPoint[1] == tree[1] {
            continue
        }
        directionV := []int {tree[0] - firstPoint[0], tree[1] - firstPoint[1]}
        currentAngle := angleBetweenLines(directionVector, directionV)
        fmt.Println("wtfcccc ", minAngle, currentAngle, tree)
        if minAngle > currentAngle {
            minAngle = currentAngle
            listOrderedPoint[1][0] = tree[0]
            listOrderedPoint[1][1] = tree[1]
            mDistance = distance(firstPoint, tree)
        }else if areFloatsEqual(minAngle, currentAngle) {
            fmt.Println("Equal ", minAngle, mDistance)
            var cDistance = distance(firstPoint, tree)
            if cDistance < mDistance {
                listOrderedPoint[1][0] = tree[0]
                listOrderedPoint[1][1] = tree[1]
                mDistance = cDistance
            }
        }
    }

    checkDupPoint := make(map[string]int)
    for _, p := range listOrderedPoint {
        checkDupPoint[fmt.Sprintf("%d-%d", p[0], p[1])]++
    }

    // get all point
    for true {
        var lastPP = len(listOrderedPoint) - 1
        lastDirectV := []int {listOrderedPoint[lastPP][0] - listOrderedPoint[lastPP-1][0], 
                              listOrderedPoint[lastPP][1] - listOrderedPoint[lastPP-1][1]}
        var maxAngle float64 = 0.0
        choosenPoint := []int {0, 0}
        var maxDistance int = 0
        for _, tree := range trees {

            if (listOrderedPoint[lastPP][0] == tree[0] && listOrderedPoint[lastPP][1] == tree[1]) || (listOrderedPoint[lastPP-1][0] == tree[0] && listOrderedPoint[lastPP-1][1] == tree[1]) {
                continue
            }

            curDirectV := []int {listOrderedPoint[lastPP][0] - tree[0], 
                                 listOrderedPoint[lastPP][1] - tree[1]}

            currAngle := angleBetweenLines(lastDirectV, curDirectV)
       //     fmt.Println("currAngle: ", currAngle, tree)
            if maxAngle < currAngle {
                maxAngle = currAngle
                choosenPoint[0] = tree[0]
                choosenPoint[1] = tree[1]
                maxDistance = distance(listOrderedPoint[lastPP], tree)
            }else if areFloatsEqual(maxAngle, currAngle) {
                var currDistance int = distance(listOrderedPoint[lastPP], tree)
                if currDistance < maxDistance {
                    choosenPoint[0] = tree[0]
                    choosenPoint[1] = tree[1]
                    maxDistance = currDistance
                }
            }
        }

        if lastPP + 1 == len(trees) || checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])] > 0 {
            break
        }
     //   fmt.Println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ choosenPoint: ", choosenPoint, maxAngle)
        listOrderedPoint = append(listOrderedPoint, choosenPoint)
        checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])]++
    }


    return listOrderedPoint
} 
*/
/*
func angleBetweenLines(A, B []int) float64 {
    dot := A[0]*B[0] + A[1]*B[1]
    cross := A[0]*B[1] - B[0]*A[1]

    return math.Abs(math.Atan2(float64(cross), float64(dot)))
}
 */
 
 /**
 	url: https://leetcode.com/problems/erect-the-fence/description/?envType=problem-list-v2&envId=geometry
 	
 	You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

Fence the entire garden using the minimum length of rope, as it is expensive. The garden is well-fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter. You may return the answer in any order.

 

Example 1:


Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
Explanation: All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.
Example 2:


Input: trees = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
Explanation: The fence forms a line that passes through all the trees.
 

Constraints:

1 <= trees.length <= 3000
trees[i].length == 2
0 <= xi, yi <= 100
All the given positions are unique.
 
 */
 
/**

1. Pick starting point = max X
2. Choose second point = smallest angle from a custom direction vector
3. Repeatedly pick next point = largest angle relative to last edge
4. Tie-breaking by shorter distance
5. Stop when encountering a duplicate point

**/
/*
func angleBetweenLines(A , B [] int) float64 {
    var numerator float64 = float64(A[0]*B[0] + A[1]*B[1])
    var denominator float64 = math.Sqrt(float64(A[0]*A[0] + A[1]*A[1]))*math.Sqrt(float64(B[0]*B[0] + B[1]*B[1]))
    if areFloatsEqual(denominator, 0) {
        return 0
    }
    return math.Acos(numerator/denominator)
}

func distance(A [] int, B[] int) int {
    return (B[0]-A[0])*(B[0]-A[0]) + (B[1]-A[1])*(B[1]-A[1])
}

// refer: chatGPT
func areFloatsEqual(a, b float64) bool {
	if a == b {
		return true
	}
	diff := math.Abs(a - b)
	return diff < 0.000001
}

func outerTrees(trees [][]int) [][]int {
    if len(trees) < 4 {
        return trees
    }
  // get the first point
    firstPoint := []int{0, 0} 
    for _, tree := range trees {
        if firstPoint[0] < tree[0] {
            firstPoint[0] = tree[0]
            firstPoint[1] = tree[1]
        }
    }
    listOrderedPoint := [][]int{}
    listOrderedPoint = append(listOrderedPoint, firstPoint)

     // get the second point
    directionVector := []int {0, -firstPoint[1]}
    minAngle := math.Pi
    var mDistance int = 0
    listOrderedPoint = append(listOrderedPoint, []int {0, 0})
    
    for _, tree := range trees {
        if firstPoint[0] == tree[0] && firstPoint[1] == tree[1] {
            continue
        }
        directionV := []int {tree[0] - firstPoint[0], tree[1] - firstPoint[1]}
        currentAngle := angleBetweenLines(directionVector, directionV)
        fmt.Println("wtfcccc ", minAngle, currentAngle, tree)
        if minAngle > currentAngle {
            minAngle = currentAngle
            listOrderedPoint[1][0] = tree[0]
            listOrderedPoint[1][1] = tree[1]
            mDistance = distance(firstPoint, tree)
        }else if areFloatsEqual(minAngle, currentAngle) {
            fmt.Println("Equal ", minAngle, mDistance)
            var cDistance = distance(firstPoint, tree)
            if cDistance < mDistance {
                listOrderedPoint[1][0] = tree[0]
                listOrderedPoint[1][1] = tree[1]
                mDistance = cDistance
            }
        }
    }

    checkDupPoint := make(map[string]int)
    for _, p := range listOrderedPoint {
        checkDupPoint[fmt.Sprintf("%d-%d", p[0], p[1])]++
    }

    // get all point
    for true {
        var lastPP = len(listOrderedPoint) - 1
        lastDirectV := []int {listOrderedPoint[lastPP][0] - listOrderedPoint[lastPP-1][0], 
                              listOrderedPoint[lastPP][1] - listOrderedPoint[lastPP-1][1]}
        var maxAngle float64 = 0.0
        choosenPoint := []int {0, 0}
        var maxDistance int = 0
        for _, tree := range trees {

            if (listOrderedPoint[lastPP][0] == tree[0] && listOrderedPoint[lastPP][1] == tree[1]) || (listOrderedPoint[lastPP-1][0] == tree[0] && listOrderedPoint[lastPP-1][1] == tree[1]) {
                continue
            }

            curDirectV := []int {listOrderedPoint[lastPP][0] - tree[0], 
                                 listOrderedPoint[lastPP][1] - tree[1]}

            currAngle := angleBetweenLines(lastDirectV, curDirectV)
       //     fmt.Println("currAngle: ", currAngle, tree)
            if maxAngle < currAngle {
                maxAngle = currAngle
                choosenPoint[0] = tree[0]
                choosenPoint[1] = tree[1]
                maxDistance = distance(listOrderedPoint[lastPP], tree)
            }else if areFloatsEqual(maxAngle, currAngle) {
                var currDistance int = distance(listOrderedPoint[lastPP], tree)
                if currDistance < maxDistance {
                    choosenPoint[0] = tree[0]
                    choosenPoint[1] = tree[1]
                    maxDistance = currDistance
                }
            }
        }

        if lastPP + 1 == len(trees) || checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])] > 0 {
            break
        }
     //   fmt.Println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ choosenPoint: ", choosenPoint, maxAngle)
        listOrderedPoint = append(listOrderedPoint, choosenPoint)
        checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])]++
    }


    return listOrderedPoint
} 
*/
/*
func angleBetweenLines(A, B []int) float64 {
    dot := A[0]*B[0] + A[1]*B[1]
    cross := A[0]*B[1] - B[0]*A[1]

    return math.Abs(math.Atan2(float64(cross), float64(dot)))
}
 */
 /**
 	refer: Chatgpt support and Copilot support.
 	 - https://copilot.microsoft.com/chats/ipyaZNGvVqU8q9mZSp11R
 	 - https://chatgpt.com/?utm_source=google&utm_medium=paidsearch_brand&utm_campaign=GOOG_C_SEM_GBR_Core_CHT_BAU_ACQ_PER_MIX_ALL_APAC_VN_EN_032525&utm_term=chatgpt&utm_content=178488388073&utm_ad=744866532619&utm_match=e&gad_source=1&gad_campaignid=22370486775&gbraid=0AAAAA-IW-UVPaE6tduSFziEtxrQYPfAPL&gclid=Cj0KCQiAiebIBhDmARIsAE8PGNL8hU0ta0msmJwYkKEVq_FdWOgPjGbYUWjpI8dZhcw-49tiBjeaDE0aAhdFEALw_wcB
 	 - Convex Hull Algorithm fit this problem: https://www.geeksforgeeks.org/dsa/convex-hull-algorithm/
 **/
func angleBetweenLines(A, B []int) float64 {
    numerator := float64(A[0]*B[0] + A[1]*B[1])
    denominator := math.Sqrt(float64(A[0]*A[0]+A[1]*A[1])) * math.Sqrt(float64(B[0]*B[0]+B[1]*B[1]))
    if areFloatsEqual(denominator, 0) {
        return 0
    }
    ratio := numerator / denominator
    if ratio > 1 {
        ratio = 1
    } else if ratio < -1 {
        ratio = -1
    }
    return math.Acos(ratio)
}

func distance(A, B []int) int {
    return (B[0]-A[0])*(B[0]-A[0]) + (B[1]-A[1])*(B[1]-A[1])
}

func areFloatsEqual(a, b float64) bool {
    diff := math.Abs(a - b)
    return diff < 1e-7
}

func outerTrees(trees [][]int) [][]int {
    if len(trees) < 4 {
        return trees
    }
    // Step 1: find starting point (rightmost)
    firstPoint := trees[0]
    for _, tree := range trees {
        if tree[0] > firstPoint[0] {
            firstPoint = tree
        }
    }
    listOrderedPoint := [][]int{firstPoint}
    checkDupPoint := map[string]int{fmt.Sprintf("%d-%d", firstPoint[0], firstPoint[1]): 1}

    // Step 2: find second point by smallest angle
    // 0 <= xi, yi <= 100
    // 300 avoid zero when calculate angle
    directionVector := []int{0, -firstPoint[1] + 300}
    minAngle := math.Pi
    mDistance := math.MaxInt
    var secondPoint []int

    for _, tree := range trees {
        if tree[0] == firstPoint[0] && tree[1] == firstPoint[1] {
            continue
        }
        directionV := []int{tree[0] - firstPoint[0], tree[1] - firstPoint[1]}
        currentAngle := angleBetweenLines(directionVector, directionV)
        cDistance := distance(firstPoint, tree)

        if (!areFloatsEqual(currentAngle, minAngle) && currentAngle < minAngle) || 
            (areFloatsEqual(currentAngle, minAngle) && cDistance < mDistance) {
            minAngle = currentAngle
            mDistance = cDistance
            secondPoint = tree
        }
    }

    listOrderedPoint = append(listOrderedPoint, secondPoint)
    checkDupPoint[fmt.Sprintf("%d-%d", secondPoint[0], secondPoint[1])]++

    for {
        lastPP := len(listOrderedPoint) - 1
        lastDirectV := []int{
            listOrderedPoint[lastPP][0] - listOrderedPoint[lastPP-1][0],
            listOrderedPoint[lastPP][1] - listOrderedPoint[lastPP-1][1],
        }

        maxAngle := 0.0
        var choosenPoint []int
        maxDistance := 0

        for _, tree := range trees {
            if (tree[0] == listOrderedPoint[lastPP][0] && tree[1] == listOrderedPoint[lastPP][1]) ||
                (tree[0] == listOrderedPoint[lastPP-1][0] && tree[1] == listOrderedPoint[lastPP-1][1]) {
                continue
            }

            curDirectV := []int{
                listOrderedPoint[lastPP][0] - tree[0],
                listOrderedPoint[lastPP][1] - tree[1],
            }

            currAngle := angleBetweenLines(lastDirectV, curDirectV)
            currDistance := distance(listOrderedPoint[lastPP], tree)

            if (!areFloatsEqual(currAngle, maxAngle)  && currAngle > maxAngle) || 
                (areFloatsEqual(currAngle, maxAngle) && currDistance < maxDistance) {
                maxAngle = currAngle
                choosenPoint = tree
                maxDistance = currDistance
            }
        }

        if choosenPoint == nil || checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])] > 0 {
            break
        }
        listOrderedPoint = append(listOrderedPoint, choosenPoint)
        checkDupPoint[fmt.Sprintf("%d-%d", choosenPoint[0], choosenPoint[1])]++
    }

    return listOrderedPoint
}