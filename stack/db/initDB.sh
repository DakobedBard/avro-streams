#!/bin/bash
docker cp db/customers.csv db:/customers.csv
docker cp db/address.csv db:/address.csv
docker cp db/populate.sql db:/populate.sql
docker exec db psql 'host=postgres user=postgres password=postgres dbname=customersDB port=5432 options=--search_path=inventory' -f populate.sql