#!/bin/bash
# Build script for Render

echo "🚀 Starting build process..."

# Make mvnw executable
chmod +x mvnw

# Clean and build the project
echo "📦 Building Spring Boot application..."
./mvnw clean install -DskipTests

echo "✅ Build completed successfully!"
