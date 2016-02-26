package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List
 * structure to allow for unbounded size.
 *
 * @param <T>
 *            the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	LLNode<T> head;
	int size = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			LLNode<T> rVal = head;
			head = head.getNext();
			rVal.setNext(null);
			size--;
			return rVal.getData();
		} else {
			throw new StackUnderflowException("Is empty");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (!isEmpty()) {
			return head.getData();
		} else {
			throw new StackUnderflowException("Is Empty");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> toAdd = new LLNode<T>(elem);
		toAdd.setNext(head);
		head = toAdd;
		size++;
	}

	public void flush() {
		LLNode<T> temp = head;
		while (head != null) {
			temp = head.getNext();
			head.setNext(null);
			head = temp;
		}
	}

}
