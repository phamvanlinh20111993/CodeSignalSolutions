/**
 *
 */
blockStorageRewrites = (blockCount, writes, threshold) => {

    let bl = new Array(blockCount).fill(0)

    for(const [a, b] of writes){
        for(let i = a; i <= b; i++){
            bl[i] += 1
        }
    }

    let r = []
    for(let ind = 0; ind < blockCount;){

        if(bl[ind] >= threshold){
            let start = ind
            let end = ind+1

            while(end < blockCount && bl[end] >= threshold) end++;

            r.push([start, end-1])
            ind = end
        }

        ind++
    }

    return r
}
