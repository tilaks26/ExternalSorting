import java.util.Scanner;

public class Node 
{

	private int element;
	private Scanner scanner;
	
	public int getElement() 
	{
		return element;
	}
	
	public void setElement(int element) 
	{
		this.element = element;
	}
	
	public Scanner getScanner() 
	{
		return scanner;
	}
	
	public void setScanner(Scanner scanner) 
	{
		this.scanner = scanner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + element;
		result = prime * result + ((scanner == null) ? 0 : scanner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (element != other.element)
			return false;
		if (scanner == null) {
			if (other.scanner != null)
				return false;
		} else if (!scanner.equals(other.scanner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [element=" + element + ", scanner=" + scanner + "]";
	}
		
}