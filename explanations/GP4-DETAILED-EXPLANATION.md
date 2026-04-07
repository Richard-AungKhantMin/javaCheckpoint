# GP4 - Detailed Solutions Explained

## Problem 1: Harmonious Fusion (Merge Sorted Arrays)

### The Problem
You have two already-sorted arrays. Merge them into one sorted array.

**Example:**
```
arr1 = [1, 3, 5]      (sorted)
arr2 = [2, 4, 6]      (sorted)

Output = [1, 2, 3, 4, 5, 6]  (one merged sorted array)
```

---

## The Key Insight: Two Pointers

Instead of mixing arrays and sorting (slow!), use two pointers to walk through both arrays simultaneously.

**Visualization:**
```
arr1: [1, 3, 5]
      ↑ (i=0)

arr2: [2, 4, 6]
      ↑ (j=0)

result: []
```

---

### Step 1: Setup

```java
int[] result = new int[len1 + len2];
int i = 0;    // Pointer for arr1
int j = 0;    // Pointer for arr2
```

**Create result array** with size = both sizes combined.

---

### Step 2: Fill result by comparing

```java
for (int k = 0; k < len1 + len2; k++) {
    if ((j >= len2) || (i < len1 && arr1[i] <= arr2[j])) {
        result[k] = arr1[i];
        i++;
    } else if ((i >= len1) || (j < len2 && arr2[j] < arr1[i])) {
        result[k] = arr2[j];
        j++;
    }
}
```

**Loop through each position in result:**

---

### Understanding the Conditions

#### Condition 1: Pick from arr1
```java
if ((j >= len2) || (i < len1 && arr1[i] <= arr2[j]))
```

**This means:** Pick from arr1 if:
- Either arr2 is exhausted `(j >= len2)`, OR
- arr1 has elements AND arr1's current is smaller/equal

**Examples:**
```
arr1=[1, 3]  arr2=[2, 4]
i=0, j=0: arr1[0]=1, arr2[0]=2
          1 <= 2? YES → pick 1 from arr1
          
arr1=[1, 3]  arr2=[2]
i=0, j=1: j >= len2? YES (1 >= 1)
          arr2 is exhausted → pick remaining from arr1
```

---

#### Condition 2: Pick from arr2
```java
else if ((i >= len1) || (j < len2 && arr2[j] < arr1[i]))
```

**This means:** Pick from arr2 if:
- Either arr1 is exhausted `(i >= len1)`, OR
- arr2 has elements AND arr2's current is smaller

---

### Full Example Trace

```
arr1 = [1, 3, 5]
arr2 = [2, 4, 6]

result = [?, ?, ?, ?, ?, ?] (size 6)

k=0, i=0, j=0:
    arr1[0]=1, arr2[0]=2
    1 <= 2? YES → result[0] = 1, i++
    result = [1, ?, ?, ?, ?, ?]

k=1, i=1, j=0:
    arr1[1]=3, arr2[0]=2
    3 <= 2? NO
    2 < 3? YES → result[1] = 2, j++
    result = [1, 2, ?, ?, ?, ?]

k=2, i=1, j=1:
    arr1[1]=3, arr2[1]=4
    3 <= 4? YES → result[2] = 3, i++
    result = [1, 2, 3, ?, ?, ?]

k=3, i=2, j=1:
    arr1[2]=5, arr2[1]=4
    5 <= 4? NO
    4 < 5? YES → result[3] = 4, j++
    result = [1, 2, 3, 4, ?, ?]

k=4, i=2, j=2:
    arr1[2]=5, arr2[2]=6
    5 <= 6? YES → result[4] = 5, i++
    result = [1, 2, 3, 4, 5, ?]

k=5, i=3, j=2:
    i >= len1? YES (3 >= 3)
    arr1 exhausted → result[5] = arr2[2] = 6, j++
    result = [1, 2, 3, 4, 5, 6]

DONE! ✓
```

---

### Why This Works

**Key insight:** Both input arrays are already sorted.

By comparing "fronts" and picking the smaller, we maintain sortedness in the result.

**Time complexity:** O(n + m) - linear! (vs O((n+m)log(n+m)) if you sort)

---

## Problem 2: Distinct Substring Length

### The Problem
Find the longest substring with NO repeating characters.

