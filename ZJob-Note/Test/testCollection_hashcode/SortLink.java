package testCollection_hashcode;

import java.util.Iterator;

public class SortLink {

    Entry first;
    Entry last;

    SortLink() {
        first = null;
        last = null;
    }

    void insert(Integer value) {
        Entry entry = new Entry(value);
        Entry follow = first;
        Entry previous = null;  //record previous data for the

        while (follow != null && entry.compareTo(follow.value) > 0) {
            previous = follow;
            follow = follow.next;
        }
        // when insert before the first
        if (previous == null) {
            if (first != null) {
                entry.next = first;
            }
            first = entry;

        } else {
            entry.next = follow;
            previous.next = entry;
        }
    }

    public Iterator iterator() {
        return new Iterator() {
            Entry current = first;

            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                // TODO Auto-generated method stub
                Entry temporary = current;
                current = current.next;
                return temporary;
            }

            @Override
            public void remove() {

            }

        };
    }

    void display() {
        StringBuffer sb = new StringBuffer();
        Entry current = first;
        while (current != null) {
            sb.append(current.value + "--");
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    class Entry implements Compare {
        Integer value;
        Entry next;

        Entry(Integer value) {
            this.value = value;

        }

        @Override
        public int compareTo(Integer value) {
            return this.value.compareTo(value);
        }
    }

    public static void main(String[] args) {
        SortLink sl = new SortLink();
        sl.insert(5);
        sl.insert(1);
        sl.insert(3);
        sl.insert(8);
        sl.insert(7);
        sl.insert(9);
        sl.insert(4);
        sl.display();
        System.out.println("----------------------");
        Iterator iterator = sl.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            System.out.println(entry.value);
        }
    }

}

interface Compare {
    int compareTo(Integer value);
}