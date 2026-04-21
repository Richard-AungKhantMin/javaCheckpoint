package gp3.FactoryBlueprint;

public class Factory {
    public Product createProduct(String type) {

    if (type == "A") return new ConcreteProductA();
    if (type == "B") return new ConcreteProductB();
    
    return null;

    }
}