**Examples:**
```
"abcabcbb" -> "abc" (length 3)
"bbbbb" -> "b" (length 1)
"pwwkew" -> "wke" (length 3)
```

---

## The Approach: Sliding Window

Think of a "window" that expands and shrinks.

```
"abcabcbb"
[abc]      -> length 3, then 'a' repeats
    [bca] -> sliding window moves right
      [cab]
        [ab] -> length 2
           [bc] -> length 2, etc.
```

---

### The Solution

```java
for (int start = 0; start < s.length(); start++) {
    result[start] = 0;
    biggestSubstring = String.valueOf(s.charAt(start));
    result[start]++;
```

**For each starting position:**
- Initialize biggestSubstring with first character
- Count it (result[start]++)

---

```java
    for (int i = start + 1; i < s.length(); i++) {
        String character = String.valueOf(s.charAt(i));
        
        if (biggestSubstring.contains(character)) {
            break;  // Hit a repeat, stop expanding
        }
        
        biggestSubstring += character;
        result[start]++;
    }
}
```

**Expand the window:**
- Look at next character
- If already exists in window, STOP (found repeat)
- Otherwise, add to window and increment count

---

### Full Example: "abcabcbb"

```
Start=0, start='a':
    window = "a" (length 1)
    i=1: 'b' in "a"? NO → window = "ab" (length 2)
    i=2: 'c' in "ab"? NO → window = "abc" (length 3)
    i=3: 'a' in "abc"? YES → BREAK
    result[0] = 3

Start=1, start='b':
    window = "b" (length 1)
    i=2: 'c' in "b"? NO → window = "bc" (length 2)
    i=3: 'a' in "bc"? NO → window = "bca" (length 3)
    i=4: 'b' in "bca"? YES → BREAK
    result[1] = 3

Start=2, start='c':
    window = "c" (length 1)
    i=3: 'a' in "c"? NO → window = "ca" (length 2)
    i=4: 'b' in "ca"? NO → window = "cab" (length 3)
    i=5: 'c' in "cab"? YES → BREAK
    result[2] = 3

Start=3, start='a':
    window = "a" (length 1)
    i=4: 'b' in "a"? NO → window = "ab" (length 2)
    i=5: 'c' in "ab"? NO → window = "abc" (length 3)
    i=6: 'b' in "abc"? YES → BREAK
    result[3] = 3

Start=4, start='b':
    window = "b" (length 1)
    i=5: 'c' in "b"? NO → window = "bc" (length 2)
    i=6: 'b' in "bc"? YES → BREAK
    result[4] = 2

Start=5, start='c':
    window = "c" (length 1)
    i=6: 'b' in "c"? NO → window = "cb" (length 2)
    i=7: 'b' in "cb"? YES → BREAK
    result[5] = 2

Start=6, start='b':
    window = "b" (length 1)
    i=7: 'b' in "b"? YES → BREAK
    result[6] = 1

Start=7, start='b':
    window = "b" (length 1)
    (no more characters)
    result[7] = 1

Maximum = 3 ✓
```

---

## Problem 3: First Unique Character

### The Problem
Find the first character that appears only once.

**Examples:**
```
"leetcode" -> 'l' (appears at position 0, never again)
"loveleetcode" -> 'v' (first unique char after checking all)
"aabbcc" -> '_' (all characters repeat)
```

---

### The Solution

```java
char first = s.charAt(0);

if (!s.substring(1).contains(String.valueOf(first))) {
    return first;  // First char is unique!
}
```

**Quick check:** If first character doesn't appear anywhere else, return it.

---

```java
for (int i = 0; i < s.length() - 1; i++) {
    char c = s.charAt(i);
    
    if (!s.substring(0, i).contains(String.valueOf(c)) &&
        !s.substring(i + 1).contains(String.valueOf(c))) {
        return c;  // Found first unique!
    }
}
```

**For each middle character:**
- Check substring BEFORE it
- Check substring AFTER it
- If char appears in neither → it's unique!

