#/bin/sh
mvn clean package && java -jar ./target/statistic-service-1.0.0-SNAPSHOT.jar --input input2.txt --inputtype integer --operations rev,neg --threads 1  --output output2.txt
