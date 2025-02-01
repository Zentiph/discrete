package sets;

import java.util.List;

import sets.ordered.OrderedGroup;
import sets.ordered.OrderedPair;

/**
 * Base for all set implementations.
 *
 * A compliment method is not included as part of SetBase because for
 * IntervalSets, a universe is not needed because infinite sets can be
 * handled, but for regular sets, a universe is needed.
 *
 * Most Javadoc comments contain definitions of certain operations, which were supplemented by
 * <a href="https://www.tutorialspoint.com/discrete_mathematics/discrete_mathematics_sets.htm">tutorialspoint</a>.
 *
 * @author Gavin Borne
 */
public interface SetBase<E> {
    /**
     * Add an element to this set.
     *
     * @param element - Element to add
     */
    public void add(E element);

    /**
     * Remove an element from this set.
     *
     * @param element - Element to remove
     */
    public void remove(E element);

    /**
     * Check whether this set contains an element.
     *
     * @param element - Element to check
     * @return Whether this set contains the given element
     */
    public boolean contains(E element);

    /**
     * Calculate the cardinality of this set.
     * The cardinality of a set is defined as the number of elements within the set.
     *
     * @return The cardinality of this set
     */
    public int cardinality();

    /**
     * Determine whether this set is finite (has a finite number of elements).
     *
     * @return Whether this set is finite
     */
    public boolean isFinite();

    /**
     * Check if this set is a subset of another set.
     * A set, A, is a subset of another set, B, if every element of A is an element of B.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return Whether this set is a subset of the other set
     */
    public <E2> boolean isSubsetOf(SetBase<E2> other);

    /**
     * Check if this set is a proper subset of another set.
     * A set, A, is a proper subset of another set, B, if every element of A is an element of B
     * and the two sets are not equal.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return Whether this set is a proper subset of the other set
     */
    public <E2> boolean isProperSubsetOf(SetBase<E2> other);

    /**
     * Determine whether this set is empty.
     *
     * @return If this set is empty
     */
    public boolean isEmpty();

    /**
     * Determine whether this set is a unit set.
     * A set is a unit set if it contains only one element.
     *
     * @return Whether this set is a unit set.
     */
    public boolean isUnit();

    /**
     * Determine if this set is equal to another set.
     * Two sets are defined to be equal if they contain the same elements.
     *
     * @param other - Other set
     * @return Whether the two sets are equal
     */
    @Override
    public boolean equals(Object other);

    /**
     * Determine if this set is equivalent to another set.
     * Two sets are defined to be equivalent if their cardinalities are the same.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return Whether the two sets are equivalent
     */
    public <E2> boolean isEquivalentTo(SetBase<E2> other);

    /**
     * Determine if this set and another set are overlapping.
     * Two sets are defined to be overlapping if they have at least one element in common.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return Whether the two sets are overlapping
     */
    public <E2> boolean isOverlappingWith(SetBase<E2> other);

    /**
     * Determine if this set and another set are disjoint.
     * Two sets are defined to be disjoint if they do not have any elements in common.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return Whether the two sets are disjoint
     */
    public <E2> boolean isDisjointWith(SetBase<E2> other);

    /**
     * Generate the union of this set and another set.
     * The union of two sets, A and B, is the set of element which are in A, B, or both A and B.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return The union of the two sets
     */
    public <E2> SetBase<E> union(SetBase<E2> other);

    /**
     * Generate the intersection of this set and another set.
     * The intersection of two sets, A and B, is the set of element which are in both A and B.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return The intersection of the two sets
     */
    public <E2> SetBase<E> intersection(SetBase<E2> other);

    /**
     * Generate the difference (also known as relative complement) of this set and another set.
     * The difference between two sets, A and B, is the set of elements which are only in A but not in B.
     * Note that the difference between A and B is not the same as the difference between B and A.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return The difference between this set and the other set
     */
    public <E2> SetBase<E> difference(SetBase<E2> other);

    /**
     * Generate the compliment of this set.
     * The complement of a set is the set of elements which are not in the set.
     * In this case, the elements not in this set are determined by the universe given,
     * which acts as a set containing every possible value for this context.
     * For example, the compliment of {1, 2, 3} in the universe {1, 2, 3, 4, 5} is {4, 5}.
     *
     * @param universe - The universal set such that S' = (U - S)
     * @return The compliment of this set
     */
    public SetBase<E> compliment(SetBase<E> universe);

    /**
     * Generate the cartesian product (cross product) of this set and another set.
     * The cartesian product of two sets, A1 and A2, is defined as all possible
     * ordered pairs (x1, x2) where x1 is a member of A1 and x2 is a member of A2.
     * It should be noted that the cartesian product of two sets, A and B, is not
     * the same as the cartesian product of B and A.
     *
     * @param <E2> - Other set's generic type
     * @param other - Other set
     * @return The cartesian product of this set and the other set
     */
    public <E2> SetBase<OrderedPair<E, E2>> cartesianProduct(SetBase<E2> other);

    /**
     * Generate the cartesian product (cross product) of this set and an arbitrary number of other sets.
     * The cartesian product of sets denoted A1, A2, ...An is defined as all possible
     * ordered groups (x1, x2, ...xn) where x1 is a member of A1, x2 is a member of A2, etc.
     * It should be noted that the order the sets are added is important.
     *
     * @param others - Other sets
     * @return The cartesian product of this set and the given sets
     */
    public <T> SetBase<OrderedGroup<T>> cartesianProduct(List<SetBase<T>> others);

    /**
     * Generate the cartesian product (cross product) of this set and an arbitrary number of other sets.
     * The cartesian product of sets denoted A1, A2, ...An is defined as all possible
     * ordered groups (x1, x2, ...xn) where x1 is a member of A1, x2 is a member of A2, etc.
     * It should be noted that the order the sets are added is important.
     *
     * @param others - Other sets
     * @return The cartesian product of this set and the given sets
     */
    public SetBase<OrderedGroup<Object>> cartesianProductGeneric(List<SetBase<?>> others);

    /**
     * Generate the power set of this set.
     * The power set of a set S is the set of all subsets of S including the empty set.
     * The cardinality of a power set of a set with cardinality n is 2^n.
     *
     * @return The power set of this set
     */
    public SetBase<SetBase<E>> powerSet();

    /**
     * Partition this set at the given indexes.
     *
     * @param splitAt - Indexes to partition at
     * @return Partitioned set
     * @throws IllegalArgumentException If an index in splitAt is out of range for this set
     */
    public List<SetBase<E>> partition(int... splitAt) throws IllegalArgumentException;

    /**
     * Determine whether the given list of sets is a valid partitioning of this set.
     * The partition of a set S is a collection of sets P1, P2, ...Pn that satisfy the following conditions:
     * <ol>
     *     <li>None of the partition sets contain the empty set.</li>
     *     <li>The union of the subsets must equal the original set.</li>
     *     <li>The intersection of any two partition sets is empty (all partition sets are disjoint with each other).</li>
     * </ol>
     * @param other
     * @return
     */
    public boolean isPartition(List<SetBase<E>> other);

    /**
     * Calculate the nth Bell number for this set.
     *
     * @param n - Bell number to compute
     * @return nth Bell number
     * @throws IllegalArgumentException If n is larger than the cardinality of this set
     */
    public int bellNumber(int n) throws IllegalArgumentException;
}
// TODO: add set addition and subtraction to TypedSets (type safety)
// TODO: add Set class, TypedSet class, and IntervalSet class ({x | x > 0})
// TODO: add .fromN(), .fromZ(), etc methods to IntervalSet and isFinite()