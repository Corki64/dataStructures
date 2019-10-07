/**
 * Implementation of generic dynamic array.
 */
public class DynamicArray<T> implements Iterable<T> {
        private T[] arr;
        private int len = 0;
        private int capacity = 0;

        public DynamicArray(int capacity) {
            if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
            this.capacity = capacity;
            arr = (T[]) new Object[capacity];
        }

        public int size() {
            return len;
        }





    /**
     * Removing an item from an dynamic array.
     * @param removeIndex - index to be removed
     * @return - data held in index removed
     */
    public T removeAt(int removeIndex) {
            if (removeIndex >= len || removeIndex < 0) throw new IndexOutOfBoundsException();

            // copy the data to be returned before deletion
            T data = arr[removeIndex];

            // create a new array to hold the modified array missing the element we are removing
            T[] newArray = (T[]) new Object [len - 1];

            // this is the funky part so we have two counters we begin traversing down the array copying every element
            // using our else statement, but when we arrive at the index to be removed we decrement our j counter
            // then continue copying from there
            for (int i = 0, j = 0; i < len; i++, j++) {
                if (i == removeIndex) {
                    j--;
                }
                else {
                    newArray[j] = arr[i];
                }
            }

            // copy the final contents of our modified array to overwrite our arr
            arr = newArray;

            // change counter for our actual array size
            capacity = --len;

            // return element removed from array
            return data;
        }

        public boolean remove(Object objectIn) {
            int index = indexOf(objectIn);
            if (index == -1) {
                return false;
            }
            removeAt(index);
            return true;
        }

        public int indexOf(Object objectIn) {
            for (int i = 0; i < len; i++) {
                if (objectIn == null) {
                    if (arr[i] == null) {
                        return i;
                    }
                    else {
                        if (objectIn.equals(arr[i])) {
                            return i;
                        }
                    }
                }
            }
            return -1;
        }


        public boolean contains(Object objectIn) {
            return indexOf(objectIn) != -1;
        }

        public java.util.Iterator<T> iterator() {
            return new java.util.Iterator<T>() {
                int index = 0;

                @Override
                public boolean hasNext() {
                    return index < len;
                }

                @Override
                public T next() {
                    return arr[index++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
             };
        }

        @Override
        public String toString() {
            if (len == 0) return "[ ]";
            else {
                StringBuilder sb = new StringBuilder(len).append("[");
                for (int i = 0; i < len; i++) {
                    sb.append(arr[i] + ", ");
                }
                return sb.append(arr[len - 1] + "]").toString();
            }
        }
}
