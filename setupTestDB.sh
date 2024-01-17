#!/bin/sh

docker run --detach --name postgresql -p 127.0.0.1:5432:5432 -e POSTGRESQL_USERNAME=secrets -e POSTGRESQL_PASSWORD=secrets -e POSTGRESQL_DATABASE=secrets bitnami/postgresql:16.1.0