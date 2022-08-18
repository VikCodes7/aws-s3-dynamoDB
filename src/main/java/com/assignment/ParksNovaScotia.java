package com.assignment;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ParksNovaScotia {

    int Id;
    String address;
    String contact_number;
    String location;
    String name;
    int size;
    String type_of_park;

    TypeOfCampSites typeOfCampSites;

    public TypeOfCampSites getTypeOfCampSites() {
        return typeOfCampSites;
    }

    public void setTypeOfCampSites(TypeOfCampSites typeOfCampSites) {
        this.typeOfCampSites = typeOfCampSites;
    }

    @DynamoDbPartitionKey
    public int getId() {
        return Id;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getType_of_park() {
        return type_of_park;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType_of_park(String type_of_park) {
        this.type_of_park = type_of_park;
    }
}
