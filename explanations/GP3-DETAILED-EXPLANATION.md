# GP3 - Detailed Solutions Explained

## Problem 1: Single Linked List

### The Problem
Create a list where:
- You can add elements
- Access elements by index
- Remove elements by index
- Get the size

**Why not just use an array?**
- Arrays have fixed size
- Linked lists grow dynamically
- Easy to insert/remove in middle

---

## Understanding the Node Concept

Think of a linked list like a train:

```
TRAIN:  [Car 1]→[Car 2]→[Car 3]→[Car 4]→null
        (data)  (data)  (data)  (data)

Each car (node) contains:
- value: the data (cargo in the car)
- next: pointer to next car (coupling to next car)
```

---

### The Node Class

```java
private class Node {
    int value;
    Node next;
    
    Node(int value) {
        this.value = value;
        this.next = null;  // Initially, no next node
    }
}
```

**Private class:** Only used inside SingleLinkedList, not exposed to others.

**Why `Node next` is null initially?** Until we link it to another node, it points to nothing.

---

### The SingleLinkedList Class

```java
public class SingleLinkedList implements LinkedList {
    private Node head;  // First node of the list
    private int size;   // Track how many nodes exist
```

**head:** The entry point into the whole list. Everything is accessed starting from head.

**size:** Helps with bounds checking and tracking count.

---

## The Add Method

```java
public void add(int value) {
    if (head == null) {
        size = 0;
        Node last = null;
        this.head = new Node(value);
        this.head.setNext(last); // Points to null
    } else {
        // Find the last node
        Node currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = this.next(currentNode);
        }
        // Create new node and attach it
        Node newNode = new Node(value);
        newNode.setNext(currentNode.next);
        currentNode.setNext(newNode);
    }
    this.size++;
}
```

**Case 1: Adding to empty list (head == null)**
```
Before: List is empty

After adding 5:
head → [5|null]
size = 1
```

**Case 2: Adding to non-empty list**
```
Step 1: Find the last node
List: head → [5|→] → [3|→] → [7|null]
              ^                    ^
              Start here          This one!

Step 2: Create new node
newNode = [4|null]

Step 3: Attach it
[7|→] → [4|null]

Result: head → [5|→] → [3|→] → [7|→] → [4|null]
```

---

## The At Method (Access by index)

```java
public int at(int index) {
    if (index < 0 || index > this.size) {
        return -1;  // Invalid index
    }
    if (index == 0) {
        return this.head.value;  // Quick access to first
    }
    if (index == this.size) {
        return -1;  // Exactly at size is out of bounds
    }
    
    // Traverse to the index
    Node currentNode = this.head;
    for (int i = 0; i < index; i++) {
        currentNode = this.next(currentNode);
    }
    return currentNode.value;
}
```

**Why check bounds first?** Prevent crashes from invalid indices.

**Why quick return for index 0?** No need to loop if we want the first element.

**Why check index == size?** With size 3, valid indices are 0, 1, 2. Index 3 is out of bounds.

---

### Visualization of Traversal

```
Access at(1):

head → [5|→] → [3|→] → [7|null]
       i=0      i=1      i=2

Start: currentNode = head (index 0)

Loop iteration 1 (i=0):
    Is i < index (0 < 1)? YES
    currentNode = next(currentNode)
    currentNode now points to second node

Loop iteration 2 (i=1):
    Is i < index (1 < 1)? NO
    Exit loop

Return currentNode.value = 3
```

---

## The Remove Method

```java
public void remove(int index) {
    if (index < 0 || index > this.size) {
        return;  // Invalid, do nothing
    }
    
    if (index == 0) {
        head = head.next;  // Skip the first node
    } else {
        // Find the node before the one to remove
        Node currentNode = this.head;
        for (int i = 0; i < index - 1; i++) {
            currentNode = this.next(currentNode);
        }
        // Remove: skip over the target node
        currentNode.next = currentNode.next.next;
    }
    this.size--;
}
```

**Case 1: Remove first node (index 0)**
```
Before: head → [5|→] → [3|→] → [7|null]

After remove(0):
head = head.next
head → [3|→] → [7|null]

The original [5|→] is orphaned and garbage collected!
```

