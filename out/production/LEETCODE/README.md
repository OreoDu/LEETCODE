# LEETCODE

Problems from Leetcode.
You can find some details about algorithms and data structure [here](https://oreodu.github.io/).

## Data Structure

### Map

| Problems Number| Name                       | Context                                     |         |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 001            | Two Sum                    |                                             | [Easy](https://github.com/OreoDu/LEETCODE/blob/master/Data%20Structure/lc_001.py)    |
| 350           | Intersection of Two Arrays Ⅱ | [Set](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Map/lc350IntersectionOfArrays2.java), Two Pointers | Easy    |
| 015           | Three Sum                   |  Set, Map, [Two Pointers]()|  Medium |
|  3           | Longest Substring Without Repeating Characters  | [HashMap](), Array, Two Pointers  |  Medium   |
|     |                    |        |        |            


### Set
| Problems Number| Name                       | Context                                     |         |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 349            | Intersection of Two Arrays | [Set](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Set/lc349IntersectionOfArrays1.java), Two Pointers, Binary Search | Easy    |

### Heap
**Conditions of Use**
- Find the max/min
- Top k (nlogk)
- Data operation with O(logn) time complexity.

**Conditions that cannot use**
- the ceiling or floor of certain value(-> balanced BST).
- Find the maximum and minimum values of a certain interval. (->segment tree)
- Top k with time complexity.(-> quick sort)

### Linked list

| Problems Number| Name                       | Context                                     |         |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 002            | Add Two Numbers            | | [Medium](https://github.com/OreoDu/LEETCODE/blob/master/Data%20Structure/lc_002.py)    |
| 206  | Reverse Linked List  | [Recursive](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/LinkedList/lc206ReLList1.java), Iterative, Two pointers | Easy  |
| 092  | Reverse Linked List Ⅱ | [Recursive](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/LinkedList/lc92ReLList2.java), Iterative, Two pointers| Medium  |
| 024  | Swap Nodes in Pairs  | [Recursive](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/LinkedList/lc24SwapPairs.java), Iterative,Two pointers| Medium  |
| 025  | Reverse Nodes in k-Group  | [Recursive](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/LinkedList/lc25ReKGroup.java), Iterative,Two pointers| Hard  |
| 142  | Linked List Cycle  | [HashSet](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/LinkedList/lc142LListCycle.java), Floyd Cycle Dectctipn, Two pointers | Medium  |

### Stack
| Problems Number| Name                       | Context                                     |       |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 020  | Valid Parentheses  | [Stack](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc20ValidParentheses.java)       | Easy  |
| 232  | Implement Queue using Stacks  | [Stack](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc232ImQueuewithStacks.java)       | Easy  |
| 225  | Implement Stack using Queues  | [Queue](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc225ImStackwithQueue.java)       | Easy  |
| 155  | Min Stack  | [Stack](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc155MinStack.java)       | Easy  |
| 716  | Max Stack  | [Stack](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc716MaxStack.java)       | Easy  |
| 739  | Daily Temperature | [Stack](https://github.com/OreoDu/LEETCODE/blob/master/DataStructure/Stack/lc739DailyTemperature.java)  |       Medium          |
|  85   |  Maximal Rectangle     |  [Monotone Stack]()      |  Hard  |            
| 84 |  Largest Rectangle in Histogram  |  [Monotone Stack]()  |  Hard   |            
|     |                    |        |        |            
|     |                    |        |        |            




### Hash Table
| Problems Number| Name                       | Context                                     |         |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
|     |                    |        |        |            



### Trie

| Problems Number    | Name                       | Context          |        |
| ----| ------------------ | ----------------------- | ----------- |
| 208 | Implement Trie (Prefix Tree)   |   [Trie]()      | Medium  |
|  212  |  Word Search    |           [Trie](), BackTracking, DFS    |       Hard     |
|  421  |   Maximum XOR of Two Numbers in a Array     |   [Trie]()        |   Medium        |
|    |        |                              |                                |



## Search
### Binary Search
**Conditions of Use**
- In an ordered array.
- When you are asked to find an algorithm that has time complexity smaller than O(n).
- When you have to find the split position in the array that the left part meets a certain condition, and the right part does not.
- When you have to find a max/min to meet certain condition.

| Problems Number| Name                       | Context                                     |        |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 004            | Median of two sorted arrays| Binary search in two arrays  | [Hard](https://github.com/OreoDu/LEETCODE/blob/master/Search/lc_004.py)    |
| 029            | Divide Two Integers        |                              |                                |
|     |                    |        |        |            


### BFS
**Conditions of Use**
- Topological sort
- Connected block
- Hierarchical traversal
- The shortest path of a simple graph
- Given a transformation rule, at least how many steps should be taken from the initial state to the final state?

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 102     | Binary Tree Level Order Traversal |[BFS](), DFS                  | Medium   |
|                |                            |                              |                   



### DFS
**Conditions of Use**
- Find all the solutions that meet the condition.
- Binary Tree
- Combination problem: all the combinations that meet the conditions. (The order of the elements is irrelevant.)
- Permutation problem: all the permutations that meet the conditions. (The order of the elements is relevant.)

## Sort


## Greedy Algorithm



## Divide and Conquer
| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 23           | Merge k Sorted Lists |[Divide and Conquer](https://github.com/OreoDu/LEETCODE/blob/master/Divide%20and%20Conquer/lc23MergeKSortedLists.java), Priority Queue | Hard    |
|                |                            |                              |                   


## Recursion



## Backtracking

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 357           | Count Numbers with Unique Digits |[Backtracking](https://github.com/OreoDu/LEETCODE/blob/master/BackTracking/lc357CountNumbersWithUniqueDigits.java), Dynamic program | Medium    |
|  22    | Generate Parentheses   |      [Backtracking]() / DFS, BFS, Recursion + Memorize / Dynamic programing  |        Medium   |
|                |                            |                              |                                     |
|                |                            |                              |                                     |
|                |                            |                              |                                     |
|                |                            |                              |                                     |



## Dynamic Programing
**Important keys for DP:**
- definition of the different states    
- initial condition
- recurrence formula
- final results
- space optimize (state machine)

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
|   121 |  Best Time to Buy and Sell Stock |[Dynamic Programing]( ), Two Pointers |  Easy    |
|   122 |  Best Time to Buy and Sell Stock II    | [Dynamic Programing]( ), Greedy algorithm, DFS |   Easy     |
|   123 |  Best Time to Buy and Sell Stock III  | [Dynamic Programing]( ), DFS       |  Hard |
|   309 |  Best Time to Buy and Sell Stock with Cool Down |  [Dynamic Programing]( )        |          Medium         |
|   188 | Best Time to Buy and Sell Stock IV |   [Dynamic Programing]( )    |    Hard           |
|   714 | Best Time to Buy and Sell Stock with Transaction Fee |  [Dynamic Programing]( )        |  Medium   |
|   120 |      Triangle             | [Dynamic Programing]( ) |        Medium      |
|     |                            |                              |                                     |
|     |                            |                              |                                     |
|     |                            |                              |                                     |




## Graph

## String

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 6              | ZigZag Conversion   |   [Index pattern]()    |  Medium       |
|                |                            |                              |                                     |




### Trie
**Conditions of Use**
- Whether a string containing a certain prefix exists.
- Find words in character matrix

## Other

### Two Pointers

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 167            | Two Sum Ⅱ                 |                              |                                     |
| 42        |  Trapping Rain Water      | [Two Pointers](), Monotone Stack, dynamic programing    |        Hard             |
| pramp      |    Smallest Substring Of All Characters      |  [Two Pointers](), map  |                                     |
| 1695       |    Maximum Erasure Value  |      [Two Pointers](), map     |                                     |
|               |                                            |                              |                                     |



### Sliding window

| Problems Number| Name                       | Context                                     |      |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 003           | Longest substring without repeating        |[Sliding Window](https://github.com/OreoDu/LEETCODE/blob/master/Other/SlidingWindow/lc_003.py), Hash                            |   Medium                                 |
|               |                                            |                              |                                     |


### Union Find
**Conditions of Use**
- Check the connection status of the graph.
- Quickly merge two sets.

| Problems Number| Name                       | Context                                     |       |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 200            | Number of Islands          |[DFS](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc200dfs.java), [BFS](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc200bfs.java), [UnionFind](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc200UnionFind.java)   |   Medium |
| 547            | Friend Circles            | [DFS](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc547dfs.java), [BFS](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc547bfs.java), [UnionFind](https://github.com/OreoDu/LEETCODE/blob/master/Other/UnionFind/lc547UnionFind.java)   |  Medium |
|                |          |          |        |



## Weekly Contest

| Problems Number| Name                       | Context                                     |       |
| ---------------| -------------------------- | ------------------------------------------- | ----------- |
| 185-1          |                    |                              |                                     |
| 185-2          |                    |                              |                                     |
| 185-3          |                   |                              |                                     |
| 185-4             |                    |                              |                                     |
|   186-1(5392)     |   Maximum Score After Splitting a String   |                 | [Easy](https://github.com/OreoDu/LEETCODE/blob/master/WeeklyContest/186-1.py)   |
|   186-2(5393)     |   Maximum Points You Can Obtain from Cards     |             | [Medium](https://github.com/OreoDu/LEETCODE/blob/master/WeeklyContest/186-2.py) |
|   186-3(5394)     |   Diagonal Traverse II        |                              |                                     |
|   186-4(5180)     |   Constrained Subset Sum    |                              |                                     |



