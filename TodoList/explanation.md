# TodoList Assignment - Complete Step-by-Step Guide

## Understanding the Problem

You need to build a **task management system** where you can:
- Add tasks to a list
- Update task descriptions
- Change task status (NEW, IN_PROGRESS, COMPLETED)
- Display all tasks in a formatted way

Think of it like a simplified version of apps like Todoist or Microsoft To Do.

---

## Breaking Down the Requirements

### **Three Components Needed:**

1. **TaskStatus (Enum)** - Defines the possible states a task can be in
2. **Task (Class)** - Represents a single task with description and status
3. **TodoList (Class)** - Manages multiple tasks (like a container)

---

## Part 1: Understanding Enums

### **What is an Enum?**

An enum (enumeration) is a special type that represents a **fixed set of constants**.

Think of it like a multiple-choice question where you can only pick from specific options:
- **Pizza sizes:** SMALL, MEDIUM, LARGE
- **Days of week:** MONDAY, TUESDAY, WEDNESDAY...
- **Task status:** NEW, IN_PROGRESS, COMPLETED

### **Why Use Enums?**

Instead of using strings like "new", "New", "NEW" (inconsistent), enums ensure you can only use the exact values defined:

```java
// ❌ WITHOUT ENUM - prone to errors
String status = "new";      // lowercase
String status = "New";      // capitalized
String status = "completed" // typo: should be "complete"?

// ✅ WITH ENUM - safe and consistent
TaskStatus status = TaskStatus.NEW;
TaskStatus status = TaskStatus.IN_PROGRESS;
// Can't accidentally type TaskStatus.NEWW - compiler error!
```

### **Creating the TaskStatus Enum**

```java
enum TaskStatus {
    NEW, IN_PROGRESS, COMPLETED
}
```

**Simple as that!** These are the only three valid statuses a task can have.

---

## Part 2: Creating the Task Class

### **Step-by-Step Thinking Process**

**Question 1: What information does a single task need?**
- A description (what needs to be done)
- A status (is it done? in progress? new?)

**Question 2: Should this information be accessible from outside?**
- Yes, we need to read and modify both description and status
- But we should control HOW they're accessed → use private + getters/setters

**Question 3: What's the initial state of a new task?**
- When you create a task, it hasn't been started yet
- So the default status should be `NEW`

### **Building the Task Class**

#### **Step 1: Define Attributes**

```java
class Task {
    private String description;
    private TaskStatus status;
}
```

**Why private?**
- **Encapsulation** - we control access to the data
- Can't accidentally set invalid values from outside
- Can add validation later if needed

#### **Step 2: Create Constructor**

```java
public Task(String description) {
    this.description = description;
    this.status = TaskStatus.NEW;  // Always starts as NEW
}
```

**Thinking:** 
- We need the description when creating a task
- Status should default to NEW (task just created, not started yet)
- Use `this.` to distinguish between parameter and instance variable

#### **Step 3: Add Getters**

```java
public String getDescription() {
    return description;
}

public TaskStatus getStatus() {
    return status;
}
```

**Why getters?**
- Allow reading the private variables from outside
- Just return the value, no fancy logic needed here

#### **Step 4: Add Setters**

```java
public void setDescription(String description) {
    this.description = description;
}

public void setStatus(TaskStatus status) {
    this.status = status;
}
```

