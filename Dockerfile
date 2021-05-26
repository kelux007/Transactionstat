FROM jboss/wildfly
COPY /target/transtatservice-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/