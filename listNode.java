//STUDENT NAME: JAY ABI-SAAD
//STUDENT ID: 260801368

public class listNode {
	public String key_val; // Payload
	public listNode next; // Pointer to the next node
	public listNode previous; // Pointer to the previous node

	public listNode(String key_val) {
		super();
		this.key_val = key_val;
		next = null;
		previous = null;
	}
}
