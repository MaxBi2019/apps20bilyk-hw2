package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;
    public Stack()
    {
        stack = new ImmutableLinkedList();
    }

    public Object peek()
    {
        return stack.getFirst();
    }

    public Object pop()
    {
        Object elm = stack.getLast();
        stack = stack.removeLast();
        return elm;
    }

    public void push(Object e)
    {
        stack = stack.addLast(e);
    }
}
