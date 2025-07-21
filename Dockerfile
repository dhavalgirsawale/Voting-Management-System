# Use Railway's Nixpacks base image
FROM ghcr.io/railwayapp/nixpacks:ubuntu-1745885067

# Set working directory inside the container
WORKDIR /app/

# Copy the Nixpacks configuration file (for dependency setup)
COPY .nixpacks/nixpkgs-59dc10b5a6f2a592af36375c68fda41246794b86.nix .nixpacks/nixpkgs.nix

# Install dependencies from Nix file
RUN nix-env -if .nixpacks/nixpkgs.nix && nix-collect-garbage -d

# Copy entire project to /app
COPY . /app/.

# Fix: Add execute permission to build.sh explicitly inside Docker
RUN chmod +x build.sh

# Run the build script with Maven cache mount
RUN --mount=type=cache,id=s/e736c460-f034-4abb-854f-02c156883464-m2/repository,target=/app/.m2/repository ./build.sh

# Final command to start the app
CMD ["java", "-jar", "votingapp/target/votingapp-0.0.1-SNAPSHOT.jar"]
