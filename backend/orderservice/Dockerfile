FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app
 
# Copy the Maven wrapper and the pom.xml file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Ensure the Maven wrapper script has execute permissions
RUN chmod +x mvnw

# Download dependencies to cache them in a separate layer
RUN ./mvnw dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/orderservice-0.0.1-SNAPSHOT.jar"]