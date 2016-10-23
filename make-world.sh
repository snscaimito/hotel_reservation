#!/bin/bash

cd room-inventory-service
mvn clean install
cd ../frontdesk-service
mvn clean install
cd ..
