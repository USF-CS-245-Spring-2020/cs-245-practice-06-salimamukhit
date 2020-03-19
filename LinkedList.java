public class LinkedList<T> implements List {
    Node head;
    int items;
    public LinkedList() {
        this.head = null;
        this.items = 0;
    }

    private Node<T> getTail(Node head_node) {
        // Error checking if head is the tail
        if(head_node.next == null) return head_node;

        Node curr = head_node;
        while(curr.next != null) curr = curr.next;

        return curr;
    }

    private Node<T> getNode(int pos) {
        // Error checking if the list doesn't exist
        if(this.head == null) return null;

        // Loop through the list and increment our counter and the position
        int currPos = 0;
        Node curr = this.head;
        while(curr.next != null && currPos < pos) {
            curr = curr.next;
            currPos++;
        }

        // Check if while loop succeeded by checking negation cases
        if(currPos != (pos - 1) && currPos != pos) {
            return null;
        }
        return curr;
    }

    @Override
    public void add(Object item) {
        // If the LinkedList is empty then we need to set a new head
        if(this.head == null) {
            this.head = new Node(item, null, null);
        }
        // Otherwise in this method we insert the new Node as the tail of the list
        else {
            Node oldTail = getTail(this.head);
            oldTail.next = new Node(item, oldTail, null);
        }
        this.items++;
    }

    @Override
    public void add(int pos, Object item) {
        // Error checking if list is empty
        if(this.head == null && pos != 0) return;

        // If we are inserting at the head we need to take special precautions
        if(pos == 0) {
            // If the LinkedList is empty then we need to set a new head
            if(this.head == null) {
                this.head = new Node(item, null, null);
            }
            // If a head already exists then we need to redefine the head as the new node
            else {
                Node newHead = new Node(item, null, this.head);
                this.head.prev = newHead;
                this.head = newHead;
            }
        } else {
            Node selected = getNode(pos);

            // Error checking
            if(selected == null) return;

            // Inserting
            Node nextNode = selected.next;
            Node newNode = new Node(item, selected, nextNode);
            selected.next = newNode;
           if(nextNode != null) nextNode.prev = newNode;
        }
        this.items++;
    }

    @Override
    public Object get(int pos) {
        // Error checking if list is empty
        if(this.head == null) return null;

        // Loop through the list and increment our counter and the position
        int currPos = 0;
        Node curr = this.head;
        while(curr != null && currPos < pos) curr = curr.next;

        // Check if while loop succeeded by checking negation cases
        if(currPos != pos || curr == null) {
            return null;
        }
        return curr.value;
    }

    @Override
    public Object remove(int pos) {
        // Removing the head case
        if(pos == 0) {
            // Save the value in a temporary Object because we will null the Node out before we return.
            Object value;
            // If the head is not the only node
            if(this.head.next != null) {
                value = this.head.value;
                this.head = this.head.next;
                this.head.prev = null;
            }
            // If the head is the only node then we null the head
            else {
                value = this.head.value;
                this.head = null;
            }
            this.items--;
            return value;
        }

        // Get the node that we wish to insert at
        Node selected = getNode(pos);

        // If selected is null then it doesn't exist, so return null.
        if(selected == null) {
            return null;
        }

        // If the selected node is a tail then we treat is differently.
        if(selected.next == null) {
            (selected.prev).next = null;
        } else {
            (selected.prev).next = selected.next;
        }
        this.items--;
        return selected.value;
    }

    @Override
    public int size() {
        return this.items;
    }

    private void printLL() {
        Node curr = this.head;
        while(curr != null) {
            if(curr.next != null) System.out.printf("[%d] ->", curr.value);
            else System.out.printf("[%d]\n", curr.value);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.add(new Integer(0));
        ll.add(new Integer(1));
        ll.add(new Integer(2));
        ll.add(new Integer(3));
        ll.add(new Integer(4));
        ll.add(new Integer(5));
        ll.printLL();

        ll.remove(0);
        ll.printLL();
        ll.add(0, 0);
        ll.printLL();
        ll.add(6);
        ll.printLL();
        ll.add(3,3);
        ll.printLL();
    }
}
