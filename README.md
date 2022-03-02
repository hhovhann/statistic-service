# Statistic-Service Demo Console Application
Statistic provider demo application

# Application tech stack
* Java 17
* Spring Boot 2.6.4

# Software Design
Using Spring Boot Framework to provide the way of using Dependency Injection (IoC). In main application class have Field injections in Data Processor service constructor injections

* CommandLineParserService - responsible for parsing CLI arguments
* DataProcessingService - responsible for process data reading and transformation (may say data processor)
* DataTransformationService - responsible for data transformation based on operations and input types (reveres, negate, capitalize)
* FileTransformationService - responsible for read and write data from and to input/output files

## How to run application with different Scenarios from IDEA
* Add environment arguments: `--input input1.txt --inputtype string --operations cap --threads 1 --output output1.txt` and run the application
* Add environment arguments: `--input input2.txt --inputtype integer --operations rev,neg --threads 1  --output output2.txt` and run the application
* Add environment arguments: `--input input3.txt --inputtype double --operations rev,neg --threads 1  --output output3.txt` and run the application

## How to run application with different Scenarios (Under construction ... :)
* Run task1.sh for Scenario 1 with arguments: `--input input1.txt --inputtype string --operations cap --threads 1 --output output1.txt`
  * Result would be written in output/output1.txt
* Run task2.sh for Scenario 2 with arguments: `--input input2.txt --inputtype integer --operations rev,neg --threads 1  --output output2.txt`
  * Result would be written in output/output2.txt
* Run task3.sh for Scenario 3 with arguments: `--input input3.txt --inputtype double --operations rev,neg --threads 1  --output output3.txt`
  * Result would be written in output/output3.txt

# Nice to have 
* Could have enums for switch for operations(rev,neg,cap) and input types(string, integer, double)
* Could have Integration tests to not testing manually by scripts the correct results
