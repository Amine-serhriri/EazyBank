#Start wit a base imae containing Java Runtime
FROM openjdk:17-jdk-slim

#Information about who maintains te image
MAINTAINER eazybytes.com

#Add te application jar to te image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]