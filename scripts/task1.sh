#/bin/sh
mvn clean package && java -jar ./target/statistic-service-1.0.0-SNAPSHOT.jar --input input1.txt --inputtype string --operations cap --threads 1 --output output1.txt
