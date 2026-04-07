# GP2 Cheatsheet: Algorithms & Advanced Patterns

## Key Concepts

### 1. **Abstract Classes & Inheritance**

Abstract classes are like "blueprints" - they define methods that child classes MUST implement.

```java
// Abstract base class (cannot create with new)
public abstract class Factorial {
    public abstract long calculate(int n); // Must be implemented by children
}

// Child class implements the abstract method
public class IterativeFactorial extends Factorial {
    @Override // Tells compiler: "I'm implementing the abstract method"
    public long calculate(int n) {
        // Implementation here
        return result;
    }
}

// Usage
Factorial fact = new IterativeFactorial(); // Use child, not abstract
long result = fact.calculate(5);
```

**When to use:** When multiple classes share similar behavior but implement it differently.

---

### 2. **Recursion vs Iteration**

#### Recursion (function calls itself)
```java
public long calculateRecursive(int n) {
    if (n <= 1) return 1;      // Base case - STOP HERE
    return n * calculateRecursive(n - 1); // Recursive call
}

// Example: 5! = 5 * 4! = 5 * 4 * 3 * 2 * 1 = 120
// 5 * (4 * (3 * (2 * (1))))
```

**How it works:**
1. Function calls itself with smaller input
2. Each call waits for the next call to return
3. Base case stops the recursion

#### Iteration (using loops)
```java
public long calculateIterative(int n) {
    if (n <= 1) return 1;
    long result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i; // Multiply step by step
    }
    return result;
}

// Example: 5! = 1 * 1 * 2 * 3 * 4 * 5 = 120
```

**Comparison:**
- **Recursion:** Elegant, easier to understand, slower, uses more memory
- **Iteration:** Faster, uses less memory, sometimes harder to code

---

### 3. **Sorting Algorithms**

#### Bubble Sort - Compare adjacent pairs, swap if wrong order
```java
public void sort() {
    int[] numbers = getArray();
    boolean swapped = false;
    
    for (int i = 0; i < numbers.length; i++) {
        swapped = false;
        // Compare each pair of adjacent elements
        for (int j = 0; j < numbers.length - i - 1; j++) {
            if (numbers[j] > numbers[j + 1]) {
                // Swap
                int temp = numbers[j];
                numbers[j] = numbers[j + 1];
                numbers[j + 1] = temp;
                swapped = true;
            }
        }
        // If nothing swapped, array is sorted
        if (!swapped) break;
    }
}
```

**How it works:** 
- Array [64, 34, 25, 12]
- Pass 1: [34, 25, 12, 64] - largest moves to end
- Pass 2: [25, 12, 34, 64] - second largest in place
- Pass 3: [12, 25, 34, 64] - sorted!

#### Insertion Sort - Build sorted array piece by piece
```java
public void sort() {
    int[] numbers = getArray();
    
    // Start from second element
    for (int i = 1; i < numbers.length; i++) {
        int key = numbers[i]; // Element to insert
        int j = i - 1;
        
        // Shift larger elements to the right
        while (j >= 0 && numbers[j] > key) {
            numbers[j + 1] = numbers[j];
            j--;
        }
        
        // Insert key in its correct position
        numbers[j + 1] = key;
    }
}
```

**How it works:**
- Consider [64, 34, 25, 12]
- Start: [64], insert 34 → [34, 64]
- Insert 25 → [25, 34, 64]
- Insert 12 → [12, 25, 34, 64]

---

### 4. **Regular Expressions (Regex)**

Regex is a language for matching text patterns.

#### Common Regex Patterns
```
.     = Any single character
\d    = Any digit (0-9)
\w    = Word character (a-z, A-Z, 0-9, _)
\s    = Whitespace (space, tab, newline)
*     = 0 or more of previous
+     = 1 or more of previous
?     = 0 or 1 of previous
[abc] = Any of a, b, or c
^     = Start of line
$     = End of line
|     = OR
```

