package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker, Benjamin Powell  u1379684 & Sebastien Combes u1362580
 * @version 4-13-22
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException 
	{
		BinaryMaxHeap<E> kLargest = new BinaryMaxHeap<E>(items);
		if(k > kLargest.size() -1 || k < 0)
			throw new IllegalArgumentException("k is invalid");
		List<E> list = new ArrayList<E>();
		for(int i = 0; i < k;i++)
		{
			list.add(kLargest.extractMax());
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException 
	{
		BinaryMaxHeap<E> kLargest = new BinaryMaxHeap<E>(items, cmp);
		if(k > kLargest.size() -1 || k < 0)
			throw new IllegalArgumentException("k is invalid");
		List<E> list = new ArrayList<E>();
		for(int i = 0; i < k;i++)
		{
			list.add(kLargest.extractMax());
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException 
	{
		if(k > items.size() -1 || k < 0)
			throw new IllegalArgumentException("k is invalid");
		Collections.sort(items);
		List<E> list = new ArrayList<E>();
		for(int i = items.size()-1; i > items.size()-1-k ;i--)
			list.add(items.get(i));
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException 
	{
		if(k > items.size() -1 || k < 0)
			throw new IllegalArgumentException("k is invalid");
		Collections.sort(items, cmp);
		List<E> list = new ArrayList<E>();
		for(int i = items.size()-1; i > items.size()-1-k ;i--)
			list.add(items.get(i));
		return list;
	}
}