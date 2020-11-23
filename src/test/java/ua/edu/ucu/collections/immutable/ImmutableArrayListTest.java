package ua.edu.ucu.collections.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableArrayListTest {

    @Test
    public void testAdd()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Object[] dbl = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        Object[] rev = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        ImmutableList lst1 = new ImmutableArrayList();
        ImmutableList lst2 = new ImmutableArrayList();
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
        ImmutableList lst  = new ImmutableArrayList();
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
        String str   = "ImmutableArrayList: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]";
        Object[] emp = {};
        ImmutableList lst = new ImmutableArrayList();
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
        ImmutableList lst = new ImmutableArrayList();
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
        ImmutableList lst = new ImmutableArrayList();
        assertTrue(lst.isEmpty());
        lst = lst.addAll(arr);
        assertFalse(lst.isEmpty());
        lst = lst.clear();
        assertTrue(lst.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongRemove()
    {
        ImmutableList lst = new ImmutableLinkedList();

        // expect exception here
        lst.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongSet()
    {
        ImmutableList lst = new ImmutableLinkedList();

        // expect exception here
        lst.set(1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongGet()
    {
        ImmutableList lst = new ImmutableLinkedList();

        // expect exception here
        lst.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongAdd()
    {
        ImmutableList lst = new ImmutableLinkedList();

        // expect exception here
        lst.add(1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongAddAll()
    {
        ImmutableList lst = new ImmutableLinkedList();

        // expect exception here
        Object[] arr = {1, 2, 3};
        lst.add(1, arr);
    }
}
