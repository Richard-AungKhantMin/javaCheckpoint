# DayOfWeekFinder Assignment - Step by Step Guide

## What You Need to Do

Given a starting date and a day of the week (like "Monday"), find the date of the **next** occurrence of that day.

**Example:** If today is Thursday (2023-06-22) and you ask for "Monday", it should return the next Monday (2023-06-26).

---

## Understanding the New Import: `java.time.DayOfWeek`

### What is `DayOfWeek`?

- `DayOfWeek` is an **enum** (a special type with fixed values) representing the 7 days of the week
- Values: `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
- Used to work with days of the week in calculations

### How to Use It

```java
// Convert a string to DayOfWeek enum
DayOfWeek targetDay = DayOfWeek.valueOf("MONDAY");  // Note: UPPERCASE

// Get the day of week from a LocalDate
LocalDate date = LocalDate.parse("2023-06-22");
DayOfWeek currentDay = date.getDayOfWeek();  // Returns THURSDAY

// Get numeric value (Monday=1, Tuesday=2, ..., Sunday=7)
int dayNumber = DayOfWeek.MONDAY.getValue();  // Returns 1
```

---

## Step-by-Step Solution

### **Step 1: Parse the Start Date**

Convert the String date to a LocalDate:

```java
LocalDate start = LocalDate.parse(startDate);  // "2023-06-22" → LocalDate
```

---

### **Step 2: Convert Day of Week String to Enum**

The input is "Monday" but the enum needs "MONDAY" (uppercase):

```java
DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
```

**Important:** This will throw an exception if the day name is invalid (like "Funday"), which we'll catch later.

**Why `.toUpperCase()`?**
- User input: "Monday" (mixed case)
- Enum values: `MONDAY` (all uppercase)
- We convert to match the enum format

---

### **Step 3: Get the Current Day of Week**

Find out what day of the week the start date is:

```java
DayOfWeek currentDay = start.getDayOfWeek();
```

For "2023-06-22", this returns `THURSDAY`.

---

### **Step 4: Calculate Days Until Target Day**

We need to find how many days to add to reach the next occurrence of the target day.

**The logic:**
- Each day has a numeric value: Monday=1, Tuesday=2, ..., Sunday=7
- Calculate the difference between target and current
- If the difference is 0 or negative, add 7 (to get NEXT week's occurrence)

```java
int currentDayValue = currentDay.getValue();    // e.g., 4 for Thursday
int targetDayValue = targetDay.getValue();      // e.g., 1 for Monday

int daysToAdd = targetDayValue - currentDayValue;

