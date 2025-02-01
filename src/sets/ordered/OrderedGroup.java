package sets.ordered;

import java.util.ArrayList;
import java.util.List;

public class OrderedGroup<E> implements Ordered {
    private ArrayList<E> items;

    public OrderedGroup() {
        this.items = new ArrayList<>();
    }

    public OrderedGroup(List<E> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        return items.get(index);
    }

    @Override
    public String toString() {
        String out = "(";
        int i = 0;
        for (E element : items) {
            out += element.toString();
            if (i != items.size() - 1) {
                // If not the final item add a comma
                out += ", ";
            }
            i++;
        }
        return out + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedGroup<?>)) {
            return false;
        }
        OrderedGroup<?> otherOrderedPair = (OrderedGroup<?>) other;
        return items.equals(otherOrderedPair.items);
    }
}
