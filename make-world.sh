#!/bin/bash
rm dist/*

cd room-inventory-service
mvn clean install

cd ../frontdesk-service
mvn clean install

cd ..

cd dist
find .. -name "*.jar" -exec cp {} . \;

vagrant destroy -f
vagrant up
