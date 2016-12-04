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
	public static ArrayList<Integer> heapList = new ArrayList<Integer>();
	
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
		FileOutputStream fileOutputStream = null;
		String output = "C:/Users/Sneha/Documents/Output"+ counter +".txt";
		
		try
		{
			fileOutputStream = new FileOutputStream(output);
			
			for (int index = 0; index < arrayList.size(); index++)
			{
				fileOutputStream.write(arrayList.get(index).toString().getBytes());
				fileOutputStream.write(" ".getBytes());
			}
			readFirst(output);
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
	
	public static void readFirst(String output)
	{
		FileInputStream fileInputStream = null;
		
		try
		{
			fileInputStream = new FileInputStream(output);
			
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
	
	public static void mergeLists() throws FileNotFoundException
	{	

	}
	
	public static void main (String[] args) throws FileNotFoundException
	{
		System.out.println("Reading Data...");
		readChunk();
		mergeLists();
		System.out.println("Writing Data...");
	}
	
}