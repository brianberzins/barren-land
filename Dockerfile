FROM openjdk:10-jre-slim

ADD build/libs/barren-land.jar /barren-land.jar

ENTRYPOINT java -jar barren-land.jar