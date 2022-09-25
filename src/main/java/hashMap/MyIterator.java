package hashMap;

import java.util.Iterator;
import java.util.List;

public class MyIterator<K> implements Iterator<K> {
    final List<K> keyList;
    int i = 0;

    public MyIterator(List<K> keyList) {
        this.keyList = keyList;
    }

    @Override
    public boolean hasNext() {
        if (i < keyList.size()) return keyList.get(i) != null;
        return false;
    }

    @Override
    public K next() {
        return keyList.get(i++);
    }
}
