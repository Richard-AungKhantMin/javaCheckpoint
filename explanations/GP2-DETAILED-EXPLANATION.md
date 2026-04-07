# GP2 - Detailed Solutions Explained

## Problem 1: Factorial Master

### The Problem
Calculate N! (factorial) = N × (N-1) × (N-2) × ... × 1

**Examples:**
```
5! = 5 × 4 × 3 × 2 × 1 = 120
0! = 1 (by definition)
1! = 1
```

---

## Understanding the Two Approaches

There are two fundamentally different ways to think about factorial:

### Approach 1: Iteration (Loop)
Think step-by-step: "I'll multiply all numbers one by one"

### Approach 2: Recursion (Self-calling)
Think recursively: "5! = 5 × 4! and 4! = 4 × 3! and so on..."

---

## Iterative Factorial - Explained

```java
public long calculate(int n) {
    if (n <= 1) {
        return 1;
    }
```

**Why check `n <= 1` first?**
- Factorial is only defined for positive integers
- 0! = 1 by definition (this is weird but mathematically agreed upon)
- Negative numbers don't have factorials
- So if we get 0 or 1 or anything negative, return 1 immediately

**Example:**
```
calculate(0) → return 1 immediately
calculate(-5) → return 1 immediately (error case)
```

---

```java
    long result = 1;
```

**Why start with 1?** Because we're going to multiply. If we started with 0, everything becomes 0!

**Think like a calculator:**
```
Starting value: 1
Multiply by 1: 1 × 1 = 1
Multiply by 2: 1 × 2 = 2
Multiply by 3: 2 × 3 = 6
Multiply by 4: 6 × 4 = 24
Multiply by 5: 24 × 5 = 120
Result: 120
```

---

```java
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
```

**How the loop works:**
- Start `i` at 1
- Keep going while `i <= n`
- Each time, add 1 to `i`

**What's `*=`?** It's shorthand:
```java
result *= i;  // Means: result = result * i
```

**Step-by-step for calculate(5):**
```
Initial: result = 1

i=1: result = result * 1 = 1 * 1 = 1
i=2: result = result * 2 = 1 * 2 = 2
i=3: result = result * 3 = 2 * 3 = 6
i=4: result = result * 4 = 6 * 4 = 24
i=5: result = result * 5 = 24 * 5 = 120
Loop ends (i=6, which is > 5)

return 120
```

---

### Why Iterative Works

**Pros:**
- Easy to understand: just "keep multiplying"
- Fast: no function call overhead
- Simple loop structure

**Cons:**
- Less elegant than recursive approach
- Not as intuitive for some people

---

## Recursive Factorial - Explained

```java
public long calculate(int n) {
    if (n <= 1) {
        return 1;
    }
```

**Same base case:** If n is 0 or 1, return 1. This is crucial to prevent infinite recursion!

---

```java
    long result = n;
    return result * calculate(n-1);
}
```

**What's happening?**
- Instead of looping, we call the function again with `n-1`
- Each call multiplies `n` by the result of `calculate(n-1)`

**Example: calculate(5)**

```
calculate(5)
    5 * calculate(4)
        ↓ Need to calculate calculate(4), so call it
        
calculate(4)
    4 * calculate(3)
        ↓ Need to calculate calculate(3), so call it
        
calculate(3)
    3 * calculate(2)
        ↓ Need to calculate calculate(2), so call it
        
calculate(2)
    2 * calculate(1)
        ↓ Need to calculate calculate(1), so call it
        
calculate(1)
    return 1 (BASE CASE! Stop recursion)
    
Now we can work backwards:
calculate(1) = 1
calculate(2) = 2 * 1 = 2
calculate(3) = 3 * 2 = 6
calculate(4) = 4 * 6 = 24
calculate(5) = 5 * 24 = 120
```

---

### The "Stack" Explanation

When you call a function, it goes on a **call stack**. Each call waits for the next call to finish:

```
STACK (newest on top):
[calculate(1)] ← Finishes first, returns 1
[calculate(2)] ← Waiting for calculate(1)
[calculate(3)] ← Waiting for calculate(2)
[calculate(4)] ← Waiting for calculate(3)
[calculate(5)] ← Started first, finishes last
```

It's like a stack of plates: you put the most recent on top, and you take from the top first.

