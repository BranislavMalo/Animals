#!/bin/bash

# Initialize variables
USER_PATH="$HOME/Animals"

# Parse command line options
while getopts "p:" opt; do
  case $opt in
    p)
      USER_PATH="$OPTARG"
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
  esac
done

# Check if the user provided the -p option
if [ "$OPTIND" -eq 1 ]; then
  echo "No path specified to the Animals."
  exit 1
fi

# Set the path to your JAR file
JAR_FILE="$USER_PATH/Animals/target/Animals-0.0.1-SNAPSHOT.jar"

# Check if the JAR file exists
if [ ! -f "$JAR_FILE" ]; then
    echo "Error: JAR file not found at $JAR_FILE"
    exit 1
fi

# Run the Spring Boot application
java -jar "$JAR_FILE"