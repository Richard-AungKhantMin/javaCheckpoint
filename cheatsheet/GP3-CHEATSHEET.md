# GP3 Cheatsheet: Design Patterns & Data Structures

## Key Concepts

### 1. **Linked Lists - Dynamic Collections**

A linked list is a chain of "nodes". Each node holds a value and a pointer to the next node.

```
Node structure:
[value | next] → [value | next] → [value | next] → null

Advantages:
- Insert/delete anywhere: O(1) if you have the position
- Don't need to know size in advance
- No wasted space

Disadvantages:
- Access by index is slow: O(n)
- Need extra memory for "next" pointers
```

#### Single Linked List (one direction)
```java
private class Node {
    int value;
    Node next;
    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

// Add element at end
public void add(int value) {
    if (head == null) {
        head = new Node(value);
    } else {
        Node current = head;
        while (current.next != null) {
            current = next(current); // Traverse to end
        }
        current.next = new Node(value);
    }
    size++;
}

// Access by index
public int at(int index) {
    if (index < 0 || index >= size) return -1;
    Node current = head;
    for (int i = 0; i < index; i++) {
        current = next(current); // Move i steps
    }
    return current.value;
}

// Remove by index
public void remove(int index) {
    if (index == 0) {
        head = head.next; // Remove first
    } else {
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = next(current);
        }
        current.next = current.next.next; // Skip the node
    }
    size--;
}
```

#### Circular Linked List (last points back to first)
```java
// In circular list, last node.next = head (not null)
// Useful for: Round-robin scheduling, carousel

public void add(int value) {
    if (head == null) {
        head = new Node(value);
        head.next = head; // Points to itself
    } else {
        Node current = head;
        while (current.next != head) {
            current = next(current);
        }
        current.next = new Node(value, head); // New node points to head
    }
    size++;
}

// Accessing works differently!
public int at(int index) {
    if (size == 0) return -1;
    Node current = head;
    for (int i = 0; i < index % size; i++) { // Use modulo for wraparound
        current = next(current);
    }
    return current.value;
}
```

#### Double Linked List (bidirectional)
```java
private class Node {
    int value;
    Node next;
    Node prev; // Can go backwards!
    Node(int value) {
        this.value = value;
    }
}

// Optimization: Start from closer end
public int at(int index) {
    int len = size();
    int fromHead = index;
    int fromTail = (len - 1) - index;
    
    if (fromTail < fromHead) {
        // Closer to end: start from tail, go backwards
        Node current = tail;
        for (int i = len - 1; i > index; i--) {
            current = prev(current);
        }
        return current.value;
    } else {
        // Closer to start: go forward from head
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = next(current);
        }
        return current.value;
    }
}
```

**Memory vs Speed:**
- Single LL: 1 pointer per node
- Double LL: 2 pointers per node, but faster access from end
- Circular LL: Can wrap around, useful for queues

---

### 2. **Design Patterns**

#### Factory Pattern - Create objects without specifying exact class

```java
// Product interface/class (what we create)
public interface Product {
    void showDetails();
}

public class ConcreteProductA implements Product {
    @Override
    public void showDetails() {
        System.out.println("This is ConcreteProductA.");
    }
}

public class ConcreteProductB implements Product {
    @Override
    public void showDetails() {
        System.out.println("This is ConcreteProductB.");
    }
}

// Factory - decides which product to create
public class Factory {
    public Product createProduct(String type) {
        if (type == null) return null;
        
        return switch (type) {
            case "A" -> new ConcreteProductA();
            case "B" -> new ConcreteProductB();
            default -> null;
        };
    }
}

// Usage
Factory factory = new Factory();
Product productA = factory.createProduct("A");
productA.showDetails(); // Prints: "This is ConcreteProductA."
```

**Why use it?**
- Hides complex creation logic
- Change how objects are created without changing code using them
- Example in real world: DatabaseFactory (could return MySQL, PostgreSQL, etc.)

---

#### Builder Pattern - Construct complex objects step by step

