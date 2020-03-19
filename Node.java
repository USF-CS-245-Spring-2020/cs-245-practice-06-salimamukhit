public class Node<T> {
    T value;
    Node next;
    Node prev;
    public Node(T value, Node prev, Node next) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
