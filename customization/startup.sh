#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

ls -al $JBOSS_HOME/bin

# Deploy the WAR
cp -vr /opt/jboss/wildfly/customization/transtatservice-1.0-SNAPSHOT.war $JBOSS_HOME/$JBOSS_MODE/deployments/transtatservice-1.0-SNAPSHOT.war

echo "=> Starting WildFly"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG

