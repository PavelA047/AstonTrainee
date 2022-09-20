package arrayList;

public interface List<E> extends Iterable<E>{
    void add(E element);

    void clear();

    E get(int index);

    void remove(E element);

    boolean contains(E element);

    int indexOf(E element);

    void set(int index, E element);

    int size();

    boolean isEmpty();
}