**Case 2: Remove middle node (index 1)**
```
Before: head → [5|→] → [3|→] → [7|null]

Step 1: Find node before target
Loop from start, go to index-1
currentNode = [5|→]

Step 2: Skip over [3|→]
[5|→].next = [5|→].next.next
[5|→].next = [7|null]

After: head → [5|→] → [7|null]
       [3|→] is orphaned!
```

---

## Problem 2: Circular Linked List

### The Difference

**Single Linked List:**
```
head → [5|→] → [3|→] → [7|null]
```

**Circular Linked List:**
```
       ↓←←←←←←←←←←←←←↓
head → [5|→] → [3|→] → [7|↻]
       ↑← lastNode.next = head
```

The last node points back to the first! It's a circle.

**Use cases:** Circular playlists, round-robin scheduling

---

### Add Method

```java
public void add(int value) {
    if (head == null) {
        head = new Node(value);
        head.next = head;  // Points to itself!
    } else {
        if (this.size == 1) {
            head.next = new Node(value, head);  // New points back to head
        } else {
            Node currentNode = head;
            for (int i = 0; i < this.size - 1; i++) {
                currentNode = next(currentNode);
            }
            currentNode.next = new Node(value, head);
        }
    }
    size++;
}
```

**Adding first element:**
```
head = [5|?]
head.next = head
Result: [5|↻] (points to itself)
```

**Adding second element:**
```
Before: [5|↻] (size=1)

Step: head.next = new Node(3, head)
      [3|→head]

Result: [5|→] → [3|↻]
        ↑← connection back to 5
```

**Adding third element:**
```
Before: [5|→] → [3|↻]

Step: Find last (via loop starting from head, going size-1 times)
      That's [3|...]

Step: [3|→] → new Node(7, head)
      [3|→] → [7|↻]

Result: [5|→] → [3|→] → [7|↻]
```

---

### At Method (Handles Wraparound!)

```java
public int at(int index) {
    if (size == 0 || index < 0) {
        return -1;
    }
    Node currentNode = head;
    for (int i = 0; i < index; i++) {
        currentNode = next(currentNode);
    }
    return currentNode.value;
}
```

**The magic: no bounds check needed!**

Why? With size 3:
- at(0) → head → [5]
- at(1) → next → [3]
- at(2) → next → [7]
- at(3) → next of [7] → [5] (wraps around!)
- at(4) → next of [5] → [3]
- at(100) → goes around many times → lands somewhere

This is **intentional** - circular list allows infinite access!

---

## Problem 3: Double Linked List

### The Difference

**Single:** One direction
```
[5|→] → [3|→] → [7|null]
```

**Double:** Both directions
```
[5|⇄] ⇄ [3|⇄] ⇄ [7|⇄]
```

Each node has:
- `value`: the data
- `next`: pointer to next node
- `prev`: pointer to previous node

---

### The Node Class

```java
private class Node {
    int value;
    Node next;
    Node prev;
    
    public Node(int value) {
        this.value = value;
    }
}
```

**Three fields instead of two.**

---

### The Smart At Method

```java
public int at(int index) {
    int len = size();
    if (index < 0 || index >= len) return -1;
    
    // Quick returns for ends
    if (index == 0) return head.value;
    if (index == len - 1) return tail.value;
    
    // Calculate distances
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
        // Closer to start: go forward
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = next(current);
        }
        return current.value;
    }
}
```

**Why this is clever:**

```
Access at(7) in a list of 10:

Distance from head: 7 steps
Distance from tail: (10-1) - 7 = 2 steps

2 < 7, so start from tail and go backwards 2 times!
→ FASTER!

Visualization:
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9]
head                               target tail

Going from head: 7 steps
Going from tail: 2 steps ← Choose this!
```

**This optimization is ONLY possible with bidirectional list!**

---

## Problem 4: Factory Pattern

### The Problem
Decoupling object creation from usage.

**Bad way:**
```java
// Everywhere in code, you do:
if (type.equals("A")) {
    product = new ConcreteProductA();
} else if (type.equals("B")) {
    product = new ConcreteProductB();
}
```

**Good way (Factory):**
```java
Factory factory = new Factory();
Product product = factory.createProduct(type);
```

---

### The Solution

```java
public class Factory {
    public Product createProduct(String type) {
        if (type == null) {
            return null;
        }
        
        return switch (type) {
            case "A" -> new ConcreteProductA();
            case "B" -> new ConcreteProductB();
            default -> null;
        };
    }
}
```

