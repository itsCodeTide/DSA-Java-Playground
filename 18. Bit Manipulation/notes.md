
# **Bit Manipulation**

Bit manipulation refers to the act of algorithmically modifying bits or binary digits, which are the most basic form of data in computing. These operations are performed at the bit-level and are extremely fast, often used to optimize performance or reduce memory usage in programs.

Bitwise operations are a key part of low-level programming and are especially useful in algorithm design, cryptography, compression, and competitive coding.

---

## **Why Bit Manipulation?**

* **Efficiency**: Bitwise operations are low-level and execute quickly.
* **Compact Code**: Often replaces complex logic with a single line.
* **Memory Optimization**: Helps in reducing memory footprint by packing data in bits.
* **Mathematical Tricks**: Used in clever ways to solve problems that involve binary representations (e.g., checking power of two, toggling bits).

---

## **Bitwise Operators in Java**

Java supports several bitwise operators. These operators work on the binary representations of integers.

### **1. AND Operator (`&`)**

Compares each bit of two numbers. Result is `1` only if both bits are `1`.

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a & b;  // 0001 -> 1
```

### **2. OR Operator (`|`)**

Compares each bit of two numbers. Result is `1` if **either** bit is `1`.

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a | b;  // 0111 -> 7
```

### **3. XOR Operator (`^`)**

Result is `1` only if the corresponding bits are **different**.

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a ^ b;  // 0110 -> 6
```

### **4. NOT Operator (`~`)**

Inverts each bit. Converts 1 to 0 and 0 to 1.

```java
int a = 5;     // 0101
int c = ~a;    // 1010 (in 32-bit: becomes -6 due to 2's complement)
```

> ⚠️ Note: `~` in Java works on signed 32-bit integers. Be cautious with sign bits.

---

## **Bit Shift Operators**

Java provides operators to shift bits left or right.

### **5. Left Shift Operator (`<<`)**

Shifts bits to the left, inserting 0 on the right.

```java
int a = 5;      // 0101
int b = a << 1; // 1010 -> 10
```

Shifting left by `n` is equivalent to multiplying by `2^n`.

### **6. Right Shift Operator (`>>`)**

Shifts bits to the right, maintaining the sign bit (sign-extended).

```java
int a = -8;     // 11111000 (signed 8-bit view)
int b = a >> 2; // 11111110 -> -2
```

### **7. Unsigned Right Shift Operator (`>>>`)**

Shifts bits to the right, **filling 0** on the left. Used for unsigned numbers.

```java
int a = -8;
int b = a >>> 2;  // Becomes a large positive number
```

---

## **Common Bit Manipulation Tricks**

1. **Check if a number is even or odd**

   ```java
   if ((num & 1) == 0) // even
   ```

2. **Check if a number is a power of 2**

   ```java
   if ((num & (num - 1)) == 0)
   ```

3. **Count set bits (Brian Kernighan's Algorithm)**

   ```java
   while (n > 0) {
       count++;
       n = n & (n - 1);
   }
   ```

4. **Toggle i-th bit**

   ```java
   num = num ^ (1 << i);
   ```

5. **Set the i-th bit**

   ```java
   num = num | (1 << i);
   ```

6. **Clear the i-th bit**

   ```java
   num = num & ~(1 << i);
   ```

---

## Understanding Bits and Binary Representation

First, let's establish what we're working with. Every integer in Java is stored as a sequence of bits (binary digits: 0s and 1s). For example, the number 5 is represented as `101` in binary, which means 1×2² + 0×2¹ + 1×2⁰ = 4 + 0 + 1 = 5.

```java
public class BitBasics {
    public static void main(String[] args) {
        int num = 5;
        System.out.println("Number: " + num);
        System.out.println("Binary: " + Integer.toBinaryString(num));
        // Output: Binary: 101
        
        // Java integers are 32-bit, so 5 is actually:
        // 00000000000000000000000000000101
    }
}
```

## Core Bitwise Operators

Think of bitwise operators as tools that work on individual bits rather than the whole number. Let me show you each operator with clear examples:

### AND Operator (&)

The AND operator returns 1 only when both bits are 1. It's like asking "are both conditions true?"

```java
public class BitwiseAND {
    public static void main(String[] args) {
        int a = 12; // Binary: 1100
        int b = 10; // Binary: 1010
        int result = a & b; // Binary: 1000 = 8
        
        System.out.println(a + " & " + b + " = " + result);
        System.out.println("Binary breakdown:");
        System.out.println("  " + Integer.toBinaryString(a) + " (12)");
        System.out.println("& " + Integer.toBinaryString(b) + " (10)");
        System.out.println("= " + Integer.toBinaryString(result) + " (" + result + ")");
    }
}
```

### OR Operator (|)

The OR operator returns 1 when at least one bit is 1. Think of it as "is either condition true?"

```java
public class BitwiseOR {
    public static void main(String[] args) {
        int a = 12; // Binary: 1100
        int b = 10; // Binary: 1010
        int result = a | b; // Binary: 1110 = 14
        
        System.out.println(a + " | " + b + " = " + result);
        // This combines all the 1-bits from both numbers
    }
}
```

### XOR Operator (^)

XOR (exclusive OR) returns 1 when bits are different. It's like asking "is exactly one condition true?"

```java
public class BitwiseXOR {
    public static void main(String[] args) {
        int a = 12; // Binary: 1100
        int b = 10; // Binary: 1010
        int result = a ^ b; // Binary: 0110 = 6
        
        System.out.println(a + " ^ " + b + " = " + result);
        
        // XOR has a special property: x ^ x = 0
        System.out.println("Self XOR: " + (a ^ a)); // Always 0
        
        // Another property: x ^ 0 = x
        System.out.println("XOR with 0: " + (a ^ 0)); // Always the original number
    }
}
```

## Bit Shifting Operations

Bit shifting is like moving all bits left or right, which has the effect of multiplying or dividing by powers of 2.

```java
public class BitShifting {
    public static void main(String[] args) {
        int num = 8; // Binary: 1000
        
        // Left shift: multiply by 2^n
        int leftShift = num << 2; // Shift 2 positions left
        System.out.println(num + " << 2 = " + leftShift); // 8 * 4 = 32
        System.out.println("Binary: " + Integer.toBinaryString(num) + 
                          " becomes " + Integer.toBinaryString(leftShift));
        
        // Right shift: divide by 2^n (integer division)
        int rightShift = num >> 1; // Shift 1 position right
        System.out.println(num + " >> 1 = " + rightShift); // 8 / 2 = 4
        
        // Unsigned right shift: always fills with 0s
        int negativeNum = -8;
        System.out.println("Signed shift: " + negativeNum + " >> 1 = " + (negativeNum >> 1));
        System.out.println("Unsigned shift: " + negativeNum + " >>> 1 = " + (negativeNum >>> 1));
    }
}
```

## Practical Bit Manipulation Techniques

Now let's explore some common patterns that you'll encounter in programming problems:

### Checking and Setting Individual Bits

```java
public class BitOperations {
    // Check if the i-th bit is set (counting from right, starting at 0)
    public static boolean isBitSet(int num, int position) {
        // Create a mask with only the position-th bit set
        int mask = 1 << position;
        // AND with the mask - result is non-zero if bit is set
        return (num & mask) != 0;
    }
    