---

### Why Recursion Works

**Pros:**
- Elegant: mirrors the mathematical definition
- Intuitive: "factorial of n = n × factorial of n-1"

**Cons:**
- Slower: each function call has overhead
- Uses more memory: the call stack grows
- Can cause stack overflow if n is very large
- Harder to understand if new to programming

---

### Iteration vs Recursion Comparison

```
For calculate(1000000):

Iteration:
- Loop 1 million times
- Uses constant memory
- Fast

Recursion:
- Call function 1 million times
- Stack might overflow! (too many calls)
- Memory usage: 1 million * (memory per call)
```

---

## Problem 2: Flexisort (Bubble Sort & Insertion Sort)

### The Problem
Sort an array of numbers in ascending order.

**Example:**
```
Input:  [64, 34, 25, 12, 22, 11, 90]
Output: [11, 12, 22, 25, 34, 64, 90]
```

---

## Abstract Sorter Class

```java
public abstract class Sorter {
    private int[] array;
    
    public int[] getArray() {
        return this.array;
    }
    
    public void setArray(int[] array) {
        this.array = array;
    }
    
    public abstract void sort();
}
```

**Why abstract?**
- We don't want to create a `Sorter` object directly
- We want `BubbleSort extends Sorter` and `InsertionSort extends Sorter`
- Each subclass must implement `sort()` their own way

**The getters/setters:**
- Encapsulation: private `array` can't be accessed directly
- Must use `setArray()` to set, `getArray()` to get
- Adds control: could add validation if needed

---

## Bubble Sort - Detailed

**The Concept:** Imagine bubbles rising to the surface. Larger numbers "bubble up" to the end.

```java
public void sort() {
    int[] numbers = this.getArray();
```

**Get the array from the object.**

---

```java
    if (numbers == null || numbers.length < 2) {
        return;
    }
```

**Edge cases:**
- If array is null (no data), can't sort → return
- If array has 0 or 1 element, it's already sorted → return

---

```java
    int n = numbers.length;
    boolean swapped = false;
```

**What's `swapped`?** An optimization flag. If no swaps happen in a pass, the array is sorted!

---

```java
    for (int i = 0; i < n; i++) {
        if (i != 0 && !swapped) {
            break; // Array is already sorted!
        }
        swapped = false;
```

**What's happening:**
- Each iteration is one "pass" through the array
- At the start of each pass (except first), check if we swapped anything last time
- `!swapped` means "did NOT swap"
- If we didn't swap AND we're not on first pass, the array is sorted!

---

```java
        for (int j = 0; j < n - i - 1; j++) {
            if (numbers[j] > numbers[j + 1]) {
```

**The inner loop compares pairs:**
- Start at position 0
- Compare pairs: `numbers[j]` and `numbers[j+1]`
- Why `< n - i - 1`? Because after each full pass, the largest unsorted number is in its final position

**Visual:**
```
Array: [64, 34, 25, 12]

Pass 1 (i=0):
  j=0: Compare [0] and [1]: 64 > 34? Swap → [34, 64, 25, 12]
  j=1: Compare [1] and [2]: 64 > 25? Swap → [34, 25, 64, 12]
  j=2: Compare [2] and [3]: 64 > 12? Swap → [34, 25, 12, 64]
  ← 64 is now in correct position! Don't need to check it again.

Pass 2 (i=1):
  j=0: Compare [0] and [1]: 34 > 25? Swap → [25, 34, 12, 64]
  j=1: Compare [1] and [2]: 34 > 12? Swap → [25, 12, 34, 64]
  ← Now 34 is in position. Don't check positions 2 and 3 anymore (they're sorted).
```

---

```java
                int temp = numbers[j];
                numbers[j] = numbers[j + 1];
                numbers[j + 1] = temp;
                swapped = true;
            }
        }
    }
}
```

**The swap:**
- You can't just do `numbers[j] = numbers[j+1]` (you'd lose the original value!)
- Use a temporary variable:
  1. Save the original value in `temp`
  2. Put the second value where the first was
  3. Put the saved value where the second was

**Mark that we swapped:** Set `swapped = true`

---

### Bubble Sort Example - Complete

