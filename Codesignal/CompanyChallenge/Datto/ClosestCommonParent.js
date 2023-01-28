/**
 * https://app.codesignal.com/company-challenges/datto/qTNybKyWB9Nj72x8h
 * 
 * You're on a team at Datto tasked with implementing a backup system aimed at
 * minimizing storage space. While exploring potential solutions, you notice
 * something interesting: while performing backups on enterprise client
 * accounts, the system often outputs clusters of files originating from the
 * same source file, but each with slight differences. You come up with an idea
 * to back up the original file and then use incremental differences to generate
 * all the other files as needed.
 * 
 * To implement this feature, you start by finding the closest common parent
 * file (CCPF) of two files. More specifically, if you define the distance
 * between a parent (the original) file and a child (modified) file as the
 * number of intermediate files between them, then their CCPF is the file that
 * has the least total distance to both of the given files among all of the
 * files in the cluster. Assume that the distance between a file and a file
 * itself is 0.
 * 
 * Given a list of files in a cluster, and the files each of them originated
 * from as an array, parents, find the CCPF of file1 and file2.
 * 
 * Example
 * 
 * For files = ["F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8"], parents =
 * ["-1", "F1", "F1", "F2", "F2", "F4", "F4", "F4"], file1 = "F5", and file2 =
 * "F8", the output should be solution(files, parents, file1, file2) = "F2".
 * 
 * The CCPF of "F5" and "F8" is "F2", since it's a parent for both "F5" and
 * "F8", and is located below "F1" (which is also a parent to both of the
 * files).
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.string files
 * 
 * Array of unique file names. File names can only contain digits and English
 * letters and can't be empty.
 * 
 * Guaranteed constraints: 2 ≤ files.length ≤ 50.
 * 
 * [input] array.string parents
 * 
 * Array of the same length as files, where the parents[i] is the direct parent
 * of files[i]. If parents[i] = "-1", then i is the original file in the cluster
 * and has no parent. It is guaranteed that there is only one original file in
 * the cluster, and all other files originated from it. It is also guaranteed
 * that for each valid i such that parents[i] ≠ "-1" parents[i] is present in
 * files.
 * 
 * Guaranteed constraints: parents.length = files.length.
 * 
 * [input] string file1
 * 
 * The first file, a string that is guaranteed to be present in files.
 * 
 * Guaranteed constraints: 1 ≤ file1.length ≤ 10.
 * 
 * [input] string file2
 * 
 * The second file, a string that is guaranteed to be present in files.
 * 
 * Guaranteed constraints: 1 ≤ file2.length ≤ 10, file1 ≠ file2.
 * 
 * [output] string
 * 
 * The CCPF of file1 and file2.
 */


let listRoot = [], 
    findFistChild = false, 
    isChild = '';
dfsFindParent = (root, tree, a, b) => {
    let listChild = tree.get(root);
    if(listChild){
        for(let i = 0; i < listChild.length; i++){
            if(findFistChild)
                break;
            if(a == listChild[i] || b == listChild[i]){
                isChild = a == listChild[i] ? a : b;
                if(!findFistChild){
                    listRoot.push(isChild);
                    findFistChild = true;
                    break;
                }
            }else
               dfsFindParent(listChild[i], tree, a, b) 
        }
        
        listRoot.push(root)
    }
}

dfs = (root, tree, a, isVisited) => {
    let stack = [root];
    
    while(stack.length > 0){
        let node = stack.pop();
        if(node == a)
            return true;
        
        isVisited.set(node, 1);
        let listChild = tree.get(node);
        if(listChild){
            for(let ind = 0; ind < listChild.length; ind++)
                if(!isVisited.has(listChild[ind]))
                    stack.push(listChild[ind]);
        }
    }
    
    return false;
}

solution = (files, parents, file1, file2) => {
    
    let tree = new Map(), root = '';
    for(let i = 0; i < files.length; i++){
        if(parents[i] != '-1'){
            let arr = tree.get(parents[i]) || []
            arr.push(files[i])
            tree.set(parents[i], arr)
        }else
            root = files[i];
    }
    
    dfsFindParent(root, tree, file1, file2);
    isChild = isChild == file1 ? file2 : file1;
    let isVisited = new Map();

    for(const parent of listRoot)
         if(dfs(parent, tree, isChild, isVisited))
             return parent;
    
    return 'There are some mistakes at the beginning of the article'
}