**Why setters?**
- Allow modifying the private variables
- Could add validation here (e.g., description can't be empty)
- For now, just update the value

---

## Part 3: Creating the TodoList Class

This is the most complex part! Let's think through it step by step.

### **Thinking Process**

**Question 1: How do we store multiple tasks?**
- We need a collection to hold Task objects
- The requirement says "specified number of tasks" → fixed size
- **Answer:** Use an array with fixed capacity

**Question 2: What information does the TodoList need to track?**
- The tasks themselves (Task array)
- Maximum capacity (how many tasks can fit)
- Current size (how many tasks are actually stored)

**Question 3: Why track current size separately?**
```java
// If capacity = 5
Task[] tasks = new Task[5];  // Array: [null, null, null, null, null]

// After adding 2 tasks
// Array: [Task1, Task2, null, null, null]
// We need to know only 2 slots are filled!
```

### **Step-by-Step Implementation**

#### **Step 1: Define Attributes**

```java
public class TodoList {
    private Task[] tasks;     // Store the tasks
    private int capacity;     // Maximum number of tasks
    private int currentSize;  // How many tasks currently stored
}
```

**Thinking:**
- `tasks[]` - the container for Task objects
- `capacity` - prevents adding more than allowed
- `currentSize` - tracks how many are actually filled (not null)

---

#### **Step 2: Constructor**

```java
public TodoList(int capacity) {
    this.capacity = capacity;
    this.tasks = new Task[capacity];  // Create array of size capacity
    this.currentSize = 0;             // Start with 0 tasks
}
```

**Thinking:**
- User specifies how many tasks the list can hold
- Create an array of that size (filled with nulls initially)
- Start with 0 because no tasks added yet

**Example:**
```java
TodoList list = new TodoList(3);
// Creates: tasks = [null, null, null]
// capacity = 3, currentSize = 0
```

---

#### **Step 3: addTask() Method**

**Thinking Process:**
1. Where should the new task go? → At index `currentSize`
2. What if the list is full? → Don't add anything
3. After adding, what changes? → Increment `currentSize`

```java
public void addTask(String description) {
    // Check if there's space
    if (currentSize < capacity) {
        // Create new task and add at current position
        tasks[currentSize] = new Task(description);
        // Move to next position
        currentSize++;
    }
    // If full, do nothing (as per requirements)
}
```

**Visual Example:**
```java
TodoList list = new TodoList(3);  // [null, null, null], size=0

list.addTask("Buy milk");
// [Task("Buy milk"), null, null], size=1
//  ^index 0

list.addTask("Call mom");
// [Task("Buy milk"), Task("Call mom"), null], size=2
//  ^index 0         ^index 1

list.addTask("Study Java");
// [Task("Buy milk"), Task("Call mom"), Task("Study Java")], size=3
//  ^index 0         ^index 1          ^index 2

list.addTask("Fourth task");
// Still: [Task("Buy milk"), Task("Call mom"), Task("Study Java")], size=3
// Can't add - list is full!
```

---

#### **Step 4: setStatus() Method**

**Thinking Process:**
1. User provides an index and new status
2. Need to validate the index is valid
3. Change the status of that specific task

**What makes an index valid?**
- Not negative: `index >= 0`
- Within actual tasks (not just capacity): `index < currentSize`
- Task exists at that position: `tasks[index] != null`

```java
public void setStatus(int index, TaskStatus status) {
    // Validate index
    if (index >= 0 && index < currentSize && tasks[index] != null) {
        // Safe to access and update
        tasks[index].setStatus(status);
    }
    // If invalid index, do nothing
}
```

**Why check `index < currentSize` not `index < capacity`?**

```java
TodoList list = new TodoList(10);  // capacity = 10
list.addTask("Task 1");            // currentSize = 1
list.addTask("Task 2");            // currentSize = 2

// Array: [Task1, Task2, null, null, null, null, null, null, null, null]

list.setStatus(5, TaskStatus.COMPLETED);
// index=5 is < capacity(10) ✓
// BUT tasks[5] is null! 💥 Would crash!

// CORRECT: index=5 is NOT < currentSize(2) ✗
// Method does nothing - safe!
```

---

#### **Step 5: setDescription() Method**

**Thinking:** Almost identical to `setStatus()`, just updating a different property.

```java
public void setDescription(int index, String newDescription) {
    if (index >= 0 && index < currentSize && tasks[index] != null) {
        tasks[index].setDescription(newDescription);
    }
}
```

---

#### **Step 6: displayTasks() Method**

**Thinking Process:**
1. Print a header: "Tasks:"
2. Loop through all actual tasks (not the whole capacity)
3. Format each task nicely

**Formatting requirement:**
- Description should take up 34 characters (padded with spaces)
- Followed by " | " (space-pipe-space)
- Then the status

```java
public void displayTasks() {
    System.out.println("Tasks:");
    
    // Loop through actual tasks only
    for (int i = 0; i < currentSize; i++) {
        Task task = tasks[i];
        
        // Pad description to 34 characters (left-aligned)
        String paddedDescription = String.format("%-34s", task.getDescription());
        
        // Print: "description (34 chars) | STATUS"
        System.out.println(paddedDescription + " | " + task.getStatus());
    }
}
```

**Understanding String.format("%-34s", text):**

```java
String.format("%-34s", "Hello")
// Result: "Hello                             " (34 chars total)
//         ^5 chars    ^29 spaces

String.format("%-34s", "This is a longer description")
// Result: "This is a longer description      " (34 chars total)
//         ^28 chars                    ^6 spaces
```

**Visual Example:**
```
Tasks:
Go grocery shopping            | COMPLETED
Pay all utility bills          | NEW
^--------- 34 chars --------^ ^space-pipe-space^ ^STATUS^
```

---

## Complete Example Walkthrough

Let's trace through the example from the README:

```java
TodoList myList = new TodoList(3);
```
**State:** `tasks = [null, null, null]`, capacity=3, currentSize=0

---

```java
myList.addTask("Go grocery shopping");
```
**What happens:**
1. Check: `currentSize (0) < capacity (3)` ✓
2. Create: `new Task("Go grocery shopping")` with status=NEW
3. Store: `tasks[0] = that task`
4. Increment: `currentSize = 1`

**State:** `tasks = [Task1(NEW), null, null]`, currentSize=1

---

```java
myList.addTask("Pay electricity bill");
```
**What happens:**
1. Check: `currentSize (1) < capacity (3)` ✓
2. Create: `new Task("Pay electricity bill")` with status=NEW
3. Store: `tasks[1] = that task`
4. Increment: `currentSize = 2`

**State:** `tasks = [Task1(NEW), Task2(NEW), null]`, currentSize=2

---

```java
myList.setStatus(0, TaskStatus.COMPLETED);
```
**What happens:**
1. Validate: `0 >= 0` ✓, `0 < 2` ✓, `tasks[0] != null` ✓
2. Call: `tasks[0].setStatus(TaskStatus.COMPLETED)`
3. Task1 status changes: NEW → COMPLETED

**State:** `tasks = [Task1(COMPLETED), Task2(NEW), null]`, currentSize=2

---

```java
myList.setDescription(1, "Pay all utility bills");
```
**What happens:**
1. Validate: `1 >= 0` ✓, `1 < 2` ✓, `tasks[1] != null` ✓
2. Call: `tasks[1].setDescription("Pay all utility bills")`
3. Task2 description changes

**State:** `tasks = [Task1(COMPLETED), Task2(NEW, updated), null]`, currentSize=2

---

```java
myList.displayTasks();
```
**What happens:**
1. Print: "Tasks:"
2. Loop i=0 to 1 (currentSize=2)
   - i=0: Format "Go grocery shopping" → 34 chars, print with "| COMPLETED"
   - i=1: Format "Pay all utility bills" → 34 chars, print with "| NEW"

**Output:**
```
Tasks:
Go grocery shopping            | COMPLETED
Pay all utility bills          | NEW
```

---

## Key Concepts Explained

### **1. Arrays**

Arrays are fixed-size containers:

```java
Task[] tasks = new Task[5];  // Creates array of size 5
// Index:  0      1      2      3      4
// Value: [null, null, null, null, null]

tasks[0] = new Task("First");  // Add at index 0
tasks[1] = new Task("Second"); // Add at index 1
```

**Important:**
- Array size is fixed (can't grow or shrink)
- Indices start at 0
- Default values are `null` for objects

### **2. Tracking Size vs Capacity**

```java
// Capacity = maximum slots
int capacity = 10;

// Current size = how many are filled
int currentSize = 3;

// Array might look like:
// [Task, Task, Task, null, null, null, null, null, null, null]
//  ^-currentSize-^   ^----------empty---------^
//  ^--------------capacity=10------------------^
```

**Why this matters:**
- When looping, use `currentSize` not `capacity`
- When adding, check against `capacity`
- When validating index, check against `currentSize`

### **3. Encapsulation**

Making variables `private` and using getters/setters:

```java
// ❌ BAD - direct access
class Task {
    public String description;
}
Task task = new Task();
task.description = "";  // Could set to empty! No control

// ✅ GOOD - controlled access
class Task {
    private String description;
    
    public void setDescription(String desc) {
        if (desc != null && !desc.isEmpty()) {  // Can validate!
            this.description = desc;
        }
    }
}
```

### **4. Bounds Checking**

Always validate array indices:

```java
// ❌ DANGEROUS - might crash
tasks[index].setStatus(...);  // What if index is -1? or 100? or tasks[index] is null?

// ✅ SAFE - validate first
if (index >= 0 && index < currentSize && tasks[index] != null) {
    tasks[index].setStatus(...);
}
```

---

## Common Mistakes and How to Avoid Them

### **Mistake 1: Using Capacity Instead of Current Size**

```java
// ❌ WRONG
for (int i = 0; i < capacity; i++) {
    tasks[i].getDescription();  // Might access null!
}

// ✅ CORRECT
for (int i = 0; i < currentSize; i++) {
    tasks[i].getDescription();  // Only accesses real tasks
}
```

### **Mistake 2: Not Incrementing Current Size**

```java
// ❌ WRONG
public void addTask(String description) {
    tasks[currentSize] = new Task(description);
    // Forgot to increment! Next task overwrites this one!
}

// ✅ CORRECT
public void addTask(String description) {
    tasks[currentSize] = new Task(description);
    currentSize++;  // Don't forget!
}
```

### **Mistake 3: Wrong Padding Number**

```java
// ❌ WRONG - test expects 34
String.format("%-30s", desc);  // Only 30 characters

// ✅ CORRECT
String.format("%-34s", desc);  // 34 characters as required
```

### **Mistake 4: Not Initializing Default Status**

```java
// ❌ WRONG - status will be null!
public Task(String description) {
    this.description = description;
}

// ✅ CORRECT - always set default
public Task(String description) {
    this.description = description;
    this.status = TaskStatus.NEW;
}
```

---

## Testing Your Implementation

### **Test 1: Basic Functionality**
```java
TodoList list = new TodoList(2);
list.addTask("Task 1");
list.addTask("Task 2");
list.displayTasks();
// Should show both tasks with NEW status
```

### **Test 2: List Full**
```java
TodoList list = new TodoList(2);
list.addTask("Task 1");
list.addTask("Task 2");
list.addTask("Task 3");  // Should be ignored
list.displayTasks();
// Should only show 2 tasks
```

### **Test 3: Invalid Index**
```java
TodoList list = new TodoList(5);
list.addTask("Task 1");
list.setStatus(5, TaskStatus.COMPLETED);  // Out of bounds
list.setStatus(-1, TaskStatus.COMPLETED); // Negative
list.displayTasks();
// Should still show Task 1 with NEW status (no crash!)
```

### **Test 4: Update Operations**
```java
TodoList list = new TodoList(3);
list.addTask("Original description");
list.setDescription(0, "Updated description");
list.setStatus(0, TaskStatus.IN_PROGRESS);
list.displayTasks();
// Should show updated description and status
```

---

## Design Decisions Summary

| Decision | Reasoning |
|----------|-----------|
| Use enum for status | Type safety, prevents typos |
| Private attributes | Encapsulation, controlled access |
| Track currentSize | Know how many tasks are real vs null |
| Array for storage | Fixed capacity as per requirements |
| Default status NEW | Logical starting state |
| Bounds checking | Prevent crashes from invalid indices |
| Format with padding | Clean, aligned output |

---

## What You Learned

1. **Enums** - Creating and using fixed constant sets
2. **Classes** - Building objects with attributes and methods
3. **Arrays** - Fixed-size collections and index management
4. **Encapsulation** - Private data with public access methods
5. **Validation** - Checking bounds before array access
6. **String formatting** - Padding for aligned output
7. **State management** - Tracking size separately from capacity

This assignment teaches fundamental OOP concepts you'll use in every Java project!
