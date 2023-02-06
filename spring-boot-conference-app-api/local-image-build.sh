#!/bin/bash

#eval $(minikube docker-env)
eval $(minikube -p minikube docker-env)
docker build -t krsrdjan/conference-app-api:0.0.1-SNAPSHOT .
