{"namespace": "org.mddarr.orders.records.dto" ,
 "type": "record",
 "name": "Customer",
 "fields": [
     {"name": "id", "type": "long"},
     {"name": "firstName", "type": "string"},
     {"name": "lastName", "type": "string"},
     {"name": "email", "type": "string"},
     {"name": "address", "type": "string"},
    {"name": "transactionDate", "type": ["int", "null"], "logicalType" : "date"}
 ]
}


  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    database: POSTGRESQL
    show-sql: true
    hibernate:
      ddl-auto: create-drop
  datasource:
    url: jdbc:postgresql://localhost:5433/productdb
    username: database1_role
    password: database1_password
    driverClassName: org.postgresql.Driver



