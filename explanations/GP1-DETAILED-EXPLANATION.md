# GP1 - Detailed Solutions Explained

## Problem 1: Age Finder

### The Problem
You're given a birthdate string like `"2000-01-01"` and you need to calculate how old the person is **today**.

### Step-by-Step Explanation

```java
public int calculateAge(String date) {
    try {
        // Line explanation below...
    }
}
```

**Why try-catch?** What if someone passes an invalid date like `"2000-13-45"`? The parsing will fail and crash the program. Try-catch prevents that crash and returns `-1` to indicate an error.

---

#### Step 1: Create a formatter to parse the string

```java
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
```

**What's happening here?**
- Java needs to know the **exact format** of your date string
- `"yyyy-MM-dd"` means: 4-digit year, dash, 2-digit month, dash, 2-digit day
- Examples:
  - ✓ `"2000-01-01"` - matches the pattern
  - ✗ `"01-01-2000"` - does NOT match (day first)
  - ✗ `"2000/01/01"` - does NOT match (slashes instead of dashes)

**Think of it like:** You're telling Java, "When you see text like `2000-01-01`, interpret it as a date."

---

#### Step 2: Convert the string to a LocalDate object

```java
LocalDate bd = LocalDate.parse(date, format);
```

**What's happening?**
- `LocalDate.parse()` is a function that takes:
  - Your date string (e.g., `"2000-01-01"`)
  - The formatter you just created
- It returns a `LocalDate` object that Java understands as a date

**Example:**
```
Input: "2000-01-01"
↓ (parse with formatter)
Output: LocalDate object representing January 1, 2000
```

**Why do this?** A string is just text. We need Java to understand it as a DATE so we can do math with it.

---

#### Step 3: Get today's date

```java
LocalDate today = LocalDate.now();
```

**What's happening?**
- `LocalDate.now()` automatically gets today's date from your computer
- If today is April 2, 2026, this returns a LocalDate representing April 2, 2026

**Real-world analogy:** It's like asking your phone, "What's today's date?" and it tells you.

---

#### Step 4: Calculate the difference

```java
Period diff = Period.between(bd, LocalDate.now());
```

**What's happening?**
- `Period.between()` calculates the difference between two dates
- It returns a `Period` object containing years, months, and days

**Example:**
```
Birthdate: January 1, 2000
Today: April 2, 2026

Period = 26 years, 3 months, 2 days
```

---

#### Step 5: Extract the years

```java
int ans = diff.getYears();
```

**What's happening?**
- `.getYears()` gets just the **years** part from the Period
- Ignores the months and days

**Example from above:**
```
Period = 26 years, 3 months, 2 days
↓ (get years only)
ans = 26
```

---

#### Step 6: Check for error

```java
if (ans < 0) {
    return -1;
}
```

**What's happening?**
- If the age is negative, that's impossible! (nobody has a negative age)
- This means the birthdate is in the future (like `"2030-01-01"`)
- Return `-1` to signal "error, invalid date"

**Example:**
```
If someone passes: "2030-01-01" (future date)
Then: ans = -4 (4 years in the future)
This is invalid, return -1
```

---

#### Step 7: Return the result

```java
return ans;
```

**What's happening?** Return the valid age.

---

### Why This Design?

| Decision | Reason |
|----------|--------|
| Use try-catch | Prevent crashes from bad input |
| Return -1 on error | Signal that something went wrong |
| Check for negative age | Catch impossible/future dates |
| Use Period API | Java's built-in date math is reliable |

---

### Full Code Flow - Example

```
Input: calculateAge("2000-01-01")

Step 1: format = "yyyy-MM-dd" pattern
Step 2: bd = January 1, 2000 (as LocalDate object)
Step 3: now = April 2, 2026
Step 4: diff = 26 years, 3 months, 2 days (as Period object)
Step 5: ans = 26 (extracted years)
Step 6: Is 26 < 0? No, continue
Step 7: return 26

Output: 26 years old
```

---

## Problem 2: Day Of Week Finder

### The Problem
Given a starting date and a day name (like "Monday"), find the **next** occurrence of that day.

**Example:**
```
Starting date: "2023-06-22" (a Thursday)
Looking for: "Monday"
Answer: "2023-06-27" (the next Monday)
```

---

### Step-by-Step Explanation

```java
public String findNextDayOfWeek(String startDate, String dayOfWeek) {
    try {
```

Again, we use try-catch because parsing could fail.

---

#### Step 1: Parse the starting date

```java
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate start = LocalDate.parse(startDate, format);
```

**Same as before:** Convert the string to a date object.

---

#### Step 2: Convert day name to DayOfWeek

```java
DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
```

**What's happening?**
- `.toUpperCase()` converts "Monday" → "MONDAY"
- `DayOfWeek.valueOf()` converts the string to a DayOfWeek object

