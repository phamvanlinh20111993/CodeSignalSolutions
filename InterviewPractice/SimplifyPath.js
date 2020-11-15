/**
 *
 */
simplifyPath = p => {
     p = p.replace(/(\/)(\.\/)+/g, "$1")
     p = p.replace(/\/{2,}(\.\/+)*/g, "\/")

    let sp = p.split("..")
    let stack = []
    // I spent 4000 just because the beginning of the article said that
    // ' All directories in the path are guaranteed to consist only of English letters.' :v
    // But but I did see . in the path name some hidden tests -_-
    // First time line 11:  let spN = path.match(/\w+/g)
    for(const path of sp){
        let spN = path.match(/[a-zA-Z0-9.]+/g)
        if(spN){
            for(const s of spN)
                stack.push(s)
        }
        stack.push('..')
    }
    stack.pop()

    for(let ind = 0;ind < stack.length;){
        if(stack[ind] == ".."){
            stack.splice(ind, 1)
            if(ind-1 >= 0)
                stack.splice(ind-1, 1)
            ind -= 2
        }
        ind++
    }

    return "/" + stack.join('/')
}
