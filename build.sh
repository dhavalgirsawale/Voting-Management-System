#!/bin/bash
cd votingapp
chmod +x mvnw
./mvnw clean package -DskipTests
