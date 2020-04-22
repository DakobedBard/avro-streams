#!/bin/bash
docker exec -it db psql 'host=postgres user=postgres password=postgres dbname=customersDB port=5432 options=--search_path=inventory'
