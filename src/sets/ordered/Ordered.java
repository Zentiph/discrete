package sets.ordered;

public interface Ordered {
    /**
     * Get the number of elements.
     *
     * @return Number of elements
     */
    public int size();

    /**
     * Get the item at the given index.
     *
     * @param index - Index to get item from
     * @return Item at index
     */
    public Object get(int index) throws IndexOutOfBoundsException;
}
