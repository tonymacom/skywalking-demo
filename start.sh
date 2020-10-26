#!/usr/bin/env sh
mvn clean install -DskipTests

docker build -t itmabo/sky:v1.0.0 . -f Dockerfile-Sky
docker push itmabo/sky:v1.0.0

docker build -t itmabo/bees:v1.0.0 . -f Dockerfile-Bees
docker push itmabo/bees:v1.0.0

kubectl apply -f ui-deployment.yaml -f oap-deploymnet.yaml -f sky-deployment.yaml -f bees-deployment.yaml