**Java switch expression (Java 14+):**
```java
return switch (value) {
    case "A" -> returnValue1;
    case "B" -> returnValue2;
    default -> defaultValue;
};
```

**Benefits:**
- If you add a new product type, you only change the Factory
- All other code stays the same
- Much cleaner for consumer code

---

## Problem 5: Builder Pattern

### The Problem
Building complex objects with many options in a readable way.

**Bad way:**
```java
Regex regex = new Regex("Hello", true, false, true, false, "\\s", ".");
// What does each boolean mean? Confusing!
```

**Good way (Builder):**
```java
RegexBuilder builder = new ConcreteRegexBuilder();
builder.buildLiteral("Hello");
builder.buildWhitespace();
builder.buildWordCharacter();
builder.buildAnyCharacter();
Regex regex = builder.getResult();
```

---

### The Components

**RegexBuilder interface:** Defines building steps
```java
public interface RegexBuilder {
    void buildLiteral(String literal);
    void buildAnyCharacter();  // .
    void buildDigit();         // \d
    void buildWhitespace();    // \s
    void buildWordCharacter(); // \w
    Regex getResult();
}
```

**ConcreteRegexBuilder:** Does the building
```java
public class ConcreteRegexBuilder implements RegexBuilder {
    private List<String> components = new ArrayList<>();
    
    @Override
    public void buildLiteral(String literal) {
        components.add(literal);
    }
    
    @Override
    public void buildDigit() {
        components.add("\\d");
    }
    
    // ... other methods ...
    
    @Override
    public Regex getResult() {
        return new Regex(components);
    }
}
```

**RegexDirector:** Controls the building sequence
```java
public class RegexDirector {
    private RegexBuilder builder;
    
    public void setBuilder(RegexBuilder builder) {
        this.builder = builder;
    }
    
    public Regex construct() {
        builder.buildLiteral("Hello");      // Step 1
        builder.buildWhitespace();          // Step 2
        builder.buildWordCharacter();       // Step 3
        builder.buildAnyCharacter();        // Step 4
        return builder.getResult();         // Build
    }
}
```

**Example:**
```java
RegexDirector director = new RegexDirector();
RegexBuilder builder = new ConcreteRegexBuilder();
director.setBuilder(builder);
Regex regex = director.construct();

// Result regex pattern: "Hello\s\w."
```

---

## Problem 6: Singleton Pattern

### The Problem
Ensure only ONE instance of a class exists globally.

**Why need this?**
- Database connection: expensive, should share one instance
- Logger: one logging system for whole app
- Configuration: one config file reader

---

### The Solution

```java
public class Singleton {
    private static Singleton instance;  // The single instance
    
    private Singleton() {  // Private constructor!
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // Create only once
        }
        return instance;
    }
}
```

**How it works:**

```
First call: getInstance()
    instance == null? YES
    Create it: instance = new Singleton()
    return instance

Second call: getInstance()
    instance == null? NO (it was created)
    return instance (same one as before!)

Result: Both calls get the EXACT SAME object
```

---

### The Proof

```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();

System.out.println(s1 == s2);  // true! Same object
```

---

### Why Private Constructor?

Without it:
```java
private constructor: Someone could do:
    Singleton s = new Singleton();  // Create another instance!
    
The "single" is broken!
```

With private constructor:
```java
Singleton s = new Singleton();  // COMPILE ERROR!
    // Constructor is private, can't call from outside
```

---

### Thread Safety Issue

The basic version has a problem in multi-threaded apps:

```
Thread 1: Call getInstance()
    Check: instance == null? YES
    (pause - thread switched)
    
Thread 2: Call getInstance()
    Check: instance == null? YES (other hasn't created yet)
    Create instance
    return instance
    
Thread 1: Resume
    Create another instance!
    
Now there are TWO instances!
```

**Solution (thread-safe):**
```java
public synchronized static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
```

`synchronized` makes only one thread can run this method at a time.

---

## Summary

GP3 covers design patterns and data structures:

| Pattern/Structure | Purpose |
|------------------|---------|
| Linked List | Dynamic list, efficient insert/delete |
| Circular LL | Wrap-around access |
| Double LL | Bidirectional traversal |
| Factory | Decouple creation from usage |
| Builder | Build complex objects step-by-step |
| Singleton | Guarantee single instance |

These are **industry-standard patterns** you'll see everywhere!
