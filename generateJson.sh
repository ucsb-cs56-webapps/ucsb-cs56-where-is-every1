#!/bin/sh
mvn compile && mvn exec:java && mv catalog.json src/main/resources/
