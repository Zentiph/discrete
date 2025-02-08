package sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TypedSet<E> implements SetBase<E> {
    private ArrayList<E> elements;

    /**
     * Create a set starting with no elements.
     */
    public TypedSet() {
        this.elements = new ArrayList<>();
    }

    /**
     * Create a set with a list of elements.
     *
     * @param elements - Elements to initialize the set with
     */
    public TypedSet(List<E> elements) {
        this.elements = (ArrayList<E>) elements;
    }

    /**
     * Create a set by copying another set.
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(List<E> elements) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> getElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElements'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardinality() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cardinality'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinite'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSubsetOf(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSubsetOf'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperSubsetOf(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isProperSubsetOf'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUnit'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEquivalentTo'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOverlappingWith(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOverlappingWith'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDisjointWith(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDisjointWith'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> union(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'union'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> union(List<SetBase<E>> others) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'union'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> intersection(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersection'");
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public SetBase<E> intersection(List<SetBase<E>> others) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersection'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> difference(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'difference'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> symmetricDifference(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'difference'");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<E> complement(SetBase<E> universe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'complement'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(SetBase<E> other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cartesianProduct'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<OrderedGroup> cartesianProduct(List<SetBase<E>> others) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cartesianProduct'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SetBase<SetBase<E>> powerSet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'powerSet'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SetBase<E>> partition(int segments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partition'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPartition(List<SetBase<E>> partition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPartition'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger bellNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bellNumber'");
    }

    @Override
    public String toString() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
}