package com.example.typestamagotchimicroservice.model;


import javax.persistence.*;

@Entity
public class TypeTamagotchi {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String typeName;
    private String description;
    private int maxWeight;
    private int minWeight;
    private int minHealth;
    private int neuroticism;
    private int metabolism;
    private int minHappiness;

    public TypeTamagotchi() {
    }

    public TypeTamagotchi(String typeName) {
        this.typeName = typeName;
    }

    public TypeTamagotchi(String typeName, String description, int maxWeight, int minWeight, int minHealth, int neuroticism, int metabolism, int minHappiness) {
        this.typeName = typeName;
        this.description = description;
        this.maxWeight = maxWeight;
        this.minWeight = minWeight;
        this.minHealth = minHealth;
        this.neuroticism = neuroticism;
        this.metabolism = metabolism;
        this.minHappiness = minHappiness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public int getMinHealth() {
        return minHealth;
    }

    public void setMinHealth(int minHealth) {
        this.minHealth = minHealth;
    }

    public int getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(int neuroticism) {
        this.neuroticism = neuroticism;
    }

    public int getMetabolism() {
        return metabolism;
    }

    public void setMetabolism(int metabolism) {
        this.metabolism = metabolism;
    }

    public int getMinHappiness() {
        return minHappiness;
    }

    public void setMinHappiness(int minHappiness) {
        this.minHappiness = minHappiness;
    }
}