```
Initial: [64, 34, 25, 12]

Pass 1 (i=0):
  Pair [0,1]: 64 > 34? YES → [34, 64, 25, 12] (swapped=true)
  Pair [1,2]: 64 > 25? YES → [34, 25, 64, 12] (swapped=true)
  Pair [2,3]: 64 > 12? YES → [34, 25, 12, 64] (swapped=true)
  64 is now in final position!

Pass 2 (i=1):
  Check: i != 0? YES. swapped? YES. Continue.
  swapped = false (reset)
  Pair [0,1]: 34 > 25? YES → [25, 34, 12, 64] (swapped=true)
  Pair [1,2]: 34 > 12? YES → [25, 12, 34, 64] (swapped=true)
  34 is now in final position!

Pass 3 (i=2):
  Check: i != 0? YES. swapped? YES. Continue.
  swapped = false (reset)
  Pair [0,1]: 25 > 12? YES → [12, 25, 34, 64] (swapped=true)
  25 is now in final position!

Pass 4 (i=3):
  Check: i != 0? YES. swapped? YES. Continue.
  swapped = false (reset)
  No pairs to compare (n - i - 1 = 0)
  
Pass 5 (i=4):
  Check: i != 0? YES. swapped? NO. BREAK!

Result: [12, 25, 34, 64] ✓
```

---

## Insertion Sort - Detailed

**The Concept:** Build a sorted array one element at a time, like inserting cards into your hand.

```java
public void sort() {
    int[] numbers = this.getArray();
    if (numbers == null || numbers.length < 2) {
        return;
    }
```

**Same checks as bubble sort.**

---

```java
    int n = numbers.length;
    boolean sorted = true;
    for (int i = 1; i < n; i++) {
        if (numbers[i] < numbers[i - 1]) {
            sorted = false;
        }
    }
    if (sorted) return; // Early exit if already sorted
```

**What's happening?**
- Quick check: is the array already sorted?
- If yes, return immediately (optimization)

---

```java
    for (int i = 1; i < n; i++) {
        int key = numbers[i]; // The element to insert
        int j = i - 1;
```

**Start from second element (index 1):**
- `key` is the current element we want to insert
- `j` is the position to its left

---

```java
        while (j >= 0 && numbers[j] > key) {
            numbers[j + 1] = numbers[j]; // Shift larger element right
            j--;
        }
```

**The shifting loop:**
- Compare `key` with elements to its left
- If element to the left is larger, shift it one position right
- Keep going left until we find a smaller element OR reach the start

**Example: inserting 25 into [12, 34, 64]**
```
Before: [12, 34, 64, 25, ...]
         key = 25, j = 2

j=2: numbers[2]=64. Is 64 > 25? YES
     Shift: numbers[3] = numbers[2] = 64
     Array: [12, 34, 64, 64, ...]
     j = 1

j=1: numbers[1]=34. Is 34 > 25? YES
     Shift: numbers[2] = numbers[1] = 34
     Array: [12, 34, 34, 64, ...]
     j = 0

j=0: numbers[0]=12. Is 12 > 25? NO
     Stop shifting!
     
Insert key: numbers[0 + 1] = numbers[1] = 25
Array: [12, 25, 34, 64, ...]
```

---

```java
        numbers[j + 1] = key; // Insert the key in its correct position
    }
}
```

**After all larger elements are shifted right, insert the key at position `j+1`.**

---

### Insertion Sort Example - Complete

```
Initial: [64, 34, 25, 12]

i=1 (insert 34):
  key=34, j=0
  Is 64 > 34? YES → shift 64 to position 1
  Array: [64, 64, 25, 12]
  j=-1 (stop, j < 0)
  Insert: numbers[0] = 34
  Array: [34, 64, 25, 12]

i=2 (insert 25):
  key=25, j=1
  Is 64 > 25? YES → shift 64 to position 2
  Array: [34, 64, 64, 12]
  j=0
  Is 34 > 25? YES → shift 34 to position 1
  Array: [34, 34, 64, 12]
  j=-1 (stop)
  Insert: numbers[0] = 25
  Array: [25, 34, 64, 12]

i=3 (insert 12):
  key=12, j=2
  Is 64 > 12? YES → shift 64 to position 3
  Array: [25, 34, 64, 64]
  j=1
  Is 34 > 12? YES → shift 34 to position 2
  Array: [25, 34, 34, 64]
  j=0
  Is 25 > 12? YES → shift 25 to position 1
  Array: [25, 25, 34, 64]
  j=-1 (stop)
  Insert: numbers[0] = 12
  Array: [12, 25, 34, 64]

Result: [12, 25, 34, 64] ✓
```

