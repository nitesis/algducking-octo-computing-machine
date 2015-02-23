package ch.fhnw.algd2.collections.list.stack;

public class MyStack<E> implements IStack<E> {
	@Override
	public E pop() {
		// TODO implement this operation
		throw new UnsupportedOperationException();
	}

	@Override
	public E push(E item) {
		// TODO implement this operation
		throw new UnsupportedOperationException();
	}

	@Override
	public E peek() {
		// TODO implement this operation
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean empty() {
		// TODO implement this operation
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>();
		System.out.println("Pushing numbers to stack (0 to 9)");
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		System.out.println("Pop numbers from stack");
		while (!stack.empty()) {
			System.out.println(stack.pop());
		}
	}
}