**Why uppercase?** DayOfWeek enum expects all-caps: `MONDAY`, `TUESDAY`, etc.

**Example:**
```
Input: "Monday"
↓ (to uppercase)
"MONDAY"
↓ (valueOf)
DayOfWeek.MONDAY (Java's built-in representation)
```

---

#### Step 3: Get the starting date's day of week

```java
DayOfWeek startDay = start.getDayOfWeek();
```

**What's happening?**
- If the starting date is Thursday, this returns `DayOfWeek.THURSDAY`

**Example:**
```
start = June 22, 2023 (which is a Thursday)
↓
startDay = DayOfWeek.THURSDAY
```

---

#### Step 4: Calculate days to add

```java
int diff = targetDay.getValue() - startDay.getValue();
```

**What's happening?**
- Each day has a numeric value:
  - Monday = 1
  - Tuesday = 2
  - ...
  - Sunday = 7
- We subtract to find the difference

**Example:**
```
We want Monday (value 1)
Starting from Thursday (value 4)

diff = 1 - 4 = -3

Negative -3 means Monday is 3 days BACK (in the past)
```

---

#### Step 5: Handle negative difference

```java
if (diff <= 0) {
    diff = diff + 7;
}
```

**What's happening?**
- If `diff` is 0 or negative, the target day is in the past or is today
- We need to find the **next** occurrence
- Adding 7 jumps to next week

**Example from above:**
```
diff = -3 (Monday is 3 days back)
↓ (add 7)
diff = 4 (next Monday is 4 days forward)

So Thursday + 4 days = Monday (next week)
```

**Why not just add 7 if diff < 0?** Because `diff == 0` means today is the target day. We want the NEXT occurrence, so add 7.

---

#### Step 6: Add days to the starting date

```java
start = start.plusDays(diff);
```

**What's happening?**
- Move the date forward by `diff` days

**Example:**
```
start = Thursday, June 22
diff = 4
↓
start = Thursday + 4 days = Monday, June 26
```

---

#### Step 7: Return the result as string

```java
return start.toString();
```

**What's happening?**
- `.toString()` converts the LocalDate back to a string like `"2023-06-26"`

---

### Full Code Flow - Example

```
Input: findNextDayOfWeek("2023-06-22", "Monday")

Step 1: start = June 22, 2023
Step 2: targetDay = DayOfWeek.MONDAY
Step 3: startDay = Thursday (June 22 is a Thursday)
Step 4: diff = 1 (Monday) - 4 (Thursday) = -3
Step 5: Is -3 <= 0? Yes! So diff = -3 + 7 = 4
Step 6: start = June 22 + 4 days = June 26
Step 7: return "2023-06-26"

Output: "2023-06-26"
```

---

## Problem 3: Monthly Period

### The Problem
Given two dates, calculate the time between them in **years and months** (ignoring days).

**Example:**
```
Start: "2020-01-01"
End: "2023-06-15"
Answer: "3 years and 5 months"
```

---

### Key Code Section

```java
Period diff = Period.between(start, end);
int yInt = Math.abs(diff.getYears());
int mInt = Math.abs(diff.getMonths());
```

**Math.abs()** makes values positive. Why? Because if dates are reversed (end before start), the period could be negative. We want the absolute difference.

---

### Smart String Building

```java
String y = (yInt == 1) ? "1 year" : yInt + " years";
String m = (mInt == 1) ? "1 month" : mInt + " months";
```

**What's the `?` doing?** This is a **ternary operator** (conditional):
```java
(condition) ? valueIfTrue : valueIfFalse
```

**Why this?** Grammar matters!
- 1 year ✓ (singular)
- 2 years ✓ (plural)
- 1 month ✓ (singular)
- 2 months ✓ (plural)

**Example:**
```
If yInt = 1:
y = "1 year" (singular)

If yInt = 3:
y = "3 years" (plural)
```

---

### Assembling the final answer

```java
if (yInt == 0 && mInt == 0) return ans; // Empty string "no time"
if (yInt == 0) return m;                 // Just months
if (mInt == 0) return y;                 // Just years
ans = y + " and " + m;                   // Both
return ans;
```

**What's happening?**
- If both are 0: return empty string (same date)
- If only years have value: return years only
- If only months have value: return months only
- If both have value: combine with "and"

**Examples:**
```
yInt=0, mInt=0 → "      " (empty)
yInt=3, mInt=0 → "3 years"
yInt=0, mInt=5 → "5 months"
yInt=3, mInt=5 → "3 years and 5 months"
```

---

## Problem 4: Multiplication Table

### The Problem
Print the multiplication table for a given number (1-10).

**Example input:** 5
**Expected output:**
```
5 x 1 = 5
5 x 2 = 10
...
5 x 10 = 50
```

---

