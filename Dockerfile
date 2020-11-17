FROM java:8

VOLUME /tmp

EXPOSE 10754

ARG JAR_FILE=grouping-core/build/libs/grouping-core-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} grouping.jar

ENTRYPOINT ["java","-jar","/grouping.jar"]