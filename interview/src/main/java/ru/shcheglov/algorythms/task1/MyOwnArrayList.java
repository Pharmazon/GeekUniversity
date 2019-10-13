package ru.shcheglov.homework2.task1;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;

public class MyOwnArrayList<T> extends AbstractList<T> implements Serializable {

    private static final long serialVersionUID = 2452757355757473673L;
    private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    public MyOwnArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = array.length;
    }

    public MyOwnArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
        } else if (capacity == 0) {
            this.array = new Object[]{};
        } else {
            throw new IllegalArgumentException("Wrong capacity: " + capacity);
        }
        this.size = array.length;
    }

    @Override
    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index:" + index + ", Size: " + size);
        return (T) array[index];
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (array[i] == null) return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(array[i])) return i;
        }
        return -1;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null) return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i])) return i;
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public T set(int index, T element) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index:" + index + ", Size: " + size);

        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
    }

    public boolean add(T e) {
        extendCapacity(size + 1);
        array[size++] = e;
        return true;
    }

    public T remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index:" + index + ", Size: " + size);

        modCount++;
        T oldValue = (T) array[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index+1, array, index,
                    numMoved);
        array[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    subRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(array[index])) {
                    subRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void subRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) System.arraycopy(array, index+1, array, index, numMoved);
        array[--size] = null;
    }

    public void clear() {
        for (int i = 0; i < size; i++) array[i] = null;
        size = 0;
    }

    private void extendCapacity(int minCapacity) {
        if (array.equals(new Object[]{})) minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        if (minCapacity - array.length > 0) extendArray(minCapacity);
    }

    private void extendArray(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_SIZE > 0) {
            if (minCapacity < 0) throw new OutOfMemoryError();
            newCapacity =  (minCapacity > MAX_SIZE) ? Integer.MAX_VALUE : MAX_SIZE;
        }
        array = Arrays.copyOf(array, newCapacity);
    }
}
