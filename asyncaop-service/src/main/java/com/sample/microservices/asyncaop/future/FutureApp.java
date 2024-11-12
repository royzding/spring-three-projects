package com.sample.microservices.asyncaop.future;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FutureApp {

	public static void main(String[] args) {

		List<String> numList = new ArrayList<>();
		
		for(int i = 0; i< 50; i++ ) {
			numList.add("" + i);
		}

		LocalDateTime sTime1 = LocalDateTime.now();
		System.out.println("***** Started @ " + sTime1);		
		new FutureApp().strProcessing(numList).forEach(System.out::println);
		System.out.println("***** Ended in " + java.time.Duration.between(sTime1, LocalDateTime.now()).toSeconds());	
		
		LocalDateTime sTime2 = LocalDateTime.now();
		System.out.println("***** Started @ " + sTime2);		
		new FutureApp().bulkStrProcess(numList).forEach(System.out::println);
		System.out.println("***** Ended in " + java.time.Duration.between(sTime2, LocalDateTime.now()).toSeconds());	

	}
	

	public List<String> bulkStrProcess(List<String> list) {		
		
		int bulkNum = 15;
		
		List<String> completedList = new ArrayList<>();
		
		List<CompletableFuture<List<String>>> futureList = new ArrayList<>();
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		try {
			
			for(int i = 0; i<(list.size()-1)/bulkNum + 1; i++) {
				
				List<String> subList = list.subList(i*bulkNum,  Math.min((i+1) * bulkNum, list.size()));
				futureList.add(CompletableFuture.supplyAsync(() -> this.strProcessing(subList), executor));
				
			}
			
			completedList = allCompletableFutures(futureList).get(10, TimeUnit.MINUTES)
					.stream().flatMap(List::stream).toList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		executor.shutdown();
		
	    try {
	        if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
	        	executor.shutdownNow();
	        }
	    } catch (InterruptedException ex) {
	    	executor.shutdownNow();
	        Thread.currentThread().interrupt();
	    }		

		
		return completedList;
		
	}
	
	public static <T> CompletableFuture<List<T>> allCompletableFutures(List<CompletableFuture<T>> futuresList) {
	    return CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]))
	    		.thenApply(v -> futuresList.stream()
	    				.map(CompletableFuture::join)
	    				.collect(Collectors.<T>toList())
	    );
	}
	

	public List<String> strProcessing(List<String> list) {
		
		List<String> completedList = new ArrayList<>();
		
		list.stream().forEach(e-> { 
			
			 completedList.add(e);
			 
			 try {
				 
				Thread.sleep(1000);
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		});	
				
		return completedList;
		
	}	

}
