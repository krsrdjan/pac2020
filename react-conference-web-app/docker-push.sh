#!/bin/bash

docker login
docker build -t krsrdjan/react-conference-web-app .
docker push krsrdjan/react-conference-web-app
