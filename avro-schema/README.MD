[{"namespace": "io.confluent.examples.streams.avro.microservices",
  "type": "enum",
  "name": "OrderState",
  "symbols" : ["CREATED", "VALIDATED", "FAILED", "SHIPPED"]
 },
  {"namespace": "org.mddarr.orders.event.dto",
    "name": "Order",
    "type":"record",
    "fields":[
        {"name": "id", "type": "string"},
        {"name": "customerId", "type": "long"},
        {"name":"productIDS","type" :  {  "type": "array","items": "long" }},
        {"name":"quantities","type" :  {  "type": "array","items": "long" }},
        {"name": "state", "type": "OrderState"}
     ]
    }
]
