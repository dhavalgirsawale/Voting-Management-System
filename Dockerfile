FROM ghcr.io/railwayapp/nixpacks:ubuntu-1745885067

WORKDIR /app/

# Copy build dependencies
COPY .nixpacks/nixpkgs-*.nix .nixpacks/
RUN nix-env -if .nixpacks/nixpkgs-*.nix && nix-collect-garbage -d

# Copy project files
COPY . /app/

# 👇 Add permission explicitly inside Docker
RUN chmod +x build.sh

# Run build script
RUN --mount=type=cache,id=s/cache-m2,target=/app/.m2/repository ./build.sh

# Run your application (adjust if needed)
CMD ["java", "-jar", "target/voting-0.0.1-SNAPSHOT.jar"]
