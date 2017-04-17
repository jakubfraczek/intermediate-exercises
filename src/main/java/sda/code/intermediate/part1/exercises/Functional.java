package sda.code.intermediate.part1.exercises;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import sda.code.intermediate.part1.exercises.data.Item;
import sda.code.intermediate.part1.exercises.data.Product;

public class Functional {

	public List<Integer> squares(List<Integer> list) {
		return list.parallelStream().map(x -> x * x).collect(java.util.stream.Collectors.toList());
	}

	public List<Integer> even(List<Integer> list) {
		return list.parallelStream().filter(x -> x % 2 == 0).collect(java.util.stream.Collectors.toList());
	}

	public Long countOdd(List<Integer> list) {
		return list.parallelStream().filter(x -> x % 2 != 0).collect(java.util.stream.Collectors.counting());
	}

	public Integer smallest(List<Integer> list) {
		return list.parallelStream().reduce((min, x) -> x < min ? x : min).orElseThrow(IllegalArgumentException::new);
	}

	private BigDecimal bruttoSum(Predicate<Product> policy, Function<BigDecimal, BigDecimal> tax,
			List<Product> products) {
		return products.stream().map(x -> policy.test(x) ? tax.apply(x.getPrice()) : x.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private Predicate<Product> taxOnItems() {
		return p -> p instanceof Item;
	}

	private Function<BigDecimal, BigDecimal> itemTax(BigDecimal tax) {
		return t -> (tax.multiply(t)).add(t);
	}

	public BigDecimal cartBruttoSum(List<Product> products) {
		return bruttoSum(taxOnItems(), itemTax(new BigDecimal("0.42")), products);
	}

}
