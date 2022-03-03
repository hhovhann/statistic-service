#/bin/sh
mvn clean package -DskipTests && java -jar ./target/statistic-service-1.0.0-SNAPSHOT.jar --input input4.txt --inputtype integer --operations rev,neg --threads 3 --output output4.txt
