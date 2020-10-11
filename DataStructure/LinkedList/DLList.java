public class DLList<T> {

    private static class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> pre;

        public Node(Node<T> pre, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
    }

    private int size= 0;
    private Node<T> sentinel;

    public DLList() {
        sentinel = new Node<>(null, null, null);
    }

    public DLList(T x) {
        sentinel = new Node<>(null,null,null);
        Node<T> first = new Node<>(sentinel,x,sentinel);
        sentinel.pre = first;
        sentinel.next = first;
        size++;
    }

    public int size() {
        return size;
    }

    public void addFirst(T x) {
        Node<T> n = new Node<>(sentinel, x, sentinel.next);
        sentinel.next = n;
        n.next.pre = n;
        size++;
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public T removeFirst() {
        if (size == 0) {
            System.out.println("The list is empty.");
            return null;
        }else {
            Node<T> re  = sentinel.next;
            sentinel.next = sentinel.next.next;
            re.next.pre = sentinel;
            size--;
            return re.item;
        }
    }

    public T getLast() {
        return sentinel.pre.item;
    }

    public void addLast(T x) {
        Node<T> n = new Node<>(sentinel.pre,x,sentinel);
        sentinel.pre.next = n;
        sentinel.pre = n;
        size++;
    }

    public T removeLast() {
        if(size == 0) {
            System.out.println("The list is empty");
            return null;
        }else {
            Node<T> re = sentinel.pre;
            sentinel.pre = re.pre;
            re.pre.next = sentinel;
            size--;
            return re.item;
        }
    }

    public void printDLList() {
        Node<T> p = sentinel.next;
        while(p != sentinel) {
            System.out.print(p.item + "->");
            p = p.next;
        }
        System.out.println("The end of the list");
    }
}
