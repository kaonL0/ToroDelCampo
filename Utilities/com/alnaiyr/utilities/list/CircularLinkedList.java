package com.alnaiyr.utilities.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple class implementing a Linked list that is rounded, meaning that the
 * next and last element are always pointing on something.
 * 
 * @author IgoЯ based on Internet
 * @version 1.5
 *          <p>
 *          <strong> Version change:</strong>
 *          <ul>
 *          <li><em> More functionality</em></li>
 *          <li><em>Fixed a bug on get(i)</em></li>
 *          <li><em>added NodeFinder</em></li>
 *          <li><em>improver findNext(i) and findPrevious(i)</em></li>
 *          <li><em>added a List iterator</em></li>
 *          <li><em>coded contains</em></li>
 *          <li><em>improved findNode</em></li>
 *          </ul>
 *          </p>
 * @param <Type>
 */
public class CircularLinkedList<Type> implements List<Type> {

    /* ****************
     * 
     * Variables
     * 
     * ****************
     */

    private Node node;

    private Node last;

    private Node first;

    private int size;

    /* *****************
     * 
     * Constructor
     * 
     * **********************
     */

    /**
	 * 
	 */
    public CircularLinkedList() {

	node = null;
	last = null;
	first = null;
	size = 0;
    }

    /* ******************
     * 
     * Methods
     * 
     * *********************
     */

    /* ******** Add ****************** */

    /**
     * Adds an element at the end of the list
     * <p>
     * <Strong>Note:</strong> addLast is equivalent to addFirst, as, obviously,
     * the list is circular
     * </p>
     * 
     * @param s
     * @return true if added
     */
    @Override
    public boolean add(Type s) {

	if (size == 0) {
	    this.node = new Node(s);
	    this.node.next = this.node;
	    this.node.previous = this.node;
	    this.node.index = 0;
	    last = node;
	    first = node;
	    size = 1;
	    return true;
	}
	Node addNode = new Node(s);
	addNode.next = first;
	addNode.previous = last;

	last.next = addNode;
	first.previous = addNode;

	addNode.index = last.index + 1;
	last = addNode;
	size++;
	return true;
    }

    /**
     * Adds an element after a specified index
     * 
     * @param t
     * @param i
     *            TODO: make work
     */

    public void addAfter(int i, Type t) {

	if (size == 0) {
	    this.node = new Node(t);
	    this.node.next = this.node;
	    this.node.previous = this.node;
	    this.node.index = 0;
	    last = node;
	    first = node;
	    size = 1;
	} else {
	    Node addNode = new Node(t);
	    Node pre = findNode(i);

	    if (pre == last)
		add(t);
	    else {
		addNode.next = pre.next;
		addNode.previous = pre;

		pre.next.setPrevious(addNode);
		pre.next = addNode;

		for (int j = i + 1; j < size; j++)
		    findNode(j).index = (j + 1);

		addNode.index = i + 1;

		size++;
	    }
	}
    }

    /**
     * Adds an element before a specified index
     * 
     * @param t
     * @param i
     *            TODO: make work
     */
    public void addBefore(int i, Type t) {

	if (size == 0) {
	    this.node = new Node(t);
	    this.node.next = this.node;
	    this.node.previous = this.node;
	    this.node.index = 0;
	    last = node;
	    first = node;
	    size = 1;
	} else {
	    Node addNode = new Node(t);
	    Node pro = findNode(i);

	    if (pro == first)
		add(t);

	    addNode.next = pro;
	    addNode.previous = pro.previous;

	    pro.previous.setNext(addNode);
	    pro.previous = addNode;

	    for (int j = i; j < size; j++)
		findNode(j).index = (j + 1);

	    addNode.index = i;

	    size++;
	}
    }

    @Override
    public boolean addAll(Collection<? extends Type> arg0) {

	for (Type t : arg0) {
	    add(t);
	}
	return true;
    }

    /* *************** Remove ********************* */

