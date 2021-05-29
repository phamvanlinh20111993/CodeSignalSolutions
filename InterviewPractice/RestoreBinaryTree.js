/**
 * Note: Your solution should have O(inorder.length) time complexity, since this
 * is what you will be asked to accomplish in an interview.
 * 
 * Let's define inorder and preorder traversals of a binary tree as follows:
 * 
 * Inorder traversal first visits the left subtree, then the root, then its
 * right subtree; Preorder traversal first visits the root, then its left
 * subtree, then its right subtree. For example, if tree looks like this:
 * 
 * 1 / \ 2 3 / / \ 4 5 6 then the traversals will be as follows:
 * 
 * Inorder traversal: [4, 2, 1, 5, 3, 6] Preorder traversal: [1, 2, 4, 3, 5, 6]
 * Given the inorder and preorder traversals of a binary tree t, but not t
 * itself, restore t and return it.
 * 
 * Example
 * 
 * For inorder = [4, 2, 1, 5, 3, 6] and preorder = [1, 2, 4, 3, 5, 6], the
 * output should be restoreBinaryTree(inorder, preorder) = { "value": 1, "left": {
 * "value": 2, "left": { "value": 4, "left": null, "right": null }, "right":
 * null }, "right": { "value": 3, "left": { "value": 5, "left": null, "right":
 * null }, "right": { "value": 6, "left": null, "right": null } } } For inorder =
 * [2, 5] and preorder = [5, 2], the output should be restoreBinaryTree(inorder,
 * preorder) = { "value": 5, "left": { "value": 2, "left": null, "right": null },
 * "right": null } Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.integer inorder
 * 
 * An inorder traversal of the tree. It is guaranteed that all numbers in the
 * tree are pairwise distinct.
 * 
 * Guaranteed constraints: 1 ≤ inorder.length ≤ 2 · 103, -105 ≤ inorder[i] ≤
 * 105.
 * 
 * [input] array.integer preorder
 * 
 * A preorder traversal of the tree.
 * 
 * Guaranteed constraints: preorder.length = inorder.length, -105 ≤ preorder[i] ≤
 * 105.
 * 
 * [output] tree.integer
 * 
 * The restored binary tree.
 */
//
// Binary trees are already defined with this interface:
	function Tree(x) {
	  this.value = x;
	  this.left = null;
	  this.right = null;
	}	

