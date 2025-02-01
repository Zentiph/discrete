package sets.ordered;

/**
 * A simple ordered pair representation.
 *
 * @author Gavin Borne
 */
public class OrderedPair<E1, E2> implements Ordered {
    private E1 first;
    private E2 second;

    /**
     * Instantiate a new OrderedPair.
     *
     * @param first - First value
     * @param second - Second value
     */
    public OrderedPair(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > 1) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0) {
            return first;
        }
        return second;
    }

    /**
     * Get the first value in this ordered pair.
     *
     * @return First item
     */
    public E1 getFirst() {
        return first;
    }

    /**
     * Get the second value in this ordered pair.
     *
     * @return Second item
     */
    public E2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first.toString(), second.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedPair<?, ?>)) {
            return false;
        }
        OrderedPair<?, ?> otherOrderedPair = (OrderedPair<?, ?>) other;
        return first.equals(otherOrderedPair.first) && second.equals(otherOrderedPair.second);
    }
}
