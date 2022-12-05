
/**
 * 
 * Ref: https://app.codesignal.com/company-challenges/quora/dLi6vGRKNQ3xnaZDc
 * 
 * Quora has many questions covering a range of different topics, and making
 * sure they're discoverable is crucial. One way to tackle this challenge is by
 * using Quora's
 * 
 * @mention selectors and search service to look up questions under a certain
 *          topic as quickly as possible.
 * 
 * Let's imagine a simplified version of Quora where each question has only one
 * topic associated with it. In turn, the topics form a simplified solution
 * where each topic has a list of children, and all topics are descendants of a
 * single root topic.
 * 
 * Your job is to design a system that allows for quick searches of questions
 * under topics. There are sets of topics, questions, and queries, given in this
 * order. Each query has a desired topic as well as a desired string prefix. For
 * each query, return the number of questions that fall under the queried topic
 * and begin with the desired string. When considering topics, include all
 * descendants of the queried topic as well as the queried topic itself. In
 * other words, each query searches over the subtree of the topic.
 * 
 * The topic solution is given in the form of a flattened tree of topic names,
 * where each topic may optionally have children. If a topic has children, they
 * are listed after it within parentheses, and those topics may have children of
 * their own, etc. See the example below for the exact input format. The tree is
 * guaranteed to have a single root topic.
 * 
 * Finally, for ease of parsing, each topic name will be composed of English
 * alphabetical characters, and each question and query text will be composed of
 * English alphabetical characters, spaces, and question marks. Each question
 * and query text will be well behaved: there will be no consecutive spaces or
 * leading/trailing spaces. All queries, however, are case sensitive.
 * 
 * Example
 * 
 * For treeRepr = "Animals ( Reptiles Birds ( Eagles Pigeons Crows ) )",
 * 
 * questions = [ "Reptiles: Why are many reptiles green?", "Birds: How do birds
 * fly?", "Eagles: How endangered are eagles?", "Pigeons: Where in the world are
 * pigeons most densely populated?", "Eagles: Where do most eagles live?" ] and
 * 
 * queries = ["Eagles How en", "Birds Where", "Reptiles Why do", "Animals Wh"]
 * the output should be solution(treeRepr, questions, queries) = [1, 2, 0, 3].
 * 
 * The first query corresponds to the green area in the diagram below, since it
 * is looking for topics under Eagles, and the query string matches just one
 * question: "How endangered are eagles?" The second query corresponds to the
 * blue area in the diagram, which is the subtree of Birds, and matches two
 * questions that begin with "Where". The third corresponds to the red area,
 * which does not have any questions that begin with "Why do". The final query
 * corresponds to the entire tree, since Animals is the root topic, and matches
 * three questions.
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string treeRepr
 * 
 * Representation of a topics tree. Refer to the examples for more context on
 * format.
 * 
 * Guaranteed constraints: treeRepr.length ≤ 200.
 * 
 * [input] array.string questions
 * 
 * An array of questions. Each question contains a topic name, followed by a
 * colon and a space, and then the question text. It is guaranteed that for each
 * question its topic is present in the tree.
 * 
 * Guaranteed constraints: questions.length ≤ 20.
 * 
 * [input] array.string queries
 * 
 * An array of queries. Each query contains a topic name, followed by a space,
 * and then the query text. It is guaranteed that for each query its topic is
 * present in the tree.
 * 
 * Guaranteed constraints: queries.length ≤ 200.
 * 
 * [output] array.integer
 * 
 * An array of query answers. ith integer corresponds to the ith query.
 * 
 */


	// "A ( B ( C D ( P Q K ) E ) F ( G ( H I J ( K L M N ) ) ) )"
	solution = (treeRepr, questions, queries) => {
		// return a map = {key: topicName, value: question}
		const extractQuestions = qs => {
			const mapQuestion = new Map()
			qs.map(question => {
				const format = question.split(/:\s*/)
				const arr = [...(mapQuestion.get(format[0]) || []), format[1]]
				mapQuestion.set(format[0], arr)
			})
			
			return mapQuestion
		}
		
		// return a object = {topicName: topicName, question: query}
		const extractQuery = qs => {
			return qs.map(query => {
				const topic = query.match(/^\w+\s*/)
				return { topicName: topic[0].trim(), question: query.substring(topic[0].length) }
			})
		}
		
		const ques = extractQuestions(questions)
				
		const extractTreeRepsToTree = treeRepr => {
		
			const treeRprArr = treeRepr.split(/\s+/)
			let treeAdj = new Map()
			let stack = []

			for(let ind = 0; ind < treeRprArr.length; ind++){
				if(treeRprArr[ind] === '('){
					stack.push(treeRprArr[ind-1])
					continue
				}else if(treeRprArr[ind]  === ')'){
					stack.pop()
					continue
				}else {
					const val = stack[stack.length-1]
					const child = { val: treeRprArr[ind], question: ques.get(treeRprArr[ind]) || ''}
					const arr = [...(!treeAdj.has(val) ? [] : treeAdj.get(val)), child ]
					treeAdj.set(val || 'root', arr)
					
					// set root is child of another
					treeAdj.set(treeRprArr[ind], [child])
				}
			}
			
			return treeAdj
		}
		
        /**
		 * We can improve this function by using Trie data structure
		 */
		const findMatchingTopic = (query, treeAdj) => {
			
			const findMatching = (queryQuestion, treeQuestions) => {
				let res = 0
				queryQuestion !== '' && (treeQuestions || []).map(question => {
					res += question.startsWith(queryQuestion)
				})
				
				return res
			}
			
			let res = 0
			
			let stack = [{val: query.topicName, question: ''}]
			const queryQuestion = query.question
			while(stack.length > 0){
				const node = stack.pop()
				const listChild = treeAdj.get(node.val)
				res += findMatching(queryQuestion, listChild[0].question)
				
				if(listChild && listChild.length > 1){
					for(let ind = 1; ind < listChild.length; ind++){
						stack.push({val:  listChild[ind].val, question:  listChild[ind].question})
					}
				}
			}
			
			
			return res
		}
		
		let res = []
		
		const treeAdj = extractTreeRepsToTree(treeRepr)
		extractQuery(queries).map(query => {
		   res.push(findMatchingTopic(query, treeAdj))
		})
		
		return res 
	}