---

### Bubble vs Insertion

| Aspect | Bubble Sort | Insertion Sort |
|--------|------------|-----------------|
| Concept | Bubbles rise | Insert into sorted part |
| Best case | O(n) if sorted | O(n) if sorted |
| Worst case | O(n²) | O(n²) |
| Real-world use | Rarely | Common (e.g., sorting small arrays) |
| Easier to understand | For beginners | Requires shifting concept |

---

## Problem 3: Almost Palindrome

### The Problem
A word is "almost" a palindrome if removing ONE letter makes it a palindrome.

**Examples:**
```
"Racedcar" → Remove 'e' → "Racecar" → Is palindrome? YES → True
"level" → Is already palindrome. To be "almost", removing 1 should still be palindrome.
         Remove 'l' → "evel" → Not palindrome
         Remove 'e' → "lvl" → Not palindrome
         ... none work → False
```

---

### The Solution

```java
public static boolean isAlmostPalindrome(String s) {
    if (s == null || s.length() < 3 || isPalindrome(s.toLowerCase())) {
        return false;
    }
```

**What's checking?**
- `s == null`: Can't work with no input
- `s.length() < 3`: Need at least 3 letters (removing 1 leaves 2, which is always palindrome!)
- `isPalindrome(s.toLowerCase())`: If ALREADY a palindrome, it's not "almost" one!

**Why `.toLowerCase()`?** Make comparison case-insensitive.

---

```java
    s = s.toLowerCase();
    for (int i = 0; i < s.length(); i++) {
```

**Loop through each position:** Try removing character at each position.

---

```java
        if (i == 0) {
            if (isPalindrome(s.substring(1))) {
                return true;
            }
        }
```

**If removing first character:**
- `s.substring(1)` means "from index 1 to end" (skip character 0)
- Check if remaining string is palindrome
- If yes, found!

**Example:**
```
s = "racedcar"
Remove index 0 ('r'): "acedcar"
Is "acedcar" palindrome? No
Continue...
```

---

```java
        else if (i == s.length() - 1) {
            if (isPalindrome(s.substring(0, i))) {
                return true;
            }
        }
```

**If removing last character:**
- `s.substring(0, i)` means "from start to index i" (not including i)
- For last character, `i = length - 1`, so `substring(0, i)` gives everything except last

**Example:**
```
s = "racedcar"
i = 7 (last)
substring(0, 7) = "racedc"
Is "racedc" palindrome? No
Continue...
```

---

```java
        else {
            if (isPalindrome(s.substring(0, i) + s.substring(i+1))) {
                return true;
            }
        }
```

**If removing middle character:**
- `s.substring(0, i)` = everything before the character
- `s.substring(i+1)` = everything after the character
- Concatenate them

**Example:**
```
s = "racedcar"
i = 1 ('a')
substring(0, 1) = "r"
substring(2) = "cedcar"
Combined = "r" + "cedcar" = "rcedcar"
Is "rcedcar" palindrome? No
Continue...

i = 2 ('c')
substring(0, 2) = "ra"
substring(3) = "edcar"
Combined = "raedcar"
Is "raedcar" palindrome? No
Continue...

i = 3 ('e')
substring(0, 3) = "rac"
substring(4) = "dcar"
Combined = "racdcar"
Is "racdcar" palindrome? No
Continue...

i = 4 ('d')
substring(0, 4) = "race"
substring(5) = "car"
Combined = "racecar"
Is "racecar" palindrome? YES! 
Return true
```

---

### The isPalindrome Helper

```java
private static boolean isPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
            return false;
        }
    }
    return true;
}
```

**How palindrome check works:**
- Compare from both ends toward middle
- `i` goes from start: 0, 1, 2, ...
- `s.length() - 1 - i` goes from end: length-1, length-2, ...
- Stop at middle (`i < length / 2`)

