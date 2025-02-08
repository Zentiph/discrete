package sets;

import java.util.List;

// TODO: get rid of this interface since its unnecessary now
public interface Ordered {
    /**
     * Get the number of elements.
     *
     * @return Number of elements
     */
    int size();

    /**
     * Get the item at the given index.
     *
     * @param index - Index to get item from
     * @return Item at index
     * @throws IndexOutOfBoundsException If index is out of range
     */
    Object get(int index) throws IndexOutOfBoundsException;

    /**
     * Get the class of the item at the given index.
     *
     * @param index - Index to get the class from
     * @return Class of the item at index
     * @throws IndexOutOfBoundsException If index is out of range
     */
    Class<?> getClass(int index) throws IndexOutOfBoundsException;

    /**
     * Get all the items in this ordered group.
     *
     * @return All items as a list
     */
    List<Object> getAll();

    /**
     * Get all the classes of each item in this ordered group.
     *
     * @return All the classes of each item as a list
     */
    List<Class<?>> getAllClasses();
}
