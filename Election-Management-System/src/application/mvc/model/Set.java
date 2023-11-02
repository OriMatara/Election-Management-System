package application.mvc.model;

import java.io.Serializable;

public class Set<T> implements Serializable {

	private final int ENLARGE_FACTOR = 2;
	public T[] arr;
	private int currentSize;

	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public T get(int index) {
		return arr[index];
	}

	public int capacity() {
		return arr.length;
	}

	public void add(T newValue) throws Exception {
		if (!(newValue instanceof Citizen)) {
			throw new Exception("you must add citizens to this set collection!!!");
		}
		if (currentSize == arr.length) {
			enlargeArray();
		}

		Citizen c = (Citizen) newValue;
		
		Citizen[] temp = new Citizen[arr.length];
		for (int i = 0; i < currentSize; i++) {
			temp[i] = (Citizen) arr[i];
		}
		for (int i = 0; i < currentSize; i++) {
			if (c.id.equals(temp[i].getId())) {
				System.out.println(" is already exist in the system");
				return;
			}
		}

		arr[currentSize] = newValue;
		currentSize++;
		System.out.println("successfully added");
	}

	private void enlargeArray() {
		T[] temp = (T[]) new Object[arr.length * ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}

	@Override
	public String toString() {
		String str = "[ ";
		for (int i = 0; i < currentSize; i++) {
			str += arr[i] + " ";
		}
		str += "]";
		return str;
	}

}
