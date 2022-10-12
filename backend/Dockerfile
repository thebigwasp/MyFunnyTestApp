FROM openjdk:17-jdk

RUN mkdir -p /opt/backend
WORKDIR /opt/backend
COPY ./mvnw .
COPY ./.mvn/ ./.mvn/
COPY ./pom.xml .
RUN ./mvnw dependency:resolve dependency:resolve-plugins
COPY . .

ENTRYPOINT ["./mvnw"]
CMD ["spring-boot:run"]
EXPOSE 8080
