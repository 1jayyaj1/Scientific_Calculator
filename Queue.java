//STUDENT NAME: JAY ABI-SAAD
//STUDENT ID: 260801368

public class Queue {

	private int count=0; //Initialize the count of the elements present in the queue.

	//If front of queue is null, then boolean expression evaluates to true (queue is empty).
	public boolean isEmpty() {
		return front==null;
	}

	public listNode back;
	public listNode front;

	int size() {	//Returns the number of elements in the queue.
		return count;
	}


	//Enqueue: adds element to the queue.
	public void Enqueue(String newStr) {
		listNode mn = new listNode(newStr);
		count++;	//Everytime an element is added (enqueued) on the queue, count increases by 1.
		//First we check if the queue is empty.
		if(back == null) {
			back = mn;
			front = mn;

		}
		else {
			//If the queue is not empty.
			mn.next = back;
			back.previous = mn;
			back = mn;
		}

	}

	//Dequeue: removes element from the queue.
	public String Dequeue() {

		//Checks whether we a have front node or not.
		if(front == null) {
			return null;
		}

		//Take the result string.
		String result_str = front.key_val;

		if(front.previous == null) {			
			front = null;
			back = null;
		}

		else {
			listNode pre_front = front.previous;
			pre_front.next = null;
			front = pre_front;	
		}
		count--;	//Everytime an element is removed (dequeued) from the queue, count decreases by 1.
		return result_str;
	}

	//Dequeue from the back De(): removes back element from the queue.
	public String De() {

		//Checks whether we a have front node or not.
		if(front == null) {
			return null;
		}

		//Take the result string.
		String result_str = back.key_val;

		if(front.previous == null) {			
			front = null;
			back = null;
		}

		else {
			listNode pre_front = back.next;
			pre_front.previous = null;
			back = pre_front;	
		}
		count--;	//Everytime an element is removed (dequeued) from the back of the queue, count also decreases by 1.
		return result_str;
	}

	//Prints queue (not used but here for future debugging purposes).
	public void print_my_queue() {

		//No back, queue is empty: nothing to print.
		if(back == null) {
			System.out.println("The queue is empty!");
		}

		//If back equals to front: prints only element present in the queue.
		else if(back.key_val == front.key_val) {
			System.out.print(front.key_val);
		}

		else {
			//While queue is not empty, prints the front element and assigns a new front.
			while(front != null) {
				System.out.print(front.key_val + " ");
				front = front.previous;
			}
		}
	}
}

