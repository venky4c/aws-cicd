# Use the official OpenJdk 21 (image from the Deocker hub)
FROM openjdk:21
# Set working directory inside the container
WORKDIR /app
# Copy the compiled Java application JAR file into the container
COPY ./target/aws-cicd-0.0.1-SNAPSHOT.jar /app/aws-cicd.jar
# Expose the port the springboot application will run on
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "aws-cicd.jar"]