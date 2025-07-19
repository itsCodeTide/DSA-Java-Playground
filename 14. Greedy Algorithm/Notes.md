# Greedy Algorithms - Complete DSA Notes with Java Examples

## Understanding the Greedy Mindset: Making the Best Choice at Every Step

Imagine you're hiking through an unfamiliar mountain trail, and at every fork in the path, you consistently choose the route that seems to climb highest or looks most promising at that moment. You're not considering the entire journey or mapping out the complete route beforehand. Instead, you're making locally optimal choices, trusting that these immediate best decisions will lead you to the summit. This is the essence of greedy algorithms.

A greedy algorithm makes the locally optimal choice at each decision point, hoping that this series of local optimizations will lead to a globally optimal solution. The word "greedy" captures this behavior perfectly because, like a person who always takes the biggest slice of cake without considering whether others might want some, greedy algorithms always choose what appears best right now without looking ahead to future consequences.

**The fundamental insight:** Greedy algorithms work on the principle that if you can identify what constitutes a "best" choice at each step, and if the problem has certain mathematical properties, then making the best choice at every step will automatically lead to the best overall solution. This approach is remarkably powerful when it works, but it requires careful analysis to determine when it's appropriate.

**Why this approach appeals to us:** Greedy algorithms mirror human decision-making in many everyday situations. When you're getting dressed in the morning, you might choose clothes based on the current weather without necessarily planning your entire day's activities. When you're shopping with a limited budget, you might buy the items that give you the best value first. These natural decision-making patterns reflect the greedy principle.

## The Core Properties: When Greedy Choices Lead to Optimal Solutions

Not every problem can be solved optimally using a greedy approach. For a greedy algorithm to produce the correct answer, the problem must exhibit two crucial mathematical properties that work together to guarantee correctness.

### Greedy Choice Property: The Foundation of Local Optimality

The greedy choice property means that you can construct a globally optimal solution by making locally optimal choices. In other words, the choice that looks best at the current step is actually part of some optimal solution to the overall problem.

**Understanding through analogy:** Consider planning the most efficient route to visit multiple friends in your city. If the problem has the greedy choice property, then starting with the closest friend (the locally optimal choice) would be part of the most efficient overall route. However, this isn't always true in routing problems, which is why GPS navigation systems use more sophisticated algorithms.

**The mathematical significance:** This property is what separates problems where greedy algorithms work from problems where they don't. Without this property, making the seemingly best choice at each step might lead you down a path that prevents you from finding the truly optimal solution.

### Optimal Substructure: Building Solutions from Optimal Parts

Optimal substructure means that an optimal solution to the problem contains optimal solutions to its subproblems. If you remove the first decision from an optimal solution, what remains should still be optimal for the reduced problem.

**Visualizing the concept:** Think of building the tallest possible tower with blocks. If your optimal solution uses a particular block at the bottom, then the rest of the tower (built with the remaining blocks) should also be the tallest possible tower you can build with those remaining blocks. If it weren't, you could replace that part with a taller arrangement and improve your overall solution.

**Why both properties matter:** These two properties work in tandem. The greedy choice property ensures that your first decision is correct, while optimal substructure ensures that you can solve the rest of the problem optimally using the same approach. Together, they create a mathematical guarantee that the greedy approach will find the optimal solution.

## Classic Example: Activity Selection Problem

Let me walk you through the activity selection problem, which beautifully demonstrates how greedy algorithms work and why they're so elegant when applied to the right problems.

### Problem Setup: Maximizing Your Schedule

Imagine you're a conference coordinator with a single meeting room, and you have multiple events that different groups want to schedule. Each event has a start time and an end time, and no two events can overlap in the room. Your goal is to select the maximum number of events that can be scheduled without conflicts.

**Why this is challenging:** At first glance, this might seem like a complex optimization problem requiring you to examine all possible combinations of events. With just 10 events, there are over 1,000 possible combinations to consider. The greedy approach, however, provides an elegant solution.

**The greedy insight:** The key insight is recognizing what makes a good choice at each step. You might initially think to choose the shortest events first, or the events that start earliest. However, the optimal greedy choice is to always select the event that ends earliest among the remaining events.