    /**
     * Removes an element from the list
     * 
     * @param i
     */
    @Override
    public boolean remove(Object i) {

	if (i instanceof Integer) {
	    Node remove = findNode((Integer) i);

	    for (int j = ((Integer) i).intValue(); j < size; j++)
		findNode(j).index = (j - 1);

	    if (size == 1)
		this.node = null;
	    else {
		remove.next.setPrevious(remove.previous);
		remove.previous.setNext(remove.next);
	    }
	    size--;

	    return true;
	}
	for (int j = 0; j < size; j++) {
	    if (findNode(j).data.equals(i))
		remove(i);
	}
	return false;
    }

    /**
     * Delete the element o from the list
     * 
     * @param o
     * @param recur
     *            : false= stop after first found
     */

    public void remove(Type o, boolean recur) {

	for (int i = 0; i < size; i++) {
	    if (findNode(i).data.equals(o))
		remove(i);
	    if (!recur)
		break;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> arg0) {

	for (Object o : arg0) {
	    remove(o);
	}
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear() {

	for (int i = 0; i < size; i++)
	    remove(i);
    }

    /* ********************** Getters ************** */

    /**
     * Returns the first element of the list
     * 
     * @return first element
     */
    public Type getFirst() {

	return first.data;
    }

    /**
     * Returns the last element of the list
     * 
     * @return last element
     */
    public Type getLast() {

	return last.data;
    }

    /**
     * Returns the i index element
     * 
     * @param i
     * @return value i
     */
    @Override
    public Type get(int i) {

	return findNode(i).data;
    }

    /**
     * Gets the element previous to i
     * 
     * @param i
     * @return previous i
     */
    public Type getPrevious(int i) {

	return findNode(i).previous.data;
    }

    /**
     * Gets the element next to i
     * 
     * @param i
     * @return next i
     */
    public Type getNext(int i) {

	return findNode(i).next.data;
    }

    /** @return the number of element in the list */
    @Override
    public int size() {

	return size;
    }

    /**
     * Describe every element in the list
     * 
     * @return toString
     */
    @Override
    public String toString() {

	if (size > 0) {
	    System.out.println(this.node.data);
	    Node currentNode = this.node.next;
	    while (currentNode != this.node) {
		System.out.println(currentNode.data);
		currentNode = currentNode.next;
	    }
	} else
	    System.out.println("empty");
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {

	return (size == 0) ? true : false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object arg0) {
	return (indexOf(arg0) != -1) ? true : false;
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {

	for (Object o : arg0) {
	    if (!contains(o))
		return false;
	}
	return true;
    }

    /**
     * Returns -1 if not found
     * 
     * @see java.util.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(Object arg0) {

	if (first.data.equals(arg0))
	    return 0;
	else if (last.data.equals(arg0))
	    return size - 1;

	Node toFindFirst = first.next.next;
	Node toFindLast = last.previous.previous;

	do {
	    // check first bot and is neighbor
	    if (toFindFirst.data.equals(arg0))
		return toFindFirst.index;
	    else if (toFindFirst.next.data.equals(arg0))
		return toFindFirst.next.index;
	    else if (toFindFirst.previous.data.equals(arg0))
		return toFindFirst.previous.index;
	    // check second bot and his neighbor
	    else if (toFindLast.data.equals(arg0))
		return toFindLast.index;
	    else if (toFindLast.next.data.equals(arg0))
		return toFindLast.next.index;
	    else if (toFindLast.previous.data.equals(arg0))
		return toFindLast.previous.index;

	    toFindFirst = toFindFirst.next.next;
	    toFindLast = toFindLast.previous.previous;

	} while (toFindFirst.index != size - 1 & toFindLast.index != 0);

	return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#subList(int, int)
     */
    @Override
    public List<Type> subList(int first, int last) {
	return null;
    }

    /* ***************** Other ***************** */

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#listIterator()
     */
    @Override
    public ListIterator<Type> listIterator() {

	return new NodeFinder();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#listIterator(int)
     */
    @Override
    public ListIterator<Type> listIterator(int index) {

	return new NodeFinder(findNode(index));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#iterator()
     */
    @Override
    public Iterator<Type> iterator() {

	return new NodeFinder();
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {

	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#toArray()
     */
    @Override
    public Object[] toArray() {

	Object[] array = new Object[size];

	for (int i = 0; i < size; i++)
	    array[i] = get(i);
	return array;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.List#toArray(T[])
     */
    @Override
    public <T> T[] toArray(T[] arg0) {

	// TODO Auto-generated method stub
	return null;
    }

    /* ****************** Setters ***************** */

    /**
     * @param i
     * @param t
     */
    public void Set(int i, Type t) {

	findNode(i).data = t;
    }

    /* ****************** Internal method *************** */

    /**
     * Finds a node using its index TODO: improve with midpoint inspired
     * algorithm
     * 
     * @param i
     * @return
     */
    private Node findNode(int i) {

	if (i >= size | i < 0)
	    throw new ArrayIndexOutOfBoundsException(i);
	if (i == 0)
	    return first;
	else if (i == size - 1)
	    return last;
	Node toFindFirst = first.next.next;
	Node toFindLast = last.previous.previous;
	do {
	    // check first bot and is neighbor
	    if (toFindFirst.index == i)
		return toFindFirst;
	    else if (toFindFirst.next.index == i)
		return toFindFirst.next;
	    else if (toFindFirst.previous.index == i)
		return toFindFirst.previous;
	    // check second bot and his neighbor
	    else if (toFindLast.index == i)
		return toFindLast;
	    else if (toFindLast.next.index == i)
		return toFindLast.next;
	    else if (toFindLast.previous.index == i)
		return toFindLast.previous;

	    toFindFirst = toFindFirst.next.next;
	    toFindLast = toFindLast.previous.previous;
	} while (toFindFirst.index != i + 1 & toFindLast.index != i - 1);
	return null;
    }

    /* ***************
     * 
     * Internal class
     * 
     * ********************
     */
    /**
     * A simple node containing datas, and knowing who's next and before
     * 
     * @author IgoЯ
     * @version 1.0
     *          <p>
     *          <Strong>Version Change:</Strong>
     *          <ul>
     *          <li><em>Works</em></li>
     *          </ul>
     */
    private class Node {

	private Type data;

	private Node next;

	private Node previous;

	private int index;

	public Node(Type data) {

	    this.data = data;
	    this.next = null;
	}

	public void setNext(Node next) {

	    this.next = next;
	}

	public void setPrevious(Node previous) {

	    this.previous = previous;
	}

    }

    /**
     * A simple class used for iteration over the list
     * 
     * @author IgoЯ
     * @version 1.0
     *          <p>
     *          <Strong>Version Change:</Strong>
     *          <ul>
     *          <li><em>Works</em></li>
     *          </ul>
     */
    private class NodeFinder implements ListIterator<Type> {

	private Node node = last;

	// //////////////// Constructors ////////////////////////

	public NodeFinder() {

	}

	public NodeFinder(Node node) {

	    this.node = node;
	}

	/** Goes to the next element */
	@Override
	public Type next() {

	    node = node.next;
	    return node.data;
	}

	/** Always have next */
	@Override
	public boolean hasNext() {

	    return true;
	}

	/** Always has previous */
	@Override
	public boolean hasPrevious() {

	    return true;
	}

	/** Returns the next index */
	@Override
	public int nextIndex() {

	    return node.next.index;
	}

	/** Goes to the previous element */
	@Override
	public Type previous() {

	    node = node.previous;
	    return node.data;
	}

	/** Returns the previous index */
	@Override
	public int previousIndex() {

	    return node.previous.index;
	}

	/** Can't work here */
	@Override
	public void remove() {

	    System.err.print("Warning: no sense here");
	}

	/** Can't work here */
	@Override
	public void set(Type e) {

	    System.err.print("Warning: no sense here");
	}

	/** Can't work here */
	@Override
	public void add(Type e) {

	    System.err.print("Warning: no sense here");
	}

    }

    // ///////////////////////// TO add /////////////////////////////
    @Override
    public void add(int arg0, Type arg1) {

	// TODO Auto-generated method stub

    }

    @Override
    public boolean addAll(int arg0, Collection<? extends Type> arg1) {

	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int lastIndexOf(Object arg0) {

	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Type remove(int arg0) {

	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Type set(int arg0, Type arg1) {

	// TODO Auto-generated method stub
	return null;
    }

}
