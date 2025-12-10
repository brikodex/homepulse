# =========================
# Stage 1: Build the WAR
# =========================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copier le pom.xml et le code source
COPY pom.xml .
COPY src ./src

# Build le WAR, tests skipés pour accélérer
RUN mvn clean package -DskipTests

# =========================
# Stage 2: Runtime WildFly
# =========================
FROM quay.io/wildfly/wildfly:36.0.0.Final

USER root

# Créer dossier pour les fichiers custom
RUN mkdir -p /opt/jboss/wildfly/custom

# Télécharger le MySQL Connector (driver JDBC)
RUN curl -L -o /opt/jboss/wildfly/custom/mysql-connector-j.jar \
    https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar

# Copier le script CLI pour configurer le datasource
COPY setup-datasource.cli /opt/jboss/wildfly/custom/

# Copier le WAR depuis le build stage
COPY --from=build /app/target/backend-1.0.0.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# Exécuter le script CLI pour créer le datasource
# Les variables sensibles (username/password) seront injectées via docker-compose
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/opt/jboss/wildfly/custom/setup-datasource.cli

# Fix permissions pour WildFly
RUN chown -R jboss:jboss /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/ \
    && chown -R jboss:jboss /opt/jboss/wildfly/standalone/log

USER jboss

# Exposer le port HTTP et le port management
EXPOSE 8080 9990

# Commande de démarrage WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