### The Solution (Simplest!)

```java
public static void generate(int num) {
    for (int i = 1; i <= 10; i++) {
        System.out.println(num + " x " + i + " = " + (num*i));
    }
}
```

**Why static?** Because this is a utility function - we don't need to create an object to use it. Just call `MultiplicationTable.generate(5)`.

---

### Breaking down the loop

```java
for (int i = 1; i <= 10; i++)
```

**What's happening:**
- Start: `i = 1`
- Condition: `i <= 10` (keep going while i is 10 or less)
- Increment: `i++` (add 1 to i each time)

**Loop iterations:**
```
Iteration 1: i=1
Iteration 2: i=2
Iteration 3: i=3
...
Iteration 10: i=10
Exit loop (11 > 10)
```

---

### Inside the loop

```java
System.out.println(num + " x " + i + " = " + (num*i));
```

**What's the `+` doing?** In Java, `+` with strings **concatenates** (joins) them:

```java
5 + " x " + 3 + " = " + (5*3)
↓
"5" + " x " + "3" + " = " + "15"
↓
"5 x 3 = 15"
```

**Why parentheses around `(num*i)`?** Without them:
```
num + " x " + i + " = " + num*i

Java would try: "... = " + num * i
This is ambiguous! Multiply first? Concatenate first?

With parentheses: (num*i) = clear: multiply first, then convert to string
```

---

### Full Example

```
generate(5)

Iteration 1: System.out.println(5 + " x " + 1 + " = " + 5)   → "5 x 1 = 5"
Iteration 2: System.out.println(5 + " x " + 2 + " = " + 10)  → "5 x 2 = 10"
Iteration 3: System.out.println(5 + " x " + 3 + " = " + 15)  → "5 x 3 = 15"
...
Iteration 10: System.out.println(5 + " x " + 10 + " = " + 50) → "5 x 10 = 50"
```

---

## Problem 5: Project Time Tracker

### The Problem
Given start and end times, calculate how many hours/minutes/days were logged and format appropriately.

**Examples:**
```
09:00 to 09:30 → "30 m" (30 minutes)
20:00 to 08:00 next day → "12 h" (12 hours)
24 hours exactly → "1 d" (1 day)
120+ days → "4 mo" (months)
```

---

### The Constructor

```java
public ProjectTime(String start, String end) {
    this.startTime = start;
    this.endTime = end;
    minDiff(); // Immediately calculate the difference
}
```

**What's happening:**
- Store both times
- Call `minDiff()` right away to calculate how much time passed

**Why call minDiff()?** Because any time someone creates a ProjectTime object or changes a time, we need to recalculate.

---

### The Core Calculation

```java
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
Date start = format.parse(this.startTime);
Date end = format.parse(this.endTime);
```

