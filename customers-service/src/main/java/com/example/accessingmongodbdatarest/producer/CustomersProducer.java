package com.example.accessingmongodbdatarest.producer;

import com.example.accessingmongodbdatarest.entity.CustomerEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.mddarr.orders.records.dto.Customer;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.UUID;


public class CustomersProducer {
    public static void main(String[] args) {
        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start();

        Document document;
        UUID uuid;
        String row;
        String[] columns;
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        try {
            db.createCollection("customerEntity");
        }catch(Exception e){

        }
        MongoCollection<Document> dbCollection = db.getCollection("customerEntity");

        try {
            BufferedReader br = new BufferedReader(new FileReader("stack/db/customers.csv"));
            while((row = br .readLine()) != null) {
                uuid = UUID.randomUUID();
                columns = row.split(",");
                document = new Document()
                        .append("_id", uuid.toString())
                        .append("firstName",columns[2])
                        .append("lastName",columns[3])
                        .append("email",columns[4])
                        .append("address", "35th street")
                        .append("age", 32);
                dbCollection.insertOne (document);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }


    }
}
