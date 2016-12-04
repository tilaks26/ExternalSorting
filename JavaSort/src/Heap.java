public class Heap
{
    private int[] Heap;
    private int size;
    private int maximumSize;
 
    private static final int FIRST = 1;
 
    public Heap(int maximumSize)
    {
        this.maximumSize = maximumSize;
        this.size = 0;
        Heap = new int[this.maximumSize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }
 
    private int parentElement(int position)
    {
        return position / 2;
    }
 
    private int leftChildElement(int position)
    {
        return (2 * position);
    }

    private int rightChildElement(int position)
    {
        return (2 * position) + 1;
    }
 
    private boolean isLeafElement(int position)
    {
        if (position >=  (size / 2)  &&  position <= size)
        { 
            return true;
        }
        return false;
    }
 
    private void swapElements(int fPosition, int sPosition)
    {
        int temp;
        temp = Heap[fPosition];
        Heap[fPosition] = Heap[sPosition];
        Heap[sPosition] = temp;
    }
 
    private void buildMinHeap(int position)
    {
        if (!isLeafElement(position))
        { 
            if ( Heap[position] > Heap[leftChildElement(position)]  || Heap[position] > Heap[rightChildElement(position)])
            {
                if (Heap[leftChildElement(position)] < Heap[rightChildElement(position)])
                {
                	swapElements(position, leftChildElement(position));
                	buildMinHeap(leftChildElement(position));
                }
                else
                {
                	swapElements(position, rightChildElement(position));
                	buildMinHeap(rightChildElement(position));
                }
            }
        }
    }
 
    public void insertElement(int value)
    {
        Heap[++size] = value;
        int currentElement = size;
 
        while (Heap[currentElement] < Heap[parentElement(currentElement)])
        {
        	swapElements(currentElement, parentElement(currentElement));
        	currentElement = parentElement(currentElement);
        }	
    }
 
    public void minHeap()
    {
        for (int position = (size / 2); position >= 1 ; position--)
        {
        	buildMinHeap(position);
        }
    }
 
    public int deleteMin()
    {
        int deletedElement = Heap[FIRST];
        Heap[FIRST] = Heap[size--]; 
        buildMinHeap(FIRST);
        return deletedElement;
    }
 
    public static void main(String[] args)
    {
        Heap minHeap = new Heap(15);
        minHeap.insertElement(5);
        minHeap.insertElement(3);
        minHeap.insertElement(13);
        minHeap.insertElement(10);
        minHeap.insertElement(84);
        minHeap.insertElement(19);
        minHeap.insertElement(6);
        minHeap.insertElement(22);
        minHeap.insertElement(9);
        minHeap.minHeap();
        
        System.out.println("The Min val is " + minHeap.deleteMin());
    }
}