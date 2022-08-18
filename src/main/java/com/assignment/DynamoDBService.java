package com.assignment;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DynamoDBService {

    public static void main(String[] args) {
        Region region = Region.US_EAST_1;
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder().region(region).build();
        DynamoDBService dynamoDBService = new DynamoDBService();
//        dynamoDBService.addCollection(dynamoDbClient);
        dynamoDBService.updateCollection(dynamoDbClient);
    }

    private void updateCollection(DynamoDbClient dynamoDbClient) {
        String tableName = "Parks_NovaScotia";
        HashMap<String, Object> newItemValues = new HashMap<>();
        ParksNovaScotia parksNovaScotia = new ParksNovaScotia();
        setParkNovaScotiaObject(parksNovaScotia);
        HashMap<String, AttributeValue> newItem = new HashMap<>();
        setItemValues(newItem, parksNovaScotia);
        newItemValues.put(":val1", parksNovaScotia.getTypeOfCampSites().getCampSiteList());
        addCollection(dynamoDbClient, newItem);
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", parksNovaScotia.getId()).
                withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "type_of_campsites")).
                withValueMap(newItemValues);
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(String.valueOf(Region.US_EAST_1)).build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable(tableName);
        UpdateItemOutcome updateItemOutcome = table.updateItem(updateItemSpec);
        System.out.println("updated the item with type of campsites new columns");
    }

    private void setParkNovaScotiaObject(ParksNovaScotia parksNovaScotia) {
        parksNovaScotia.setId(3);
        parksNovaScotia.setName("Dollar Lake");
        parksNovaScotia.setAddress("5265 Old Guysborough Road, Wyses Corner, NS B0N 1V0");
        parksNovaScotia.setContact_number("9023842770");
        parksNovaScotia.setLocation("Wyses Corner");
        parksNovaScotia.setSize(30);
        parksNovaScotia.setType_of_park("Boat Launch Parks");
        TypeOfCampSites typeOfCampSites = new TypeOfCampSites();
        List<String> typeList = new ArrayList<>();
        typeList.add("Group Campsites");
        typeOfCampSites.setCampSiteList(typeList);
        parksNovaScotia.setTypeOfCampSites(typeOfCampSites);
    }

    private void addCollection(DynamoDbClient dynamoDbClient, HashMap<String, AttributeValue> newItemValues) {
        String tableName = "Parks_NovaScotia";
        
        PutItemRequest putItemRequest = PutItemRequest.builder().tableName(tableName)
                .item(newItemValues).build();

        try {
            dynamoDbClient.putItem(putItemRequest);
            System.out.println("Item is successfully inserted");
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDb table \"%s\" cant be found. \n", tableName);
            System.out.println("Be sure the table or it exists");
            e.printStackTrace();
        } catch (DynamoDbException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setItemValues(HashMap<String, AttributeValue> newItemValues, ParksNovaScotia parksNovaScotia) {
        newItemValues.put("Id", AttributeValue.builder().n(String.valueOf(parksNovaScotia.getId())).build());
        newItemValues.put("address", AttributeValue.builder().s(parksNovaScotia.getAddress()).build());
        newItemValues.put("contact_number", AttributeValue.builder().s(parksNovaScotia.getContact_number()).build());
        newItemValues.put("location", AttributeValue.builder().s(parksNovaScotia.getLocation()).build());
        newItemValues.put("name", AttributeValue.builder().s(parksNovaScotia.getName()).build());
        newItemValues.put("size", AttributeValue.builder().n(String.valueOf(parksNovaScotia.getSize())).build());
        newItemValues.put("type_of_park", AttributeValue.builder().s(parksNovaScotia.getType_of_park()).build());
    }
}
