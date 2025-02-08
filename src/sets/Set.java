package sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Default set class, which acts like a {@link TypedSet} but
 * with the generic type set to {@code Object} in order to hold any type.
 *
 * @author Gavin Borne
 */
public class Set implements SetBase<Object>, Iterable<Object> {
    private ArrayList<Object> elements;

    /**
     * Create a set starting with no elements.
     */
    public Set() {
        this.elements = new ArrayList<>();
    }

    /**
     * Create a set with a list of elements.
     *
     * @param elements - Elements to initialize the set with
     */
    public Set(List<Object> elements) {
        this.elements = (ArrayList<Object>) elements;
    }

    /**
     * Create a set with an array of elements.
     *
     * @param elements - Elements to initialize the set with
     */
    public Set(Object... elements) {
        this.elements = new ArrayList<>(Arrays.asList(elements));
    }

    /**
     * Create a set by copying another set.
     *
     * @param set - Set to copy
     */
    public Set(SetBase<Object> set) {
        this.elements = (ArrayList<Object>) set.getElements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Object element) {
        // Prevent duplicate items
        if (contains(element)) {
            return false;
        }
        return this.elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(List<Object> elements) {
        boolean anyAdded = false;
        for (Object element : elements) {
            if (add(element)) {
                anyAdded = true;
            }
        }
        return anyAdded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object element) {
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
    public boolean contains(Object element) {
        return this.elements.contains(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getElements() {
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
        // All Sets are finite since they do not support infinite items like IntervalSets
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubsetOf(SetBase<Object> other) {
        boolean allMembersOf = true;
        for (Object element : this.elements) {
            if (!other.contains(element)) {
                allMembersOf = false;
            }
        }
        return allMembersOf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperSubsetOf(SetBase<Object> other) {
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

        Set otherSet = (Set) other;
        return this.elements.equals(otherSet.elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEquivalentTo(SetBase<Object> other) {
        return elements.size() == other.cardinality();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOverlappingWith(SetBase<Object> other) {
        for (Object element : this.elements) {
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
    public boolean isDisjointWith(SetBase<Object> other) {
        return !isOverlappingWith(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> union(SetBase<Object> other) {
        SetBase<Object> newSet = new Set();

        for (Object element : this.elements) {
            newSet.add(element);
        }
        for (Object element : other.getElements()) {
            newSet.add(element);
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> union(List<SetBase<Object>> others) {
        SetBase<Object> newSet = new Set();

        for (Object element : this.elements) {
            newSet.add(element);
        }
        for (SetBase<Object> set : others) {
            for (Object element : set.getElements()) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> intersection(SetBase<Object> other) {
        SetBase<Object> newSet = new Set();

        for (Object element : this.elements) {
            if (other.contains(element)) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> intersection(List<SetBase<Object>> others) {
        SetBase<Object> newSet = new Set(this.elements);

        for (SetBase<Object> set : others) {
            newSet = newSet.intersection(set);
            // The set is empty, its intersection will now always be empty.
            if (newSet.cardinality() == 0) {
                // So return here to not waste computation time.
                return newSet;
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> difference(SetBase<Object> other) {
        SetBase<Object> newSet = new Set();

        for (Object element : this.elements) {
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
    public SetBase<Object> symmetricDifference(SetBase<Object> other) {
        SetBase<Object> firstDifference = difference(other);
        SetBase<Object> secondDifference = other.difference(this);
        return firstDifference.union(secondDifference);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<Object> complement(SetBase<Object> universe) {
        return universe.difference(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(SetBase<Object> other) {
        SetBase<OrderedGroup> newSet = new TypedSet<>();

        for (Object element : this.elements) {
            for (Object innerElement : other.getElements()) {
                newSet.add(new OrderedGroup(element, innerElement));
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(List<SetBase<Object>> others) throws IllegalArgumentException {
        if (others.size() == 0) {
            throw new IllegalArgumentException("cartesianProduct arg 'others' cannot be an empty list");
        }

        TypedSet<OrderedGroup> newSet = new TypedSet<>();
        newSet.add(new OrderedGroup());

        // for (SetBase<Object> currentSet : others) {
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
    public SetBase<SetBase<Object>> powerSet() {
        SetBase<SetBase<Object>> newSet = new TypedSet<>();

        // Add empty set
        newSet.add(new TypedSet<>());

        for (Object element : this.elements) {
            List<SetBase<Object>> subsets = new ArrayList<>();

            for (SetBase<Object> set : newSet.getElements()) {
                SetBase<Object> newSubset = new TypedSet<>(set);
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
    public List<SetBase<Object>> partition(int segments) {
        List<SetBase<Object>> splitSets = new ArrayList<>();
        int segmentSize = Math.floorDiv(this.elements.size(), segments);

        int i = 0;
        while (i < this.elements.size()) {
            Set currentSegment = new Set();

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
    public boolean isPartition(List<SetBase<Object>> partition) {
        // Check if any set in the partition contains the empty set
        for (SetBase<Object> set : partition) {
            for (Object element : set.getElements()) {
                if (element instanceof SetBase && ((SetBase<?>)element).cardinality() == 0) {
                    return false;
                }
            }
        }

        // Check if the union of all the sets in the partition equal this set
        if (!this.equals(partition.get(0).union(partition))) {
            return false;
        }

        // Check if the sets are all disjoint with one another
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

    public BigInteger bellNumber() {
        return BellNumbers.bellNumber(this.elements.size());
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
    public Iterator<Object> iterator() {
        return new SetIterator();
    }

    private class SetIterator implements Iterator<Object> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < elements.size();
        }

        @Override
        public Object next() {
            return elements.get(index++);
        }
    }
}