**Visual:**
```
s = "leetcode"
     01234567

i=2 (character 'e' at position 2):
    substring(0, 2) = "le"  - does "le" contain 'e'? YES
    So this 'e' is NOT unique, skip

i=3 (character 't'):
    substring(0, 3) = "lee" - does "lee" contain 't'? NO
    substring(4) = "code" - does "code" contain 't'? NO
    't' is unique! But it's not the FIRST unique...
    Wait, we need to find the FIRST, so keep checking

Actually, let's check 'l' first (at position 0):
i=0 (character 'l'):
    substring(0, 0) = "" - does "" contain 'l'? NO
    substring(1) = "eetcode" - does "eetcode" contain 'l'? NO
    'l' is unique! Return 'l' ✓
```

---

### Better Approach: Using HashMap

The above solution is O(n²) because `.contains()` scans.

Better O(n):
```java
Map<Character, Integer> freq = new HashMap<>();

// Count frequencies
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

// Find first with frequency 1
for (char c : s.toCharArray()) {
    if (freq.get(c) == 1) {
        return c;
    }
}

return '_'; // None found
```

**Why better?** HashMap `.get()` is O(1), no scanning needed.

---

## Problem 4: Top K Frequent Elements

### The Problem
Find K most frequent elements.

**Example:**
```
nums = [1, 1, 1, 2, 2, 3]
k = 2

Frequencies:
  1 appears 3 times
  2 appears 2 times
  3 appears 1 time

Top 2: [1, 2] (not 3)
```

---

## The Approach: Min-Heap

**Why heap?** To efficiently keep track of top K elements.

**Min-heap:** Smallest element at the root.

---

### Step 1: Count Frequencies

```java
Map<Integer, Integer> freq = new HashMap<>();
for (int num : nums) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}
```

Result: `{1: 3, 2: 2, 3: 1}`

---

### Step 2: Use Min-Heap to Track Top K

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>(
    (a, b) -> freq.get(a) - freq.get(b)
);
```

**The comparator:** Compare elements by their frequency.
- If freq[a] < freq[b], then a comes before b
- Min-heap: smallest frequency at root

---

```java
for (int num : freq.keySet()) {
    minHeap.offer(num);  // Add element
    
    if (minHeap.size() > k) {
        minHeap.poll();  // Remove smallest frequency
    }
}
```

**The logic:**
```
Process 1 (freq 3):
    heap = [1]
    size > k? NO

Process 2 (freq 2):
    heap = [1, 2]
    size > k? NO

Process 3 (freq 1):
    heap = [1, 2, 3]
    size > k? YES (3 > 2)
    Remove min: 3 has freq 1, smallest!
    heap = [1, 2]

Result: [1, 2] → frequencies [3, 2] ✓
```

---

### Why This Works

**Key insight:** A min-heap of size K automatically keeps the K largest.

If we add too many, the smallest (at root) gets removed.

```
Adding 4th element:
heap = [1, 2] (freqs 3, 2)

Add 3 (freq 1):
heap = [1, 2, 3]
size 3 > k (2)? YES
Remove root (3 has freq 1):
heap = [1, 2]

The element with smallest frequency is automatically removed!
```

---

## Problem 5: Check Anagram

### The Problem
Are two words anagrams?

**Definition:** Same letters, same quantities, different order.

**Examples:**
```
"listen" and "silent" → YES (same letters)
"apple" and "pale" → NO ('p' appears 2x in "apple", 1x in "pale")
```

---

### The Solution

```java
public boolean isAnagram(String str1, String str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();
    
    if (str1.length() != str2.length()) {
        return false;  // Different lengths, impossible
    }
```

**Different lengths** can't be anagrams → return false immediately.

---

```java
    for (int i = 0; i < str1.length(); i++) {
        if (!str2.contains(String.valueOf(str1.charAt(i)))) {
            return false;  // Character not found in str2
        }
    }
    return true;
}
```

**Check each character in str1:**
- If it appears in str2, continue
- If not, can't be anagram

---

### Issue with This Approach

**Problem:** It only checks if characters exist, not quantities!

```
str1 = "aab"
str2 = "ab"

length check: 3 != 2? Return false ✓ (avoided bug)

But what if:
str1 = "aab"
str2 = "abb"

length: 3 == 3 ✓
Check 'a' in "abb"? YES ✓
Check 'a' in "abb"? YES ✓
Check 'b' in "abb"? YES ✓
Return true ✗ WRONG!

"aab" has 2 a's and 1 b
"abb" has 1 a and 2 b's
NOT anagrams!
```

---

### Correct Solution (Using Frequency)

```java
Map<Character, Integer> freq = new HashMap<>();

