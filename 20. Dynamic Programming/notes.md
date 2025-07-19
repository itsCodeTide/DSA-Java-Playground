
# 🧠 **Dynamic Programming (DP) – Complete Guide for Interviews**

---

## 📘 **What is Dynamic Programming?**

**Dynamic Programming** is a technique for solving complex problems by **breaking them down into simpler subproblems**, solving each subproblem **only once**, and **storing** their solutions for future use (usually in a table).

DP is used when:

* The problem has **overlapping subproblems**
* The problem has **optimal substructure**

---

## 🔍 **Why DP is Important in Interviews**

* Common in **FAANG-level interviews**
* Tests **problem-solving + optimization** skills
* Appears in **graph, string, and game theory** problems
* Often comes after array or recursion-based problems

---

## 💡 **Two Key Properties**

### ✅ **1. Overlapping Subproblems**

* Solving the same problem multiple times
* DP stores results to avoid recomputation

### ✅ **2. Optimal Substructure**

* The optimal solution to the problem can be constructed from the optimal solutions of its subproblems

---

## 🧱 **Approaches to Solve DP Problems**

### 🔁 1. **Top-Down (Memoization)**

* Recursive approach with caching
* Uses extra memory for the call stack

```java
int fib(int n, int[] dp) {
    if (n <= 1) return n;
    if (dp[n] != -1) return dp[n];
    return dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
}
```

### 🔃 2. **Bottom-Up (Tabulation)**

* Iterative approach
* Builds up from base cases

```java
int fib(int n) {
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

### 🧠 3. **Space Optimization**

* Only store the last few values if full table is unnecessary

```java
int fib(int n) {
    int a = 0, b = 1, c;
    for (int i = 2; i <= n; i++) {
        c = a + b;
        a = b;
        b = c;
    }
    return b;
}
```

---

## 🔥 **Most Important DP Patterns & Problems**

| Category                | Example Problems                       |
| ----------------------- | -------------------------------------- |
| **Fibonacci-type**      | Fibonacci, Climbing Stairs             |
| **0/1 Knapsack**        | Subset Sum, Partition Equal Subset     |
| **Unbounded Knapsack**  | Coin Change, Rod Cutting               |
| **LIS/Sequence**        | Longest Increasing Subsequence, LCS    |
| **DP on Grid**          | Unique Paths, Minimum Path Sum         |
| **Palindrome DP**       | Longest Palindromic Substring          |
| **String Matching**     | Edit Distance, Regex Matching          |
| **Digit DP** (Advanced) | Count numbers with specific properties |
| **Bitmask DP**          | Traveling Salesman Problem             |
| **Tree DP**             | Max sum on tree paths                  |
| **DP + Bitmask**        | TSP, Minimum Assignment Cost           |


## 🧩 **How to Recognize a DP Problem**

Ask yourself:

* Can the problem be broken into subproblems?
* Do the subproblems overlap?
* Is there a recursive brute-force approach that leads to TLE?
* Is optimization needed based on previous computations?

---

## 🧠 **Steps to Solve a DP Problem**

1. **Identify the problem type** (e.g. Knapsack, Subsequence)
2. **Define the state** – What parameters define a subproblem?
3. **Define the recurrence** – How is the current state built?
4. **Implement memoization or tabulation**
5. **Optimize space if needed**

## The Core Insight: Why Dynamic Programming Exists

Imagine you're asked to calculate the 50th Fibonacci number. You know that each Fibonacci number is the sum of the two preceding ones: F(n) = F(n-1) + F(n-2). 

If you solve this naively with recursion, something interesting happens. To calculate F(5), you need F(4) and F(3). But to calculate F(4), you also need F(3) and F(2). Notice how F(3) gets calculated multiple times? As the numbers get larger, this redundancy becomes massive - you end up recalculating the same values thousands of times.

```java
// This naive approach recalculates the same values repeatedly
public static int fibNaive(int n) {
    if (n <= 1) return n;
    return fibNaive(n-1) + fibNaive(n-2); // F(3) calculated many times!
}
```

Dynamic Programming recognizes this waste and says: "What if we remember the answers we've already computed?" This simple insight transforms an exponential-time algorithm into a linear-time one.

## The Two Faces of Dynamic Programming

DP can be implemented in two fundamental ways, and understanding both will give you flexibility in solving problems.

### Memoization: The "Remember as You Go" Approach

Memoization is like having a smart assistant who takes notes. As you solve the problem recursively, your assistant writes down each answer. Before solving any subproblem, you check your notes first.

```java
Map<Integer, Integer> memo = new HashMap<>();