### Java Implementation with Detailed Explanation

```java
import java.util.*;

class Activity {
    int start;
    int end;
    String name;
    
    // Constructor to create an activity with start time, end time, and name
    Activity(int start, int end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name + " (" + start + "-" + end + ")";
    }
}

public class ActivitySelection {
    
    // The core greedy algorithm for activity selection
    public static List<Activity> selectActivities(List<Activity> activities) {
        // Step 1: Sort activities by their end times (greedy choice criterion)
        // This is crucial - we always want to consider the activity that ends earliest
        Collections.sort(activities, (a, b) -> Integer.compare(a.end, b.end));
        
        List<Activity> selected = new ArrayList<>();
        
        // Step 2: Always select the first activity (the one ending earliest)
        // This is our greedy choice - it leaves the most room for future activities
        if (!activities.isEmpty()) {
            selected.add(activities.get(0));
            int lastEndTime = activities.get(0).end;
            
            // Step 3: Iterate through remaining activities
            for (int i = 1; i < activities.size(); i++) {
                Activity current = activities.get(i);
                
                // Greedy decision: if this activity starts after the last selected activity ends,
                // it's compatible and we should select it (since it ends earliest among compatible activities)
                if (current.start >= lastEndTime) {
                    selected.add(current);
                    lastEndTime = current.end; // Update our constraint for future selections
                }
                // If not compatible, we skip this activity and continue
                // This is the greedy aspect - we don't reconsider this decision later
            }
        }
        
        return selected;
    }
    
    public static void main(String[] args) {
        // Create a realistic conference scheduling scenario
        List<Activity> activities = Arrays.asList(
            new Activity(1, 4, "Team Meeting"),
            new Activity(3, 5, "Client Presentation"),
            new Activity(0, 6, "All-Day Workshop"),
            new Activity(5, 7, "Project Review"),
            new Activity(8, 9, "Standup"),
            new Activity(5, 9, "Training Session"),
            new Activity(9, 10, "Wrap-up Meeting")
        );
        
        System.out.println("All activities:");
        for (Activity activity : activities) {
            System.out.println("  " + activity);
        }
        
        List<Activity> selected = selectActivities(activities);
        
        System.out.println("\nSelected activities (maximum number without conflicts):");
        for (Activity activity : selected) {
            System.out.println("  " + activity);
        }
        
        System.out.println("\nTotal activities scheduled: " + selected.size());
    }
}
```

### Why This Greedy Choice Works

The reason we choose the activity that ends earliest is profound and demonstrates the mathematical beauty of greedy algorithms. When you select the activity that ends earliest, you leave the maximum amount of time available for scheduling future activities. This choice minimizes the "opportunity cost" of your decision.

**The proof insight:** Suppose there's an optimal solution that doesn't include the earliest-ending activity. We can modify this solution by replacing whatever activity comes first with our earliest-ending activity. Since our activity ends earlier, it won't create any new conflicts, and we'll have at least as good a solution. This proves that the greedy choice is always part of some optimal solution.

**Understanding the output:** When you run this code, you'll see that the algorithm doesn't select the "All-Day Workshop" even though it might seem important. Instead, it chooses activities that end early, allowing more activities to be scheduled overall. This demonstrates how greedy algorithms sometimes make counterintuitive choices that turn out to be optimal.

## Fractional Knapsack: When Partial Solutions Make Sense

The fractional knapsack problem introduces an important concept in greedy algorithms: sometimes the optimal strategy involves taking partial amounts rather than making all-or-nothing decisions.

### Problem Understanding: The Treasure Hunter's Dilemma

Imagine you're a treasure hunter who has discovered a cave full of valuable items, but your backpack can only carry a limited weight. Unlike the classic 0-1 knapsack problem where you must take entire items, in the fractional knapsack, you can take portions of items. For example, if there's a bag of gold dust, you can take half the bag if that's all that fits.

**The greedy insight:** The optimal strategy is to calculate the value-to-weight ratio for each item and take items in order of decreasing ratio. When you encounter an item that doesn't fully fit, you take as much of it as possible.

