import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class PalindromeCheckerApp {
    public static boolean isPalindromeReverse(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
    public static boolean isPalindromeTwoPointer(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static boolean isPalindromeRecursive(String str) {
        return checkRecursive(str, 0, str.length() - 1);
    }
    private static boolean checkRecursive(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return checkRecursive(str, left + 1, right - 1);
    }
    public static boolean isPalindromeStackQueue(String str) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (char c : str.toCharArray()) {
            stack.push(c);
            queue.add(c);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.remove()) {
                return false;
            }
        }
        return true;
    }
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Palindrome Checker App ===");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine().replaceAll("\\s+", "").toLowerCase();
        long timeReverse = measureTime(() -> isPalindromeReverse(input));
        long timeTwoPointer = measureTime(() -> isPalindromeTwoPointer(input));
        long timeRecursive = measureTime(() -> isPalindromeRecursive(input));
        long timeStackQueue = measureTime(() -> isPalindromeStackQueue(input));
        System.out.println("\nPerformance Comparison:");
        System.out.printf("%-20s %-15s%n", "Algorithm", "Execution Time (ns)");
        System.out.println("-----------------------------------------");
        System.out.printf("%-20s %-15d%n", "String Reversal", timeReverse);
        System.out.printf("%-20s %-15d%n", "Two-Pointer", timeTwoPointer);
        System.out.printf("%-20s %-15d%n", "Recursive", timeRecursive);
        System.out.printf("%-20s %-15d%n", "Stack/Queue", timeStackQueue);

        scanner.close();
    }
}