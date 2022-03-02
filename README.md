# statistic-service
Statistic provider application


# Run different Scenarios

* Scenario 1: java com.hhovhann.StatisticServiceApplication --input=input1.txt --inputtype=string --operations=cap --threads=1 --output=output1.txt
* Scenario 2: java com.hhovhann.StatisticServiceApplication --input=input2.txt --inputtype=int --operations=rev,neg --threads=1  --output=output2.txt
* Scenario 3: java com.hhovhann.StatisticServiceApplication --input=input3.txt --inputtype=double --operations=rev,neg --threads=1  --output=output3.txt


```TODO WITH  ...
System.out.println( "INFO - demo starting.  " + Instant.now() );

// Submit tasks to run on background threads.
ExecutorService executorService = Executors.newFixedThreadPool(2);
for ( int i = 0 ; i < 100 ; i++ ) { 
    executorService.submit( ( ) -> System.out.println( "Thread id " + Thread.currentThread().getId() + " is executing its task at " + Instant.now() ) );
}

// Wait for tasks to complete.
executorService.shutdown();
try { executorService.awaitTermination( 1 , TimeUnit.HOURS ); } catch ( InterruptedException e ) { e.printStackTrace(); }

System.out.println( "INFO - demo ending.  " + Instant.now() );
```