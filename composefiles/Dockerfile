# Use the official OpenJDK base image
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY ../target/Animals-1.0.0.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]