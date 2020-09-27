'''
Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings 
(i.e. left substring and right substring).
The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
>>> s = "011101"
5
'''
### Important Key: Simulate the game process
    
class Solution(object):
    def maxScore(self, s):
        """
        :type s: str
        :rtype: int
        """
        num0 = 0
        num1 = 0
        score = 0
        for i in range(len(s)):
            if s[i] == '1': num1 += 1
        for i in range(len(s)-1): #non-empty substring
            if s[i] == '0': 
                num0 += 1
            if s[i] == '1': 
                num1 -= 1
            score = max(score,num1+num0)
        return score

if __name__ == "__main__":
    s = Solution()
    string = '011101'
    result = s.maxScore(string)
    print(result)