    // Set the i-th bit to 1
    public static int setBit(int num, int position) {
        int mask = 1 << position;
        return num | mask; // OR ensures the bit becomes 1
    }
    
    // Clear the i-th bit (set it to 0)
    public static int clearBit(int num, int position) {
        int mask = ~(1 << position); // NOT flips all bits except our target
        return num & mask; // AND with inverted mask clears the bit
    }
    
    // Toggle the i-th bit
    public static int toggleBit(int num, int position) {
        int mask = 1 << position;
        return num ^ mask; // XOR flips the specific bit
    }
    
    public static void main(String[] args) {
        int num = 10; // Binary: 1010
        System.out.println("Original number: " + num + " (" + Integer.toBinaryString(num) + ")");
        
        // Check bits
        System.out.println("Bit 1 is set: " + isBitSet(num, 1)); // true (1010, second bit from right)
        System.out.println("Bit 2 is set: " + isBitSet(num, 2)); // false
        
        // Manipulate bits
        System.out.println("Set bit 2: " + setBit(num, 2) + " (" + 
                          Integer.toBinaryString(setBit(num, 2)) + ")"); // 1110 = 14
        System.out.println("Clear bit 1: " + clearBit(num, 1) + " (" + 
                          Integer.toBinaryString(clearBit(num, 1)) + ")"); // 1000 = 8
        System.out.println("Toggle bit 0: " + toggleBit(num, 0) + " (" + 
                          Integer.toBinaryString(toggleBit(num, 0)) + ")"); // 1011 = 11
    }
}
```

### Counting Set Bits

Here's a classic problem: count how many 1-bits are in a number.

```java
public class BitCounting {
    // Method 1: Simple approach - check each bit
    public static int countSetBits1(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) { // Check if the rightmost bit is 1
                count++;
            }
            n >>= 1; // Shift right to check next bit
        }
        return count;
    }
    
    // Method 2: Brian Kernighan's algorithm - more efficient
    public static int countSetBits2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // This clears the rightmost set bit
            count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        int num = 15; // Binary: 1111
        System.out.println("Number: " + num + " (" + Integer.toBinaryString(num) + ")");
        System.out.println("Set bits (method 1): " + countSetBits1(num));
        System.out.println("Set bits (method 2): " + countSetBits2(num));
        
        // Let's see why Brian Kernighan's algorithm works:
        int n = 12; // Binary: 1100
        System.out.println("\nTracing Brian Kernighan's algorithm for " + n + ":");
        int step = 1;
        while (n != 0) {
            System.out.println("Step " + step + ": " + Integer.toBinaryString(n) + " & " + 
                             Integer.toBinaryString(n-1) + " = " + Integer.toBinaryString(n & (n-1)));
            n = n & (n - 1);
            step++;
        }
    }
}
```

### Power of Two Detection

Detecting if a number is a power of 2 is a common bit manipulation problem with an elegant solution:

```java
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        // A power of 2 has exactly one bit set
        // For example: 8 = 1000, 16 = 10000
        // The trick: n & (n-1) == 0 for powers of 2
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    public static void main(String[] args) {
        int[] testNumbers = {1, 2, 3, 4, 5, 8, 16, 17, 32};
        
        for (int num : testNumbers) {
            System.out.println(num + " (" + Integer.toBinaryString(num) + 
                             ") is power of 2: " + isPowerOfTwo(num));
        }
        
        // Why does this work?
        // Powers of 2: 1, 2, 4, 8, 16... have exactly one bit set
        // When you subtract 1, all bits after (and including) that bit flip
        // 8 = 1000, 7 = 0111 -> 1000 & 0111 = 0000
        // 6 = 0110, 5 = 0101 -> 0110 & 0101 = 0100 (not zero!)
    }
}
```

### Finding Missing Number with XOR

Here's a beautiful application of XOR properties to solve a classic problem:

```java
public class MissingNumber {
    // Given an array containing n distinct numbers from 0 to n,
    // find the one missing number
    public static int findMissingNumber(int[] nums) {
        int n = nums.length;
        int result = n; // Start with the largest number that should be present
        
        // XOR all array elements with their indices
        for (int i = 0; i < n; i++) {
            result ^= i ^ nums[i];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 0, 1}; // Missing number is 2
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Missing number: " + findMissingNumber(nums));
        
        // Why this works:
        // We XOR all expected numbers (0 to n) with all present numbers
        // Since x ^ x = 0, all present numbers cancel out
        // Only the missing number remains!
        
        // Let's trace through:
        // Expected: 0, 1, 2, 3
        // Present: 3, 0, 1
        // result = 3 ^ 0 ^ 3 ^ 1 ^ 0 ^ 2 ^ 1
        // result = 3 ^ 3 ^ 0 ^ 0 ^ 1 ^ 1 ^ 2 = 2
    }
}
```

## Advanced Technique: Bit Masking

Bit masking is particularly useful when you need to represent multiple boolean states efficiently:

```java
public class BitMasking {
    // Permissions example using bit masks
    public static final int READ_PERMISSION = 1;    // 001
    public static final int WRITE_PERMISSION = 2;   // 010
    public static final int EXECUTE_PERMISSION = 4; // 100
    