**Example: "racecar" (length 7)**
```
i=0: compare position [0] and [6]: 'r' == 'r'? YES
i=1: compare position [1] and [5]: 'a' == 'a'? YES
i=2: compare position [2] and [4]: 'c' == 'c'? YES
i=3: stop (3 >= 7/2 = 3)

Middle character 'e' at position [3] doesn't need to be checked.
Return true (is palindrome)
```

---

## Problem 4: Breakdown URL

### The Problem
Parse a URL into components:
```
https://www.example.com:8080/path?name=value

Components:
- protocol: https
- domain: www.example.com
- port: 8080
- path: /path
- query: name=value
```

---

### Regular Expressions (Regex)

**The Pattern:**
```
^(([^:/?#]+):)?(//([^/?#:]*))?:?(\d+)?([^?#]*)(\?([^#]*))?(#.*)?$
```

This looks scary! Let's break it down:

**Regex building blocks:**
```
^              = Start of string
$              = End of string
(...)          = Group (capture this part)
?              = 0 or 1 (optional)
[^...]         = NOT these characters
+              = 1 or more
*              = 0 or more
\d             = Any digit
\?             = Literal ? character (escaped)
#              = Literal # character
.              = Any character
```

---

### The Pattern Broken Down

```
^(([^:/?#]+):)?
  Group 1 (optional)
    Group 2: one or more characters that are NOT :, /, ?, #
             This is the PROTOCOL (https)
    Followed by : (colon)
```

**Example:** `https:`

---

```
(//([^/?#]*))?
  Group 3 (optional)
    Starts with // (literal slashes)
    Group 4: zero or more characters that are NOT /, ?, #
             This is the DOMAIN (www.example.com)
```

**Example:** `//www.example.com`

---

```
:?(\d+)?
  Optional : (colon)
  Group 5 (optional)
    \d+ = one or more digits
    This is the PORT (8080)
```

**Example:** `:8080`

---

```
([^?#]*)
  Group 6
    Zero or more characters that are NOT ? or #
    This is the PATH (/path)
```

**Example:** `/path`

---

```
(\?([^#]*))?
  Group 7 (optional)
    Literal \? (question mark)
    Group 8: zero or more characters that are NOT #
             This is the QUERY (name=value)
```

**Example:** `?name=value`

---

### Using the Matcher

```java
Matcher m = Pattern.compile(pattern).matcher(url);

if (m.find()) {
    String protocol = m.group(2);  // Group 2 = protocol
    String domain = m.group(4);    // Group 4 = domain
    // etc.
}
```

**Why group(2) instead of group(1)?**
- Group 1 is `(([^:/?#]+):)?` - the whole protocol part
- Group 2 is `([^:/?#]+)` - just the protocol without colon
- We only want the protocol itself, not the colon

---

## Problem 5: Config Protector

### The Problem
Hide sensitive data in a config file by replacing values with asterisks.

**Example:**
```
Input:
username=admin
password=secret
host=localhost

With sensitiveKeys = ["password"]

Output:
username=admin
password=****** (6 asterisks for 6 characters)
host=localhost
```

---

### The Solution

```java
public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
    String result = "";
    if (sensitiveKeys.isEmpty()) {
        return configFile; // Nothing to hide
    }
```

---

```java
    String pattern = "^([^=]+)=(.*?)$";
    Matcher matcher = Pattern.compile(pattern, Pattern.MULTILINE).matcher(configFile);
```

**The pattern:** `^([^=]+)=(.*?)$`
- `^` = Start of line
- `([^=]+)` = Group 1: one or more characters that are NOT `=` (the KEY)
- `=` = literal equals sign
- `(.*?)` = Group 2: any characters, non-greedy (the VALUE)
- `$` = End of line

**Pattern.MULTILINE:** Process multiple lines separately (each line is start-to-end).

**Example:**
```
Line: "password=secret"

Group 1: "password" (before =)
Group 2: "secret" (after =)
```

---

```java
    while (matcher.find()) {
        result += matcher.group(1) + "="; // Add key and equals sign
```

**For each line matched:**
1. Add the key
2. Add the equals sign

---

```java
        if (sensitiveKeys.contains(matcher.group(1))) {
            for (int i = 0; i < matcher.group(2).length(); i++) {
                result += "*";
            }
        } else {
            result += matcher.group(2);
        }
        result += "\n";
    }
    return result;
}
```

