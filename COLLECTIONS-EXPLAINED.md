# Java Collections Explained (For Beginners)

## **What Are Collections?**

Collections are like different storage boxes in a warehouse:
- **Array** = fixed-size box (you decide size upfront, can't change)
- **List** = flexible container (grows as you add items)
- **Map** = filing cabinet (find things by a label, not position)
- **Set** = box of unique items only (no duplicates allowed)
- **Stack** = plate stack (grab top plate first)
- **PriorityQueue** = emergency room queue (urgent cases first)

---

## **1. Arrays: `int[] arr = new int[5];`**

### What is it?
Fixed-size container. Once you create it with size 5, it ALWAYS has 5 spots.

### Real-world analogy
Empty parking lot with exactly 5 parking spaces. You can't add a 6th space later.

### When to use it
When you know EXACTLY how many items you'll store beforehand.

### How it works
```java
int[] arr = new int[5];        // Create 5-spot container
arr[0] = 100;                  // Put 100 in spot 0
arr[1] = 200;                  // Put 200 in spot 1
arr[2] = 300;

System.out.println(arr[0]);    // Output: 100
System.out.println(arr.length); // Output: 5
```

### Key operations
```java
arr[2] = 999;          // Change value at position 2
int value = arr[0];    // Get value at position 0
arr.length             // Get total size (always 5)
```

### Common mistakes
```java
int[] arr = new int[3];
arr[5] = 100;  // ❌ CRASH! Only positions 0,1,2 exist
               // Position 5 doesn't exist in size-3 array

for (int i = 0; i <= arr.length; i++) {  // ❌ CRASH!
    // Should be i < arr.length, not <=
    // If arr.length = 3, then i goes 0,1,2 (not 3!)
}
```

---

## **2. List: `List<Integer> list = new ArrayList<>();`**

### What is it?
Flexible container that GROWS as you add items. Start empty, add as many as you want.

### Real-world analogy
A shopping basket you carry through a mall. It starts empty. As you add items, it gets bigger. No limit.

### When to use it
When you don't know how many items you'll have.

### How it works
```java
List<Integer> list = new ArrayList<>();  // Create empty list

list.add(100);      // Add first item → list has 1 item
list.add(200);      // Add second item → list has 2 items
list.add(300);      // Add third item → list has 3 items

System.out.println(list.get(0));  // Output: 100 (get item at position 0)
System.out.println(list.size());  // Output: 3 (total items)
```

### Key operations
```java
list.add(500);           // Add to end
list.add(1, 250);        // Add 250 at position 1
list.get(0);             // Get item at position 0
list.remove(0);          // Remove item at position 0
list.size();             // How many items total
list.contains(100);      // Does list have 100? (true/false)
```

### Complete example
```java
List<Integer> scores = new ArrayList<>();

// Adding values
scores.add(85);   // scores: [85]
scores.add(92);   // scores: [85, 92]
scores.add(78);   // scores: [85, 92, 78]

// Accessing
System.out.println(scores.get(1));  // Output: 92 (middle score)

// Looping through
for (int score : scores) {
    System.out.println(score);  // Prints: 85, 92, 78
}

// Common operation: find highest
int max = Collections.max(scores);  // Output: 92
```

### Common mistakes
```java
List<Integer> list = new ArrayList<>();
System.out.println(list.get(0));  // ❌ CRASH! List is empty, no position 0

list.add(100);
list.add(200);
for (int i = 0; i <= list.size(); i++) {  // ❌ CRASH!
    // Should be i < list.size(), not <=
}
```

---

## **3. Map: `Map<String, Integer> map = new HashMap<>();`**

### What is it?
Storage where you look things up by a LABEL (called a "key"), not by position.

### Real-world analogy
Dictionary: you look up a word (the KEY) to find its definition (the VALUE).
- KEY = "apple"
- VALUE = "A round red fruit"

### When to use it
When you want to find things by name/label, not by position.

### How it works
```java
Map<String, Integer> map = new HashMap<>();

map.put("John", 85);    // Store: John got 85 points
map.put("Sarah", 92);   // Store: Sarah got 92 points
map.put("Mike", 78);    // Store: Mike got 78 points

System.out.println(map.get("John"));   // Output: 85 (find John's score)
System.out.println(map.get("Sarah"));  // Output: 92
```

### Key operations
```java
map.put("key", value);         // Store a pair
map.get("key");                // Get value by key
map.remove("key");             // Remove a pair
map.containsKey("key");        // Does this key exist?
map.size();                    // How many pairs total
map.keySet();                  // Get all keys
map.values();                  // Get all values
```

### Complete example
```java
Map<String, Integer> ages = new HashMap<>();

// Adding
ages.put("Alice", 25);
ages.put("Bob", 30);
ages.put("Charlie", 25);

// Looking up
System.out.println(ages.get("Bob"));  // Output: 30

// Checking if key exists
if (ages.containsKey("Alice")) {
    System.out.println("Alice is " + ages.get("Alice"));  // Output: Alice is 25
}

// Looping through all pairs
for (String name : ages.keySet()) {
    int age = ages.get(name);
    System.out.println(name + " is " + age + " years old");
}
// Output:
// Alice is 25 years old
// Bob is 30 years old
// Charlie is 25 years old
```

### Common mistakes
```java
Map<String, Integer> map = new HashMap<>();
System.out.println(map.get("John"));  // ❌ Returns null, not error (key doesn't exist)

map.put("John", 85);
map.put("John", 95);  // ⚠️ OVERWRITES! John's score is now 95, not 85
```

---

## **4. Set: `Set<String> set = new HashSet<>();`**

### What is it?
Container that holds UNIQUE values only. Duplicates are automatically rejected.

### Real-world analogy
Invitation list for a party. Each person's name appears only ONCE. If someone tries to join twice, nothing happens.

### When to use it
When you need unique items only (no duplicates).

### How it works
```java
Set<String> set = new HashSet<>();

set.add("apple");      // set: {apple}
set.add("banana");     // set: {apple, banana}
set.add("apple");      // ✓ Trying to add apple again → ignored!
                       // set: {apple, banana} (still only 2 items)

System.out.println(set.size());  // Output: 2
System.out.println(set.contains("apple"));  // Output: true
```

### Key operations
```java
set.add("item");       // Add item (ignored if duplicate)
set.remove("item");    // Remove item
set.contains("item");  // Does set have this item?
set.size();            // How many unique items
```

### Complete example
```java
// Finding unique words
Set<String> words = new HashSet<>();

words.add("java");
words.add("python");
words.add("java");      // Duplicate, ignored
words.add("javascript");
words.add("python");    // Duplicate, ignored

System.out.println(words.size());  // Output: 3
System.out.println(words);         // Output: {java, python, javascript}
                                   // (order may vary)

// Looping through
for (String word : words) {
    System.out.println(word);
}
```

### Common mistakes
```java
Set<String> set = new HashSet<>();
set.add("apple");
set.add("apple");
set.add("apple");

System.out.println(set.size());  // Output: 1, not 3!
                                 // Sets reject duplicates automatically
```

---

## **5. Stack: `Stack<Integer> stack = new Stack<>();`**

### What is it?
Container where you add and remove from the SAME END. Last item added is first item removed (LIFO = Last-In-First-Out).

### Real-world analogy
Stack of dinner plates:
- You PUT a plate ON TOP
- You TAKE a plate FROM TOP
- The last plate you stacked is the first plate you grab

### When to use it
- Browser back button (last page visited is first to go back to)
- Undo function (last action is first to undo)
- Function call stack (most recent function call is handled first)

### How it works
```java
Stack<Integer> stack = new Stack<>();

stack.push(100);    // Add 100 → [100]
stack.push(200);    // Add 200 → [100, 200]
stack.push(300);    // Add 300 → [100, 200, 300]

System.out.println(stack.peek());  // Look at top: 300 (don't remove)
System.out.println(stack.pop());   // Remove and return: 300
                                   // Now stack: [100, 200]
System.out.println(stack.pop());   // Remove and return: 200
                                   // Now stack: [100]
```

### Key operations
```java
stack.push(value);     // Add to top
stack.pop();           // Remove and return from top
stack.peek();          // Look at top (don't remove)
stack.isEmpty();       // Is stack empty?
stack.size();          // How many items
```

### Visualization
```
push(100) → | 100 |
push(200) → | 200 |
            | 100 |
push(300) → | 300 |  ← peek() returns 300
            | 200 |
            | 100 |

pop() → Returns 300, | 200 |
                     | 100 |
```

### Complete example (Undo function)
```java
Stack<String> actions = new Stack<>();

actions.push("Typed: Hello");      // User typed
actions.push("Typed: World");      // User typed more
actions.push("Selected all");      // User selected
actions.push("Deleted");           // User deleted

// Undo button clicked 3 times
System.out.println("Undo: " + actions.pop());   // Undo: Deleted
System.out.println("Undo: " + actions.pop());   // Undo: Selected all
System.out.println("Undo: " + actions.pop());   // Undo: Typed: World
// Now stack has: [Typed: Hello]
```

### Common mistakes
```java
Stack<Integer> stack = new Stack<>();
System.out.println(stack.pop());  // ❌ CRASH! Stack is empty
System.out.println(stack.peek()); // ❌ CRASH! Stack is empty

// Always check first
if (!stack.isEmpty()) {
    stack.pop();  // ✓ Safe
}
```

---

## **6. PriorityQueue: `PriorityQueue<Integer> pq = new PriorityQueue<>();`**

### What is it?
Container where items come out in PRIORITY ORDER, not insertion order. Items with highest priority come out first.

### Real-world analogy
Emergency room queue:
- Person with broken leg: HIGH priority
- Person with headache: MEDIUM priority
- Person with flu: LOW priority

Doctor sees broken leg FIRST, even if headache person arrived first.

### When to use it
- Task scheduling (urgent tasks first)
- Hospital queues (critical patients first)
- Event processing (important events first)

### How it works
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(5);     // Add 5
pq.add(1);     // Add 1
pq.add(10);    // Add 10

System.out.println(pq.poll());  // Remove and return: 1 (smallest priority)
System.out.println(pq.poll());  // Remove and return: 5
System.out.println(pq.poll());  // Remove and return: 10
```

### Key operations
```java
pq.add(value);       // Add value
pq.poll();           // Remove and return highest priority
pq.peek();           // Look at highest priority (don't remove)
pq.isEmpty();        // Is empty?
pq.size();           // How many items
```

### Complete example (Task Queue)
```java
PriorityQueue<Integer> tasks = new PriorityQueue<>();
// Lower number = higher priority

tasks.add(3);   // Regular task: priority 3
tasks.add(1);   // Urgent task: priority 1
tasks.add(2);   // Important task: priority 2
tasks.add(4);   // Low priority task: priority 4

// Execute in priority order
while (!tasks.isEmpty()) {
    int priority = tasks.poll();
    System.out.println("Executing task with priority: " + priority);
}
// Output:
// Executing task with priority: 1
// Executing task with priority: 2
// Executing task with priority: 3
// Executing task with priority: 4
```

---

## **Quick Comparison Table**

| Container | Size | Access by | Order | Duplicates | Best for |
|-----------|------|-----------|-------|-----------|----------|
| **Array** | Fixed | Position (0,1,2...) | Fixed | ✓ Allowed | When size known upfront |
| **List** | Grows | Position (0,1,2...) | Insertion | ✓ Allowed | Most common general use |
| **Map** | Grows | Key/Label | Insertion | ✓ Allowed | Looking up by name/label |
| **Set** | Grows | N/A | Random | ✗ No duplicates | Unique values only |
| **Stack** | Grows | Top only | LIFO | ✓ Allowed | Undo, browser history |
| **PriorityQueue** | Grows | Priority order | Priority | ✓ Allowed | Task scheduling |

---

## **Which One to Use? Decision Tree**

```
Do you know the exact size upfront?
├─ YES → Array (int[] arr)
└─ NO → Do you need flexible size?
    └─ YES → How will you access items?
        ├─ By position (0, 1, 2...) → List
        ├─ By name/label → Map
        ├─ Need unique values only? → Set
        ├─ Need LIFO (last-in-first-out)? → Stack
        └─ Need priority ordering? → PriorityQueue
```

---

## **Practice: Build a Student Grade System**

```java
// Store all students and their grades
Map<String, Integer> grades = new HashMap<>();
grades.put("Alice", 95);
grades.put("Bob", 88);
grades.put("Charlie", 95);

// Get Alice's grade
System.out.println(grades.get("Alice"));  // 95

// Get all unique scores (no repeats)
Set<Integer> uniqueScores = new HashSet<>(grades.values());
System.out.println(uniqueScores);  // {95, 88}

// Process grades from highest to lowest priority
PriorityQueue<Integer> topScores = new PriorityQueue<>((a,b) -> b-a);
topScores.addAll(grades.values());
System.out.println(topScores.poll());  // 95 (highest score)
System.out.println(topScores.poll());  // 95
System.out.println(topScores.poll());  // 88
```

---

## **Key Takeaway**

Choose based on HOW you'll use the data:
- **Exact position?** → Array or List
- **By name/label?** → Map
- **Unique only?** → Set
- **Last-in-first-out?** → Stack
- **Priority order?** → PriorityQueue