### Java Implementation with Clear Logic

```java
import java.util.*;

class Item {
    int weight;
    int value;
    String name;
    
    Item(int weight, int value, String name) {
        this.weight = weight;
        this.value = value;
        this.name = name;
    }
    
    // Calculate value per unit weight - this is our greedy criterion
    double getValuePerWeight() {
        return (double) value / weight;
    }
    
    @Override
    public String toString() {
        return String.format("%s (w:%d, v:%d, ratio:%.2f)", 
                           name, weight, value, getValuePerWeight());
    }
}

public class FractionalKnapsack {
    
    public static double fractionalKnapsack(List<Item> items, int capacity) {
        // Step 1: Sort items by value-to-weight ratio in descending order
        // This is our greedy choice criterion - always take the most valuable per unit weight first
        Collections.sort(items, (a, b) -> Double.compare(b.getValuePerWeight(), a.getValuePerWeight()));
        
        double totalValue = 0.0;
        int remainingCapacity = capacity;
        
        System.out.println("Knapsack capacity: " + capacity);
        System.out.println("Items sorted by value/weight ratio:");
        
        for (Item item : items) {
            System.out.printf("  %s%n", item);
            
            if (remainingCapacity >= item.weight) {
                // We can take the entire item - greedy choice is to take it all
                totalValue += item.value;
                remainingCapacity -= item.weight;
                System.out.printf("    -> Taking entire item (value gained: %d, remaining capacity: %d)%n", 
                                item.value, remainingCapacity);
            } else if (remainingCapacity > 0) {
                // We can only take a fraction of this item
                double fraction = (double) remainingCapacity / item.weight;
                double fractionalValue = item.value * fraction;
                totalValue += fractionalValue;
                System.out.printf("    -> Taking %.2f%% of item (value gained: %.2f, remaining capacity: 0)%n", 
                                fraction * 100, fractionalValue);
                remainingCapacity = 0;
                break; // Knapsack is now full
            } else {
                System.out.println("    -> Skipping item (no remaining capacity)");
            }
        }
        
        return totalValue;
    }
    
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
            new Item(10, 60, "Gold Jewelry"),    // ratio: 6.0
            new Item(40, 40, "Silver Coins"),    // ratio: 1.0
            new Item(20, 100, "Rare Gems"),      // ratio: 5.0
            new Item(30, 120, "Ancient Artifact") // ratio: 4.0
        );
        
        int knapsackCapacity = 50;
        
        System.out.println("Available items:");
        for (Item item : items) {
            System.out.println("  " + item);
        }
        System.out.println();
        
        double maxValue = fractionalKnapsack(new ArrayList<>(items), knapsackCapacity);
        
        System.out.printf("%nMaximum value that can be obtained: %.2f%n", maxValue);
    }
}
```

### Understanding the Greedy Logic

The fractional knapsack algorithm works because taking the item with the highest value-to-weight ratio first is always optimal. If there were a better solution that didn't prioritize these high-ratio items, we could improve it by substituting higher-ratio portions for lower-ratio portions.

**The mathematical reasoning:** Think of it this way: if you have space left and you're choosing between taking a portion of an item worth $5 per pound versus an item worth $3 per pound, the choice is obvious. The greedy algorithm formalizes this intuition by always making the locally optimal choice.

**Why fractions matter:** The ability to take fractions is crucial for this greedy approach to work. In the 0-1 knapsack problem (where you must take entire items), greedy algorithms don't always produce optimal solutions because the constraint of taking whole items can make locally optimal choices globally suboptimal.

## Huffman Coding: Optimal Data Compression Through Greedy Choices

Huffman coding demonstrates how greedy algorithms can solve complex optimization problems in computer science. This algorithm creates optimal prefix-free binary codes for data compression by building a binary tree through greedy choices.

### The Problem: Efficient Character Encoding

When storing or transmitting text, we want to use fewer bits for frequently occurring characters and more bits for rare characters. Standard ASCII encoding uses 8 bits for every character, but we can do better if we know the frequency of each character.

