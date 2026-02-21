public class Factory {
    public Product createProduct(String type) {
        if (type == null) {
            return null;
        }
        return switch (type) {
            case "B" -> new ConcreteProductB();
            case "A" -> new ConcreteProductA();
            default -> null;
        };
    }
}