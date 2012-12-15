package com.alnaiyr.utilities.list;

import java.util.List;

/**
 * Gives some Utilities for lists, such as smart iterator or so...
 * 
 * @author IgoЯ
 * @version 1.0
 */
public final class ListU {

	/**
	 * Iterates over a value smartly (useful for lists). This means that if
	 * index value would throw a IndexOutOfBoundException, it instead goes back
	 * to the start of the list.
	 * 
	 * @param index
	 * @param size
	 * @return smart iteration
	 */
	public static int smartIterate(int index, int size) {

		if (index < size - 1)
			if (index >= 0)
				index++;
			else {
				index %= size;
				index = -index;
			}
		else
			index %= size;
		return index;
	}

	/**
	 * Gets a sublist, not matter the order of parameters
	 * 
	 * @param st
	 * @param sec
	 * @param list
	 * @return sublist
	 */
	public static <T> List<T> getSub(int st, int sec, List<T> list) {

		if (st <= sec)
			return list.subList(st, sec);
		return list.subList(sec, st);
	}

	/**
	 * Finds the next value of a list, as if it was a circular one
	 * 
	 * @param index
	 * @param list
	 * @return next value, circled
	 */
	public static <T> T getNext(int index, List<T> list) {

		try {
			return list.get(index + 1);
		} catch (IndexOutOfBoundsException e) {
			return index + 1 > list.size() ? list.get(0) : list
					.get(list.size() - 1);
		}
	}

	/**
	 * Finds the next value of a list, as if it was a circular one
	 * 
	 * @param index
	 * @param list
	 * @return previous value, circled
	 */
	public static <T> T getPrevious(int index, List<T> list) {

		try {
			return list.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			return index - 1 < 0 ? list.get(list.size() - 1) : list.get(0);
		}
	}

	/**
	 * @param list
	 * @return last element of a list
	 */
	public static <T> T getLast(List<T> list) {
		return list.get(list.size() - 1);
	}
}
