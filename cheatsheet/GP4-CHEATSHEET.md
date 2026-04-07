# GP4 Cheatsheet: String & Array Algorithms

## Key Concepts

### 1. **Arrays and Merging**

#### Merging Two Sorted Arrays
```java
public int[] merge(int[] arr1, int[] arr2) {
    int len1 = arr1.length;
    int len2 = arr2.length;
    int[] result = new int[len1 + len2];
    
    int i = 0;  // Pointer for arr1
    int j = 0;  // Pointer for arr2
    
    // Compare elements from both arrays
    for (int k = 0; k < len1 + len2; k++) {
        // If arr2 is exhausted OR arr1[i] is smaller
        if ((j >= len2) || (i < len1 && arr1[i] <= arr2[j])) {
            result[k] = arr1[i];
            i++;
        }
        // If arr1 is exhausted OR arr2[j] is smaller
        else if ((i >= len1) || (j < len2 && arr2[j] < arr1[i])) {
            result[k] = arr2[j];
            j++;
        }
    }
    return result;
}

// Example:
// arr1 = [1, 3, 5]
// arr2 = [2, 4, 6]
// Result = [1, 2, 3, 4, 5, 6]
```

**How it works:**
- Two pointers track position in each array
- Compare current elements, pick the smaller one
- Move that pointer forward
- When one array is exhausted, take remaining from other

**Time:** O(n + m) - Linear!

---

### 2. **String Problems Using HashMaps**

#### Character Frequency Counting
```java
// Count how many times each character appears
Map<Character, Integer> freq = new HashMap<>();

for (char c : string.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

// freq.get('a') = 3 means 'a' appears 3 times
```

#### Anagram Checking
Two words are anagrams if they have the same characters in same quantities.

```java
public boolean isAnagram(String str1, String str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();
    
    // Different lengths? Not anagrams
    if (str1.length() != str2.length()) {
        return false;
    }
    
    // Check every character in str1 exists in str2
    for (int i = 0; i < str1.length(); i++) {
        char c = str1.charAt(i);
        if (!str2.contains(String.valueOf(c))) {
            return false;
        }
    }
    return true; // All characters matched!
}

// "listen" and "silent"
// Check: l exists? ✓, i exists? ✓, s exists? ✓...
```

**⚠️ Note:** This simple method works if exact number of each character matches.
For stricter checking, use frequency maps:
```java
public boolean isAnagram(String str1, String str2) {
    if (str1.length() != str2.length()) return false;
    
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : str1.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    
    for (char c : str2.toCharArray()) {
        if (!freq.containsKey(c) || freq.get(c) == 0) {
            return false;
        }
        freq.put(c, freq.get(c) - 1);
    }
    return true;
}
```

---

### 3. **Sliding Window - Find Longest/Shortest Substring**

#### Longest Substring Without Repeating Characters
```java
public int maxLength(String s) {
    if (s == null || s.isEmpty()) {
        return 0;
    }
    
    int maxLen = 0;
    
    // Try starting from each position
    for (int start = 0; start < s.length(); start++) {
        String current = String.valueOf(s.charAt(start));
        
        // Extend from this start position
        for (int i = start + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If character repeats, stop extending
            if (current.contains(String.valueOf(c))) {
                break;
            }
            
            current += c;
        }
        
        // Track the longest
        maxLen = Math.max(maxLen, current.length());
    }
    
    return maxLen;
}

// Example: "abcabcbb"
// Start at 0: "abc" (length 3) - stops at second 'a'
// Start at 1: "bca" (length 3) - stops at second 'b'
// Start at 2: "cab" (length 3) - stops at second 'c'
// Start at 3: "abc" (length 3) - stops at second 'a'
// ...
// Maximum = 3
```

**How it works:**
- Start from each character
- Extend until you find a repeat
- Track the longest substring

**Time:** O(n²) - nested loops

---

### 4. **Finding Unique Characters**

#### First Non-Repeating Character
```java
public char findFirstUnique(String s) {
    if (s == null || s.isEmpty()) {
        return '_';
    }
    
    char first = s.charAt(0);
    
    // Check if first char appears again
    if (!s.substring(1).contains(String.valueOf(first))) {
        return first;
    }
    
    // Check middle characters
    for (int i = 0; i < s.length() - 1; i++) {
        char c = s.charAt(i);
        // Not in before AND not in after
        if (!s.substring(0, i).contains(String.valueOf(c)) &&
            !s.substring(i + 1).contains(String.valueOf(c))) {
            return c;
        }
    }
    
    // Check last character
    char last = s.charAt(s.length() - 1);
    if (!s.substring(0, s.length() - 1).contains(String.valueOf(last))) {
        return last;
    }
    
    return '_'; // No unique character found
}

// "leetcode"
// l: before="" ✗, after="eetcode" ✓ → return 'l'
```

