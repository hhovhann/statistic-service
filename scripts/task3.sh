#/bin/sh
mvn clean package -DskipTests && java -jar ./target/statistic-service-1.0.0-SNAPSHOT.jar --input input3.txt --inputtype double --operations rev,neg --threads 1  --output output3.txt
