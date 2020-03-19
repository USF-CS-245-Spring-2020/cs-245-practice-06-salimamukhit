public class ArrayList<T> implements List {
    private T[] array;
    int items;
    int size;

    public ArrayList() {
        this.size = 0;
        this.array = (T[]) new Object[this.size];
        this.items = 0;
    }

    @Override
    public void add(Object item) {
        T[] temp;
        // In case it is empty
        if (this.size == 0) {
            this.size = 10;
            this.array = (T[]) new Object[this.size];
        }
        // Check if we need some additional space for a new item
        if (this.items == this.array.length) {
            this.size *= 2;
            temp = this.array;
            this.array = (T[]) new Object[this.size];
            int i=0;
            for (T val : temp) {
                this.array[i] = val;
                i++;
            }
            this.array[this.items] = (T) item;
        }
        // Check if we don't need any additional space
        else {
            this.array[this.items] = (T) item;
        }
        this.items++;
    }

    // Helper function that we use for inserting an item
    private void shiftArray(int pos, Object item) {
        T tmp = this.array[pos];
        this.array[pos] = (T) item;
        for (int i = pos+2; i < this.items + 1; i++) {
            this.array[i] = this.array[i-1];
        }
        this.array[pos+1] = tmp;
    }

    @Override
    public void add(int pos, Object item) {
        // Initializing the array
        if (this.size == 0) {
            this.size = 10;
            this.array = (T[]) new Object[this.size];
        }
        // Check if we need some additional space for a new item
        if (this.items == this.array.length) {
            this.size *= 2;

            T[] temp = this.array;
            this.array = (T[]) new Object[this.size];

            for (int i = 0; i < temp.length; i++) {
                array[i] = temp[i];
            }
            shiftArray(pos, item);
        }
        // Check if we don't need any additional space
        else {
            if (this.array[pos] == null) {
                this.array[pos] = (T) item;
            }
            else {
                shiftArray(pos, item);
            }
        }
        this.items++;
    }

    @Override
    public Object get(int pos) {
        // Returns an item on this position
        if (this.array[pos] != null) {
            return this.array[pos];
        }
        return null;
    }

    @Override
    public Object remove(int pos) {
        // Removes an item and shifts the array
        if (pos == this.items) {
            this.array[pos] = null;
        }
        if (this.size == 0) {
            return null;
        }
        Object value = this.array[pos];
        this.array[pos] = null;
        this.items--;
        return value;
    }

    @Override
    public int size() {
        // Returns the amount of items in the array
        return this.items;
    }

    public void printArrayList() {
        for (T val : this.array) {
            if (val != null) {
                System.out.println(val);
            }
        }
    }

    // Helper function I used for debugging
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        Integer int_1 = new Integer(5);
        Integer int_2 = new Integer(6);
        Integer int_3 = new Integer(8);
        Integer int_4 = new Integer(11);
        Integer int_5 = new Integer(12);
        al.add(int_1);
        al.add(int_2);
        al.add(int_3);
        al.add(int_5);
        al.add(2, int_4);
        al.printArrayList();
        System.out.println("Size is "+al.size());
        al.remove(0);
        al.printArrayList();
        System.out.println("Size is "+al.size());
    }
}