**The greedy insight:** At each step, combine the two nodes with the smallest frequencies. This ensures that the most frequent characters end up closer to the root of the tree, giving them shorter codes.

### Java Implementation with Tree Building

```java
import java.util.*;

class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left, right;
    
    // Constructor for leaf nodes (actual characters)
    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    // Constructor for internal nodes (combined frequencies)
    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0'; // Internal nodes don't represent actual characters
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    boolean isLeaf() {
        return left == null && right == null;
    }
}

public class HuffmanCoding {
    
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
        // Step 1: Create a min-heap (priority queue) of all characters
        // The greedy choice is always to combine the two least frequent nodes
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.frequency != b.frequency) {
                return Integer.compare(a.frequency, b.frequency);
            }
            // If frequencies are equal, maintain consistent ordering
            return Character.compare(a.character, b.character);
        });
        
        // Add all characters as leaf nodes to the heap
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            minHeap.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        System.out.println("Building Huffman tree step by step:");
        int step = 1;
        
        // Step 2: Repeatedly combine the two nodes with smallest frequencies
        while (minHeap.size() > 1) {
            // Greedy choice: always take the two least frequent nodes
            HuffmanNode first = minHeap.poll();
            HuffmanNode second = minHeap.poll();
            
            // Create a new internal node with combined frequency
            HuffmanNode combined = new HuffmanNode(
                first.frequency + second.frequency, 
                first, 
                second
            );
            
            System.out.printf("Step %d: Combine nodes with frequencies %d and %d -> new node with frequency %d%n",
                            step++, first.frequency, second.frequency, combined.frequency);
            
            minHeap.offer(combined);
        }
        
        // The remaining node is the root of our Huffman tree
        return minHeap.poll();
    }
    
    // Generate the binary codes by traversing the tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> codes) {
        if (root == null) return;
        
        if (root.isLeaf()) {
            // We've reached a character - record its code
            codes.put(root.character, code.isEmpty() ? "0" : code); // Handle single character case
            return;
        }
        
        // Traverse left (append '0') and right (append '1')
        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }
    
    public static void main(String[] args) {
        // Example: frequency analysis of a text message
        String text = "hello world";
        Map<Character, Integer> frequencies = new HashMap<>();
        
        // Count character frequencies
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        
        System.out.println("Character frequencies in \"" + text + "\":");
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            char c = entry.getKey();
            String displayChar = (c == ' ') ? "SPACE" : String.valueOf(c);
            System.out.println("  " + displayChar + ": " + entry.getValue());
        }
        System.out.println();
        
        // Build Huffman tree using greedy algorithm
        HuffmanNode root = buildHuffmanTree(frequencies);
        
        // Generate binary codes
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);
        
        System.out.println("\nGenerated Huffman codes:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            char c = entry.getKey();
            String displayChar = (c == ' ') ? "SPACE" : String.valueOf(c);
            System.out.println("  " + displayChar + ": " + entry.getValue());
        }
        
        // Calculate compression efficiency
        int originalBits = text.length() * 8; // 8 bits per character in ASCII
        int compressedBits = 0;
        for (char c : text.toCharArray()) {
            compressedBits += codes.get(c).length();
        }
        
        System.out.printf("%nCompression analysis:%n");
        System.out.printf("Original size: %d bits%n", originalBits);
        System.out.printf("Compressed size: %d bits%n", compressedBits);
        System.out.printf("Compression ratio: %.1f%%%n", 
                        (1.0 - (double)compressedBits/originalBits) * 100);
    }
}
```

### Understanding the Greedy Strategy

The Huffman algorithm makes a greedy choice at each step by combining the two nodes with the smallest frequencies. This choice is optimal because it minimizes the weighted path length in the resulting tree, which directly translates to optimal code lengths.

**Why this works mathematically:** When you combine two nodes with the smallest frequencies, you're making a choice that affects the tree structure minimally. The characters involved will have their code lengths increased by exactly one bit, and since they're the least frequent, this increase has the smallest possible impact on the overall code length.

**The compression insight:** Notice how frequent characters like 'l' get shorter codes while less frequent characters get longer codes. This is the core principle of entropy coding: allocate shorter codes to more probable events.

