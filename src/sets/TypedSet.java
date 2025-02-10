package sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO turn all for element : set.getElements() into for element : set
// by making iterator thing necessary for all sets

/**
 * A set that can be typed so that it only contains a specific type.
 * For a general set (e.g. {@code TypedSet<Object>}), use {@link Set}.
 *
 * @author Gavin Borne
 */
public class TypedSet<E> implements SetBase<E> {
    private ArrayList<E> elements;

    /**
     * Create a typed set starting with no elements.
     */
    public TypedSet() {
        this.elements = new ArrayList<>();
    }

    /**
     * Create a typed set with a list of elements.
     *
     * @param elements - Elements to initialize the set with
     */
    public TypedSet(List<E> elements) {
        this.elements = (ArrayList<E>) elements;
    }

    /**
     * Create a typed set by copying another set.
     *
     * @param set - Set to copy
     */
    public TypedSet(SetBase<E> set) {
        this.elements = (ArrayList<E>) set.getElements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        return this.elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(List<E> elements) {
        boolean anyAdded = false;
        for (E element : elements) {
            if (this.elements.add(element)) {
                anyAdded = true;
            }
        }
        return anyAdded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(E element) {
        return this.elements.remove(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.elements.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E element) {
        return this.elements.contains(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> getElements() {
        return this.elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardinality() {
        return this.elements.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        // All TypedSets are finite since they do not support infinite items
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubsetOf(SetBase<E> other) {
        for (E element : this.elements) {
            // If an element in this set is not in the superset,
            // then this is not a subset.
            if (!other.contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperSubsetOf(SetBase<E> other) {
        return isSubsetOf(other) && !equals(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.elements.size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnit() {
        return this.elements.size() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SetBase)) {
            return false;
        }

        TypedSet<?> otherSet = (TypedSet<?>) other;
        return this.elements.equals(otherSet.elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEquivalentTo(SetBase<E> other) {
        return elements.size() == other.cardinality();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOverlappingWith(SetBase<E> other) {
        for (E element : this.elements) {
            if (other.contains(element)) {
                return true;
            }
        }
        // None of the elements are in common
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisjointWith(SetBase<E> other) {
        return !isOverlappingWith(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> union(SetBase<E> other) {
        SetBase<E> newSet = new TypedSet<>();

        for (E element : this.elements) {
            newSet.add(element);
        }
        for (E element : other.getElements()) {
            newSet.add(element);
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> union(List<SetBase<E>> others) throws IllegalArgumentException {
        SetBase<E> newSet = new TypedSet<>();

        for (E element : this.elements) {
            newSet.add(element);
        }
        for (SetBase<E> set : others) {
            for (E element : set.getElements()) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> intersection(SetBase<E> other) {
        SetBase<E> newSet = new TypedSet<>();

        // Prevent wasted time looping over bigger set
        // when intersection can be at most as big as the
        // smaller set
        SetBase<E> smallerSet;
        SetBase<E> biggerSet;
        if (cardinality() <= other.cardinality()) {
            smallerSet = this;
            biggerSet = other;
        } else {
            smallerSet = other;
            biggerSet = this;
        }

        for (E element : smallerSet.getElements()) {
            if (biggerSet.contains(element)) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public SetBase<E> intersection(List<SetBase<E>> others) {
        SetBase<E> newSet = new TypedSet<>(this.elements);

        for (SetBase<E> set : others) {
            newSet = newSet.intersection(set);
            // If the set is empty, its intersection will always be the empty set.
            if (newSet.cardinality() == 0) {
                // So return here to not waste time.
                return newSet;
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> difference(SetBase<E> other) {
        SetBase<E> newSet = new TypedSet<>();

        for (E element : this.elements) {
            if (!other.contains(element)) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> symmetricDifference(SetBase<E> other) {
        SetBase<E> firstDifference = difference(other);
        SetBase<E> secondDifference = other.difference(this);
        return firstDifference.union(secondDifference);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> complement(SetBase<E> universe) {
        return universe.difference(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(SetBase<E> other) {
        SetBase<OrderedGroup> newSet = new TypedSet<>();

        for (E element : this.elements) {
            for (E otherElement : other.getElements()) {
                newSet.add(new OrderedGroup(element, otherElement));
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(List<SetBase<E>> others) throws IllegalArgumentException {
        if (others.size() == 0) {
            throw new IllegalArgumentException("cartesianProduct arg 'others' cannot be an empty list");
        }

        TypedSet<OrderedGroup> newSet = new TypedSet<>();
        newSet.add(new OrderedGroup());

        for (int i = 0; i < others.size(); i++) {
            TypedSet<OrderedGroup> result = new TypedSet<>();

            for (OrderedGroup group : newSet.getElements()) {
                for (Object element : group.getAll()) {
                    group.add(element);
                    result.add(group);
                }
            }

            newSet = result;
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<SetBase<E>> powerSet() {
        SetBase<SetBase<E>> newSet = new TypedSet<>();

        // Add empty set
        newSet.add(new TypedSet<>());

        for (E element : this.elements) {
            List<SetBase<E>> subsets = new ArrayList<>();

            for (SetBase<E> set : newSet.getElements()) {
                SetBase<E> newSubset = new TypedSet<>(set);
                newSubset.add(element);

                subsets.add(newSubset);
            }

            newSet.addAll(subsets);
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SetBase<E>> partition(int segments) {
        List<SetBase<E>> splitSets = new ArrayList<>();
        int segmentSize = Math.floorDiv(this.elements.size(), segments);

        int i = 0;
        while (i < this.elements.size()) {
            TypedSet<E> currentSegment = new TypedSet<>();

            for (int j = 0; j < segmentSize; j++) {
                currentSegment.add(this.elements.get(i));
                i++;
            }

            splitSets.add(currentSegment);
        }

        return splitSets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPartition(List<SetBase<E>> partition) {
        // Check if any set in the partition contains the empty set
        for (SetBase<E> set : partition) {
            for (E element : set.getElements()) {
                if (element instanceof SetBase && ((SetBase<?>)element).cardinality() == 0) {
                    return false;
                }
            }
        }

        // Check if the union of all the sets in the partition equal this set
        if (!this.equals(partition.get(0).union(partition))) {
            return false;
        }

        // Check if the sets are all disjoin with one another
        for (int i = 0; i < partition.size(); i++) {
            // Only check each pair once
            for (int j = i + 1; j < partition.size(); j++) {
                if (!partition.get(i).isDisjointWith(partition.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger bellNumber() {
        int size = cardinality();

        // Prioritize accuracy
        if (size <= 100) {
            return BellNumbers.bellNumber(size);
        }
        return BellNumbers.bellNumberRecursive(size);
    }

    @Override
    public String toString() {
        String out = "{";
        boolean objAdded = false;

        for (Object element : this.elements) {
            if (!objAdded) {
                out += element.toString();
                objAdded = true;
            } else {
                out += ", " + element.toString();
            }
        }

        return out + "}";
    }

    /**
     * Generate an iterator over the elements in this set.
     *
     * @return Iterator over this set's elements
     */
    @Override
    public Iterator<E> iterator() {
        return new SetIterator();
    }

    private class SetIterator implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < elements.size();
        }

        @Override
        public E next() {
            return elements.get(index++);
        }
    }
}