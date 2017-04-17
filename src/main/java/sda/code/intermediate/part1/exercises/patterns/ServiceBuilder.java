package sda.code.intermediate.part1.exercises.patterns;

import sda.code.intermediate.part1.exercises.data.Service;

public class ServiceBuilder extends ProductBuilder<ServiceBuilder, Service> {
	private Integer time;

	public ServiceBuilder withTime(Integer time) {
		this.time = time;
		return this;
	}

	@Override
	protected void validate() {
		super.validate();
		if (time == null || time < 0) {
			throw new InvalidBuilderState("Wrong value");
		}
	}

	@Override
	public Service build() {
		validate();
		return new Service(name, price, time);
	}

}
