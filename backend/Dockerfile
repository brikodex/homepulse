# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the WAR file. 
# We also copy dependencies to extract the mysql jar later if needed, 
# but usually it's cleaner to download it or copy it from the maven repo.
# Here we just build the WAR.
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM quay.io/wildfly/wildfly:36.0.0.Final-jdk21

# Initial setup variables
ENV DATASOURCE_URL="jdbc:mysql://db:3306/homepulse_db"
ENV DATASOURCE_USERNAME="brikodex"
ENV DATASOURCE_PASSWORD="@1909Motdepasse!"

# Copy the WAR file
COPY --from=build /app/target/backend-1.0.0.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# Copy MySQL Driver from the build stage (Maven repo) is tricky because paths vary.
# Easier to download it directly or copy from local if we are sure where it is.
# Let's rely on the one added in pom.xml -> it's inside the war? No, provided scope?
# Wait, typical provided scope in pom.xml means it's NOT in the WAR. 
# But for Docker, we want to Put it as a module.
# Let's simple curl it in this stage to be safe and explicit.
USER root
RUN curl -L -o /opt/jboss/wildfly/custom/mysql-connector-j.jar https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar
RUN mkdir -p /opt/jboss/wildfly/custom

# Copy configuration script
COPY setup-datasource.cli /opt/jboss/wildfly/custom/

# Run the CLI script to configure the datasource
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/opt/jboss/wildfly/custom/setup-datasource.cli

# Fix permissions
RUN chown -R jboss:jboss /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/ \
    && chown -R jboss:jboss /opt/jboss/wildfly/standalone/log

USER jboss

# Expose default HTTP port
EXPOSE 8080 9990

# Set the default command to run on boot
# -b 0.0.0.0 bind to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
