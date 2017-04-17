package sda.code.intermediate.part1.exercises;

import java.math.BigInteger;

public class Algorithms {

	public int smallest(int array[]) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("No values in array");
		} else {
			int smallest = array[0];
			for (int a : array) {
				if (smallest > a) {
					smallest = a;
				}
			}
			return smallest;
		}
	}

	public int largest(int array[]) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("No values in array");
		} else {
			int lagrgest = array[0];
			for (int a : array) {
				if (lagrgest < a) {
					lagrgest = a;
				}
			}
			return lagrgest;
		}
	}

	public long factorial(long n) {
		long factorial = 1;
		if (n < 0) {
			throw new IllegalArgumentException("Value under 0");
		} else if (n == 0) {
			return factorial;
		} else {
			for (int i = 1; i <= n; i++) {
				factorial *= i;
			}
			return factorial;
		}

	}

	public long fibonacci(long n) {
		long fibonacci = 1;
		long fibonacciOneLess = 0;
		long temp;
		if (n < 0) {
			throw new IllegalArgumentException("Value under 0");
		} else {
			if (n == 0) {
				return 0;
			} else if (n == 1) {
				return 1;
			} else {
				for (int i = 2; i <= n; i++) {
					temp = fibonacci;
					fibonacci += fibonacciOneLess;
					fibonacciOneLess = temp;
				}
			}
			return fibonacci;
		}

	}

	public BigInteger bigFibonacci(int n) {
		BigInteger fibonacci = BigInteger.ONE;
		BigInteger fibonacciOneLess = BigInteger.ZERO;
		BigInteger temp;
		if (n < 0) {
			throw new IllegalArgumentException("Value under 0");
		} else {
			if (n == 0) {
				return BigInteger.ZERO;
			} else if (n == 1) {
				return BigInteger.ONE;
			} else {
				for (int i = 2; i <= n; i++) {
					temp = fibonacci;
					fibonacci = fibonacci.add(fibonacciOneLess);
					fibonacciOneLess = temp;
				}
			}
			return fibonacci;
		}
	}

	public int[] createRandomArray(int length) {
		if (length < 0){
			throw new IllegalArgumentException("Array cannot have negative length");
		}
		int[] array = new int[length];
		for (int i = 0; i < length; i++){
			array[i] = (int) (Math.random() * 100);
		}
		return array;
	}

}
