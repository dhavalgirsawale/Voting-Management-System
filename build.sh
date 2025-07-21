#!/bin/bash
cd votingapp
chmod +x mvnw
chmod +x ./mvnw
./mvnw clean package -DskipTests
