#!/bin/bash

#minikube config set cpus 2
#minikube config set memory 4096
#minikube config set disk-size 50g
#
#minikube delete
#rm ./terraform/terraform.tfstate*

minikube start
minikube addons enable ingress

eval $(minikube -p minikube docker-env)

cd terraform
./terraform.sh

minikube dashboard