```java
// What we're building
public class Regex {
    private StringBuilder pattern;
    
    public Regex(List<String> components) {
        pattern = new StringBuilder();
        for (String comp : components) {
            pattern.append(comp);
        }
    }
    
    public String getPattern() {
        return pattern.toString();
    }
}

// Builder interface - defines steps
public interface RegexBuilder {
    void buildLiteral(String literal);
    void buildAnyCharacter();
    void buildDigit();
    void buildWhitespace();
    Regex getResult();
}

// Concrete builder - does the building
public class ConcreteRegexBuilder implements RegexBuilder {
    private List<String> components = new ArrayList<>();
    
    @Override
    public void buildLiteral(String literal) {
        components.add(literal);
    }
    
    @Override
    public void buildAnyCharacter() {
        components.add(".");
    }
    
    @Override
    public void buildDigit() {
        components.add("\\d");
    }
    
    @Override
    public void buildWhitespace() {
        components.add("\\s");
    }
    
    @Override
    public Regex getResult() {
        return new Regex(components);
    }
}

// Director - controls the building process
public class RegexDirector {
    private RegexBuilder builder;
    
    public void setBuilder(RegexBuilder b) {
        builder = b;
    }
    
    public Regex construct() {
        builder.buildLiteral("Hello");
        builder.buildWhitespace();
        builder.buildWordCharacter();
        return builder.getResult(); // "Hello\s\w"
    }
}

// Usage
RegexDirector director = new RegexDirector();
RegexBuilder builder = new ConcreteRegexBuilder();
director.setBuilder(builder);
Regex regex = director.construct();
```

**Why use it?**
- Build complex objects with many options
- Example: StringBuilder is a builder for Strings
- Make code readable: `builder.addTitle().addPrice().build()`

---

#### Singleton Pattern - Only ONE instance exists

```java
public class Singleton {
    private static Singleton instance; // Holds the single instance
    
    // Private constructor - prevents new Singleton()
    private Singleton() {
    }
    
    // Static method - way to access the single instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // Create only once
        }
        return instance;
    }
    
    public String showMessage() {
        return "Hello, I am a singleton!";
    }
}

// Usage
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
// s1 and s2 are the SAME object! (s1 == s2)
```

**Why use it?**
- Global state needed (logger, database connection)
- Ensure only one instance exists
- Example: DatabaseConnection (only 1 connection pool)

**Thread-Safe Variant (for multi-threaded apps):**
```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

### 3. **Interface vs Abstract Class**

```java
// Interface - describes WHAT an object does
public interface LinkedList {
    int at(int index);
    void add(int value);
    void remove(int index);
    int size();
}

// Abstract class - describes WHAT and HOW
// (can have concrete methods)
public abstract class Animal {
    abstract void makeSound(); // Must implement
    
    public void sleep() { // Concrete - ready to use
        System.out.println("Zzz...");
    }
}

// Implementation
public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

**Key differences:**
- Interface: Multiple inheritance allowed
- Abstract class: Single inheritance only
- Interface: All methods abstract by default
- Abstract class: Can have concrete methods

---

### 4. **Key Methods Used in Data Structures**

```java
// Traversal helper
private Node next(Node node) {
    System.out.println("Go to next node");
    return node.next;
}

private Node prev(Node node) {
    System.out.println("Go to previous node");
    return node.prev;
}

// Common operations
// Add: O(1) at end, O(n) at middle
// Remove: O(n) - must find first
// Access by index: O(n) - must traverse
// Size: O(1) if you track it
```

---

## Quick Reference

| Pattern | Purpose | Key Idea |
|---------|---------|----------|
| Factory | Create objects | Hide complex creation |
| Builder | Build complex objects | Step-by-step construction |
| Singleton | Single instance | Global access point |

| Data Structure | Insert/Add | Delete | Access | Use Case |
|----------------|-----------|--------|--------|----------|
| Single LL | O(n) | O(n) | O(n) | Simple linked list |
| Double LL | O(n) | O(n) | O(n) | Fast from both ends |
| Circular LL | O(n) | O(n) | O(n) | Round-robin tasks |

## Design Pattern Quick Checklist

**Factory:** Need to create different types? Use Factory!
**Builder:** Building complex object with many options? Use Builder!
**Singleton:** Need exactly one instance globally? Use Singleton!

