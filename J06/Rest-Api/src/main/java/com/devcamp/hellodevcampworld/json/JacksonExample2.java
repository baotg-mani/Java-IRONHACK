package com.devcamp.hellodevcampworld.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonExample2 {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			/*
			 * TypeFactory typeFactory = objectMapper.getTypeFactory(); CollectionType
			 * collectionType = typeFactory.constructCollectionType( List.class,
			 * Staff.class);
			 */
        	List<Staff> staffs = objectMapper.readValue(new File("d:\\test\\staff.json"), List.class);
        	
            // JSON file to Java object
            Staff staff = mapper.readValue(new File("d:\\test\\staff1.json"), Staff.class);

            // JSON string to Java object
            String jsonInString = "{\"name\":\"mkyong\",\"age\":37,\"skills\":[\"java\",\"python\"]}";
            Staff staff2 = mapper.readValue(jsonInString, Staff.class);

            // compact print
            System.out.println(staff2);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);

            System.out.println(prettyStaff1);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}