**If the key is sensitive:**
- Count characters in the value
- Add that many asterisks

**If the key is not sensitive:**
- Add the value as-is

**Example:**
```
Key: "password"
Value: "secret" (length 6)
Is "password" in sensitiveKeys? YES
Add 6 asterisks: "******"

Line becomes: "password=******"
```

---

## Problem 6: HTML Validator

### The Problem
Check if HTML tags are properly matched (every opening tag has a closing tag in correct order).

**Valid:**
```html
<html><body><b>Hello</b></body></html>
```

**Invalid:**
```html
<html><body><b>Hello</body></b></html>
<!-- </b> comes after </body>, wrong order! -->
```

---

### The Solution Uses a Stack

**What's a stack?** A Last-In-First-Out (LIFO) data structure.

**Visual:**
```
Push (add to top):      Pop (remove from top):
    [b]                     [b] ← Remove this
    [body]                  [body]
    [html]                  [html]

                        Result: 'b'
```

---

### Step-by-Step

```java
String[] stack = new String[html.length()];
int size = 0;
```

**Create a stack (using array):**
- Array to store tag names
- `size` tracks how many tags are in the stack

---

```java
html = html.toLowerCase();
html = html.trim(); // Remove leading/trailing whitespace
```

**Normalize the input.**

---

```java
if (html.charAt(0) != '<' || html.charAt(html.length()-1) != '>') {
    return false;
}
```

**First and last characters must be `<` and `>`.**

---

```java
for (int i = 0; i < html.length(); i++) {
    if (html.charAt(i) == '<') {
        // Found opening bracket
        if (!foundEnd) {
            return false; // Previous tag not closed
        }
        first = i + 1;
        foundEnd = false;
```

**When we see `<`:**
- Check that previous tag was closed (foundEnd = true)
- Mark the start of a tag

---

```java
    } else if (html.charAt(i) == '>') {
        // Found closing bracket
        currentTag = html.substring(first, i);
        
        if (currentTag.endsWith("/")) {
            foundEnd = true;
            continue; // Self-closing tag, move on
        }
```

**When we see `>`:**
- Extract the tag name (between `<` and `>`)
- If it ends with `/` (like `<br />`), it's self-closing

---

```java
        if (currentTag.startsWith("/")) {
            // Closing tag: </html>
            if (size == 0 || !stack[size - 1].equals(currentTag.substring(1))) {
                return false; // Mismatched!
            }
            size--; // Pop from stack
        } else {
            // Opening tag: <html>
            if (size >= stack.length) {
                return false; // Stack overflow
            }
            stack[size] = currentTag;
            size++; // Push to stack
        }
        foundEnd = true;
    }
}
```

**Process the tag:**
- If closing tag (`</html>`):
  - Pop from stack
  - Check it matches the opening tag
- If opening tag (`<html>`):
  - Push onto stack

---

### Example Trace

```
Input: <html><body><b>Hello</b></body></html>

Character '<': first = 5, foundEnd = false
Character '>': currentTag = "html"
               Push "html" → stack = ["html"], size = 1

Character '<': first = 11, foundEnd = false
Character '>': currentTag = "body"
               Push "body" → stack = ["html", "body"], size = 2

Character '<': first = 16
Character '>': currentTag = "b"
               Push "b" → stack = ["html", "body", "b"], size = 3

Character '<': first = 22
Character '>': currentTag = "/b"
               Pop and check: stack[2] = "b" == "b"? YES!
               stack = ["html", "body"], size = 2

Character '<': first = 28
Character '>': currentTag = "/body"
               Pop and check: stack[1] = "body" == "body"? YES!
               stack = ["html"], size = 1

Character '<': first = 35
Character '>': currentTag = "/html"
               Pop and check: stack[0] = "html" == "html"? YES!
               stack = [], size = 0

End of input: size = 0? YES!
Return true ✓
```

---

## Summary

GP2 covers:
1. **Recursion vs Iteration:** Two ways to think about problems
2. **Sorting:** Understanding algorithm logic step-by-step
3. **String manipulation:** Substrings, character positions
4. **Regular expressions:** Pattern matching for structured data
5. **Data structures like stacks:** For matching/validation problems

Each solution shows a different problem-solving approach!
