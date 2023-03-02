FROM bellsoft/liberica-openjdk-alpine-musl
WORKDIR /app
EXPOSE 8004
COPY ./target/TinkoffService-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","TinkoffService-0.0.1-SNAPSHOT.jar"]