
public class SLList<T> {

    private static class ListNode<T> {
        public T val;
        public ListNode<T> next;

        public ListNode() {}

        public ListNode(T i) { val = i;}

        public ListNode(T i, ListNode<T> n) {
            val = i;
            next = n;
        }
    }

    // Invariant: Sentinel (be guaranteed to be true during code execution.)
    private ListNode<T> sentinel;
    // Cache: size (put aside data to speed up retrieval)
    private int size = 0;

    public SLList() {
        sentinel = new ListNode<>(null,null);
    }

    public SLList(T x) {
        ListNode<T> first = new ListNode<>(x,null);
        sentinel = new ListNode<>(null,first);
        size++;
    }

    private int size() { return size; }

    public void addFirst(T x) {
        ListNode<T> n = new ListNode<>(x,sentinel.next);
        sentinel.next = n;
        size++;
    }

    public T getFirst() {
        if(sentinel.next == null) return null;
        return sentinel.next.val;
    }

    public T removeFirst() {
        if(size == 0) {
            System.out.println("The List is empty");
            return null;
        }else {
            ListNode<T> re = sentinel.next;
            sentinel.next = sentinel.next.next;
            size--;
            return re.val;
        }
    }

    public void addLast(T x) {
        ListNode<T> p = sentinel;
        while(p.next != null) p = p.next;
        p.next = new ListNode<>(x,null);
        size++;
    }

    public void printSLList() {
        ListNode<T> p = sentinel.next;
        while(p!= null) {
            System.out.print(p.val + "-->");
            p = p.next;
        }
        System.out.println("End of the List.");
    }
}