# ProjectTime Assignment Explanation

## What You Need to Build

You need to create a **ProjectTime** class that tracks how long a project takes from start to finish.

---

## Step-by-Step Breakdown

### **Step 1: Understand the Class Structure**

You're creating a class called `ProjectTime` with three attributes:
- `startTime` (String) - when the project started (e.g., "2023-05-14 09:00")
- `endTime` (String) - when the project ended (e.g., "2023-05-14 09:30")
- `hoursLogged` (float) - how much time passed between start and end

### **Step 2: Create the Constructor**

```java
public ProjectTime(String start, String end)
```

This method runs when you create a new ProjectTime object. It should:
1. Save the `start` parameter to `startTime`
2. Save the `end` parameter to `endTime`
3. Calculate how much time passed and save it to `hoursLogged`

### **Step 3: Implement Setter Methods**

**setStartTime(String newStart)** - Updates the start time and recalculates `hoursLogged`

**setEndTime(String newEnd)** - Updates the end time and recalculates `hoursLogged`

### **Step 4: Implement Getter Methods**

**getStartTime()** - Returns the start time as a String

**getEndTime()** - Returns the end time as a String

**getHoursLogged()** - Returns the duration in a human-readable format (this is the tricky one!)

### **Step 5: Calculate Time Difference**

Use the `SimpleDateFormat` class to:
1. Parse the start and end time strings into Date objects
2. Get the time in milliseconds for both dates
3. Subtract start from end to get the difference
4. Convert milliseconds to minutes/hours/days/months

### **Step 6: Format the Output**

Based on the duration, return different formats:

- **Less than 120 minutes**: "30 m" (show minutes)
- **Less than 120 hours** (but 120+ minutes): "12 h" (show hours)  
- **Less than 120 days** (but 120+ hours): "5 d" (show days)
- **120+ days**: "4 mo" (show months)

### **Step 7: Handle Errors**

If the date format is wrong (like "2023-05-14" without time), return "-1"

---

## Key Concepts You'll Use

1. **Classes and Objects** - ProjectTime is a blueprint, you create instances of it
2. **Constructors** - Initialize objects when created
3. **Getters/Setters** - Methods to access and modify private attributes
4. **Date Parsing** - Converting text dates to Date objects
5. **Exception Handling** - Using try-catch for ParseException
6. **Conditional Logic** - if/else to determine which format to use

---

## Example Flow

When someone writes:
```java
ProjectTime shortProject = new ProjectTime("2023-05-14 09:00", "2023-05-14 09:30");
```

Your code should:
1. Parse "2023-05-14 09:00" → Date object
2. Parse "2023-05-14 09:30" → Date object
3. Calculate difference: 30 minutes
4. Store 30 in `hoursLogged`
5. When `getHoursLogged()` is called, return "30 m"

---

## Time Conversion Reference

- 1 minute = 60,000 milliseconds
- 1 hour = 60 minutes
- 1 day = 24 hours
- 1 month ≈ 30 days (for this assignment)

---

## Tips for Implementation

1. Create a helper method to calculate `hoursLogged` from start and end times
2. Call this helper method in the constructor and in both setter methods
3. Use try-catch blocks to handle ParseException when dates are invalid
4. Test each format output (minutes, hours, days, months) to make sure your calculations are correct
