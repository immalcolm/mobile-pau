package module1.examples;

public class ExampleLinkedList {

	public static void main(String[] args) {
		ExampleNode head = new ExampleNode(1); 
		ExampleNode second = new ExampleNode(2); 
		ExampleNode third = new ExampleNode(3); // Link the nodes 
		//linking the nodes
		head.next = second; 
        second.next = third; // Traverse and print 
        
        ExampleNode current = head; 

		while (current != null) { 
			System.out.print(current.data + " "); 
			current = current.next; 
		} 
	} 


}
