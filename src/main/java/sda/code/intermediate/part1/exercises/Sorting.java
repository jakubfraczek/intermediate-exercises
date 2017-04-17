package sda.code.intermediate.part1.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

	public int[] bubbleSort(int array[]) {
		int out[] = array.clone();
		bubbleSortInner(out);
		return out;
	}

	private void bubbleSortInner(int array[]) {
		int n = array.length;
		int temp;
		do {
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
			n -= 1;
		} while (n > 1);
	}

	public int[] quickSort(int array[]) {
		int out[] = array.clone();
		quickSortInner(out, 0, out.length - 1);
		return out;
	}

	private void quickSortInner(int[] out, int low, int high) {
		if (low < high) {
			int p = partition(out, low, high);
			quickSortInner(out, low, p - 1);
			quickSortInner(out, p + 1, high);
		}
	}

	private int partition(int[] out, int low, int high) {
		int pivot = out[high];
		int i = low;
		int temp;

		for (int j = low; j <= (high - 1); j++) {
			if (out[j] <= pivot) {
				temp = out[i];
				out[i] = out[j];
				out[j] = temp;
				i += 1;
			}
		}
		temp = out[i];
		out[i] = out[high];
		out[high] = temp;
		return i;
	}

	public int[] mergeSort(int array[]) {


		if (array.length <= 1) {
			return array;
		} else {

		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;

		int[] leftArray = new int[leftSize];
		int[] rightArray = new int[rightSize];
		int j = 0;
		int k = 0; 

		for (int i = 0; i < array.length; i++) {
			if (i < leftSize) {
				leftArray[j] = array[i];
				j++;
			} else {
				rightArray[k] = array[i];
				k++;
			}
		}

		return merge(mergeSort(rightArray), mergeSort(leftArray));
		}
		
//		  int out[] = array.clone(); 
//		  out = mergeSortInner(out, 0, out.length - 1); 
//		  return out;
		 
	}

	private int[] merge(int arrayLeft[], int arrayRight[]) {

		int[] result = new int[arrayLeft.length + arrayRight.length];
		int i = 0;
		int j = 0;

		for (int k = 0; k < result.length; k++) {
			if (j >= arrayRight.length || i < arrayLeft.length && arrayLeft[i] > arrayRight[j]) {
				result[k] = arrayLeft[i];
				i++;
			} else {
				result[k] = arrayRight[j];
				j++;
			}
		}
		
		
		return result;
	}

	private int[] mergeSortInner(int array[], int low, int high) {
		int halfIndex;
		int arrayTemp[];
		int leftIndex = 0;
		int rightIndex = 0;
		if (low < high) {
			arrayTemp = new int[(array.length - 1)];
			halfIndex = (low + high) / 2;
			int[] arrayLeft = mergeSortInner(array, low, halfIndex);
			int[] arrayRight = mergeSortInner(array, halfIndex + 1, high);
			for (int i = 0; i < arrayTemp.length; i++) {
				if (rightIndex > arrayRight.length || leftIndex <= arrayLeft.length && arrayLeft[leftIndex] > arrayRight[rightIndex]) {
					arrayTemp[i] = arrayLeft[leftIndex];
					leftIndex++;
				} else {
					arrayTemp[i] = arrayRight[rightIndex];
					rightIndex++;
				}
			}
		} else {
			arrayTemp = array;
		}
		return arrayTemp;

	}

	public String[] sortStrings(String[] array) {
		String[] out = array.clone();
		Arrays.sort(out);
		return out;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> sortWithComparator(ArrayList<String> array, Comparator<String> comp) {
		ArrayList<String> out = (ArrayList<String>) array.clone();
		out.sort(comp);
		return out;
	}

	public static class NumericIntegerComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return (Integer.parseInt(o1) - Integer.parseInt(o2));
		}

	}

	public <T extends Comparable<? super T>> T[] sortComparable(T array[]) {
		T[] out = array.clone();
		sortComparableInner(out);
		return out;
	}

	private <T extends Comparable<? super T>> void sortComparableInner(T[] array) {
		int n = array.length;
		T temp;
		do {
			for (int i = 0; i < n - 1; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
				}
			}
			n -= 1;
		} while (n > 1);
		;
	}

}
