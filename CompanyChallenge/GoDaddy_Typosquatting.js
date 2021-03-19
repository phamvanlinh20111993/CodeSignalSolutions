/**
 * Typosquatting is a hack that relies on mistakes made by Internet users when
 * inputting a website address into a web browser. So if a user is trying to go
 * to godaddy.com but they accidentally type in goddady.com and someone else
 * owns that domain, they could pretend to be GoDaddy and steal valuable user
 * information.
 *
 * Assume that GoDaddy is introducing a new feature that helps users protect
 * their domains from typosquatting. It is known that a typosquatter's URL is
 * usually similar to the victim's domain, but has some typos in it, where a
 * typo means that letters in two adjacent positions have been swapped.
 *
 * Given n, the number of additional domains the owner is willing to buy to
 * protect their domain against typosquatting, GoDaddy calculates the maximum
 * number k such that all of the domains with k or fewer typos can be bought
 * (excluding the original domain itself).
 *
 * Your task is to implement an algorithm that finds k given n and a domain
 * name.
 *
 * For n = 7 and domain = "godaddy.com", the output should be typosquatting(n,
 * domain) = 1.
 *
 * For k = 1 the following typos can be made:
 *
 * "ogdaddy.com" "gdoaddy.com" "goadddy.com" "goddady.com" "godadyd.com"
 * "godaddy.ocm" "godaddy.cmo" 7 domains to buy altogether. That's exactly the
 * number of domains the user can afford, so the answer is 1.
 *
 * For n = 9 and domain = "omg.tv", the output should be typosquatting(n,
 * domain) = 2.
 *
 * For k = 1, the following typos can be made:
 *
 * "mog.tv" "ogm.tv" "omg.vt" For k = 2, 4 more typos can be obtained:
 *
 * "mgo.tv" (from "mog.tv") "mog.vt" (from "mog.tv" or "omg.vt") "gom.tv" (from
 * "ogm.tv") "ogm.vt" (from "ogm.tv" or "omg.vt") For k = 3, there're 3 more
 * typos to consider:
 *
 * "gmo.tv" (from "mgo.tv" of "gom.tv") "mgo.vt" (from "mgo.tv" or "mog.vt")
 * "gom.vt" (from "gom.tv" or "ogm.vt") Since n = 9, it's not enough to buy all
 * domains with 3 or fewer typos, but it's enough to buy with 2 or fewer, so the
 * answer is 2.
 *
 * Note that equal domain strings that may be obtained differently are
 * considered the same.
 */

let getSwapStr = (str, start) => {
    let res = new Array()
    let arrStr = str.split("")
    for (let pos = 1; pos < str.length; pos++) {
        let arr = [...arrStr]
        if (arr[pos - 1] === "." || arr[pos] === "."
        || arr[pos - 1] == arr[pos]) {
            continue
        }
        let t = arr[pos - 1]
        arr[pos - 1] = arr[pos]
        arr[pos] = t
        res.push(arr.join(""))
    }

    return res;
}

let typosquatting = (n, domain) => {

    let totalPosible = new Set()
    totalPosible.add(domain)
    let levelRes = 0

    let getAllPosbile = (currentSet, level, count) => {
        let newSet = new Set()
        currentSet.forEach((value, key, map) => {
            let arr = getSwapStr(key, value)
            arr.map(v => newSet.add(v))
        })

        if (newSet.size == 0) {
            if(currentSet.size - 1 <= n)
                levelRes = -1
            return
        }

        if (count <= n) {
            newSet.forEach((value, key, map) => {
                if (currentSet.has(key))
                    newSet.delete(key)
            })

            if(newSet.size == 0){
                if(currentSet.size - 1 <= n)
                  levelRes = -1
                return
            }

            let mergeSet = new Set([...newSet, ...currentSet])
            getAllPosbile(mergeSet, level + 1, count + newSet.size)
        } else {
            if(levelRes == 0){
                levelRes = currentSet.size - 1 == n ? level - 1 : level - 2
            }
        }
    }
    getAllPosbile(totalPosible, 1, 0)

    return levelRes
}