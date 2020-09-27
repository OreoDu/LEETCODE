'''
There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
In one step, you can take one card from the beginning or from the end of the row. 
You have to take exactly k cards.Your score is the sum of the points of the cards you have taken.
Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
>>> cardPoints = [1,2,3,4,5,6,1], k = 3
12
'''
### Important key:1.Think about the features of result before working out the solution.
#                 2.Never repeat calculations when you're using sliding window.

# Solution 1: Recursion (or DFS)
# But this solution was beyond the time limit.
class Solution(object):
    def maxScore(self, cardPoints, k):
        """
        :type cardPoints: List[int]
        :type k: int
        :rtype: int
        """
        start = 0
        end = len(cardPoints)-1
        dic = dict()
        if k==len(cardPoints) :return sum(cardPoints)
        def recur_score(newlist,k,start,end): 
            l = len(newlist)
            if k==1: 
                return max(newlist[0],newlist[l-1])
            else:
                str1 = str(start+1)+str(end)
                str2 = str(start)+str(end-1)
                if str1 not in dic:
                    dic[str1] = recur_score(newlist[1:], k-1,start+1,end)
                if str2 not in dic:
                    dic[str2] = recur_score(newlist[:l-1], k-1,start,end-1)
                res1 = newlist[0]+dic[str1]
                res2 = dic[str2]+newlist[l-1]
                return max(res1,res2)
        return recur_score(cardPoints,k,start,end)

# Solution 2: Sliding window,Count every possible final result and get the max value
class Solution(object):
    def maxScore(self, cardPoints, k):
        """
        :type cardPoints: List[int]
        :type k: int
        :rtype: int
        """
        l = len(cardPoints)
        if k==l :return sum(cardPoints)
        score = sum(cardPoints[:k])
        max_score = sum(cardPoints[:k])
        for i in range(k):
            score = score - cardPoints[k-1-i] + cardPoints[l-1-i]
            max_score = max(max_score,score)
        return max_score
    
# Solution 3: Sliding window (max(score) = total - min(sum of the middle sublist))
class Solution(object):
    def maxScore(self, cardPoints, k):
        """
        :type cardPoints: List[int]
        :type k: int
        :rtype: int
        """
        l = len(cardPoints)
        total = sum(cardPoints)
        sublist = sum(cardPoints[:l-k])
        min_sublist = sublist
        for i in range(k):
            sublist = sublist-cardPoints[i] + cardPoints[l-k+i]
            min_sublist = min(min_sublist,sublist)
        return total - min_sublist

if __name__ == "__main__":
    s = Solution()
    cardPoints = [1,2,3,4,5,6,1]
    k = 3
    res = s.maxScore(cardPoints,k)
    print(res)