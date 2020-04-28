package com.example.accessingmongodbdatarest.producer;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomersProducer {

    public static void main(String[] args) throws IOException {
        boolean createCollection = false;

        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start();

        Document document;
        String row;
        String[] columns;
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");

        if(createCollection){
            db.createCollection("customerEntity");
        }
        MongoCollection<Document> dbCollection = db.getCollection("customerEntity");

        try {
            BufferedReader br = new BufferedReader(new FileReader("stack/db/parsed_customers.csv"));
            while((row = br .readLine()) != null) {

                columns = row.split(",");
                System.out.println(columns[1]);
                document = new Document()
                        .append("_id", columns[0])
                        .append("firstName",columns[1])
                        .append("lastName",columns[2])
                        .append("email",columns[3])
                        .append("address", columns[4])
                        .append("age", columns[5]);
               dbCollection.insertOne (document);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
