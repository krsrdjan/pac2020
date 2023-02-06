# README #

### What is this repository for? ###

Complete project (frontend, backend, infrastructure) for the PAC 2020 application (Conferencing app)

### How do I get set up? ###

* Summary of set up

Just start install.sh script from root folder. You need to have active internet connection to get Docker images from Docker hub.

* Configuration

Minikube configuration exist in install.sh script. Here is it:
```
minikube config set cpus 2
minikube config set memory 4096
minikube config set disk-size 50g
``` 


After minikube is created, you will need to update your /etc/hosts file to include the following:
                                                                              
| Address | Host |
| --------------- | --------------------- |
| minikube_ip  |  conference.frontend |
| minikube_ip  |  conference.backend |
| minikube_ip  |  conference.keycloak |
| minikube_ip  |  conference.prometheus |
| minikube_ip  |  conference.grafana |
| minikube_ip  |  keycloak-http.keycloak.svc.cluster.local |
                                                                              

Note: Minikube ip can be found by running `minikube ip`.  

* Dependencies

You need to have Docker, Kubernetes, Minikube, Terraform and Helm set up on your local machine.

* Database configuration

Project is using Mongo database and test data is inserted on first creation of backend Spring Boot API system. 

* How to run tests

There are none.

* Deployment instructions

Just start install.sh script from root folder. Mongo replica set might report error but it will be active after total of 12 min.

Get keycloak password with:

kubectl get secret -n keycloak keycloak-access --template={{.data.password}} | base64 -d

Go to url http://conference.keycloak/auth/ , login with admin and password and create test user in PAC realm pac-frontend client.

Get grafana password with:

kubectl get secret -n monitoring prometheus-operator-grafana -o yaml | grep password
echo -n 'encoded-password' | base64 -d

Login to grafana here http://conference.grafana, go to Configuration and add data source Prometheus. Then select Dashboard manage and select JVM micrometer.
You will get JVM Spring boot dashboard.
