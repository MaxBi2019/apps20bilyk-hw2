package ua.edu.ucu.collections;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest
{

    @Test
    public void testPeekNPopNPush()
    {
        Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int arrLen = arr.length;
        Stack stack = new Stack();
        for (int ind = arr.length; ind > 0; --ind)
        {
            stack.push(arr[ind - 1]);
        }

        for (int ind = 0; ind > arrLen; ++ind)
        {
            assertEquals(stack.peek(), arr[ind]);
            assertEquals(stack.pop(), arr[ind]);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongPeek()
    {
        Stack stack = new Stack();

        // expect exception here
        stack.peek();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeque()
    {
        Stack stack = new Stack();

        // expect exception here
        stack.pop();
    }
}