**What's happening:**
- Parse the string times into `Date` objects (Java's old date class)
- Format: `2023-05-14 09:00` (year, month, day, hour, minute)

---

### Calculate milliseconds difference

```java
Long milliS = start.getTime();
Long milliE = end.getTime();
Long diff = milliE - milliS;
```

**What's happening:**
- `.getTime()` returns milliseconds since Jan 1, 1970
- Subtracting gives the difference in milliseconds

**Example:**
```
Start: 2023-05-14 09:00 = 1684069200000 ms (since 1970)
End: 2023-05-14 09:30 = 1684071000000 ms
Difference = 1684071000000 - 1684069200000 = 1800000 ms
```

---

### Convert to minutes

```java
hoursLogged = diff / 60000f;
```

**What's happening:**
- 1 minute = 60 seconds = 60,000 milliseconds
- Dividing by 60,000 converts ms to minutes
- The `f` means use **float** (decimal number)

**Example from above:**
```
diff = 1800000 ms
hoursLogged = 1800000 / 60000 = 30 minutes
```

---

### Formatting the output

```java
public String getHoursLogged() {
    if (hoursLogged < 120) return hoursLogged + " m";        // Minutes
    if (hoursLogged < 7200) return (hoursLogged / 60) + " h"; // Hours
    if (hoursLogged < 172800) return (hoursLogged / 1440) + " d"; // Days
    return (hoursLogged / 43200) + " mo";                    // Months
}
```

**The conversions:**
```
60 minutes = 1 hour
1440 minutes = 24 hours = 1 day
43200 minutes = 30 days = 1 month (approximate)
```

**The logic:**
- If less than 120 minutes → show in minutes
- If less than 7200 minutes (120 hours) → show in hours
- If less than 172800 minutes (120 days) → show in days
- Otherwise → show in months

**Example:**
```
hoursLogged = 30 minutes
Is 30 < 120? YES → return "30 m"

hoursLogged = 150 minutes
Is 150 < 120? NO
Is 150 < 7200? YES → return (150/60) = 2.5 → "2 h"

hoursLogged = 7300 minutes (about 5 days)
Is 7300 < 120? NO
Is 7300 < 7200? NO
Is 7300 < 172800? YES → return (7300/1440) ≈ 5 → "5 d"
```

---

## Problem 6: Todo List

### The Problem
Build a task management system where:
- Each task has a description and status (NEW, IN_PROGRESS, COMPLETED)
- Store up to N tasks in a list
- Can add, update, and display tasks

---

### Task Class (Individual Task)

```java
public class Task {
    private String description;
    private TaskStatus status;
    
    public Task(String d) {
        this.description = d;
        this.status = TaskStatus.NEW; // New tasks start as NEW
    }
    
    // Getters and setters
    public String getDescription() { return this.description; }
    public void setDescription(String description) {
        this.description = description;
    }
    public TaskStatus getStatus() { return this.status; }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
```

**What's happening:**
- When you create a Task, it automatically sets status to NEW
- Getters let you READ values
- Setters let you CHANGE values

**Example:**
```java
Task t = new Task("Buy milk");
// t.description = "Buy milk"
// t.status = TaskStatus.NEW

t.setDescription("Buy bread");
// Now t.description = "Buy bread"
```

---

### TodoList Class (Manages multiple tasks)

```java
public class TodoList {
    private Task[] tasks;  // Array to hold tasks
    private int capacity;  // How many tasks can fit
    int count;             // How many tasks currently exist
    
    public TodoList(int capacity) {
        this.capacity = capacity;
        this.tasks = new Task[capacity]; // Create array of size capacity
        count = 0; // Start with 0 tasks
    }
}
```

**What's `Task[] tasks`?**
- An array of Task objects
- `new Task[3]` creates space for 3 Task objects
- Initially, all positions are empty (null)

**Visual:**
```
Capacity: 3
Array:    [ null | null | null ]
Count:    0
```

---

### Adding a Task

```java
public void addTask(String description) {
    if (this.count < capacity) {
        tasks[this.count] = new Task(description);
        this.count++;
    }
}
```

**What's happening:**
- Check: is there room? (`count < capacity`)
- Create new Task and put it at index `count`
- Increment count

**Example:**
```
Initial state:
tasks: [ null | null | null ]
count: 0

addTask("Buy milk"):
    if (0 < 3) { ✓
        tasks[0] = new Task("Buy milk")
        count = 1
    }
tasks: [ Task1 | null | null ]
count: 1

addTask("Pay bill"):
    if (1 < 3) { ✓
        tasks[1] = new Task("Pay bill")
        count = 2
    }
tasks: [ Task1 | Task2 | null ]
count: 2

addTask("Fix bug"):
    if (2 < 3) { ✓
        tasks[2] = new Task("Fix bug")
        count = 3
    }
tasks: [ Task1 | Task2 | Task3 ]
count: 3

addTask("Learn Java"):
    if (3 < 3) { ✗ Return without adding
    }
tasks: [ Task1 | Task2 | Task3 ] (unchanged!)
count: 3 (unchanged!)
```

---

### Updating Task Status

```java
public void setStatus(int index, TaskStatus status) {
    if (index >= 0 && index < this.count) {
        tasks[index].setStatus(status);
    }
}
```

**What's happening:**
- Check: is index valid? (between 0 and count-1)
- If yes: update that task's status
- If no: do nothing (silent failure)

**Example:**
```
tasks: [ Task1 | Task2 | Task3 ]
count: 3

setStatus(0, TaskStatus.COMPLETED):
    if (0 >= 0 && 0 < 3) { ✓
        tasks[0].setStatus(COMPLETED)
    }
    Task1 now has status: COMPLETED

setStatus(5, TaskStatus.COMPLETED):
    if (5 >= 0 && 5 < 3) { ✗ (5 is not < 3)
        // Nothing happens
    }
```

---

### Displaying All Tasks

```java
public void displayTasks() {
    System.out.println("Tasks:");
    for (int i = 0; i < this.count; i++) {
        System.out.printf("%-34s| %s\n", 
            tasks[i].getDescription(), 
            tasks[i].getStatus());
    }
}
```

**The printf formatting:**
- `%-34s` = left-align string in 34 characters
- `| %s\n` = pipe character, then the status, then newline

**Visual:**
```
Input task: "Buy milk"  |  Status: NEW
           34 spaces ↓
"Buy milk                         | NEW\n"
```

---

## Summary

All GP1 problems use:
1. **Strings**: Parsing formatted input
2. **Try-catch**: Handling errors gracefully
3. **Objects**: Encapsulating data with getters/setters
4. **Arrays**: Storing collections
5. **Loops**: Repeating operations
6. **Built-in APIs**: Date/Time, Period, Enums

The design philosophy: **Make invalid inputs impossible or handle them cleanly.**
