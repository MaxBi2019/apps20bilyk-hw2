package ua.edu.ucu.collections;

import org.junit.Test;
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
    
}
