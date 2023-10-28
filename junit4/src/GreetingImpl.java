public class GreetingImpl implements Greeting {
    @Override
    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must be a valid name");
        }
        return "Hello " + name;
    }
}
