'''
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
>>> (2 -> 4 -> 3) + (5 -> 6 -> 4)
7 -> 0 -> 8
'''
### Important Key: the pointer of the Linklist

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
        
# Solution 1 (Using L1 to store the result)
class Solution(object):
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        carry = 0
        h1 = l1
        while l1 and l2:
            carry, l1.val = divmod(l1.val + l2.val + carry,10)
            pre1, l1 = l1, l1.next        
            l2 = l2.next
        if l1:
            while carry:
                carry, l1.val = divmod(l1.val + carry,10)
                if not l1.next and carry:
                    l1.next = ListNode(carry) 
                    break
                l1 = l1.next
        elif l2:           
            pre1.next = l2
            while carry:
                carry, l2.val = divmod(l2.val + carry,10)
                if not l2.next and carry:
                    l2.next = ListNode(carry) 
                    break
                l2 = l2.next
        else:        
            if carry!=0:pre1.next = ListNode(carry)          
        return h1
    
# Solution 1 in a better way
class Solution(object):
	def addTwoNumbers(self, l1, l2):
		a,b,p,carry = l1,l2,None,0
		while a or b:
			carry,val = divmod((a.val if a else 0)+(b.val if b else 0)+carry,10)
			p,p.val = a if a else b,val
			a,b = a.next if a else None,b.next if b else None
			p.next = a if a else b
		if carry:
			p.next = ListNode(carry)
		return l1

# Solution 2 (Creat a new listnode)
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        prenode = ListNode(0)
        lastnode = prenode
        cur = 0
        while cur or l1 or l2:
            cur, val = divmod(cur + (l1.val if l1 else 0) + (l2.val if l2 else 0), 10)
            lastnode.next = ListNode(val)
            lastnode = lastnode.next
            l1,l2 = l1.next if l1 else None,l2.next if l2 else None
        return prenode.next


def generateList(l: list) -> ListNode:
    prenode = ListNode(0)
    lastnode = prenode
    for val in l:
        lastnode.next = ListNode(val)
        lastnode = lastnode.next
    return prenode.next

def printList(l: ListNode):
    while l:
        print("%d, " %(l.val), end = '')
        l = l.next
    print('')

if __name__ == "__main__":
    l1 = generateList([2,4,3])
    l2 = generateList([5,6,4])
    printList(l1)
    printList(l2)
    s = Solution()
    sum1 = s.addTwoNumbers(l1, l2)
    printList(sum1)
            
            
            