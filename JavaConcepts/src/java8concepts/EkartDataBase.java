package java8concepts;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EkartDataBase {
	public static List<Customer> getAll() {

		Insurance ins1 = new Insurance("Happilo");
		Car car1 = new Car("Thar", Optional.ofNullable(ins1));
		Insurance ins2 = new Insurance("ICICI");
		Car car2 = new Car("TATA SAFARI", Optional.ofNullable(ins2));
		Insurance ins3 = null;
		Car car3 = new Car("Thar", Optional.ofNullable(ins3));
		Car car4 = null;

		return Stream.of(
				new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725"),
						Optional.ofNullable(car1)),
				new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947"),
						Optional.ofNullable(car2)),
				new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236"),
						Optional.ofNullable(car3)),
				new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467"),
						Optional.ofNullable(car4)))
				.collect(Collectors.toList());
	}
}
