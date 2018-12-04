#!/bin/sh
mvn compile && mvn package && java -jar target/ucsb-where-is-every1-0.0.1.jar json && mv catalog.json src/main/resources/
