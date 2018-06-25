//STUDENT NAME: JAY ABI-SAAD
//STUDENT ID: 260801368

public class Stack {

	//Referring to the top element of the listNode.
	public static listNode top;

	//If top of stack is null, then boolean expression evaluates to true (stack is empty).
	public boolean isEmpty() {
		return top==null;
	}

	//Lets me peek at the top of the stack to evaluate and compare it.
	public String peek() {
		if(top==null) {
		}

		else;
		return top.key_val;
	}

	//Push to stack.
	public void push(String newStr) {
		listNode mn = new listNode(newStr);

		if(top != null) {		
			top.next = mn;
			mn.previous = top;		
		}
		top = mn;
	}

	//Pop from stack.
	public String pop() {

		//Null top means that the stack is empty and can't be "popped".
		if(top == null) {
			return null;
		}

		String result_str = top.key_val;		
		//We have one node in the stack.
		if(top.previous == null) {
			top = null;  
		}

		else {
			//We have more than one node in the stack.
			listNode pre_top = top.previous;
			pre_top.next = null;
			top = pre_top;
		}			
		return result_str;
	}

	//Prints stack (not used but here for future debugging purposes).
	public void print_my_stack() {

		//If top is null, then stack is empty: nothing to print.
		if(top == null) {
			System.out.println("The stack is empty!");
		}

		//If what's previous to the top is null, the top is the only element in the stack: prints top.
		else if(top.previous == null) {
			System.out.println(top.key_val);
		}

		//While the stack is not empty, prints top and assigns previous top to new top position.
		else {
			while(top != null) {
				System.out.println(top.key_val);
				top = top.previous;
			}
		}
	}
}