public static int fibMemo(int n) {
    if (memo.containsKey(n)) {
        return memo.get(n); // Found it in our notes!
    }
    
    if (n <= 1) return n;
    
    int result = fibMemo(n-1) + fibMemo(n-2);
    memo.put(n, result); // Write it down for next time
    return result;
}
```

The beauty of memoization is that it preserves the natural recursive structure of your thinking. You solve the problem exactly as you would naturally, but with the added intelligence of memory.

### Tabulation: The "Build from the Ground Up" Approach

Tabulation takes a different philosophy. Instead of starting with the big problem and breaking it down, you start with the smallest problems and build your way up to the answer.

```java
public static int fibTable(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n + 1];
    dp[0] = 0; // We know these base cases
    dp[1] = 1;
    
    // Build up from the bottom
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2]; // Use previously computed values
    }
    
    return dp[n];
}
```

Think of tabulation like filling in a spreadsheet where each cell depends on cells you've already filled. You work systematically from the simplest cases to more complex ones.

## Identifying When to Use Dynamic Programming

Not every problem needs DP. The technique shines when you encounter these two key characteristics:

**Overlapping Subproblems**: The same smaller problems appear multiple times when solving the larger problem. This is where the "caching" aspect of DP provides value.

**Optimal Substructure**: The optimal solution to a problem can be constructed from optimal solutions to its subproblems. This means that if you know the best way to solve smaller pieces, you can combine them to get the best solution for the whole problem.

Let me show you this with a classic example: the Coin Change problem.

## The Coin Change Problem: A Perfect DP Example

Suppose you want to make change for 11 dollars using coins of denominations 1, 4, and 5 dollars. What's the minimum number of coins needed?

Let's think about this step by step. To make 11 dollars, you could:
- Use a 1-dollar coin, then solve for the remaining 10 dollars
- Use a 4-dollar coin, then solve for the remaining 7 dollars  
- Use a 5-dollar coin, then solve for the remaining 6 dollars

The minimum coins for 11 dollars equals 1 plus the minimum coins for the best remaining amount.

```java
public static int coinChange(int[] coins, int amount) {
    // Create a table where dp[i] = min coins needed for amount i
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1); // Initialize with impossible value
    dp[0] = 0; // Base case: 0 coins needed for amount 0
    
    // For each amount from 1 to our target
    for (int i = 1; i <= amount; i++) {
        // Try each coin denomination
        for (int coin : coins) {
            if (coin <= i) { // Can we use this coin?
                // Update if this gives us fewer coins
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}
```

Notice the structure: we build up solutions for smaller amounts and use them to solve larger amounts. Each entry in our table represents the solution to a subproblem.

## The Knapsack Problem: Understanding Value Optimization

Let's explore another classic: the 0/1 Knapsack problem. You have a backpack that can carry a certain weight, and you have items with different weights and values. You want to maximize the value while staying within the weight limit.

The key insight is that for each item, you have two choices: take it or leave it. The optimal solution considers both possibilities and picks the better one.

```java
public static int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    // dp[i][w] = max value using first i items with weight limit w
    int[][] dp = new int[n + 1][capacity + 1];
    
    for (int i = 1; i <= n; i++) {
        for (int w = 1; w <= capacity; w++) {
            // Option 1: Don't take the current item
            dp[i][w] = dp[i-1][w];
            
            // Option 2: Take the current item (if it fits)
            if (weights[i-1] <= w) {
                int valueWithItem = values[i-1] + dp[i-1][w - weights[i-1]];
                dp[i][w] = Math.max(dp[i][w], valueWithItem);
            }
        }
    }
    
    return dp[n][capacity];
}
```

This two-dimensional table captures all the subproblems we need to solve. Each cell represents the best we can do with a certain number of items and a certain weight limit.

## Space Optimization: The Art of Efficiency

Once you understand the basic DP approach, you can often optimize space usage. Notice that in many problems, you only need the previous row or the previous few values to compute the current one.

For Fibonacci, instead of storing all values, you only need the last two:

```java
public static int fibOptimized(int n) {
    if (n <= 1) return n;
    
    int prev2 = 0, prev1 = 1, current;
    
    for (int i = 2; i <= n; i++) {
        current = prev1 + prev2;
        prev2 = prev1;  // Slide the window
        prev1 = current;
    }
    
    return prev1;
}
```

For the knapsack problem, you can optimize from 2D to 1D by processing weights in reverse order:

```java
public static int knapsackOptimized(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];
    
    for (int i = 0; i < weights.length; i++) {
        // Process in reverse to avoid using updated values
        for (int w = capacity; w >= weights[i]; w--) {
            dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
        }
    }
    
    return dp[capacity];
}
```

## Common DP Patterns You Should Recognize

As you practice DP problems, you'll start noticing recurring patterns:

**Linear DP**: Problems where each state depends on a few previous states in a sequence. Examples include climbing stairs, house robber, and longest increasing subsequence.

**Grid DP**: Problems involving 2D movement or choices, like unique paths in a grid or edit distance between strings.

**Interval DP**: Problems where you solve smaller intervals first, then combine them. Matrix chain multiplication is a classic example.

**Tree DP**: Problems on trees where you solve subtrees first, then combine results at each node.

## The Longest Common Subsequence: A String DP Classic

Let me show you how DP applies to string problems with the Longest Common Subsequence (LCS) problem. Given two strings, find the length of their longest common subsequence.

```java
public static int lcs(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    // dp[i][j] = LCS length for first i chars of text1 and first j chars of text2
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                // Characters match - extend the LCS
                dp[i][j] = 1 + dp[i-1][j-1];
            } else {
                // Characters don't match - take the better option
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    
    return dp[m][n];
}
```

The elegance here lies in how we break down the string comparison problem into smaller, manageable pieces. Each cell in our table represents the solution to a specific substring comparison problem.

## Developing Your DP Intuition

To master DP, focus on developing these thinking patterns:

**Define your state clearly**: What does dp[i] or dp[i][j] represent? Be precise about this definition.

**Find the recurrence relation**: How does the current state relate to previous states? This is often the heart of the solution.

**Identify base cases**: What are the simplest problems you can solve directly?

**Consider the order of computation**: In tabulation, make sure you compute dependencies before you need them.

**Think about optimization**: Can you reduce space complexity? Are there redundant computations?

## Practice Problems to Build Your Skills

Start with these problems to build your DP foundation:

**Beginner**: Climbing Stairs, House Robber, Maximum Subarray

**Intermediate**: Coin Change, Longest Increasing Subsequence, Unique Paths

**Advanced**: Edit Distance, Palindrome Partitioning, Regular Expression Matching

Remember, DP is as much about recognizing patterns as it is about implementing algorithms. The more problems you solve, the better you'll become at spotting when DP is the right tool and which specific pattern applies.

Think of each DP problem as a puzzle where you're looking for the right way to break it into smaller, overlapping pieces. Once you see that breakdown, the solution often becomes a straightforward translation of your insight into code.

The journey to DP mastery takes practice, but the payoff is enormous. These techniques will help you solve complex optimization problems elegantly and efficiently, giving you a powerful tool for both competitive programming and real-world software development.