## Real-World Applications: Where Greedy Algorithms Excel

Understanding when and where greedy algorithms are used in practice helps you recognize patterns and apply these techniques effectively in your own projects.

### CPU Scheduling: Shortest Job First

Operating systems use greedy algorithms for task scheduling to optimize system performance and user experience.

```java
import java.util.*;

class Process {
    String name;
    int burstTime;
    int arrivalTime;
    
    Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
    
    @Override
    public String toString() {
        return String.format("%s (arrival:%d, burst:%d)", name, arrivalTime, burstTime);
    }
}

public class ShortestJobFirst {
    
    public static void scheduleProcesses(List<Process> processes) {
        // Greedy choice: always select the process with shortest burst time
        // among those that have arrived
        List<Process> scheduled = new ArrayList<>();
        List<Process> remaining = new ArrayList<>(processes);
        int currentTime = 0;
        
        System.out.println("Process scheduling using Shortest Job First (Greedy):");
        System.out.println("Available processes:");
        for (Process p : processes) {
            System.out.println("  " + p);
        }
        System.out.println();
        
        while (!remaining.isEmpty()) {
            // Find processes that have arrived by current time
            List<Process> available = new ArrayList<>();
            for (Process p : remaining) {
                if (p.arrivalTime <= currentTime) {
                    available.add(p);
                }
            }
            
            if (available.isEmpty()) {
                // No process has arrived yet, advance time to next arrival
                int nextArrival = remaining.stream()
                    .mapToInt(p -> p.arrivalTime)
                    .min()
                    .orElse(currentTime);
                currentTime = nextArrival;
                continue;
            }
            
            // Greedy choice: select process with minimum burst time
            Process shortest = available.stream()
                .min((a, b) -> Integer.compare(a.burstTime, b.burstTime))
                .get();
            
            System.out.printf("Time %d: Starting %s (will finish at time %d)%n", 
                            currentTime, shortest.name, currentTime + shortest.burstTime);
            
            scheduled.add(shortest);
            remaining.remove(shortest);
            currentTime += shortest.burstTime;
        }
        
        // Calculate average waiting time
        int totalWaitingTime = 0;
        int completionTime = 0;
        System.out.println("\nCompletion analysis:");
        for (Process p : scheduled) {
            int waitingTime = completionTime - p.arrivalTime;
            if (waitingTime < 0) waitingTime = 0;
            totalWaitingTime += waitingTime;
            completionTime += p.burstTime;
            System.out.printf("  %s: waiting time = %d%n", p.name, waitingTime);
        }
        
        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        System.out.printf("Average waiting time: %.2f%n", avgWaitingTime);
    }
    
    public static void main(String[] args) {
        List<Process> processes = Arrays.asList(
            new Process("Email Client", 0, 3),
            new Process("Web Browser", 1, 8),
            new Process("Text Editor", 2, 2),
            new Process("Calculator", 3, 1),
            new Process("File Manager", 4, 5)
        );
        
        scheduleProcesses(processes);
    }
}
```

**Why this greedy choice works:** Shortest Job First minimizes the average waiting time for all processes. By completing short jobs quickly, we reduce the total time that all jobs spend waiting in the queue. This is optimal for average waiting time, though it might not be fair to longer processes.

### Network Routing: Dijkstra's Algorithm Foundation

While Dijkstra's algorithm is more complex, it uses greedy principles by always selecting the unvisited node with the smallest known distance.

