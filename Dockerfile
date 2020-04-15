FROM openjdk:8
ADD target/transactions-0.0.2-SNAPSHOT.jar transactions-0.0.2-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "transactions-0.0.2-SNAPSHOT.jar"]