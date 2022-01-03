package com.example.typestamagotchimicroservice.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeTamagotchiDTO {
    private String typeName;
    private String description;
    private int maxWeight;
    private int minWeight;
    private int minHealth;
    private int neuroticism;
    private int metabolism;
    private int minHappiness;
}
