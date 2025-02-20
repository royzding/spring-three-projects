import java.util.concurrent.*;

public class MultiThreadedJobProcessor {

    public static void main(String[] args) {
        // Number of jobs to process
        int numberOfJobs = 10;

        // Create an ExecutorService using virtual threads (new in Java 21)
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            // Store CompletableFuture objects for each job
            CompletableFuture<?>[] futures = new CompletableFuture[numberOfJobs];

            // Submit jobs to the executor service and store their CompletableFuture objects
            for (int i = 0; i < numberOfJobs; i++) {
                String jobName = "Job " + (i + 1);
                futures[i] = CompletableFuture.runAsync(() -> new Job(jobName).run(), executorService)
                        .whenComplete((result, throwable) -> {
                            if (throwable != null) {
                                System.out.println(jobName + " encountered an error: " + throwable.getMessage());
                            } else {
                                System.out.println(jobName + " has been completed successfully.");
                            }
                        });
            }

            // Wait for all CompletableFuture tasks to complete
            CompletableFuture.allOf(futures).join();

        } // ExecutorService is auto-closed here
    }
}

// Job class that implements Runnable
class Job implements Runnable {
    private String jobName;

    public Job(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing " + jobName);

        // Simulate job processing by sleeping for a random time
        try {
            Thread.sleep((long) (Math.random() * 2000)); // Sleep for up to 2 seconds
        } catch (InterruptedException e) {
            System.out.println(jobName + " was interrupted.");
        }

        System.out.println(jobName + " has been completed by " + Thread.currentThread().getName());
    }
}