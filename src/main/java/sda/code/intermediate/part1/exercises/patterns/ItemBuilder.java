package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.part1.exercises.data.Item;

public class ItemBuilder extends ProductBuilder<ItemBuilder, Item> {
	private Double weight;

	public ItemBuilder withWeight(Double weight) {
		this.weight = weight;
		return this;
	}

	@Override
	protected void validate() {
		super.validate();
		if (weight == null || weight < 0){
			throw new InvalidBuilderState("Wrong value");
		}
	}

	@Override
	public Item build() {
		validate();
		return new Item(name, price, weight);
	}

}
