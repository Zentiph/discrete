package sets;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Base for all discrete set implementations.
 *
 * Most Javadoc comments contain definitions of certain operations, which were supplemented by
 * <a href="https://www.tutorialspoint.com/discrete_mathematics/discrete_mathematics_sets.htm">tutorialspoint</a>.
 *
 * @author Gavin Borne
 */
public interface DiscreteSet<E>
    extends Collection<E>
{
    /**
     * Add an element to this set.
     * Sets cannot contain duplicate items.
     *
     * @param element - Element to add
     * @return Whether this operation changed the items inside the set
     */
    boolean add(E element);

    /**
     * Add a list of elements to this set.
     * Sets cannot contain duplicate items.
     *
     * @param elements - Elements to add
     * @return Whether this operation changed the items inside the set
     */
    boolean addAll(Collection<? extends E> elements);

    /**
     * Remove an element from this set.
     *
     * @param o - Element to remove
     * @return Whether this operation changed the items inside the set
     */
    boolean remove(Object o);

    /**
     * Remove all the elements in a given collection from this set.
     *
     * @param c - Collection of elements to remove
     * @return Whether this operation changed the items inside the set
     */
    @Override
    public boolean removeAll(Collection<?> c);

    /**
     * Retain only the elements in this set that are in a given collection.
     *
     * @param c - Collection of elements to retain
     * @return Whether this operation changed the items inside the set
     */
    @Override
    public boolean retainAll(Collection<?> c);

    /**
     * Remove all the elements from this set.
     */
    void clear();

    /**
     * Check whether this set contains an element.
     *
     * @param o - Element to check
     * @return Whether this set contains the given element
     */
    boolean contains(Object o);

    /**
     * Check whether this set contains all the elements in a collection.
     *
     * @param c - Collection to check
     * @return Whether this set contains all the elements in the given collection
     */
    @Override
    public boolean containsAll(Collection<?> c);

    /**
     * Get the elements in this set.
     *
     * @return The elements in this set
     */
    Set<E> getElements();

    /**
     * Calculate the cardinality of this set.
     * The cardinality of a set is defined as the number of elements within the set.
     *
     * @return The cardinality of this set
     */
    int cardinality();

    /**
     * Calculate the cardinality of this set.
     * The cardinality of a set is defined as the number of elements within the set.
     * This method is identical to {@link #cardinality()}.
     *
     * @return The cardinality of this set
     */
    @Override
    int size();

    /**
     * Determine whether this set is finite (has a finite number of elements).
     *
     * @return Whether this set is finite
     */
    boolean isFinite();

    /**
     * Check if this set is a subset of another set.
     * A set, A, is a subset of another set, B, if every element of A is an element of B.
     *
     * @param other - Other set
     * @return Whether this set is a subset of the other set
     */
    boolean isSubsetOf(DiscreteSet<E> other);

    /**
     * Check if this set is a proper subset of another set.
     * A set, A, is a proper subset of another set, B, if every element of A is an element of B
     * and the two sets are not equal.
     *
     * @param other - Other set
     * @return Whether this set is a proper subset of the other set
     */
    boolean isProperSubsetOf(DiscreteSet<E> other);

    /**
     * Determine whether this set is empty.
     *
     * @return If this set is empty
     */
    boolean isEmpty();

    /**
     * Determine whether this set is a unit set.
     * A set is a unit set if it contains only one element.
     *
     * @return Whether this set is a unit set.
     */
    boolean isUnit();

    /**
     * Determine if this set is equal to another set.
     * Two sets are defined to be equal if they contain the same elements.
     *
     * @param other - Other set
     * @return Whether the two sets are equal
     */
    @Override
    boolean equals(Object other);

    /**
     * Determine if this set is equivalent to another set.
     * Two sets are defined to be equivalent if their cardinalities are the same.
     *
     * @param other - Other set
     * @return Whether the two sets are equivalent
     */
    boolean isEquivalentTo(DiscreteSet<E> other);

    /**
     * Determine if this set and another set are overlapping.
     * Two sets are defined to be overlapping if they have at least one element in common.
     *
     * @param other - Other set
     * @return Whether the two sets are overlapping
     */
    boolean isOverlappingWith(DiscreteSet<E> other);

    /**
     * Determine if this set and another set are disjoint.
     * Two sets are defined to be disjoint if they do not have any elements in common.
     *
     * @param other - Other set
     * @return Whether the two sets are disjoint
     */
    boolean isDisjointWith(DiscreteSet<E> other);

    /**
     * Generate the union of this set and another set.
     * The union of two sets, A and B, is the set of elements which are in A, B, or both A and B.
     *
     * @param other - Other set
     * @return The union of the two sets
     */
    DiscreteSet<E> union(DiscreteSet<E> other);

    /**
     * Generate the union of this set and an arbitrary number of other sets.
     * The union of sets, A1, A2, ...An, is the set of elements which are in at least one of the sets A1, A2, ...An.
     *
     * @param others - Other sets
     * @return The union of the sets
     * @throws IllegalArgumentException If others is an empty list
     */
    DiscreteSet<E> union(List<DiscreteSet<E>> others) throws IllegalArgumentException;

    /**
     * Generate the intersection of this set and another set.
     * The intersection of two sets, A and B, is the set of elements which are in both A and B.
     *
     * @param other - Other set
     * @return The intersection of the two sets
     */
    DiscreteSet<E> intersection(DiscreteSet<E> other);

    /**
     * Generate the intersection of this set and an arbitrary number of other sets.
     * The intersection of sets, A1, A2, ...An, is the set of elements which are in all of the sets A1, A2, ...An.
     *
     * @param others - Other sets
     * @return The intersection of the sets
     * @throws IllegalArgumentException If others is an empty list
     */
    DiscreteSet<E> intersection(List<DiscreteSet<E>> others) throws IllegalArgumentException;

    /**
     * Generate the difference (also known as relative complement) of this set and another set.
     * The difference between two sets, A and B, is the set of elements which are only in A but not in B.
     * Note that the difference between A and B is not the same as the difference between B and A.
     *
     * @param other - Other set
     * @return The difference between this set and the other set
     */
    DiscreteSet<E> difference(DiscreteSet<E> other);

    /**
     * Generate the symmetric difference of this set and another set.
     * The symmetric difference between two sets, A and B, is the set of elements that result
     * from the union of the difference between A and B and the difference between B and A.
     *
     * @param other - Other set
     * @return The symmetric difference between this set and the other set
     */
    DiscreteSet<E> symmetricDifference(DiscreteSet<E> other);

    /**
     * Generate the complement of this set.
     * The complement of a set is the set of elements which are not in the set.
     * In this case, the elements not in this set are determined by the universe given,
     * which acts as a set containing every possible value for this context.
     * For example, the complement of {1, 2, 3} in the universe {1, 2, 3, 4, 5} is {4, 5}.
     * The complement of a set in a given universe is equal to the difference between the universe and that set.
     *
     * @param universe - The universal set such that S' = (U - S)
     * @return The compliment of this set
     */
    DiscreteSet<E> complement(DiscreteSet<E> universe);

    /**
     * Generate the cartesian product (cross product) of this set and another set.
     * The cartesian product of two sets, A1 and A2, is defined as all possible
     * ordered pairs (x1, x2) where x1 is a member of A1 and x2 is a member of A2.
     * It should be noted that the cartesian product of two sets, A and B, is not
     * the same as the cartesian product of B and A.
     *
     * @param other - Other set
     * @return The cartesian product of this set and the other set
     */
    DiscreteSet<OrderedGroup> cartesianProduct(DiscreteSet<E> other);

    /**
     * Generate the cartesian product (cross product) of this set and an arbitrary number of other sets.
     * The cartesian product of sets denoted A1, A2, ...An is defined as all possible
     * ordered groups (x1, x2, ...xn) where x1 is a member of A1, x2 is a member of A2, etc.
     * It should be noted that the order the sets are added is important.
     *
     * @param others - Other sets
     * @return The cartesian product of this set and the given sets
     * @throws IllegalArgumentException If others is an empty list
     */
    DiscreteSet<OrderedGroup> cartesianProduct(List<DiscreteSet<E>> others) throws IllegalArgumentException;

    /**
     * Generate the power set of this set.
     * The power set of a set S is the set of all subsets of S including the empty set.
     * The cardinality of a power set of a set with cardinality n is 2^n.
     *
     * @return The power set of this set
     */
    DiscreteSet<DiscreteSet<E>> powerSet();

    /**
     * Partition this set into a number of equally sized segments.
     * The final segment will be made shorter if the set cannot be evenly divided.
     * Example: Partitioning {1, 2, 3} with size 2 will give [{1, 2}, {3}].
     *
     * @param segments - Number of segments
     * @return Partitioned set
     */
    List<DiscreteSet<E>> partition(int segments);

    /**
     * Determine whether the given list of sets is a valid partitioning of this set.
     * The partition of a set S is a collection of sets P1, P2, ...Pn that satisfy the following conditions:
     * <ol>
     *     <li>None of the partition sets contain the empty set.</li>
     *     <li>The union of the subsets must equal the original set.</li>
     *     <li>The intersection of any two partition sets is empty (all partition sets are disjoint with each other).</li>
     * </ol>
     * @param partition
     * @return
     */
    boolean isPartition(List<DiscreteSet<E>> partition);

    /**
     * Calculate the Bell number for this set.
     *
     * @return This set's Bell number
     */
    BigInteger bellNumber();

    /**
     * Generate a String representation of this set.
     *
     * @return String representation
     */
    @Override
    public String toString();

    /**
     * Convert this set's elements into an array.
     *
     * @return Array of elements in this set
     */
    @Override
    public Object[] toArray();

    /**
     * Convert this set's elements into a typed array.
     *
     * @param <T> Type of the array
     * @param a - Initial typed array
     * @return Typed array of elements in this set
     */
    @Override
    public <T> T[] toArray(T[] a);

    /**
     * Generate an iterator over the elements in this set.
     *
     * @return Iterator over this set's elements
     */
    @Override
    public Iterator<E> iterator();
}

// TODO: add ContinuousSet class ({x | x > 0})
// TODO: add .fromN(), .fromZ(), etc methods to ContinuousSet and isFinite()
// TODO: look into java collections framework and take inspirations