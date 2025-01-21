package com.sample.microservices.common.versions.v21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Explanation:
Import: We import the Executors class to create a virtual thread executor.
Executor: We create a newVirtualThreadPerTaskExecutor, which creates a new virtual thread for each submitted task.
Loop: We submit 10 tasks to the executor. Each task:
Prints a message indicating it's running on a virtual thread.
Sleeps for 1 second to simulate some work.
Try-with-resources: Ensures the executor is properly closed after all tasks are completed.
Key Points:
Ease of Use:
Creating and using virtual threads is straightforward with the newVirtualThreadPerTaskExecutor.
Lightweight:
Virtual threads have a smaller memory footprint than traditional platform threads, allowing you to create many more of them.
Improved Concurrency:
Virtual threads make it easier to write highly concurrent applications that can handle a large number of tasks efficiently.
 */

public class VirtualThreads {

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 10; i++) {

                int j = i;

                // Submit a task that prints a message
                Future<?> future = executor.submit(() -> {
                    Thread.currentThread().setName("Name-" + j);
                    System.out.println("Hello from virtual thread: id "  + Thread.currentThread().threadId() + " name: " + Thread.currentThread().getName());

                    try {
                        Thread.sleep(1000); // Simulate some work
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                // Wait for the task to complete
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Task finished: " + i);

            }
        }
    }
}
