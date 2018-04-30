FROM gradle:4.6.0-jdk8-alpine as builder
COPY ./* ./
RUN gradle clean build

FROM openjdk:8-jre-alpine
COPY --from=builder /home/gradle/build/libs/barren-land.jar /barren-land.jar
ENTRYPOINT java -jar barren-land.jar