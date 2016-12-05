public class Heap
{
    private Node[] Heap;
    private int size;
    private int maximumSize;
 
    private static final int FIRST = 1;
 
    public Heap(int maximumSize)
    {
        this.maximumSize = maximumSize;
        this.size = 0;
        Heap = new Node[this.maximumSize + 1];
        Node initNode = new Node();
        initNode.setElement(0);
        initNode.setScanner(null);
        Heap[0] = initNode;
        //Heap[0] = Integer.MIN_VALUE;
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
        Node tempNode = new Node();
        tempNode = Heap[fPosition];
        Heap[fPosition] = Heap[sPosition];
        Heap[sPosition] = tempNode;
    }
 
    private void buildMinHeap(int position)
    {
        System.out.println("in buidMinHEAP "+position);
    	System.out.println("Heap[position].getElement()"+Heap[position].getElement());
        //System.out.println("Heap[leftChildElement(position)].getElement()"+Heap[leftChildElement(position)].getElement());
    	//System.out.println("Heap[rightChildElement(position)].getElement())"+Heap[rightChildElement(position)].getElement());
    	if (!isLeafElement(position))
        { 
            if ( Heap[position].getElement() > Heap[leftChildElement(position)].getElement()  || Heap[position].getElement() > Heap[rightChildElement(position)].getElement())
            {
                if (Heap[leftChildElement(position)].getElement() < Heap[rightChildElement(position)].getElement())
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
    
    public void minHeap()
    {
    	System.out.println("size value"+size);
    	//double tempsize=(size/2);
    	/*if(size==2){
    		System.out.println("size==2");
    		buildMinHeap(1);
    		return;
    	}*/
        for (int position = size/2; position >= 1 ; position--)
        {	
        	System.out.println("pos value in minHeap for loop"+position);
        	buildMinHeap(position);
        }
    }
    
    public void insertElement(Node node)
    {
    	//int value = node.getElement();
        Heap[++size] = node;
        int currentElement = size;
 
        System.out.println("Curr: " +Heap[currentElement].getElement());
        System.out.println("Par: " +Heap[parentElement(currentElement)].getElement());
        
        while (Heap[currentElement].getElement() < Heap[parentElement(currentElement)].getElement())
        {
        	swapElements(currentElement, parentElement(currentElement));
        	currentElement = parentElement(currentElement);
        }	
    }
 
    public void reduceSize()
    {
    	size--;
    }
    
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            /*System.out.print(" PARENT : " + Heap[i].getElement() + " LEFT CHILD : " + Heap[2*i].getElement() 
                + " RIGHT CHILD :" + Heap[2 * i  + 1].getElement());
           */ 
        	System.out.println();
        } 
    }
 
    public Node deleteMin()
    {
        Node deletedNode = Heap[FIRST];
        
        System.out.println("Delete node is"+deletedNode.getElement());
        if(size>0)
        {
        	Heap[FIRST] = Heap[size--];	
        }
        if(size==0)
        {
        	deletedNode=Heap[FIRST];
        }
         
        buildMinHeap(FIRST);
        return deletedNode;
    }

}