// If target day is today or already passed this week, go to next week
if (daysToAdd <= 0) {
    daysToAdd += 7;
}
```

**Examples:**
- Thursday (4) → Monday (1): difference = 1 - 4 = -3, then +7 = **4 days**
- Thursday (4) → Friday (5): difference = 5 - 4 = **1 day**
- Thursday (4) → Sunday (7): difference = 7 - 4 = **3 days**
- Thursday (4) → Thursday (4): difference = 4 - 4 = 0, then +7 = **7 days** (next week!)

---

### **Step 5: Add Days to Start Date**

Use the `plusDays()` method to move forward in time:

```java
LocalDate nextDate = start.plusDays(daysToAdd);
```

This automatically handles month/year changes. If you're at June 28 and add 5 days, it becomes July 3.

---

### **Step 6: Format the Result**

Convert the LocalDate back to a String in "yyyy-MM-dd" format:

```java
return nextDate.toString();  // LocalDate.toString() gives "yyyy-MM-dd" format
```

**Note:** Unlike other date classes, `LocalDate.toString()` already returns the format we need!

---

### **Step 7: Handle Errors**

Wrap everything in a `try-catch` block to catch any exceptions and return "Error":

```java
try {
    // Your code here
} catch (Exception e) {
    return "Error";
}
```

**Possible errors:**
- Invalid date format: "invalid-date" → `DateTimeParseException`
- Invalid day of week: "Funday" → `IllegalArgumentException`

---

## Complete Example Walkthrough

**Input:** startDate = "2023-06-22", dayOfWeek = "Monday"

1. **Parse date:** start = June 22, 2023 (which is a Thursday)
2. **Convert day:** targetDay = MONDAY (enum value)
3. **Get current day:** currentDay = THURSDAY (value = 4)
4. **Calculate days:**
   - targetDayValue = 1 (Monday)
   - currentDayValue = 4 (Thursday)
   - daysToAdd = 1 - 4 = -3
   - Since -3 ≤ 0, add 7: daysToAdd = 4
5. **Add days:** June 22 + 4 days = June 26, 2023
6. **Format:** "2023-06-26"
7. **Return:** "2023-06-26"

---

## Why "Next" Means Skip Today

Notice in the logic: `if (daysToAdd <= 0)` includes 0.

This means **if the start date is already the target day, we still go to the NEXT occurrence** (7 days later).

**Example:** 
- Start: Monday, June 19, 2023
- Looking for: "Monday"
- daysToAdd = 1 - 1 = 0
- Since 0 ≤ 0, add 7: daysToAdd = 7
- Result: Next Monday (June 26, 2023), not today

This matches the word "next" in the method name: `findNextDayOfWeek`.

---

## Visual Day of Week Values

Understanding the numeric values helps with the calculation:

```
MONDAY    = 1
TUESDAY   = 2
WEDNESDAY = 3
THURSDAY  = 4
FRIDAY    = 5
SATURDAY  = 6
SUNDAY    = 7
```

**Why this matters:**
- If you're on Thursday (4) looking for Sunday (7): 7 - 4 = 3 days ahead
- If you're on Thursday (4) looking for Monday (1): 1 - 4 = -3, need next week (+7) = 4 days

---

## Key Java Methods Used

| Method | Purpose | Example |
|--------|---------|---------|
| `DayOfWeek.valueOf(string)` | Convert string to enum | `DayOfWeek.valueOf("MONDAY")` |
| `string.toUpperCase()` | Convert to uppercase | `"Monday".toUpperCase()` → "MONDAY" |
| `date.getDayOfWeek()` | Get day of week from date | Returns `THURSDAY` enum |
| `dayOfWeek.getValue()` | Get numeric value (1-7) | `MONDAY.getValue()` → 1 |
| `date.plusDays(n)` | Add days to a date | `date.plusDays(4)` |
| `date.toString()` | Convert to "yyyy-MM-dd" | "2023-06-22" |

---

## Common Mistakes to Avoid

### 1. **Forgetting to Convert to Uppercase**

```java
// ❌ WRONG - will throw exception
DayOfWeek day = DayOfWeek.valueOf("Monday");  // "Monday" doesn't match "MONDAY"

// ✅ CORRECT
DayOfWeek day = DayOfWeek.valueOf("Monday".toUpperCase());
```

### 2. **Not Handling Same Day**

```java
// ❌ WRONG - returns today if already the target day
if (daysToAdd < 0) {
    daysToAdd += 7;
}

// ✅ CORRECT - returns NEXT occurrence even if today
if (daysToAdd <= 0) {
    daysToAdd += 7;
}
```

### 3. **Manually Formatting the Date**

```java
// ❌ UNNECESSARY - too complicated
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
return nextDate.format(formatter);

// ✅ SIMPLER - toString() already gives yyyy-MM-dd
return nextDate.toString();
```

---

## Algorithm in Pseudocode

```
1. TRY:
   a. Parse startDate to LocalDate
   b. Convert dayOfWeek string to uppercase
   c. Convert to DayOfWeek enum
   d. Get current day of week from start date
   e. Calculate difference in day values
   f. IF difference <= 0:
         Add 7 to go to next week
   g. Add calculated days to start date
   h. Return formatted date string
2. CATCH any exception:
   Return "Error"
```

---

## Testing Different Scenarios

### Test Case 1: Future Day This Week
- Start: Thursday, June 22
- Target: Friday
- Result: June 23 (tomorrow)

### Test Case 2: Past Day (Next Week)
- Start: Thursday, June 22
- Target: Monday
- Result: June 26 (4 days ahead, next week)

### Test Case 3: Weekend Day
- Start: Thursday, June 22
- Target: Sunday
- Result: June 25 (3 days ahead)

### Test Case 4: Invalid Date
- Start: "invalid-date"
- Target: Monday
- Result: "Error"

### Test Case 5: Invalid Day Name
- Start: June 22, 2023
- Target: "Funday"
- Result: "Error"

---

## Why DateTimeFormatter Is Not Needed

The README shows this import, but we don't actually need it because:
- `LocalDate.parse()` understands "yyyy-MM-dd" by default
- `LocalDate.toString()` returns "yyyy-MM-dd" by default
- No custom formatting required!

You can remove it or keep it - it won't hurt anything, just unused.
