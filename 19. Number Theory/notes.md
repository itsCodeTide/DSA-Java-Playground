
# **Number Theory**

**Number Theory** is a branch of mathematics that deals with the properties and relationships of numbers, particularly **integers**. It is considered the foundation of pure mathematics and is often referred to as **“The Queen of Mathematics”** due to its fundamental importance.

Number theory plays a vital role in computer science and programming, especially in topics like cryptography, hashing, modular arithmetic, prime numbers, and algorithm design.

---

## **Why Number Theory for Coding Interviews?**

* It provides **efficient problem-solving techniques** for mathematical problems.
* Many competitive programming problems involve **divisibility, primes, modulo**, and **GCD/LCM** logic.
* Understanding number theory helps in **optimizing brute-force solutions**.

---

## **Key Concepts to Revise**

### ✅ **1. GCD (Greatest Common Divisor)**

The **GCD** of two numbers is the largest number that divides both of them without leaving a remainder.

#### **Euclidean Algorithm** (Efficient Method)

```java
int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}
```

* **Time complexity**: O(log(min(a, b)))

#### **Properties of GCD**

* `gcd(a, 0) = a`
* `gcd(a, b) = gcd(b, a)`
* `gcd(a, b, c) = gcd(gcd(a, b), c)`

---

### ✅ **2. LCM (Least Common Multiple)**

The **LCM** of two numbers is the smallest number that is a multiple of both.

#### **Formula using GCD**

```java
int lcm(int a, int b) {
    return (a / gcd(a, b)) * b;
}
```

> ⚠️ Make sure to divide before multiplying to avoid integer overflow.

---

### ✅ **3. Relationship Between GCD and LCM**

$$
\text{GCD}(a, b) \times \text{LCM}(a, b) = a \times b
$$

This identity helps in deriving one from the other.

---

## **Other Important Number Theory Concepts to Prepare**

> Even though GCD and LCM are commonly asked, here are some other number theory topics often seen in coding rounds:

* **Prime numbers** and **Sieve of Eratosthenes**
* **Modular Arithmetic** (e.g., fast exponentiation)
* **Divisibility rules**
* **Number of divisors** and **sum of divisors**
* **Modular Inverse**
* **Chinese Remainder Theorem (Advanced)**
* **Fermat’s Little Theorem**

---

## **Examples**

### GCD Example:

```java
gcd(24, 18) => 6
```

### LCM Example:

```java
lcm(24, 18) => (24 / 6) * 18 = 72
```

---
