'''
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
>>> nums = [2, 7, 11, 15], target = 9
[0, 1]

'''
### Important Key: Store D-vlue in a hashmap

### Brute solutions ### O(n) = n(n-1) / 2
def twoSum(nums, target):
    r = list()
    n = len(nums)
    for i in range(n):
        for j in range(n-i):
            if nums[i]+nums[j] == target:
                r = [i,j]
                return r

### Use D-value ### O(n) = n(n-1) / 2 (The worset situation)
def twoSum(nums, target):
    r = list()
    n = len(nums)
    for i in range(n):
        d = target - nums[i]
        if d < 0: continue
        for j in range(n-i):
            if d == nums[j]:
                r = [i,j]
                return r
                     
### use map data structure to store the D-value
def twoSum(nums, target):
    h = {}
    for i,num in enumerate(nums):
        if target - num in h:
            return [h[target - num], i]
        h[num] = i
        
if __name__ == "__main__":
    nums = [2, 3, 3, 4,3,6,19]
    target = 6
    print(twoSum(nums,target))         