```java
import java.util.*;

class Edge {
    int destination;
    int weight;
    
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class SimpleRouting {
    
    // Simplified version focusing on the greedy choice aspect
    public static void findShortestPaths(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];
        
        // Initialize distances to infinity except source
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        System.out.printf("Finding shortest paths from node %d using greedy approach:%n", source);
        
        for (int i = 0; i < n; i++) {
            // Greedy choice: always select unvisited node with minimum distance
            int current = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (current == -1 || distances[j] < distances[current])) {
                    current = j;
                }
            }
            
            if (distances[current] == Integer.MAX_VALUE) break; // Unreachable nodes remain
            
            visited[current] = true;
            System.out.printf("Step %d: Selected node %d (distance: %d)%n", 
                            i + 1, current, distances[current]);
            
            // Update distances to neighbors
            for (Edge edge : graph.get(current)) {
                int newDistance = distances[current] + edge.weight;
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance;
                    System.out.printf("  Updated distance to node %d: %d%n", 
                                    edge.destination, newDistance);
                }
            }
        }
        
        System.out.println("\nFinal shortest distances:");
        for (int i = 0; i < n; i++) {
            String distance = (distances[i] == Integer.MAX_VALUE) ? "∞" : String.valueOf(distances[i]);
            System.out.printf("  Node %d: %s%n", i, distance);
        }
    }
    
    public static void main(String[] args) {
        // Create a simple network graph
        int numNodes = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Add edges (bidirectional for simplicity)
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(3, 8));
        graph.get(2).add(new Edge(4, 10));
        graph.get(3).add(new Edge(4, 2));
        
        findShortestPaths(graph, 0);
    }
}
```

**The greedy insight in routing:** At each step, we permanently determine the shortest path to one more node by selecting the unvisited node that's closest to our source. This works because once we've found the shortest path to a node, we know that any other path to that node (going through unprocessed nodes) must be longer.

## When Greedy Algorithms Fail: Understanding the Limitations

It's crucial to understand that greedy algorithms don't work for all optimization problems. Recognizing when they fail helps you choose the right algorithmic approach for each situation.

### The Coin Change Problem: A Tale of Two Scenarios

The coin change problem illustrates how the same greedy strategy can work perfectly in one scenario but fail completely in another.

```java
import java.util.*;

public class CoinChange {
    
    // Greedy approach: always take the largest denomination possible
    public static List<Integer> greedyCoinChange(int[] coins, int amount) {
        // Sort coins in descending order for greedy selection
        Integer[] sortedCoins = Arrays.stream(coins).boxed()
            .sorted((a, b) -> Integer.compare(b, a))
            .toArray(Integer[]::new);
        
        List<Integer> result = new ArrayList<>();
        int remaining = amount;
        
        System.out.printf("Making change for %d using greedy approach:%n", amount);
        
        for (int coin : sortedCoins) {
            while (remaining >= coin) {
                result.add(coin);
                remaining -= coin;
                System.out.printf("  Used coin %d, remaining amount: %d%n", coin, remaining);
            }
        }
        
        if (remaining > 0) {
            System.out.println("  Cannot make exact change with these coins!");
            return null;
        }
        
        return result;
    }
    
    // Dynamic programming approach for comparison
    public static List<Integer> optimalCoinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] parent = new int[amount + 1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    if (dp[i - coin] + 1 < dp[i]) {
                        dp[i] = dp[i - coin] + 1;
                        parent[i] = coin;
                    }
                }
            }
        }
        
        if (dp[amount] == Integer.MAX_VALUE) {
            return null;
        }
        
        List<Integer> result = new ArrayList<>();
        int current = amount;
        while (current > 0) {
            int coin = parent[current];
            result.add(coin);
            current -= coin;
        }
        
        return result;
    }
    
    public static void testCoinChange(int[] coins, int amount) {
        System.out.printf("Coin denominations: %s%n", Arrays.toString(coins));
        System.out.printf("Target amount: %d%n%n", amount);
        
        List<Integer> greedyResult = greedyCoinChange(coins, amount);
        List<Integer> optimalResult = optimalCoinChange(coins, amount);
        
        System.out.println("Results comparison:");
        if (greedyResult != null) {
            Collections.sort(greedyResult, Collections.reverseOrder());
            System.out.printf("  Greedy: %s (total coins: %d)%n", greedyResult, greedyResult.size());
        } else {
            System.out.println("  Greedy: No solution found");
        }
        
        if (optimalResult != null) {
            Collections.sort(optimalResult, Collections.reverseOrder());
            System.out.printf("  Optimal: %s (total coins: %d)%n", optimalResult, optimalResult.size());
        } else {
            System.out.println("  Optimal: No solution found");
        }
        
        System.out.println();
    }
    
    public static void main(String[]