// Count characters in str1
for (char c : str1.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

// Check against str2
for (char c : str2.toCharArray()) {
    if (!freq.containsKey(c) || freq.get(c) == 0) {
        return false;
    }
    freq.put(c, freq.get(c) - 1);  // Decrement count
}

return true;
```

**How it works:**
```
str1 = "aab"
freq = {a: 2, b: 1}

Check str2 = "abb":
  'a': in freq? YES, count=2. Decrement: {a: 1, b: 1}
  'b': in freq? YES, count=1. Decrement: {a: 1, b: 0}
  'b': in freq? YES, count=0. Return FALSE! ✓

Correct!
```

---

## Problem 6: Longest Common Prefix

### The Problem
What letters do all strings start with?

**Examples:**
```
["flower", "flow", "flight"]
All start with "fl" → "fl"

["dog", "racecar", "car"]
Different first letters → ""
```

---

### The Approach: Character-by-Character

```java
if (strs == null || strs.length == 0) {
    return "";
}
```

**Edge case:** Empty input.

---

```java
int firstIndex = findTheLongestIndex(strs);
String commonPrefix = strs[firstIndex];
```

**Optimization:** Start with the longest string.

Why? Prefix can't be longer than the shortest string, but we don't know which that is yet. The longest is fine to try.

---

```java
for (int i = 0; i < strs.length; i++) {
    for (int j = 0; j < strs[i].length(); j++) {
        if (j == 0 && commonPrefix.charAt(j) != strs[i].charAt(j)) {
            return "";  // First char doesn't match
        } else if (commonPrefix.length() - 1 < j) {
            break;  // commonPrefix is too short
        } else if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
            commonPrefix = commonPrefix.substring(0, j);  // Trim it
            break;
        }
    }
}
```

**Compare position by position:**

```
strs = ["flower", "flow", "flight"]
commonPrefix = "flower"

i=0 (str="flower"):
  j=0: commonPrefix[0]='f', str[0]='f'? YES
  j=1: commonPrefix[1]='l', str[1]='l'? YES
  j=2: commonPrefix[2]='o', str[2]='o'? YES
  j=3: commonPrefix[3]='w', str[3]='w'? YES
  j=4: commonPrefix[4]='e', str[4]='e'? YES
  j=5: commonPrefix[5]='r', str[5]='r'? YES
  (loop done)

i=1 (str="flow"):
  j=0: commonPrefix[0]='f', str[0]='f'? YES
  j=1: commonPrefix[1]='l', str[1]='l'? YES
  j=2: commonPrefix[2]='o', str[2]='o'? YES
  j=3: commonPrefix[3]='w', str[3]='w'? YES
  j=4: commonPrefix.length()-1=5, j=4? 5 < 4? NO, continue
       But str length is 4, str[j] doesn't exist!
       Actually, check: commonPrefix.length()-1 < j
       5 < 4? NO
       oh wait, that check is weird...
       
Actually let me reread the code:
    else if ((commonPrefix.length() - 1) < j) {
        break;
    }

At j=4, is (6-1) < 4? Is 5 < 4? NO
So we don't break...

Oh I see the issue. Let me re-examine by hand:
str = "flow" (length 4)

j=0,1,2,3: all match
j=4: j < strs[i].length() (4 < 4)? NO, inner loop exits!

i=2 (str="flight"):
  j=0: commonPrefix[0]='f', str[0]='f'? YES
  j=1: commonPrefix[1]='l', str[1]='l'? YES
  j=2: commonPrefix[2]='o', str[2]='i'? NO!
       Trim: commonPrefix = commonPrefix.substring(0, 2) = "fl"
       break

Return "fl" ✓
```

---

## Summary

GP4 covers **string and array algorithms:**

| Problem | Technique |
|---------|-----------|
| Merge sorted arrays | Two pointers |
| Longest substring | Sliding window |
| First unique char | Frequency counting |
| Top K frequent | Min-heap + HashMap |
| Anagram | Frequency comparison |
| Longest prefix | Character-by-character comparison |

**Key patterns:**
- **Two pointers:** Compare from different positions
- **Sliding window:** Expand/contract to find patterns
- **Frequency maps:** Count occurrences efficiently
- **Priority queues:** Keep ordered data efficiently

