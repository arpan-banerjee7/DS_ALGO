package java8concepts;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTest {
	public static void main(String[] args) {
		List<String> lis = Arrays.asList("Dog", "cat", "test", "tortoise", "Zebra");
		Optional<String> s = Optional.ofNullable("hello");
		String s1 = s.filter(e -> e.startsWith("h")).map(e -> e.toUpperCase()).orElse("WORLD");
		System.out.println(s1);
		List<Customer> custList = EkartDataBase.getAll();
		// find a customer with email id that starts with p
		Optional<Customer> eP = custList.stream().filter(e -> e.getEmail().get().startsWith("p")).findAny();
		System.out.println(eP.isPresent() ? eP.get().getEmail().get() : new RuntimeException("Email Not present"));

		// use of flamap- [397937955, 21654725, 89563865, 2487238947, 38946328654,
		// 3286487236, 389246829364, 948609467]
		List<List<String>> phones1 = custList.stream().map(customer -> customer.getPhoneNumbers())
				.collect(Collectors.toList());
		System.out.println(phones1);
		List<String> phones = custList.stream().flatMap(customer -> customer.getPhoneNumbers().stream())
				.collect(Collectors.toList());
		System.out.println(phones);
		System.out.println("**********************************************************");

		// flatmap in optional-
		// https://kheri.net/java-optional-flatmap/
		// https://stackoverflow.com/questions/30864583/java-8-difference-between-optional-flatmap-and-optional-map#:~:text=flatMap%20also%20returns%20the%20mapped,wraps%20the%20object%20in%20Optional%20.
		// find the car of a person whose name is john and find its insurance policy
		// relationship- customer->car->insurance car and insurance are Optional
		Optional<Insurance> insurance = custList.stream().filter(c -> c.getName().equalsIgnoreCase("john")).findFirst()
				.flatMap(c -> c.getCar()).flatMap(c -> c.getOptionalInsurance());
		if (insurance.isPresent()) {
			System.out.println(insurance.get().getName());
		}

		/*
		 * Compared to map(Function), flatMap does not wrap it with an additional
		 * Optional. flatMap also returns the mapped object "wrapped" in an Optional.
		 * The difference is that in the case of flatMap, the mapper function wraps the
		 * mapped object in the Optional while map itself wraps the object in Optional.
		 */
		System.out.println("**********************************************************");
		Optional<Customer> cus = custList.stream().filter(c -> c.getName().equalsIgnoreCase("john")).findFirst();
		Optional<Optional<Car>> cusCarMap = cus.map(c -> c.getCar()); // wraps in an extra optional
		System.out.println(cusCarMap.get().get().getName());
		Optional<Car> cusCarFlat = cus.flatMap(c -> c.getCar());
		System.out.println(cusCarFlat.get().getName());

		Optional<Employee> employeeOptional = Optional.of(new Employee("Emp001", "James Bond", 200000)); // empId, name,
																											// salary
		Optional<String> nameOptional = employeeOptional.flatMap(emp -> Optional.of(emp.getName()));
		System.out.println(nameOptional.get()); // James Bond
		// String s=new String(null);

		// closures in java

	}

	static class Employee {
		String empId;
		String name;
		int id;

		public Employee(String empId, String name, int id) {
			super();
			this.empId = empId;
			this.name = name;
			this.id = id;
		}

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}
}
