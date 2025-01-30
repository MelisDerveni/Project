import java.time.Instant;
import java.time.Duration;

public class PerformanceTest {
    public static void main(String[] args) {
        Instant start = Instant.now();
        
        // Simulate database query or API call
        simulateTransactionProcess();

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Response Time: " + timeElapsed.toMillis() + " ms");
        
        // Validate that response time is within an acceptable limit
        long acceptableTime = 500; // 500 ms threshold
        if (timeElapsed.toMillis() > acceptableTime) {
            System.out.println("WARNING: System response time is too slow!");
        } else {
            System.out.println("Performance test passed.");
        }
    }

    private static void simulateTransactionProcess() {
        try {
            Thread.sleep(300); // Simulate processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
