package sets;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class OrderedGroup implements Ordered {
    private ArrayList<Object> items;
    private ArrayList<Class<?>> types;

    /**
     * Create an empty ordered group.
     */
    public OrderedGroup() {
        this.items = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    /**
     * Create an ordered group with a list of objects.
     *
     * @param items - Items in the group
     */
    public OrderedGroup(List<Object> items) {
        this.items = new ArrayList<>(items);
        this.types = new ArrayList<>();
        for (Object item : items) {
            types.add(item.getClass());
        }
    }

    /**
     * Create an ordered group with an array of objects.
     *
     * @param items - Items in the group
     */
    public OrderedGroup(Object... items) {
        this.items = new ArrayList<>(Arrays.asList(items));
        this.types = new ArrayList<>();
        for (Object item : items) {
            types.add(item.getClass());
        }
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        return this.items.get(index);
    }

    @Override
    public Class<?> getClass(int index) throws IndexOutOfBoundsException {
        return this.types.get(index);
    }

    @Override
    public List<Object> getAll() {
        return this.items;
    }

    @Override
    public List<Class<?>> getAllClasses() {
        return this.types;
    }

    @Override
    public String toString() {
        String out = "(";
        int i = 0;
        for (Object element : this.items) {
            out += element.toString();
            if (i != this.items.size() - 1) {
                // If not the final item add a comma
                out += ", ";
            }
            i++;
        }
        return out + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedGroup)) {
            return false;
        }
        OrderedGroup otherOrderedPair = (OrderedGroup) other;
        return items.equals(otherOrderedPair.items);
    }

    protected void add(Object element) {
        this.items.add(element);
        this.types.add(element.getClass());
    }
}
