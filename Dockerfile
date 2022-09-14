FROM openjdk:17
#COPY build/libs/Bank-0.0.1-SNAPSHOT.jar /bank/bank.jar
#WORKDIR /bank
EXPOSE 8099
#ARG JAR_FILE=build/libs/Bank-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} Bank-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "bank.jar"]

COPY build/libs/Bank-0.0.1-SNAPSHOT.jar /bank/bank.jar
WORKDIR /bank
CMD ["java", "-jar", "Bank.jar"]
