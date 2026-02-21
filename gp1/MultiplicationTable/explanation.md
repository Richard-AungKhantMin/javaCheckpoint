# MultiplicationTable Assignment - Step by Step Guide

## What You Need to Do

Print the multiplication table for a given number from 1 to 10.

**Example:** For number 5, print:
```
5 x 1 = 5
5 x 2 = 10
5 x 3 = 15
...
5 x 10 = 50
```

---

## Step-by-Step Solution

### **Step 1: Understand the Pattern**

For any number `n`, the multiplication table follows this pattern:
```
n x 1 = n * 1
n x 2 = n * 2
n x 3 = n * 3
...
n x 10 = n * 10
```

The multiplier goes from **1 to 10** (always 10 rows).

---

### **Step 2: Use a Loop**

Since we're repeating the same operation 10 times (multiply by 1, 2, 3...10), we use a **for loop**:

```java
for (int i = 1; i <= 10; i++) {
    // multiply num by i
}
```

**Why `i = 1` to `i <= 10`?**
- We start at 1 (not 0) because multiplication by 0 isn't useful
- We go up to and including 10 (hence `<=` not `<`)
- Total iterations: 10 (i = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

---

### **Step 3: Calculate the Result**

Inside the loop, multiply the number by the current iteration:

```java
int result = num * i;
```

For example:
- When i = 1: result = 5 * 1 = 5
- When i = 2: result = 5 * 2 = 10
- When i = 10: result = 5 * 10 = 50

---

### **Step 4: Print in the Correct Format**

The format is: `"num x i = result"`

```java
System.out.println(num + " x " + i + " = " + result);
```

**Understanding the format:**
- `num` - the original number (e.g., 5)
- `" x "` - literal text with spaces around 'x'
- `i` - current multiplier (1, 2, 3...10)
- `" = "` - literal text with spaces around '='
- `result` - the calculated answer

**Important:** Notice the spaces! It's `" x "` not `"x"`, and `" = "` not `"="`.

---

### **Step 5: Put It All Together**

Combine the loop, calculation, and printing:

```java
public static void generate(int num) {
    for (int i = 1; i <= 10; i++) {
        int result = num * i;
        System.out.println(num + " x " + i + " = " + result);
    }
}
```

---

## Example Walkthrough

**Input:** `generate(5)`

**Loop iterations:**

| i | Calculation | Output |
|---|-------------|--------|
| 1 | 5 * 1 = 5   | `5 x 1 = 5` |
| 2 | 5 * 2 = 10  | `5 x 2 = 10` |
| 3 | 5 * 3 = 15  | `5 x 3 = 15` |
| 4 | 5 * 4 = 20  | `5 x 4 = 20` |
| 5 | 5 * 5 = 25  | `5 x 5 = 25` |
| 6 | 5 * 6 = 30  | `5 x 6 = 30` |
| 7 | 5 * 7 = 35  | `5 x 7 = 35` |
| 8 | 5 * 8 = 40  | `5 x 8 = 40` |
| 9 | 5 * 9 = 45  | `5 x 9 = 45` |
| 10 | 5 * 10 = 50 | `5 x 10 = 50` |

**Total lines printed:** 10

---

## Simplified One-Line Version

You can also skip the `result` variable and calculate directly in the print statement:

```java
public static void generate(int num) {
    for (int i = 1; i <= 10; i++) {
        System.out.println(num + " x " + i + " = " + (num * i));
    }
}
```

**Note:** The parentheses around `(num * i)` ensure multiplication happens first before string concatenation.

**Why parentheses matter:**
```java
// With parentheses (CORRECT)
5 + " x " + 1 + " = " + (5 * 1)  // Result: "5 x 1 = 5"

// Without parentheses (WRONG)
5 + " x " + 1 + " = " + 5 * 1   // Won't compile! Can't multiply string by number
```

---

## Key Concepts

### **1. For Loop**

Repeats code a specific number of times:
```java
for (initialization; condition; increment) {
    // code to repeat
}
```

**Breakdown:**
- **Initialization:** `int i = 1` - runs once at the start
- **Condition:** `i <= 10` - checked before each iteration
- **Increment:** `i++` - runs after each iteration (increases i by 1)

**How it works:**
```
1. Set i = 1
2. Check: is i <= 10? Yes → run the loop body
3. Execute code inside {}
4. Increment: i++ (now i = 2)
5. Check: is i <= 10? Yes → run again
6. ... repeat until i = 11
7. Check: is i <= 10? No → exit loop
```

---

### **2. String Concatenation**

Using `+` to combine strings and numbers:
```java
String result = "5" + " x " + "1" + " = " + "5";  
// Result: "5 x 1 = 5"
```

**Java automatically converts numbers to strings when concatenating:**
```java
int num = 5;
int i = 3;
String output = num + " x " + i + " = " + (num * i);
// Becomes: "5 x 3 = 15"
```

---

### **3. Static Methods**

`static` means the method belongs to the class, not an instance:

```java
// Static method - called on the class
MultiplicationTable.generate(5);  // No object needed

// Non-static method - needs an object
MultiplicationTable table = new MultiplicationTable();
table.generate(5);  // Would work if method wasn't static
```

**Why static here?**
- We don't need to store any data (no instance variables)
- The method just performs an action and returns nothing
- Makes it simpler to call without creating an object

---

## Common Mistakes to Avoid

### **1. Starting from 0 instead of 1**

```java
// ❌ WRONG - starts with "5 x 0 = 0"
for (int i = 0; i < 10; i++) {
    System.out.println(num + " x " + i + " = " + (num * i));
}

// ✅ CORRECT - starts with "5 x 1 = 5"
for (int i = 1; i <= 10; i++) {
    System.out.println(num + " x " + i + " = " + (num * i));
}
```

### **2. Using < instead of <=**

```java
// ❌ WRONG - only goes up to 9
for (int i = 1; i < 10; i++) {
    // Prints 1-9, misses 10!
}

// ✅ CORRECT - goes up to and includes 10
for (int i = 1; i <= 10; i++) {
    // Prints 1-10
}
```

### **3. Missing spaces in output**

```java
// ❌ WRONG - no spaces
System.out.println(num + "x" + i + "=" + (num * i));
// Output: "5x1=5" (hard to read!)

// ✅ CORRECT - proper spacing
System.out.println(num + " x " + i + " = " + (num * i));
// Output: "5 x 1 = 5"
```

### **4. Forgetting parentheses in calculation**

```java
// ❌ WRONG - syntax error
System.out.println(num + " x " + i + " = " + num * i);

// ✅ CORRECT - parentheses ensure proper order
System.out.println(num + " x " + i + " = " + (num * i));
```

---

## Testing Different Inputs

### Test with 3:
```
3 x 1 = 3
3 x 2 = 6
3 x 3 = 9
3 x 4 = 12
3 x 5 = 15
3 x 6 = 18
3 x 7 = 21
3 x 8 = 24
3 x 9 = 27
3 x 10 = 30
```

### Test with 12:
```
12 x 1 = 12
12 x 2 = 24
12 x 3 = 36
12 x 4 = 48
12 x 5 = 60
12 x 6 = 72
12 x 7 = 84
12 x 8 = 96
12 x 9 = 108
12 x 10 = 120
```

### Test with 0:
```
0 x 1 = 0
0 x 2 = 0
0 x 3 = 0
...all zeros
```

---

## Alternative Solutions

### Using a while loop:
```java
public static void generate(int num) {
    int i = 1;
    while (i <= 10) {
        System.out.println(num + " x " + i + " = " + (num * i));
        i++;
    }
}
```

### Using String.format for better formatting:
```java
public static void generate(int num) {
    for (int i = 1; i <= 10; i++) {
        System.out.println(String.format("%d x %d = %d", num, i, num * i));
    }
}
```

Both work, but the for loop with concatenation is simpler for beginners!

---

## Summary

**What we learned:**
1. How to use for loops to repeat code
2. String concatenation to build output
3. Static methods for utility functions
4. Proper formatting with spaces
5. Why we use `<=` to include the final value

This is a foundational exercise that teaches loop control - you'll use these concepts constantly in programming!
