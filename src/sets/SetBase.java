package sets;

import java.math.BigInteger;
import java.util.List;

/**
 * Base for all set implementations.
 *
 * Most Javadoc comments contain definitions of certain operations, which were supplemented by
 * <a href="https://www.tutorialspoint.com/discrete_mathematics/discrete_mathematics_sets.htm">tutorialspoint</a>.
 *
 * @author Gavin Borne
 */
public interface SetBase<E> {
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
    boolean addAll(List<E> elements);

    /**
     * Remove an element from this set.
     *
     * @param element - Element to remove
     * @return Whether this operation changed the items inside the set
     */
    boolean remove(E element);

    /**
     * Remove all the elements from this set.
     */
    void clear();

    /**
     * Check whether this set contains an element.
     *
     * @param element - Element to check
     * @return Whether this set contains the given element
     */
    boolean contains(E element);

    /**
     * Get the elements in this set.
     *
     * @return The elements in this set
     */
    List<E> getElements();

    /**
     * Calculate the cardinality of this set.
     * The cardinality of a set is defined as the number of elements within the set.
     *
     * @return The cardinality of this set
     */
    int cardinality();

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
    boolean isSubsetOf(SetBase<E> other);

    /**
     * Check if this set is a proper subset of another set.
     * A set, A, is a proper subset of another set, B, if every element of A is an element of B
     * and the two sets are not equal.
     *
     * @param other - Other set
     * @return Whether this set is a proper subset of the other set
     */
    boolean isProperSubsetOf(SetBase<E> other);

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
    boolean isEquivalentTo(SetBase<E> other);

    /**
     * Determine if this set and another set are overlapping.
     * Two sets are defined to be overlapping if they have at least one element in common.
     *
     * @param other - Other set
     * @return Whether the two sets are overlapping
     */
    boolean isOverlappingWith(SetBase<E> other);

    /**
     * Determine if this set and another set are disjoint.
     * Two sets are defined to be disjoint if they do not have any elements in common.
     *
     * @param other - Other set
     * @return Whether the two sets are disjoint
     */
    boolean isDisjointWith(SetBase<E> other);

    /**
     * Generate the union of this set and another set.
     * The union of two sets, A and B, is the set of elements which are in A, B, or both A and B.
     *
     * @param other - Other set
     * @return The union of the two sets
     */
    SetBase<E> union(SetBase<E> other);

    /**
     * Generate the union of this set and an arbitrary number of other sets.
     * The union of sets, A1, A2, ...An, is the set of elements which are in at least one of the sets A1, A2, ...An.
     *
     * @param others - Other sets
     * @return The union of the sets
     * @throws IllegalArgumentException If others is an empty list
     */
    SetBase<E> union(List<SetBase<E>> others) throws IllegalArgumentException;

    /**
     * Generate the intersection of this set and another set.
     * The intersection of two sets, A and B, is the set of elements which are in both A and B.
     *
     * @param other - Other set
     * @return The intersection of the two sets
     */
    SetBase<E> intersection(SetBase<E> other);

    /**
     * Generate the intersection of this set and an arbitrary number of other sets.
     * The intersection of sets, A1, A2, ...An, is the set of elements which are in all of the sets A1, A2, ...An.
     *
     * @param others - Other sets
     * @return The intersection of the sets
     * @throws IllegalArgumentException If others is an empty list
     */
    SetBase<E> intersection(List<SetBase<E>> others) throws IllegalArgumentException;

    /**
     * Generate the difference (also known as relative complement) of this set and another set.
     * The difference between two sets, A and B, is the set of elements which are only in A but not in B.
     * Note that the difference between A and B is not the same as the difference between B and A.
     *
     * @param other - Other set
     * @return The difference between this set and the other set
     */
    SetBase<E> difference(SetBase<E> other);

    /**
     * Generate the symmetric difference of this set and another set.
     * The symmetric difference between two sets, A and B, is the set of elements that result
     * from the union of the difference between A and B and the difference between B and A.
     *
     * @param other - Other set
     * @return The symmetric difference between this set and the other set
     */
    SetBase<E> symmetricDifference(SetBase<E> other);

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
    SetBase<E> complement(SetBase<E> universe);

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
    SetBase<OrderedGroup> cartesianProduct(SetBase<E> other);

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
    SetBase<OrderedGroup> cartesianProduct(List<SetBase<E>> others) throws IllegalArgumentException;

    /**
     * Generate the power set of this set.
     * The power set of a set S is the set of all subsets of S including the empty set.
     * The cardinality of a power set of a set with cardinality n is 2^n.
     *
     * @return The power set of this set
     */
    SetBase<SetBase<E>> powerSet();

    /**
     * Partition this set into a number of equally sized segments.
     * The final segment will be made shorter if the set cannot be evenly divided.
     * Example: Partitioning {1, 2, 3} with size 2 will give [{1, 2}, {3}].
     *
     * @param segments - Number of segments
     * @return Partitioned set
     */
    List<SetBase<E>> partition(int segments);

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
    boolean isPartition(List<SetBase<E>> partition);

    /**
     * Calculate the Bell number for this set.
     *
     * @return This set's Bell number
     */
    BigInteger bellNumber();
}
// TODO: add set addition and subtraction to TypedSets (type safety)
// TODO: add Set class, TypedSet class, and IntervalSet class ({x | x > 0})
// TODO: add .fromN(), .fromZ(), etc methods to IntervalSet and isFinite()