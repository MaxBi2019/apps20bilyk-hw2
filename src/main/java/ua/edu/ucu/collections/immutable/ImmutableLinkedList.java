package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList
{
    private static final int NOTFOUND = -1;
    private Node head;
    private Node tail;
    private int  size;

    public ImmutableLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    private ImmutableLinkedList copy()
    {
        ImmutableLinkedList copied = new ImmutableLinkedList();
        Node cur = head;
        for (int index = 0; index < size; ++index, cur = cur.getNext())
        {
            insert(index, cur.getData(), copied);
        }
        return copied;
    }

    private void checkInd(int index,
                          ImmutableLinkedList lst,
                          boolean existingOnly)
    {
        int limit = lst.size;
        if (existingOnly)
        {
            --limit;
        }
        if (limit < index || index < 0)
        {
            throw new IndexOutOfBoundsException("Index out of RANGE");
        }
    }

    private void inBetween(Node first, Node last, Object center)
    {
        Node centerNode = new Node(center);
        link(first, centerNode);
        centerNode.setNext(last);
    }

    private int findNode(Object value, ImmutableLinkedList lst)
    {
        int counter = 0;
        Node cur = lst.head;
        while (counter < lst.size)
        {
            if (cur.getData() == value)
            {
                return counter;
            }
            ++counter;
            cur = cur.getNext();
        }
        return NOTFOUND;

    }

    private void link(Node first, Node second)
    {
        first.setNext(second);
    }

    private void rmNode(int index, ImmutableLinkedList lst)
    {
        checkInd(index, lst, true);
        Node previous;
        if (lst.size == 1)
        {
            lst.head = null;
            lst.tail = null;
        }
        else if (index == 0)
        {
            lst.head = lst.head.getNext();
        }
        else if (index == lst.size-1)
        {
            lst.tail = getNode(index-1, lst);
            lst.tail.setNext(null);
        }
        else
        {
            previous = getNode(index-1, lst);
            link(previous, previous.getNext().getNext());
        }
        lst.size -= 1;
    }

    private void after(Node last, Object cur, ImmutableLinkedList arr)
    {
        Node curNode = new Node(cur);
        link(last, curNode);
        arr.tail = curNode;
    }

    private void before(Node first, Object cur, ImmutableLinkedList arr)
    {
        Node curNode = new Node(cur);
        link(curNode, first);
        arr.head = curNode;
    }

    private Node getNode(int index, ImmutableLinkedList lst)
    {
        checkInd(index, lst, true);
        Node cur = lst.head;
        for (int pos = 0; pos < index; ++pos)
        {
            cur = cur.getNext();
        }
        return cur;
    }

    private void insert(int index, Object elm, ImmutableLinkedList lst)
    {
        checkInd(index, lst, false);
        if (lst.size == 0)
        {
            lst.head = new Node(elm);
            lst.tail = lst.head;
        }
        else if (index == 0)
        {
            before(lst.head, elm, lst);
        }
        else if (index == lst.size)
        {
            after(lst.tail, elm, lst);
        }
        else
        {
            Node cur = getNode(index-1, lst);
            inBetween(cur, cur.getNext(), elm);
        }
        lst.size += 1;
    }

    private void insertArr(int index, Object[] arr, ImmutableLinkedList lst)
    {
        int length = arr.length;
        for (int ind = 0; ind < length; ++ind)
        {
            insert(index+ind, arr[ind], lst);
        }
    }


    @Override
    public ImmutableList add(int index, Object e)
    {
        ImmutableLinkedList newLst = copy();
        insert(index, e, newLst);
        return newLst;
    }

    @Override
    public ImmutableList add(Object e)
    {
        return add(size, e);
    }

    @Override
    public ImmutableList addAll(Object[] c)
    {
        return addAll(size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c)
    {
        ImmutableLinkedList newLst = copy();
        insertArr(index, c, newLst);
        return newLst;
    }

    @Override
    public Object get(int index)
    {
        return getNode(index, this).getData();
    }

    @Override
    public ImmutableList remove(int index)
    {
        ImmutableLinkedList newLst = copy();
        rmNode(index, newLst);
        return newLst;
    }

    @Override
    public ImmutableList set(int index, Object e)
    {
        ImmutableLinkedList newLst = copy();
        Node toBeSet = getNode(index, newLst);
        toBeSet.setData(e);
        return newLst;
    }

    @Override
    public int indexOf(Object e)
    {
        return findNode(e, this);
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public ImmutableList clear()
    {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public Object[] toArray()
    {
        Object[] lst = new Object[size];
        int length = size;
        Node   cur = head;
        for (int index = 0; index < length; ++index, cur = cur.getNext())
        {
            lst[index] = cur.getData();
        }
        return lst;
    }

    @Override
    public String toString()
    {
        Object[] arr = this.toArray();
        return "ImmutableLinkedList: " + Arrays.toString(arr);
    }


    private static class Node
    {
        private Object data;
        private Node next;
        public Node(Object data)
        {
            this.data = data;
            this.next = null;
        }


        private Node getNext()
        {
            return next;
        }
        private void setData(Object newData)
        {
            data = newData;
        }
        private void setNext(Node newNext)
        {
            next = newNext;
        }
        private Object getData()
        {
            return data;
        }
    }

    public ImmutableLinkedList addFirst(Object e)
    {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e)
    {
        return (ImmutableLinkedList) add(size, e);
    }

    public Object getFirst()
    {
        return get(0);
    }
    public Object getLast()
    {
        return get(size-1);
    }
    public ImmutableLinkedList removeFirst()
    {
        return (ImmutableLinkedList) remove(0);
    }
    public ImmutableLinkedList removeLast()
    {
        return (ImmutableLinkedList) remove(size -1);
    }
}