    public static void main(String[] args) {
        // Grant read and execute permissions
        int permissions = READ_PERMISSION | EXECUTE_PERMISSION; // 001 | 100 = 101
        
        System.out.println("Permissions: " + Integer.toBinaryString(permissions));
        
        // Check specific permissions
        boolean canRead = (permissions & READ_PERMISSION) != 0;
        boolean canWrite = (permissions & WRITE_PERMISSION) != 0;
        boolean canExecute = (permissions & EXECUTE_PERMISSION) != 0;
        
        System.out.println("Can read: " + canRead);
        System.out.println("Can write: " + canWrite);
        System.out.println("Can execute: " + canExecute);
        
        // Add write permission
        permissions |= WRITE_PERMISSION; // Now has all permissions: 111
        System.out.println("After adding write: " + Integer.toBinaryString(permissions));
        
        // Remove execute permission
        permissions &= ~EXECUTE_PERMISSION; // Clear execute bit: 011
        System.out.println("After removing execute: " + Integer.toBinaryString(permissions));
    }
}
```

The beauty of bit manipulation lies in its efficiency and elegance. These operations are incredibly fast because they work directly with the computer's native binary representation. As you practice these techniques, you'll start to see patterns and develop an intuition for when bit manipulation can provide clean, efficient solutions to complex problems.

Would you like me to explore any specific bit manipulation pattern in more detail, or shall we look at how these techniques apply to solving particular algorithmic challenges?
