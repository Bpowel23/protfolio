package findLargestK;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Creates a BinaryMaxHeap
 * @author Benjamin Powell & Sebastien Combes
 * @version April 11, 2022
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E>
{
	private Comparator<? super E> cmp;
	private E[] backingArray;
	private int size;
	private int checkedIndex;
	
	//constructor to build empty heap
	public BinaryMaxHeap()
	{
		backingArray = createGenericArray(7);
	}
	
	//constructor to build heap using given comparator
	public BinaryMaxHeap(Comparator<? super E> cmp)
	{
		this.cmp = cmp;
		backingArray = createGenericArray(7);
	}
	
	//constructor to build heap out of a list
	public BinaryMaxHeap(List<? extends E> list)
	{
		backingArray = createGenericArray(list.size());
		buildHeap(list, cmp);
	}
	
	//constructor to build heap out of a list and a given comparator
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp)
	{
		backingArray = createGenericArray(list.size());
		this.cmp = cmp;
		buildHeap(list, cmp);
	}
	/* private method to handle creating backing array
	 */
	@SuppressWarnings("unchecked")
	private E[] createGenericArray(int length) 
	{
		return (E[]) new Object[length];
	}
	//private method that moves items to the left array based on the comparator given and the
	//parent of the index that might need to move
	private void percolateUp(int index, Comparator<? super E> cmp)
	{
		
		if(internalCompare(backingArray[index], backingArray[(index-1)/2]) > 0)
		{
			E value = backingArray[index];
			backingArray[index] = backingArray[(index-1)/2];
			backingArray[(index-1)/2] = value;
		}
	}
	//private method that goes though parent and child indexes and swaps then acording to 
	//the comparator given.
	private void percolateAllDown(int index, Comparator<? super E> cmp)
	{
		E value = backingArray[index];
		//left and right child index
		int leftIndex = 2 * index+1;
		int rightIndex = 2 * (index+1);
		// 1st base case for recursion (leaf index)
		if(leftIndex > (size -1) && rightIndex > (size -1))
			return;
		// 2nd case for recusion (has rightIndex)
		else if(leftIndex > (size -1))
		{
			if(internalCompare(value, backingArray[rightIndex]) < 0)
			{
				checkedIndex = rightIndex;
				backingArray[index] = backingArray[checkedIndex];
			 	backingArray[checkedIndex] = value;
			}
			return;
		}
		// 3rd case for recursion (has leftIndex)
		else if(rightIndex > (size -1))
		{
			if(internalCompare(value, backingArray[leftIndex]) < 0)
			{
				checkedIndex = leftIndex;
				backingArray[index] = backingArray[checkedIndex];
			 	backingArray[checkedIndex] = value;
			}
			return;
		}
		//4th case for recursion (has both indexs)
		if(internalCompare(value, backingArray[leftIndex]) < 0 || internalCompare(value, backingArray[rightIndex]) < 0)
		{
			if(internalCompare(backingArray[leftIndex], backingArray[rightIndex]) > 0)
				checkedIndex = leftIndex;
			else
				checkedIndex = rightIndex;
			backingArray[index] = backingArray[checkedIndex];
			backingArray[checkedIndex] = value;
		}
		//final case for recursion if has both and no swap
		if(internalCompare(value, backingArray[leftIndex]) > 0 || internalCompare(value, backingArray[rightIndex]) > 0)
			return;
		if(internalCompare(backingArray[leftIndex], backingArray[rightIndex]) == 0)
			return;
		percolateAllDown(checkedIndex, cmp);
	}
	//private method that builds a heap based on a list given in constructor
	private void buildHeap(List<? extends E> list, Comparator<? super E> cmp)
	{
		
		for(int i = 0; i < list.size(); i++)
		{
			backingArray[i] = list.get(i);
			size++;
		}
		int tempSize = size;
		for(int i = list.size()-1; i >= 0; i--)
		{
			percolateAllDown((tempSize-1)/2 , cmp);
			tempSize--;
		}
	}
	//adds item to heap and calls perculateUp to find that items place in backing array
	public void add(E item) 
	{
		if(size + 1 > backingArray.length)
		{
			E[] tempArray = createGenericArray(backingArray.length*2);
			for(int i = 0; i < size; i++)
			{
				tempArray[i] = backingArray[i];
			}
			backingArray = tempArray;
		}
		backingArray[size] = item;
		checkedIndex = size;
		while(internalCompare(backingArray[checkedIndex], backingArray[(checkedIndex - 1) / 2]) > 0)
		{
			percolateUp(checkedIndex, cmp);
			checkedIndex = (checkedIndex - 1) / 2;
		}
		size++;	
	}
	//returns item at index 0 in backing array
	public E peek() throws NoSuchElementException 
	{
		if(size == 0)
			throw new NoSuchElementException("Index out of bounds");
		return backingArray[0];
	}
	//returns the value of backing array at index 0 swaps with end of array and calls 
	//perculateAllDown on new item
	public E extractMax() throws NoSuchElementException 
	{
		if(size <= 0)
		{
			throw new NoSuchElementException("Index out of bounds");
		}
		E value = backingArray[0];
		backingArray[0] = backingArray[size-1];
		backingArray[size - 1] = null;
		size--;
		checkedIndex = 0;
		percolateAllDown(checkedIndex, cmp);
		return value;
	}
	//returns int of how many items are in backing array
	public int size() 
	{
		return size;
	}
	//returns a boolean to see if heap is empty based on size
	public boolean isEmpty() 
	{
		return size == 0;
	}
	//resets values of heap to base
	public void clear() 
	{
		backingArray = createGenericArray(7);
		size = 0;
	}
	//returns a list of items based on items in backing array
	public Object[] toArray() 
	{
		E[] array = createGenericArray(size);
		for(int i = 0; i < size; i++)
		{
			array[i] = backingArray[i];
		}
		return array;
	}
	//private method that takes comparator returns a base comparator or comparator given
	private int internalCompare(E itemOne, E itemTwo) 
	{
		if (cmp == null) {
			return ((Comparable<? super E>) itemOne).compareTo(itemTwo);
		}
		return cmp.compare(itemOne, itemTwo);
	}
}
