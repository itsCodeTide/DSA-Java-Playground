
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

## 🧱 Core Concepts in Number Theory

| Concept                     | Description                                                                 |
|----------------------------|-----------------------------------------------------------------------------|
| **Prime Numbers**          | Numbers greater than 1 with no divisors other than 1 and itself             |
| **Divisibility**           | Rules to determine if one number divides another without remainder         |
| **GCD & LCM**              | Greatest Common Divisor and Least Common Multiple                          |
| **Modular Arithmetic**     | Arithmetic with remainders (e.g., `a mod m`) — think of clock math         |
| **Congruences**            | Expressions like `a ≡ b (mod m)` showing equivalence under modulo          |
| **Co-prime Numbers**       | Two numbers with GCD = 1                                                    |
| **Euler’s Totient Function** | Counts numbers ≤ n that are co-prime to n                                 |
| **Fermat’s Little Theorem** | If `p` is prime and `a` is not divisible by `p`, then `a^(p-1) ≡ 1 (mod p)`|
| **Chinese Remainder Theorem** | Solves systems of modular equations with different moduli               |

## 🧠 Core Areas of Advanced Number Theory

### 1. **Modular Arithmetic & Congruences**
- **Euler’s Theorem**:  
  If `gcd(a, n) = 1`, then `a^φ(n) ≡ 1 (mod n)`
- **Fermat’s Little Theorem**:  
  If `p` is prime and `a` is not divisible by `p`, then `a^(p−1) ≡ 1 (mod p)`
- **Chinese Remainder Theorem (CRT)**:  
  Solves systems of congruences with pairwise coprime moduli.

### 2. **Diophantine Equations**
- Equations that seek integer solutions:
  - **Linear**: `ax + by = c`
  - **Pell’s Equation**: `x² − Ny² = 1`
  - **Exponential**: `a^x + b^y = c^z`

### 3. **Quadratic Residues & Reciprocity**
- **Legendre Symbol**: `(a/p)` indicates whether `a` is a quadratic residue mod `p`
- **Quadratic Reciprocity Law**:  
  Determines the solvability of `x² ≡ a (mod p)`

### 4. **Primitive Roots & Orders**
- A number `g` is a **primitive root mod n** if its powers generate all residues mod `n`
- **Order of an element**: Smallest `k` such that `a^k ≡ 1 (mod n)`

### 5. **Möbius Function & Inversion**
- **μ(n)**: Indicates square-free structure of `n`
- **Möbius Inversion**: Recovers a function from its summatory form

### 6. **Euler’s Totient Function (φ)**
- Counts integers ≤ `n` that are co-prime to `n`
- Formula:  
  `φ(n) = n × Π(1 − 1/p)` for all prime divisors `p` of `n`

---

## 📊 Advanced Applications in DSA

| Concept                     | Use Case in DSA Problems                          |
|----------------------------|---------------------------------------------------|
| Modular Exponentiation     | Fast power calculations under modulo              |
| Modular Inverse            | Division in modular arithmetic                    |
| CRT                        | Solving multiple congruences                      |
| Totient Function           | Cycle lengths in modulo operations                |
| Quadratic Residues         | Cryptographic algorithms, primality testing       |
| Diophantine Equations      | Integer solutions in constraints-based problems   |

---

## 📚 Suggested Deep-Dive Resources

- [Art of Problem Solving – Advanced Number Theory](https://artofproblemsolving.com/wiki/index.php/Number_theory/Advanced)
- [Editverse IMO-Level Number Theory](https://editverse.com/advanced-number-theory-and-arithmetic-structures/)
- [GeeksforGeeks Advanced Number Theory Concepts](https://www.geeksforgeeks.org/engineering-mathematics/number-theory/)

---

