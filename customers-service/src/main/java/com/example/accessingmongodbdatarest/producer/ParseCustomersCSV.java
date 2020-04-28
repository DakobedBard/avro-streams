package com.example.accessingmongodbdatarest.producer;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class ParseCustomersCSV {
    public static void main(String[] args) throws IOException {
        FileWriter csvWriter = new FileWriter("stack/db/parsed_customers.csv");
//        appendHeader(csvWriter);
        String row;
        String[] columns;

        try {
            BufferedReader br = new BufferedReader(new FileReader("stack/db/customers.csv"));
            while((row = br .readLine()) != null) {
                appendRow(csvWriter, row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        csvWriter.flush();
        csvWriter.close();

    }
    private static void appendHeader(FileWriter csvWriter) throws IOException {
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("firstName");
        csvWriter.append(",");
        csvWriter.append("lastName");
        csvWriter.append(",");
        csvWriter.append("email");
        csvWriter.append(",");
        csvWriter.append("address");
        csvWriter.append(",");
        csvWriter.append("age");
        csvWriter.append("\n");
    }
    private static void appendRow(FileWriter csvWriter, String row) throws IOException {

        UUID uuid = UUID.randomUUID();
        String[] columns = row.split(",");

        csvWriter.append(uuid.toString());
        csvWriter.append(",");
        csvWriter.append(columns[2]);
        csvWriter.append(",");
        csvWriter.append(columns[3]);
        csvWriter.append(",");
        csvWriter.append(columns[4]);
        csvWriter.append(",");
        csvWriter.append("503 34 AVE");
        csvWriter.append(",");
        Integer age = (int) (50.0 * Math.random() + 15);
        csvWriter.append(age.toString( (int) (50.0 * Math.random() + 15)));
        csvWriter.append("\n");
    }
}
