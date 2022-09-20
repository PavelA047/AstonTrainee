package arrayList;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final Object[] EMPTY_ELEMENT_DATA_WITH_DEFAULT_CAPACITY = new Object[DEFAULT_CAPACITY];
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = EMPTY_ELEMENT_DATA_WITH_DEFAULT_CAPACITY;
    }

    @Override
    public void add(E element) {
        add(element, elementData, size);
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow() {
        if (elementData.length > 0) {
            return elementData = Arrays.copyOf(elementData, size + DEFAULT_CAPACITY);
        } else {
            return elementData = EMPTY_ELEMENT_DATA_WITH_DEFAULT_CAPACITY;
        }
    }

    @Override
    public void clear() {
        elementData = EMPTY_ELEMENT_DATA;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException("there is no such index");
        return (E) elementData[index];
    }

    @Override
    public void remove(E element) {
        if (size == 0) throw new IndexOutOfBoundsException("there is no such index");
        int removedIndex = indexOf(element);
        Object[] tempElementDataLeft = Arrays.copyOfRange(elementData, 0, removedIndex);
        Object[] tempElementDataRight = Arrays.copyOfRange(elementData, removedIndex + 1, elementData.length - 1);
        elementData = new Object[elementData.length - 1];
        int i = 0;
        for (; i < tempElementDataLeft.length; i++) {
            elementData[i] = tempElementDataLeft[i];
        }
        for (int j = 0; j < tempElementDataRight.length; j++, i++) {
            elementData[i] = tempElementDataRight[j];
        }
        size--;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != 0;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) return i;
        }
        return 0;
    }

    @Override
    public void set(int index, E element) {
        if (index >= size) throw new IndexOutOfBoundsException("there is no such index");
        elementData[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                if (elementData.length == 0) return false;
                return elementData[i] != null && elementData[i] != elementData[elementData.length - 1];
            }

            @Override
            public E next() {
                return (E) elementData[i++];
            }
        };
    }
}
