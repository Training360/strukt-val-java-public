FROM adoptopenjdk:11-jre-hotspot
WORKDIR /opt/app
RUN apt update \
    && apt-get install wget \
    && apt-get install -y netcat \
    && wget http://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x ./wait-for-it.sh
COPY target/*.jar /opt/app/employees.jar
ENTRYPOINT ["java", "-jar", "employees.jar"]
