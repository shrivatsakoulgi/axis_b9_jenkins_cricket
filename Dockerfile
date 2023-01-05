FROM openjdk:8

#Adding and renaming the jar file
ADD /target/springboot-demo-0.1.jar cricketapp.jar

#Expose 8081 port for hitting REST end points
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "cricketapp.jar" ]