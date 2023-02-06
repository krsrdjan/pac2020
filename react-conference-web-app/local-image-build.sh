#!/bin/bash

#eval $(minikube docker-env)
eval $(minikube -p minikube docker-env)
docker build -t krsrdjan/react-conference-web-app .
