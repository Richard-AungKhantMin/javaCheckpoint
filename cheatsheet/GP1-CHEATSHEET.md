# GP1 Cheatsheet: Date/Time APIs & Basic Classes

## Key Concepts

### 1. **Java Date/Time API (java.time package)**

#### LocalDate - Working with dates (year, month, day only)
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Create a date from string
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate bd = LocalDate.parse("2000-01-01", format);

// Get current date
LocalDate today = LocalDate.now();

// Convert back to string
String dateStr = bd.toString(); // "2000-01-01"
```

#### Period - Calculate difference between dates
```java
import java.time.Period;

LocalDate start = LocalDate.parse("2020-01-01", format);
LocalDate end = LocalDate.parse("2023-06-15", format);
Period diff = Period.between(start, end);

int years = diff.getYears();     // How many years
int months = diff.getMonths();   // How many months (0-11)
int days = diff.getDays();       // How many days (0-30)
```

#### DayOfWeek - Work with days of the week
```java
import java.time.DayOfWeek;

DayOfWeek day = DayOfWeek.MONDAY;
int dayValue = day.getValue(); // 1=Monday, 7=Sunday

DayOfWeek targetDay = DayOfWeek.valueOf("MONDAY"); // From string
String dayName = targetDay.toString(); // "MONDAY"
```

#### Date Arithmetic
```java
LocalDate date = LocalDate.now();
LocalDate future = date.plusDays(5);    // Add 5 days
LocalDate past = date.minusMonths(2);   // Subtract 2 months
```

---

### 2. **Exception Handling with Try-Catch**

When parsing dates or working with strings that might be invalid, use try-catch:

```java
try {
    LocalDate bd = LocalDate.parse(input, format);
    // Do something with bd
} catch (Exception e) {
    return -1; // Error case
}
```

**When to use:** For date parsing, file operations, or any input that might be malformed.

---

### 3. **String Formatting**

#### String.format() and printf()
```java
// Format with padding (useful for aligning output)
System.out.printf("%-34s| %s\n", description, status);
// %-34s = left-align string in 34 characters
// %s = string placeholder

// For numbers
System.out.printf("%05d\n", 42); // Prints: "00042"
```

---

### 4. **Static Methods vs Instance Methods**

```java
// STATIC - Can call on CLASS, no object needed
public class MultiplicationTable {
    public static void generate(int num) { // static keyword
        System.out.println(num + " x 1 = " + (num * 1));
    }
}
MultiplicationTable.generate(5); // Call without new

// INSTANCE - Need to create object first
public class AgeFinder {
    public int calculateAge(String date) { // NO static keyword
    }
}
AgeFinder finder = new AgeFinder(); // Must create with new
finder.calculateAge("2000-01-01");
```

---

### 5. **Loops - For Loop Basics**

```java
// Build a multiplication table
for (int i = 1; i <= 10; i++) {
    System.out.println(num + " x " + i + " = " + (num * i));
}

// Iterate through condition
for (int i = 0; i < size; i++) {
    // Do something with i
}
```

---

### 6. **Arrays**

```java
// Create and access arrays
String[] tasks = new String[3]; // Array of 3 elements
tasks[0] = "Buy milk";
tasks[1] = "Write code";

// Access length
int size = tasks.length;

// Loop through array
for (int i = 0; i < tasks.length; i++) {
    System.out.println(tasks[i]);
}
```

---

### 7. **Enums - Fixed Set of Values**

```java
enum TaskStatus {
    NEW, IN_PROGRESS, COMPLETED // Only these 3 values allowed
}

TaskStatus status = TaskStatus.NEW;
status = TaskStatus.COMPLETED; // Valid

// Print the name
System.out.println(status); // "COMPLETED"
```

---

### 8. **Classes with Constructors, Getters, and Setters**

```java
public class Task {
    private String description;  // private = hidden from outside
    private TaskStatus status;
    
    // Constructor - called with new
    public Task(String d) {
        this.description = d;
        this.status = TaskStatus.NEW;
    }
    
    // Getter - retrieve value
    public String getDescription() {
        return this.description;
    }
    
    // Setter - change value
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }
}

// Using the class
Task task = new Task("Buy milk");
System.out.println(task.getDescription()); // "Buy milk"
task.setDescription("Buy bread");
```

---

### 9. **Working with Time Duration**

```java
// Convert milliseconds to minutes
long milliseconds = 180000; // 3 minutes
float minutes = milliseconds / 60000f;

// Key conversions:
// 1 second = 1000 ms
// 1 minute = 60 seconds = 60,000 ms
// 1 hour = 60 minutes = 3,600,000 ms
```

---

### 10. **String Methods**

```java
String s = "hello";

// Length
int len = s.length(); // 5

// Get character at position
char c = s.charAt(0); // 'h'

// Convert to uppercase/lowercase
String upper = s.toUpperCase(); // "HELLO"
String lower = s.toLowerCase(); // "hello"

// Compare strings
boolean equal = s.equals("hello"); // true
boolean equalIgnoreCase = s.equalsIgnoreCase("HELLO"); // true

// Get substring
String sub = s.substring(1); // "ello" (from index 1 to end)
String sub2 = s.substring(1, 3); // "el" (from 1 to 3, not including 3)

// Check if contains
boolean has = s.contains("ell"); // true

// Replace
String replaced = s.replace("l", "L"); // "heLLo"
```

---

## Common Interview Questions for GP1

1. **What's the difference between static and instance methods?**
   - Static: Called on class, no instance needed (`Math.max()`)
   - Instance: Called on object, need `new` first

2. **How do you parse a date string?**
   - Use `DateTimeFormatter` with `LocalDate.parse()`

3. **How to calculate age from a birthdate?**
   - Use `Period.between()` to get the difference, extract `.getYears()`

4. **What's the purpose of try-catch?**
   - Catch errors (like invalid date formats) without crashing

---

## Quick Reference Table

| Task | Code |
|------|------|
| Parse date | `LocalDate.parse(dateStr, formatter)` |
| Get now | `LocalDate.now()` |
| Days between | `Period.between(start, end).getDays()` |
| Loop 1-10 | `for (int i = 1; i <= 10; i++)` |
| Array length | `array.length` |
| Create enum | `enum Name { VALUE1, VALUE2 }` |
| Getter | `public type getFoo() { return foo; }` |
| Setter | `public void setFoo(type f) { foo = f; }` |
