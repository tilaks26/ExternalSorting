import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ExternalSorting 
{

	public static final String inputFilename = "C:/Users/Sneha/Documents/Input.txt";
	public static final String outputFilename = "C:/Users/Sneha/Documents/Output.txt";
	public static final byte[] buffer = new byte[1024];
	public static ArrayList<Node> heapList = new ArrayList<Node>();
	public static ArrayList<String> fileNames = new ArrayList<String>();
	public static ArrayList<Scanner> scannerlist=new ArrayList<Scanner>();
	public static Heap heap = new Heap(100);
	
	public static void readChunk() throws FileNotFoundException
	{	
		FileInputStream fileInputStream = null;
		
		try
		{
			fileInputStream = new FileInputStream(inputFilename);			
			int endPointer = 0;
			int counter = 0;
			
			while ((endPointer = fileInputStream.read(buffer)) != -1)
			{
				counter++;
				String chunk = new String(buffer, 0, endPointer);
				Scanner scanner = new Scanner(chunk);
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				while (scanner.hasNext())
				{
					if (scanner.hasNextInt())
					{
						arrayList.add(scanner.nextInt());
					}
					else
					{
						scanner.next();
					}
				}
				
				scanner.close();
				int low = 0;
				int high = arrayList.size() - 1;
				arrayList = quickSort(arrayList, low, high);
				System.out.println("List of integers : "+arrayList);
				writeChunk(arrayList, counter);
			}
			
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileInputStream != null)
				{
					fileInputStream.close();
				}
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
		
	}
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> arrayList, int low, int high)
	{
		if (arrayList == null || arrayList.size() == 0)
			return null;
 
		if (low >= high)
			return null;
		
		Random random = new Random();
		int pivotIndex = random.nextInt(high - low + 1) + low;
		int pivot = arrayList.get(pivotIndex);
		
		int i = low, j = high;
		while (i <= j) 
		{
			while (arrayList.get(i) < pivot) 
			{
				i++;
			}
 
			while (arrayList.get(j) > pivot) 
			{
				j--;
			}

			if (i <= j) 
			{	
				Collections.swap(arrayList, i, j);
				i++;
				j--;
			}
		}
		
		if (low < j)
			quickSort(arrayList, low, j);
 
		if (high > i)
			quickSort(arrayList, i, high);
		
		return arrayList;
	}
	
	public static void writeChunk(ArrayList<Integer> arrayList, int counter) throws FileNotFoundException
	{
		System.out.println("Counter is : "+counter);
		FileOutputStream fileOutputStream = null;
		String output = "C:/Users/Sneha/Documents/Output"+ counter +".txt";
		
		try
		{
			fileOutputStream = new FileOutputStream(output);
			fileNames.add(output);
			
			for (int index = 0; index < arrayList.size(); index++)
			{
				fileOutputStream.write(arrayList.get(index).toString().getBytes());
				fileOutputStream.write(" ".getBytes());
			}
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileOutputStream != null)
				{
					fileOutputStream.close();
				}
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public static void initPrepareHeap() throws FileNotFoundException
	{
		for (String output : fileNames)
		{
			System.out.println(output);
			Node node = new Node();
			Scanner scanner = new Scanner(new File(output));
			scannerlist.add(scanner);
			
			
			int element = Integer.parseInt(scanner.next());
			System.out.println("Element : "+element);
			node.setElement(element);
			node.setScanner(scanner);
			heapList.add(node);
		}
		
		for (Node node : heapList)
		{
			heap.insertElement(node);
		}
	}
	
	public static void mergeLists()
	{	
		
		try
		{
			initPrepareHeap();
			System.out.println("Init heap set up done!!");
			int loopcount=0;
			while(heapList.size()>=1)
			{				
					
				System.out.println("Loop counter"+loopcount);
				loopcount++;
				for(Node node:heapList)
				{
					System.out.println("values in heapList"+node.getElement());	
				}
				
				if(heapList.size()==1)
				{
					heap.minHeap();
					heap.deleteMin();
					writeFinal(heapList.get(0).getElement());
					System.out.println("Last element is"+heapList.get(0).getElement());
					break;
				}
				
				heap.minHeap();
				heap.print();
				
				Node delNode = heap.deleteMin();
				System.out.println("Min: "+delNode.getElement());
				
				writeFinal(delNode.getElement());
				
				heapList.remove(delNode);
				
				for (Node node : heapList)
				{
					System.out.println("Heap list element: "+ node.getElement());
				}
				
				if(delNode.getScanner().hasNext())
				{
					
					System.out.println("element returned from delete min"+delNode.getElement());
					if (delNode.getScanner() != null)
					{
						System.out.println(delNode.getScanner() != null);
						getNext(delNode);
					}
					
					else
					{
						System.out.println("in else");
						continue;
					}
				}
				else
				{
					Scanner newScanner = null;				
					scannerlist.remove(delNode.getScanner());
					boolean flag = false;
					
					for (Scanner scanner : scannerlist)
					{
						System.out.println("Scanner "+scanner);
					}
					
					if(!scannerlist.isEmpty())
					{
						for(int i = 0; i<scannerlist.size(); i++)
						{
							if (scannerlist.get(i) != null)
							{
								System.out.println("Here");
								if (scannerlist.get(i).hasNext())
								{
									System.out.println("In");
									newScanner=scannerlist.get(i);
									System.out.println("New scanner "+ newScanner);
									flag = true;
									break;
								}
							}
						}
						
						if (flag == true)
						{
							int element = Integer.parseInt(newScanner.next());
							Node otherNode=new Node();
							otherNode.setElement(element);
							otherNode.setScanner(newScanner);
							heapList.add(otherNode);
							heap.insertElement(otherNode);
						}						
						
						System.out.println("end of file reached");
						continue;
					}
					else
					{
						System.out.println("Reached here");
						heap.reduceSize();
					}
					
				}
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public static void getNext(Node node)
	{
		int element = Integer.parseInt(node.getScanner().next());
		System.out.println("Del ele: "+element);
		Node newNode = new Node();
		newNode.setElement(element);
		newNode.setScanner(node.getScanner());
		heapList.add(newNode);
		heap.insertElement(newNode);
	}
	
	public static void writeFinal(int element)
	{
		FileOutputStream fileOutputStream = null;
		
		try
		{
			fileOutputStream = new FileOutputStream(outputFilename, true);
			fileOutputStream.write(((Integer)element).toString().getBytes());
			fileOutputStream.write(" ".getBytes());
			
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileOutputStream != null)
				{
					fileOutputStream.close();
				}
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public static void main (String[] args) throws FileNotFoundException
	{
		
		System.out.println("Reading Data...");
		
		double start = System.currentTimeMillis();
		
		readChunk();
		mergeLists();
		
		double end = System.currentTimeMillis();
		
		System.out.println("Writing Data...");
		
		double totalTime = end - start;
		
		System.out.printf("Total time taken (in Seconds) : %.4f%n", totalTime/1e3);
	}
	
}