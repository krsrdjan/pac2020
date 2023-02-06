#!/bin/bash

minikube start
minikube addons enable ingress

kubectl apply -f mongo-persistent-volume-claim.yaml
kubectl apply -f mongo-deployment.yaml
kubectl apply -f mongo-service.yaml
kubectl apply -f student-app-api-deployment.yaml
kubectl apply -f student-app-api-service.yaml
kubectl apply -f student-app-client-deployment.yaml
kubectl apply -f student-app-client-service.yaml
kubectl apply -f student-app-ingress.yaml

minikube dashboard
minikube service --all