#### Using Regex in Java
```java
import java.util.regex.*;

String pattern = "[0-9]+"; // 1 or more digits
String text = "I have 123 apples";

Pattern p = Pattern.compile(pattern);
Matcher m = p.matcher(text);

if (m.find()) {
    System.out.println(m.group()); // "123"
}
```

#### Parsing URLs with Groups ()
```java
String pattern = "^(([^:/?#]+):)?(//([^/?#]*))?:?(\\d+)?([^?#]*)(\\?([^#]*))?$";
//                 Group 2  protocol  Group 4    domain     Group 5 port  Group 6 path  Group 8 query
Matcher m = Pattern.compile(pattern).matcher(url);

if (m.find()) {
    String protocol = m.group(2);  // "https"
    String domain = m.group(4);    // "www.example.com"
    String port = m.group(5);      // "8080"
    String path = m.group(6);      // "/path"
    String query = m.group(8);     // "name=value"
}
```

---

### 5. **String Manipulation**

```java
// Replace characters
String result = configLine.replace("=", "=***"); // password=*** replaces ;

// Substring
String sub = s.substring(1);      // From index 1 to end
String sub2 = s.substring(0, 5);  // From 0 to 5 (not including 5)

// Character at position
char c = s.charAt(0); // First character

// Remove letter by removing first i characters and adding rest
String removed = s.substring(0, i) + s.substring(i+1);
// "racedcar" with index 1 → "r" + "cedcar" = "rcedcar"

// Check contains
if (s.contains("ell")) { } // "hello" contains "ell"

// Case sensitivity
String lower = s.toLowerCase(); // All lowercase
String upper = s.toUpperCase(); // All uppercase
```

---

### 6. **Palindrome Checking**

A palindrome reads the same forwards and backwards.

```java
// Check if "hello" backwards is same as forward
public static boolean isPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
        // Compare character at i with character at (length - 1 - i)
        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
            return false; // Not a palindrome
        }
    }
    return true;
}

// "racecar"
//  r a c e c a r
//  0 1 2 3 4 5 6
// Compare: 0↔6, 1↔5, 2↔4 (stop before middle)
```

---

### 7. **Prime Numbers**

A prime number is only divisible by 1 and itself.

```java
public static boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false; // Even numbers not prime (except 2)
    
    // Check odd dividers from 3 to sqrt(n)
    for (int i = 3; i <= n / 2; i += 2) {
        if (n % i == 0) {
            return false; // Found a divisor, not prime
        }
    }
    return true;
}

// Find next prime after a number
public static Integer nextPrime(Integer n) {
    for (int i = n + 1; ; i++) {
        if (isPrime(i)) return i;
    }
}
```

**Optimization:** Only check up to `n/2` because a divisor can't be larger than half.

---

### 8. **Stack-Based HTML Validation**

Use a stack to match opening and closing tags.

```java
Stack<String> stack = new Stack<>();

for (int i = 0; i < html.length(); i++) {
    if (html.charAt(i) == '<') {
        // Found opening tag
        String tag = html.substring(i+1, endIndex);
        stack.push(tag); // Add to stack
    } else if (html.charAt(i) == '>') {
        // Found closing tag
        String tag = html.substring(i+1, endIndex);
        if (!stack.pop().equals(tag)) {
            return false; // Mismatch!
        }
    }
}

return stack.isEmpty(); // All matched if stack is empty
```

**Concept:** Each opening tag pushed to stack, closing tag pops and checks it matches.

---

## Quick Reference

| Concept | Usage |
|---------|-------|
| Abstract method | `public abstract void method();` |
| @Override | Marks that you're implementing abstract method |
| Extends | `class Child extends Parent` |
| Recursion vs Loop | Recursion: elegant, Iteration: fast |
| contains() | `"hello".contains("ell")` → true |
| substring() | `"hello".substring(1, 3)` → "el" |
| Regex group | `m.group(1)` gets first captured group |
| isPrime | Check divisibility up to n/2 |
| Palindrome | Compare from both ends toward middle |

