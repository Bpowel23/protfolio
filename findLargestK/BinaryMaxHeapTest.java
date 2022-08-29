package findLargestK;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
/**
 * Tests a BinaryMaxHeap
 * @author Benjamin Powell & Sebastien Combes
 * @version April 11, 2022
 */
class BinaryMaxHeapTest 
{
	@Test
	void ExtractMaxOnSmallAndSizeTest()
	{
		BinaryMaxHeap<Integer> a = new BinaryMaxHeap<Integer>();
		a.add(1);
		assertEquals(1, a.size());
		a.add(2);
		assertEquals(2, a.size());
		a.add(3);
		assertEquals(3, a.size());
		
		assertEquals(3, a.extractMax());
		assertEquals(2, a.extractMax());
		assertEquals(1, a.extractMax());
		assertEquals(0, a.size());
	}
	@Test
	void ExtractMaxOnMidTest() 
	{
		BinaryMaxHeap<Integer> a = new BinaryMaxHeap<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(8);
		a.add(4);
		a.add(19);
		a.add(12);
		a.add(12);
		a.add(13);
		assertEquals(19, a.extractMax());
		assertEquals(13, a.extractMax());
		assertEquals(12, a.extractMax());
		assertEquals(12, a.extractMax());
		assertEquals(8, a.extractMax());
	}
	@Test
	void clearEmptyPeekTest()
	{
		BinaryMaxHeap<Integer> a = new BinaryMaxHeap<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		assertEquals(3, a.peek());
		assertFalse(a.isEmpty());
		a.clear();
		assertTrue(a.isEmpty());
		a.add(19);
		a.add(12);
		a.add(12);
		assertEquals(19, a.peek());
	}
	@Test
	void duplicantsAndExceptionsTest()
	{
		BinaryMaxHeap<Integer> a = new BinaryMaxHeap<Integer>();
		//max exception
		NoSuchElementException maxException = assertThrows(NoSuchElementException.class, () -> a.extractMax());
		assertTrue(maxException.getMessage().contains("Index out of bounds"));
		// peek exception
		NoSuchElementException peekException = assertThrows(NoSuchElementException.class, () -> a.extractMax());
		assertTrue(peekException.getMessage().contains("Index out of bounds"));
		//same values test
		a.add(12);
		a.add(12);
		a.add(12);
		a.add(12);
		a.add(12);
		a.add(12);
		a.extractMax();
		assertEquals(12, a.extractMax());
	}
	@Test
	void listAndToArrayTest() 
	{
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(6);
		ints.add(3);
		ints.add(9);
		ints.add(10);
		ints.add(4);
		ints.add(12);
		ints.add(20);
		ints.add(0);
		ints.add(21);
		BinaryMaxHeap<Integer> a = new BinaryMaxHeap<Integer>(ints, (i1, i2) -> i2-i1);
		Object[] toArray =  a.toArray();
		assertEquals(0, toArray[0]);
		assertEquals(1, toArray[1]);
		assertEquals(3, toArray[2]);
		assertEquals(0, a.extractMax());
		assertEquals(1, a.extractMax());
		assertEquals(3, a.extractMax());
		assertEquals(4, a.extractMax());
	}
	@Test
	void KlargestTest()
	{
		List<Integer> ints = new ArrayList<Integer>();
		List<Integer> kInts = new ArrayList<Integer>();
		ints.add(1);
		ints.add(6);
		ints.add(3);
		ints.add(9);
		ints.add(10);
		ints.add(4);
		ints.add(12);
		ints.add(20);
		ints.add(0);
		ints.add(21);
		kInts = FindKLargest.findKLargestHeap(ints, 3);
		assertTrue(kInts.contains(21));
		assertTrue(kInts.contains(20));
		assertTrue(kInts.contains(12));
	}
	@Test
	void KSmallestComparatorTest()
	{
		List<Integer> ints = new ArrayList<Integer>();
		List<Integer> kInts = new ArrayList<Integer>();
		ints.add(1);
		ints.add(6);
		ints.add(3);
		ints.add(9);
		ints.add(10);
		ints.add(4);
		ints.add(12);
		ints.add(20);
		ints.add(0);
		ints.add(21);
		kInts = FindKLargest.findKLargestHeap(ints, 3,(i1, i2) -> i2-i1);
		assertTrue(kInts.contains(0));
		assertTrue(kInts.contains(1));
		assertTrue(kInts.contains(3));
	}
}