**Better approach using HashMap:**
```java
public char findFirstUnique(String s) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    
    // Find first with frequency 1
    for (char c : s.toCharArray()) {
        if (freq.get(c) == 1) {
            return c;
        }
    }
    return '_';
}
```

---

### 5. **Priority Queues (Heaps) - Top K Elements**

#### Finding K Most Frequent Elements
```java
public List<Integer> findTopKFrequent(int[] nums, int k) {
    // Step 1: Count frequency of each number
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
    
    // Step 2: Use min-heap to keep top k elements
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(
        (a, b) -> freq.get(a) - freq.get(b) // Compare by frequency
    );
    
    // Step 3: Add frequencies, keeping only k largest
    for (int num : freq.keySet()) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll(); // Remove smallest frequency
        }
    }
    
    // Step 4: Extract results
    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
        result.add(minHeap.poll());
    }
    Collections.reverse(result); // Reverse to get largest first
    return result;
}

// nums = [1,1,1,2,2,3], k = 2
// Frequencies: {1:3, 2:2, 3:1}
// Keep only k=2 largest: 1(freq 3), 2(freq 2)
// Result: [1, 2]
```

**How Priority Queue works:**
- `offer()` - add element (maintains heap)
- `poll()` - remove smallest/root
- `peek()` - view smallest without removing

---

### 6. **String Comparison Problems**

#### Longest Common Prefix
```java
public String findLongestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
        return "";
    }
    
    // Start with longest string for efficiency
    int longestIdx = 0;
    for (int i = 1; i < strs.length; i++) {
        if (strs[i].length() > strs[longestIdx].length()) {
            longestIdx = i;
        }
    }
    
    String common = strs[longestIdx];
    
    // Compare with each string
    for (int i = 0; i < strs.length; i++) {
        // Compare character by character
        for (int j = 0; j < strs[i].length(); j++) {
            if (j == 0 && common.charAt(0) != strs[i].charAt(0)) {
                return ""; // First char doesn't match
            } else if (j >= common.length() || 
                       common.charAt(j) != strs[i].charAt(j)) {
                // Mismatch found, reduce prefix
                common = common.substring(0, j);
                break;
            }
        }
    }
    return common;
}

// ["flower", "flow", "flight"]
// Start with "flower"
// Compare with "flow": "fl" common (matches up to position 2)
// Compare with "flight": "fl" common
// Result: "fl"
```

---

### 7. **Palindrome and Mirror Checks**

#### Simple Palindrome Check
```java
public boolean isPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
            return false;
        }
    }
    return true;
}

// "racecar"
// Compare: pos 0↔6, pos 1↔5, pos 2↔4
// r==r, a==a, c==c → True
```

---

## Quick Algorithm Reference

| Problem | Approach | Time |
|---------|----------|------|
| Merge sorted arrays | Two pointers | O(n+m) |
| Longest substring unique | Sliding window | O(n²) |
| First unique char | Char frequency | O(n) |
| Top K frequent | Min heap + HashMap | O(n log k) |
| Longest common prefix | Compare character by character | O(n*m) |
| Palindrome check | Compare from ends inward | O(n) |
| Anagram check | Sort or frequency map | O(n log n) or O(n) |

---

## Common Collections Used

```java
// HashMap - key-value pairs, O(1) lookup
Map<Integer, Integer> freq = new HashMap<>();

// ArrayList - dynamic array, O(1) access, O(n) insert
List<Integer> result = new ArrayList<>();

// PriorityQueue - heap, O(log n) add/remove
PriorityQueue<Integer> heap = new PriorityQueue<>();

// HashSet - unique values only, O(1) lookup
Set<Character> seen = new HashSet<>();
```

---

## Pro Tips

1. **String.substring() creates new string** - O(k) where k is length
2. **Use HashMap for frequency** - Much faster than multiple contains() calls
3. **PriorityQueue is a min-heap by default** - Use comparator to customize
4. **Collections.reverse()** - Reverses list after extracting from heap
5. **Character operations:**
   ```java
   String.valueOf(char c) // char to String
   char c = s.charAt(i)   // String to char
   ```

