package sda.code.intermediate.part1.exercises.patterns;

import java.math.BigDecimal;

@SuppressWarnings("unchecked")
public abstract class ProductBuilder<T, R> {
	protected String name;
	protected BigDecimal price;
	private String maybyPrice;

	public T withName(String name) {
		this.name = name;
		return (T) this;
	}

	public T withPrice(String price) {
		maybyPrice = price;
		return (T) this;
	}

	protected void validate() {
		try {
			price = new BigDecimal(maybyPrice);
		} catch (NumberFormatException | NullPointerException e) {
			throw new InvalidBuilderState(e);
		}
		if (name == null || name.isEmpty() || price == null || price.equals(BigDecimal.ZERO)) {
			throw new InvalidBuilderState("Wrong value");
		}
	}

	public abstract R build();
}
