
import java.util.*;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Main {

    public static List<Integer> topKFrequent(int[] nums, int k)
            throws InvalidInputException {

        if (nums == null || nums.length == 0) {
            throw new InvalidInputException(
                "Input array cannot be empty."
            );
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(
                num,
                frequencyMap.getOrDefault(num, 0) + 1
            );
        }

        if (k < 1 || k > frequencyMap.size()) {
            throw new InvalidInputException(
                "Invalid k. Enter a value between 1 and "
                + frequencyMap.size()
            );
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
            );

        for (Map.Entry<Integer, Integer> entry :
                frequencyMap.entrySet()) {

            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of elements: ");
            int n = sc.nextInt();

            if (n <= 0) {
                throw new InvalidInputException(
                    "Number of elements must be positive."
                );
            }

            int[] nums = new int[n];

            System.out.println("Enter array elements:");

            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            System.out.print("Enter k: ");
            int k = sc.nextInt();

            List<Integer> answer = topKFrequent(nums, k);

            System.out.println(
                "Top " + k + " frequent elements: " + answer
            );

        } catch (InvalidInputException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {

            System.out.println(
                "Error: Please enter valid integer values."
            );

        } finally {

            System.out.println("Program execution completed.");
            sc.close();
        }
    }
}
