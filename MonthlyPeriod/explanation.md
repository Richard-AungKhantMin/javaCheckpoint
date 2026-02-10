# MonthlyPeriod Assignment - Step by Step Guide

## What You Need to Build

Create a `MonthlyPeriod` class with a method that calculates the time difference between two dates and returns it as a formatted string like "3 years and 5 months".

---

## Understanding the Imports

### 1. `java.time.LocalDate`

**What it is:**
- `LocalDate` represents a date without time (just year, month, and day)
- Think of it like a calendar date: "2023-06-15"
- Unlike the old `Date` class, it's simpler and doesn't deal with hours/minutes/seconds

**How to use it:**

```java
// Parse a string into a LocalDate
LocalDate date = LocalDate.parse("2023-06-15");

// The default format it understands is "yyyy-MM-dd"
// This creates a LocalDate object representing June 15, 2023
```

**Why it helps:**
- You receive dates as Strings: "2020-01-01"
- You need to convert them to LocalDate objects to calculate the difference
- Much easier to use than the old `Date` class!

---

### 2. `java.time.Period`

**What it is:**
- `Period` represents a duration in terms of **years, months, and days**
- It's the difference between two dates
- Example: "3 years, 5 months, and 14 days"

**How to use it:**

```java
LocalDate start = LocalDate.parse("2020-01-01");
LocalDate end = LocalDate.parse("2023-06-15");

// Calculate the period between two dates
Period period = Period.between(start, end);

// Extract individual components
int years = period.getYears();    // Gets the years part
int months = period.getMonths();  // Gets the months part
int days = period.getDays();      // Gets the days part
```

**Why it helps:**
- Automatically calculates the difference between two dates
- Breaks it down into years, months, and days for you
- No need to do complex date math yourself!

---

### 3. `java.time.format.DateTimeFormatter`

**What it is:**
- `DateTimeFormatter` is used to parse dates in **custom formats**
- Similar to `SimpleDateFormat` but more modern and thread-safe
- For this assignment, you might not need it since the default format is already "yyyy-MM-dd"

**How to use it:**

```java
// Create a formatter for a specific pattern
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// Use it to parse a date
LocalDate date = LocalDate.parse("2020-01-01", formatter);

// But since "yyyy-MM-dd" is the default, you can also just do:
LocalDate date = LocalDate.parse("2020-01-01");  // Works without formatter!
```

**Why it's included:**
- Handles custom date formats if needed
- For this assignment, the default format works, so you may not need to use it explicitly

---

## Step-by-Step Solution

### **Step 1: Parse the Input Dates**

Convert the String dates into `LocalDate` objects:

```java
LocalDate start = LocalDate.parse(startDate);  // "2020-01-01" → LocalDate
LocalDate end = LocalDate.parse(endDate);      // "2023-06-15" → LocalDate
```

### **Step 2: Calculate the Period**

Use `Period.between()` to find the difference:

```java
Period period = Period.between(start, end);
```

This automatically calculates: years, months, and days between the two dates.

### **Step 3: Extract Years and Months**

Get the individual components:

```java
int years = period.getYears();
int months = period.getMonths();
```

**Important:** You only need years and months for this assignment (ignore days).

### **Step 4: Format the Output**

Create the proper string format based on the values:

- **0 years, 0 months:** (shouldn't happen based on examples)
- **0 years, X months:** "X months" or "X month"
- **X years, 0 months:** "X years" or "X year"
- **X years, Y months:** "X years and Y months" (handle singular/plural)

**Examples:**
- 3 years and 5 months → "3 years and 5 months"
- 1 year and 1 month → "1 year and 1 month" (singular!)
- 4 months → "4 months"
- 3 years → "3 years"

### **Step 5: Handle Errors**

Wrap everything in a `try-catch` block to catch parsing errors:

```java
try {
    // Your code here
} catch (Exception e) {
    return "Error";
}
```

---

## Example Walkthrough

Let's trace through: `calculatePeriod("2020-01-01", "2023-06-15")`

1. **Parse dates:**
   - start = LocalDate of January 1, 2020
   - end = LocalDate of June 15, 2023

2. **Calculate period:**
   - `Period.between(start, end)` → 3 years, 5 months, 14 days

3. **Extract values:**
   - years = 3
   - months = 5

4. **Format output:**
   - years > 1, so "years" (plural)
   - months > 1, so "months" (plural)
   - Result: "3 years and 5 months"

---

## Key Logic Patterns

### Handling Singular vs Plural

```java
// For years
if (years == 1) {
    yearsText = "1 year";
} else {
    yearsText = years + " years";
}

// Similar for months
```

### Handling Different Cases

1. **Both years and months:** "3 years and 5 months"
2. **Only years:** "3 years"
3. **Only months:** "4 months"
4. **Going backwards** (start > end): Period still works! It gives negative values

---

## Important Notes

- `Period.between(start, end)` works even if end is before start (negative period)
- The `getYears()` and `getMonths()` methods return the **components**, not total months
  - Example: 1 year and 2 months → years=1, months=2 (NOT months=14)
- Always handle singular vs plural for proper grammar
- Use try-catch to return "Error" for invalid date formats

---

## Quick Reference: What Each Tool Does

| Tool | Purpose | Example |
|------|---------|---------|
| `LocalDate` | Represents a date | `LocalDate.parse("2020-01-01")` |
| `Period` | Difference between dates | `Period.between(start, end)` |
| `DateTimeFormatter` | Custom date formats | Usually not needed for "yyyy-MM-dd" |

---

## The Algorithm in Pseudocode

```
1. TRY to parse both dates
2. Calculate period between them
3. Get years and months from period
4. IF years > 0 AND months > 0:
      return "X year(s) and Y month(s)"
5. ELSE IF only years > 0:
      return "X year(s)"
6. ELSE IF only months > 0:
      return "X month(s)"
7. CATCH any errors:
      return "Error"
```

Remember to handle singular forms (1 year, not 1 years)!