/**
 * Try to find another solution to improve this complexity if i have time.
 */
	let buildTree = treeStructure =>{
		let tree = new Tree(treeStructure[0].val)
		let treeTmp = tree;
		let currentLevel = 1;
		let treeInMap = new Map()
		treeInMap.set(treeStructure[0].val, tree)
		for(let ind = 1; ind < treeStructure.length; ind++){
			if(treeStructure[ind].isLeftChild){
				let leftChild = treeInMap.get(treeStructure[ind].parent)
				leftChild.left = new Tree(treeStructure[ind].val)
				treeInMap.set(treeStructure[ind].val, leftChild.left)
			}else{
				let rightChild = treeInMap.get(treeStructure[ind].parent)
				rightChild.right = new Tree(treeStructure[ind].val)
				treeInMap.set(treeStructure[ind].val, rightChild.right)
			}
		}	
		return tree;
	}

	restoreBinaryTree = (inorder, preorder) => {
		
		let storeInorderIndex = new Map()
		inorder.map((v, i) => storeInorderIndex.set(v, 
			{ind: i, 
			 isConnectLeftChild: false, 
			 isConnectRightChild: false,
			 isExistLeftChild: false, 
			 isExistRightChild: false,
			 isRightChild: false,
			 isLeftChild: false,
			 level: 0, 
			 isVisited: false, 
			 parent: null}))
			 
		for(let ind = 0; ind < preorder.length; ind++){
			let objectData = storeInorderIndex.get(preorder[ind])
			if(objectData.ind - 1 >= 0){
			 let nextObjectData = {...storeInorderIndex.get(inorder[objectData.ind - 1])}
				if(!nextObjectData.isVisited)
					objectData.isExistLeftChild = true;
			}
			if(objectData.ind + 1 < inorder.length){
			    let nextObjectData = {...storeInorderIndex.get(inorder[objectData.ind + 1])}
				if(!nextObjectData.isVisited)
					objectData.isExistRightChild = true;
			}
			// find parent and level up
			if(ind > 0){
				let tmp = objectData.ind - 1
				let isExistLeftChild = false;
				while(tmp >= 0){
					let objectLeftData = {...storeInorderIndex.get(inorder[tmp])}
					if(objectLeftData.isVisited 
						&& objectLeftData.isExistRightChild 
						&& !objectLeftData.isConnectRightChild){
						objectLeftData.isConnectRightChild = true
						storeInorderIndex.set(inorder[tmp], objectLeftData)
						objectData.parent = inorder[tmp]
						objectData.level = objectLeftData.level + 1
						objectData.isRightChild = true
						isExistLeftChild = true
						break;
					}else{
						tmp--;
					}
				}	
				// find right parent
				if(!isExistLeftChild){
					let tmp = objectData.ind + 1
					while(tmp < inorder.length){
						let objectRightData = {...storeInorderIndex.get(inorder[tmp])}
						if(objectRightData.isVisited 
							&& objectRightData.isExistLeftChild
						    && !objectRightData.isConnectLeftChild){
							objectRightData.isConnectLeftChild = true
							storeInorderIndex.set(inorder[tmp], objectRightData)
							objectData.parent = inorder[tmp]
							objectData.isLeftChild = true
							objectData.level = objectRightData.level + 1
							break;
						}else{
							tmp++;
						}
					}
				}
			}
			objectData.isVisited = true
			storeInorderIndex.set(preorder[ind], objectData)
		}
		
		let treeStructure = []
		for(let k of storeInorderIndex.keys()){
			let v = storeInorderIndex.get(k)
			treeStructure.push({val: k, 
                                isLeftChild: v.isLeftChild, 
                                isRightChild: v.isRightChild, 
                                level: v.level, 
                                parent: v.parent})
		}
        
		treeStructure = treeStructure.sort((a, b) => a.level - b.level)
		return buildTree(treeStructure);
	}
	
	
	
	/** Try with O(n) time, we can using n time when build tree in loop but, i split logic so its 
	 * O(nlog(n)) because sort * */
	restoreBinaryTree = (inorder, preorder) => {
		
		let storeInorderIndex = new Map()
		inorder.map((v, i) => storeInorderIndex.set(v, 
			{ind: i, 
			 isExistLeftChild: false, 
			 isExistRightChild: false,
			 isRightChild: false,
			 isLeftChild: false,
			 level: 0, 
			 isConnectLeftChild: false,
			 isConnectRightChild: false,
			 val: v,
			 isVisited: false, 
			 parent: null}))
			 
		let stackCheckParent = new Array();
			 
		for(let ind = 0; ind < preorder.length; ind++){
			let objectData = {...storeInorderIndex.get(preorder[ind])}
			let parentOnStack = stackCheckParent.length - 1 >= 0 ? {...stackCheckParent[stackCheckParent.length - 1]}
																: {...storeInorderIndex.get(preorder[ind])}
			
			if(objectData.ind - 1 >= 0){
			 let nextObjectData = storeInorderIndex.get(inorder[objectData.ind - 1])
				if(!nextObjectData.isVisited)
					objectData.isExistLeftChild = true;
			}
			
			if(objectData.ind + 1 < inorder.length){
			    let nextObjectData = storeInorderIndex.get(inorder[objectData.ind + 1])
				if(!nextObjectData.isVisited)
					objectData.isExistRightChild = true;
			}
			
			if(parentOnStack.isExistLeftChild && !parentOnStack.isConnectLeftChild){
				parentOnStack.isConnectLeftChild = true
				objectData.isLeftChild = true
				objectData.parent = parentOnStack.val
				objectData.level = parentOnStack.level + 1
			}else 
			if(parentOnStack.isExistRightChild && !parentOnStack.isConnectRightChild){
				parentOnStack.isConnectRightChild = true
				objectData.isRightChild = true
				objectData.parent = parentOnStack.val
				objectData.level = parentOnStack.level + 1
			}
			
			let count = 0
			if(parentOnStack.isExistLeftChild){
				if(parentOnStack.isConnectLeftChild) count++
			}else{
				count++
			}
			
			if(parentOnStack.isExistRightChild){
				if(parentOnStack.isConnectRightChild) count++
			}else{
				count++
			}
			
			if(count == 2 && stackCheckParent.length > 0){
				stackCheckParent.pop()
			}else{
				stackCheckParent.length > 0 && (stackCheckParent[stackCheckParent.length - 1] = {...parentOnStack})
			}
			
			objectData.isVisited = true;
			// i dont undertand, but if not put this, isVisited will be false, fuck of js
            parentOnStack.isVisited = true;
			storeInorderIndex.set(preorder[ind], {...objectData})
			storeInorderIndex.set(parentOnStack.val, {...parentOnStack})	
			if(objectData.isExistLeftChild || objectData.isExistRightChild){
				stackCheckParent.push({...objectData})
			}
		}
		
		let treeStructure = []
		for(let k of storeInorderIndex.keys()){
			let v = storeInorderIndex.get(k)
			treeStructure.push({val: k, isLeftChild: v.isLeftChild, isRightChild: v.isRightChild, 
				level: v.level, parent: v.parent})
		}
		treeStructure = treeStructure.sort((a, b) => a.level - b.level)
		
		return buildTree(treeStructure);
	}
	

