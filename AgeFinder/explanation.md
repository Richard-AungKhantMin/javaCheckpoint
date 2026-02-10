# AgeFinder Assignment - Step by Step Guide

## What You Need to Do

Calculate someone's age based on their birth date and today's date.

---

## Understanding the Task

This is simpler than MonthlyPeriod because:
- **MonthlyPeriod:** Calculates period between ANY two dates you provide
- **AgeFinder:** Calculates period from birth date to TODAY (current date)

The key difference: You need to use `LocalDate.now()` to get the current date automatically!

---

## Step-by-Step Solution

### **Step 1: Parse the Birth Date**

Convert the String date into a `LocalDate` object:

```java
LocalDate birthDate = LocalDate.parse(date);  // "2000-01-01" → LocalDate
```

This converts the text "2000-01-01" into a date object representing January 1, 2000.

---

### **Step 2: Get Today's Date**

Get the current date using `LocalDate.now()`:

```java
LocalDate today = LocalDate.now();  // Gets the current system date
```

**Important:** This automatically gets whatever date it is when the program runs! So if you run it on February 10, 2026, `today` will be February 10, 2026.

---

### **Step 3: Calculate the Period**

Find the difference between birth date and today:

```java
Period period = Period.between(birthDate, today);
```

This calculates how much time has passed from the birth date until now.

---

### **Step 4: Extract the Age (Years Only)**

Get only the years component - that's the age!

```java
int age = period.getYears();
```

**Note:** We don't need months and days for age calculation. Just years.

---

### **Step 5: Handle Negative Ages**

The hint says "Have you ever seen someone with a negative age???"

This means: **If the birth date is in the future, return -1**

```java
if (age < 0) {
    return -1;
}
```

Or more concisely using a ternary operator:

```java
return age < 0 ? -1 : age;
```

---

### **Step 6: Handle Errors**

Wrap everything in try-catch and return -1 for invalid date formats:

```java
try {
    // Your code here
} catch (Exception e) {
    return -1;  // Return -1 for any parsing errors
}
```

---

## Example Walkthrough

**Scenario:** Today is February 10, 2026 and birth date is "2000-01-01"

1. **Parse birth date:**
   - birthDate = January 1, 2000

2. **Get today:**
   - today = February 10, 2026

3. **Calculate period:**
   - Period.between(birthDate, today) → 26 years, 1 month, 9 days

4. **Extract years:**
   - age = 26

5. **Check negative:**
   - 26 is not negative, so return 26

6. **Result:** 26

---

## Edge Cases to Handle

### **1. Future Birth Date**

```java
birthDate = "2030-01-01"  (in the future)
today = "2026-02-10"
period = -3 years...
age = -3
Return: -1  (no negative ages!)
```

### **2. Invalid Date Format**

```java
date = "not-a-date"
LocalDate.parse() throws Exception
Return: -1
```

### **3. Born Today**

```java
birthDate = today
period = 0 years, 0 months, 0 days
age = 0
Return: 0  (newborn baby!)
```

---

## Key Java Concepts Used

### `LocalDate.now()`

- Gets the current system date
- Changes based on when you run the program
- This is what makes it an "age calculator" instead of just a "period calculator"

```java
LocalDate today = LocalDate.now();  // Automatic!
```

### `Period.getYears()`

- Returns only the years component
- For age, we don't care about months/days, just years

```java
int age = period.getYears();  // Just the number of years
```

---

## Complete Algorithm

```
1. TRY to parse the birth date string
2. Get today's date using LocalDate.now()
3. Calculate period between birth date and today
4. Extract the years (age)
5. IF age is negative:
      Return -1 (future birth date)
6. ELSE:
      Return age
7. CATCH any errors:
      Return -1
```

---

## Why DateTimeFormatter is Not Needed

The import list includes `DateTimeFormatter`, but we don't use it because:
- `LocalDate.parse()` already understands "yyyy-MM-dd" format by default
- No custom formatting needed
- It's included in the template but can be removed

---

## Quick Reference

| Method | Purpose | Example |
|--------|---------|---------|
| `LocalDate.parse(date)` | Convert String to LocalDate | `LocalDate.parse("2000-01-01")` |
| `LocalDate.now()` | Get current date | `LocalDate.now()` |
| `Period.between(start, end)` | Calculate time difference | `Period.between(birth, today)` |
| `period.getYears()` | Get years component only | `period.getYears()` |

---

## Testing Tips

When you test, remember:
- The output will change based on the current date
- If today is Feb 10, 2026 and birth is Jan 1, 2000 → age is 26
- If you test tomorrow (Feb 11, 2026) with the same birth date → still 26
- If you test on Jan 2, 2027 → age becomes 27!

This is expected behavior - age calculators should reflect the current date!
