package ua.edu.ucu.collections.immutable;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    
    @Test
    public void testAdd()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Object[] dbl = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        Object[] rev = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        ImmutableList lst1 = new ImmutableLinkedList();
        ImmutableList lst2 = new ImmutableLinkedList();
        for (int times = arr.length; times > 0; --times)
        {
            lst1 = lst1.add(arr[times - 1]);
            lst2 = lst2.add(0, arr[times-1]);
        }

        assertArrayEquals(lst1.toArray(), rev);
        assertArrayEquals(lst2.toArray(), arr);

        for (int times = arr.length; times > 0; --times)
        {
            lst2 = lst2.add(times, arr[times-1]);
        }

        assertArrayEquals(lst2.toArray(), dbl);
    }

    @Test
    public void testAddAllNGet()
    {
        Object[] beginning = {1, 2, 3};
        Object[] middle    = {4, 5, 6};
        Object[] ending    = {7, 8, 9};
        Object[] ready     = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ImmutableList lst  = new ImmutableLinkedList();
        lst = lst.addAll(ending);
        lst = lst.addAll(0, beginning);
        lst = lst.addAll(3, middle);

        assertEquals(lst.size(), ready.length);
        for (int index = ready.length; index > 0; --index)
        {
            assertEquals(ready[index - 1], lst.get(index-1));
        }
    }

    @Test
    public void testRemoveNSet()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Object[] rev = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        String str   = "ImmutableLinkedList: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]";
        Object[] emp = {};
        ImmutableList lst = new ImmutableLinkedList();
        lst = lst.addAll(arr);
        for (int index = arr.length; index > 0; --index)
        {
            lst = lst.set(index-1, rev[index-1]);
        }
        assertArrayEquals(lst.toArray(), rev);
        assertTrue(str.equals(lst.toString()));

        lst.remove(1);
        lst.remove(0);
        for (int index = lst.size(); index > 0; --index)
        {
            lst = lst.remove(index-1);
        }
        assertArrayEquals(lst.toArray(), emp);
    }

    @Test
    public void testIndexOF()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ImmutableList lst = new ImmutableLinkedList();
        lst = lst.addAll(arr);
        for (int index = arr.length; index > 0; --index)
        {
            assertEquals(arr[index-1], arr[lst.indexOf(arr[index-1])]);
        }
        assertEquals(-1 , lst.indexOf(arr.length + 1));
    }

    @Test
    public void testClearNIsEmpty()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ImmutableList lst = new ImmutableLinkedList();
        assertTrue(lst.isEmpty());
        lst = lst.addAll(arr);
        assertFalse(lst.isEmpty());
        lst = lst.clear();
        assertTrue(lst.isEmpty());
    }

    @Test
    public void TestAddLastNGet()
    {
        Object[][] arr = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                          {1, 2, 3, 4, 5, 6, 7, 8, 9},
                          {1, 2, 3, 4, 5, 6, 7, 8},
                          {1, 2, 3, 4, 5, 6, 7},
                          {1, 2, 3, 4, 5, 6},
                          {1, 2, 3, 4, 5},
                          {1, 2, 3, 4},
                          {1, 2, 3},
                          {1, 2},
                          {1},
                          {}};
        int arrSize = arr.length;
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst.addAll(arr[arrSize-1]);
        for (int ind = arrSize-1; ind > 0; --ind)
        {
            lst = lst.addLast(arr[ind-1][arrSize-ind-1]);
            assertArrayEquals(arr[ind-1], lst.toArray());
            assertEquals(arr[ind-1][arrSize-ind-1], lst.getLast());
        }

    }

    @Test
    public void TestAddFirstNGet()
    {
        Object[][] arr = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                          {2, 3, 4, 5, 6, 7, 8, 9, 10},
                          {3, 4, 5, 6, 7, 8, 9, 10},
                          {4, 5, 6, 7, 8, 9, 10},
                          {5, 6, 7, 8, 9, 10},
                          {6, 7, 8, 9, 10},
                          {7, 8, 9, 10},
                          {8, 9, 10},
                          {9, 10},
                          {10},
                          {}};
        int arrSize = arr.length;
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst = (ImmutableLinkedList) lst.addAll(arr[arrSize-1]);
        for (int ind = arrSize-1; ind > 0; --ind)
        {
            lst = lst.addFirst(arr[ind-1][0]);
            assertArrayEquals(arr[ind-1], lst.toArray());
            assertEquals(arr[ind-1][0], lst.getFirst());
        }

    }

    @Test
    public void TestRemoveFL()
    {
        Object[][] arr = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                            {2, 3, 4, 5, 6, 7, 8, 9, },
                               {3, 4, 5, 6, 7, 8, },
                                   {4, 5, 6, 7},
                                       {5, 6},
                                         {}};
        int arrSize = arr.length;
        ImmutableLinkedList lst = new ImmutableLinkedList();
        lst = (ImmutableLinkedList) lst.addAll(arr[0]);
        for (int ind = 0; ind < arrSize; ++ind)
        {
            System.out.println(lst.toString());
            assertArrayEquals(arr[ind], lst.toArray());
            if (lst.isEmpty())
                break;
            lst = lst.removeFirst();
            lst = lst.removeLast();
        }

    }

}
