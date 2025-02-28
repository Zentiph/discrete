package sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A set that can be typed so that it only contains a specific type.
 * For a general set (e.g. {@code TypedSet<Object>}), use {@link RawDiscreteSet}.
 *
 * This class uses LinkedLists under the hood to store items since
 * object retrieval is not supported.
 *
 * @author Gavin Borne
 */
public class GenericDiscreteSet<E>
    implements DiscreteSet<E>, Cloneable
{
    private Set<E> elements;
    // private List<E> elements;

    /**
     * Create a typed set starting with no elements.
     */
    public GenericDiscreteSet() {
        this.elements = new HashSet<>();
    }

    /**
     * Create a typed set with a list of elements.
     *
     * @param elements - Elements to initialize the set with
     */
    public GenericDiscreteSet(Collection<E> elements) {
        this.elements = new HashSet<>(elements);
    }

    /**
     * Create a typed set by copying another set.
     *
     * @param set - Set to copy
     */
    public GenericDiscreteSet(DiscreteSet<E> set) {
        this.elements = new HashSet<>(set.getElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E element) {
        if (contains(element)) return false;
        return this.elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> elements) {
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
    public boolean remove(Object o) {
        return this.elements.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return this.elements.removeAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return this.elements.retainAll(c);
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
    public boolean contains(Object o) {
        return this.elements.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return this.elements.containsAll(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<E> getElements() {
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
    public int size() {
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
    public boolean isSubsetOf(DiscreteSet<E> other) {
        for (E element : this.elements) {
            // If an element in this set is not in the superset,
            // then this is not a subset.
            if (!other.contains(element)) return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperSubsetOf(DiscreteSet<E> other) {
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
        if (this == other) return true;
        if (!(other instanceof DiscreteSet)) return false;

        GenericDiscreteSet<?> otherSet = (GenericDiscreteSet<?>) other;
        return this.elements.equals(otherSet.elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEquivalentTo(DiscreteSet<E> other) {
        return elements.size() == other.cardinality();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOverlappingWith(DiscreteSet<E> other) {
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
    public boolean isDisjointWith(DiscreteSet<E> other) {
        return !isOverlappingWith(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<E> union(DiscreteSet<E> other) {
        DiscreteSet<E> newSet = new GenericDiscreteSet<>();

        for (E element : this.elements) {
            newSet.add(element);
        }
        for (E element : other) {
            newSet.add(element);
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<E> union(List<DiscreteSet<E>> others) throws IllegalArgumentException {
        DiscreteSet<E> newSet = new GenericDiscreteSet<>();

        for (E element : this.elements) {
            newSet.add(element);
        }
        for (DiscreteSet<E> set : others) {
            for (E element : set) {
                newSet.add(element);
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<E> intersection(DiscreteSet<E> other) {
        DiscreteSet<E> newSet = new GenericDiscreteSet<>();

        // Prevent wasted time looping over bigger set
        // when intersection can be at most as big as the
        // smaller set
        DiscreteSet<E> smallerSet;
        DiscreteSet<E> biggerSet;
        if (cardinality() <= other.cardinality()) {
            smallerSet = this;
            biggerSet = other;
        } else {
            smallerSet = other;
            biggerSet = this;
        }

        for (E element : smallerSet) {
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
    public DiscreteSet<E> intersection(List<DiscreteSet<E>> others) {
        DiscreteSet<E> newSet = new GenericDiscreteSet<>(this.elements);

        for (DiscreteSet<E> set : others) {
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
    public DiscreteSet<E> difference(DiscreteSet<E> other) {
        DiscreteSet<E> newSet = new GenericDiscreteSet<>();

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
    public DiscreteSet<E> symmetricDifference(DiscreteSet<E> other) {
        DiscreteSet<E> firstDifference = difference(other);
        DiscreteSet<E> secondDifference = other.difference(this);
        return firstDifference.union(secondDifference);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<E> complement(DiscreteSet<E> universe) {
        return universe.difference(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<OrderedGroup> cartesianProduct(DiscreteSet<E> other) {
        DiscreteSet<OrderedGroup> newSet = new GenericDiscreteSet<>();

        for (E element : this.elements) {
            for (E otherElement : other) {
                newSet.add(new OrderedGroup(element, otherElement));
            }
        }

        return newSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscreteSet<OrderedGroup> cartesianProduct(List<DiscreteSet<E>> others) throws IllegalArgumentException {
        if (others.size() == 0) {
            throw new IllegalArgumentException("cartesianProduct arg 'others' cannot be an empty list");
        }

        GenericDiscreteSet<OrderedGroup> newSet = new GenericDiscreteSet<>();
        newSet.add(new OrderedGroup());

        for (int i = 0; i < others.size(); i++) {
            GenericDiscreteSet<OrderedGroup> result = new GenericDiscreteSet<>();

            for (OrderedGroup group : newSet) {
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
    public DiscreteSet<DiscreteSet<E>> powerSet() {
        DiscreteSet<DiscreteSet<E>> newSet = new GenericDiscreteSet<>();

        // Add empty set
        newSet.add(new GenericDiscreteSet<>());

        for (E element : this.elements) {
            List<DiscreteSet<E>> subsets = new ArrayList<>();

            for (DiscreteSet<E> set : newSet) {
                DiscreteSet<E> newSubset = new GenericDiscreteSet<>(set);
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
    public List<DiscreteSet<E>> partition(int segments) {
        List<DiscreteSet<E>> splitSets = new ArrayList<>();
        int segmentSize = Math.floorDiv(this.elements.size(), segments);

        Iterator<E> iterator = iterator();
        iterator.next(); // Place pointer at first item

        int i = 0;
        while (i < this.elements.size()) {
            GenericDiscreteSet<E> currentSegment = new GenericDiscreteSet<>();

            for (int j = 0; j < segmentSize && iterator.hasNext(); j++) {
                currentSegment.add(iterator.next());
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
    public boolean isPartition(List<DiscreteSet<E>> partition) {
        // Check if any set in the partition contains the empty set
        for (DiscreteSet<E> set : partition) {
            for (E element : set) {
                if (element instanceof DiscreteSet && ((DiscreteSet<?>)element).cardinality() == 0) {
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

    /**
     * {@inheritDoc}
     */
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
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return this.elements.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return this.elements.toArray(a);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return this.elements.iterator();
    }
}