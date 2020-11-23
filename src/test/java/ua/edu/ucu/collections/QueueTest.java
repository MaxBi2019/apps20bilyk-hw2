package ua.edu.ucu.collections;

import org.junit.Test;
import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.ImmutableList;

import static org.junit.Assert.*;

public class QueueTest {
    
    @Test
    public void testEnqueueNDequeueNDequeue()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Queue queue = new Queue();
        for (int ind = arr.length; ind > 0; --ind)
        {
            queue.enqueue(arr[ind-1]);
        }

        for (int ind = arr.length; ind > 0; --ind)
        {
            assertEquals(queue.peek(), arr[ind-1]);
            assertEquals(queue.dequeue(), arr[ind-1]);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongPeek()
    {
        Queue queue = new Queue();

        // expect exception here
        queue.peek();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeque()
    {
        Queue queue = new Queue();

        // expect exception here
        queue.dequeue();
    }
}
