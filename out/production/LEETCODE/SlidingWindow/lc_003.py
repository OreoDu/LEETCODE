'''
Given a string, find the length of the longest substring without repeating characters
>>> "abcabcbb"
3
'''
### Important key: Using different data structure to represent sliding window.
#                  Different ways to update the start of the sliding window.  
    
# The origunal solution which is not very good.
# Store the whole substring in the list until meet the character which has already been in the substring.
# The runing time is pretty slow because I deleted all the elements in the list every time and have some repeating steps(add the same elements)
class Solution:
    def lengthOfLongestSubstring(self,s:str)->int:
        if not s:return 0
        sub = list()
        lens = len(s)
        longest = 0
        cur_len = 0
        i = 0
        flag = 0
        while i<lens:
            if s[i] in sub:
                i = flag + sub.index(s[i]) +1
                flag = i
                # delete all the elements in the list.
                sub.clear()
            else:
                sub.append(s[i])
                cur_len = len(sub)
                i += 1
            longest = cur_len if cur_len > longest else longest
        return longest
      
# The list which stored the substring is like a Sliding window.
# Keep moving: add elements and delete some elements in some situation(Meet the repeating element).  
class Solution:
    def lengthOfLongestSubstring(self,s:str)->int:
        if not s:return 0
        sub = list()
        longest = 0
        for i in range(len(s)):
            if s[i] in sub:
                n = sub.index(s[i]) +1
                j = 0
                # only delete the elements from the begining to the repeating elements.
                while j<n:
                    del sub[0]
                    j+=1
            sub.append(s[i])
            cur_len = len(sub)
            longest = max(cur_len,longest)
        return longest
 
# Hash map : Dictionary
class Solution:
    def lengthOfLongestSubstring(self,s:str)->int:
        if not s:return 0
        m = dict()
        longest = 0
        start = 0
        for i in range(len(s)):
            if s[i] in m and m[s[i]]>= start:
                start = m[s[i]] +1
            m[s[i]] = i
            cur_len = i - start + 1
            longest = max(cur_len,longest)
        return longest

# Hash map : Array
class Solution:
    def lengthOfLongestSubstring(self,s:str)->int:
        if not s:return 0
        m = [-1 for _ in range(127)]
        longest = 0
        start = 0
        for i in range(len(s)):           
            start = max(m[ord(s[i])] +1,start)
            m[ord(s[i])] = i
            cur_len = i - start + 1
            longest = max(cur_len,longest)
        return longest


if __name__ == "__main__":
    str1 ='aaaa'
    s = Solution()
    lens = s.lengthOfLongestSubstring(str1)
    print(lens)          

