package java8concepts;

import java.util.Optional;

public class Car {
	private String name;
	private Optional<Insurance> optionalInsurance;

	public Car(String name, Optional<Insurance> optionalInsurance) {
		super();
		this.name = name;
		this.optionalInsurance = optionalInsurance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<Insurance> getOptionalInsurance() {
		return optionalInsurance;
	}

	public void setOptionalInsurance(Optional<Insurance> optionalInsurance) {
		this.optionalInsurance = optionalInsurance;
	}
}
