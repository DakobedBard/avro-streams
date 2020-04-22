# 
curl -i -X POST -H  http://localhost:8083/connectors/ -d @connectors/inventory.json

Delete a connector
curl -X DELETE localhost:8083/connectors/customers-connector/product

# Console consumer
confluent local consume dbserver.inventory.customer -- --value-format avro --from-beginning --property print.key=true

# List the topics 
kafka-topics --list --zookeeper localhost:2181

INSERT INTO customers (id, first_name,last_name, email) VALUES (1005,'Dan','Pass', 'dpass@gmail.com');
INSERT INTO orders (id, order_date,purchaser,quantity, product_id) VALUES (10005,'2017-02-21',1001,3,102);
INSERT INTO customers (id, first_name,last_name, email) VALUES (1008,'Chuck','Beer','chee@gmail.com');