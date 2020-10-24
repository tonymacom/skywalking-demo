#!/usr/bin/env sh
cd sky
mvn clean install -DskipTests

cd ../bees
mvn clean install -DskipTests

cd ../

docker build -t itmabo/skywalking-agent:latest . -f Dockerfile-Agent
docker push itmabo/skywalking-agent:latest

docker build -t itmabo/sky:v1.0.0 . -f Dockerfile-Sky
docker push itmabo/sky:v1.0.0

docker build -t itmabo/bees:v1.0.0 . -f Dockerfile-Bees
docker push bees:v1.0.0

kubectl apply -f Deployment.yaml
