package concepts;

class Engine {
	int id;
	String name;

	public Engine(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

final class ImmutableClassExample {
	private final int id;
	private final String type;
	private final Engine engine;

	public ImmutableClassExample(int id, String type, Engine engine) {
		this.id = id;
		this.type = type;
		this.engine = new Engine(engine.id, engine.name);
	}

	public static void main(String[] args) {
		Engine engine = new Engine(1, "hero");
		ImmutableClassExample imClass = new ImmutableClassExample(11, "bike", engine);
		System.out.println("From immutable class- " + imClass.engine.name);
		engine.name = "TVS";
		System.out.println("From immutable class after modification- " + imClass.engine.name);
		System.out.println(engine.name);

	}

}
