package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList{

    private final int startSize = 10;
    private int pSize;
    private int lSize;
    private Object[] arrayList;

    public ImmutableArrayList()
    {
        pSize = startSize;
        lSize = 0;
        arrayList = new Object[startSize];
    }

    private boolean hasEnoughSpace(int num)
    {
        return pSize > (lSize + num);
    }

    private void checkInd(int index,
                          ImmutableArrayList array,
                          boolean existingOnly)
    {
        int limit = array.lSize;
        if (existingOnly)
        {
            --limit;
        }
        if (limit < index || index < 0)
        {
            throw new IndexOutOfBoundsException("Index out of RANGE");
        }
    }

    private void copyTo(Object[] Dest, int posDest, int posFrom, int length)
    {
        if (length == -1)
            length = lSize;
        System.arraycopy(arrayList, posFrom, Dest, posDest, length);
    }

    private ImmutableArrayList configArray()
    {
        int size = lSize;
        Object[] buffer = new Object[size];
        copyTo(buffer, 0, 0, lSize);
        ImmutableArrayList array = new ImmutableArrayList();
        array.arrayList = buffer;
        array.pSize = pSize;
        array.lSize = lSize;
        return array;
    }

    private ImmutableArrayList configArray(int index)
    {
        int size = lSize;
        Object[] buffer = new Object[size];

        if (index == 0)
        {
            copyTo(buffer, 0, index+1, lSize-1);
        }
        else if (index == lSize-1)
        {
            copyTo(buffer, 0, 0, lSize-1);
        }
        else
        {
            copyTo(buffer, 0, 0, index-1);
            copyTo(buffer, index - 1, index+1, lSize - index-1);
        }

        ImmutableArrayList array = new ImmutableArrayList();
        array.arrayList = buffer;
        array.pSize = size;
        array.lSize = lSize-1;
        return array;
    }


    private ImmutableArrayList configArray(int      index,
                                           Object[] toInsert)
    {
        int size;
        if (hasEnoughSpace(toInsert.length))
            size = pSize;
        else
            size = (toInsert.length > pSize) ? toInsert.length*2 : pSize*2;
        Object[] buffer = new Object[size];
        for (int ind = toInsert.length; ind > 0; --ind)
            buffer[ind+index-1] = toInsert[ind-1];

        if (!isEmpty())
        {
            if (index == 0)
            {
                copyTo(buffer, toInsert.length, 0, lSize);
            }
            else if (index == lSize)
            {
                copyTo(buffer, 0, 0, lSize);
            }
            else
            {
                copyTo(buffer, 0, 0, index);
                copyTo(buffer, index + toInsert.length, index, lSize - index);
            }
        }

        ImmutableArrayList array = new ImmutableArrayList();
        array.arrayList = buffer;
        array.pSize = size;
        array.lSize = toInsert.length + lSize;
        return array;
    }

    @Override
    public ImmutableList add(Object e)
    {
        Object[] soloList = {e};
        return configArray(lSize, soloList);
    }

    @Override
    public ImmutableList add(int index, Object e)
    {
        checkInd(index, this, false);
        Object[] soloList = {e};
        return configArray(index, soloList);
    }

    @Override
    public ImmutableList addAll(Object[] c)
    {
        return configArray(lSize, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c)
    {
        checkInd(index, this, false);
        return configArray(index, c);

    }

    @Override
    public Object get(int index)
    {
        checkInd(index, this, true);
        return  arrayList[index];
    }

    @Override
    public ImmutableList remove(int index)
    {
        checkInd(index, this, true);
        return configArray(index);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkInd(index, this, true);
        ImmutableArrayList array = configArray();
        array.arrayList[index] = e;
        return array;
    }

    @Override
    public int indexOf(Object e)
    {
        for (int ind = lSize; ind > 0; --ind)
        {
            if (arrayList[lSize - ind] == e)
                return  lSize - ind;
        }
        return -1;
    }

    @Override
    public int size() {
        return lSize;
    }

    @Override
    public ImmutableList clear()
    {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return lSize == 0;
    }

    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[lSize];
        copyTo(array, 0, 0, -1);
        return  array;
    }

    @Override
    public String toString()
    {
        return "ImmutableArrayList: " + Arrays.toString(toArray());
    }
}
