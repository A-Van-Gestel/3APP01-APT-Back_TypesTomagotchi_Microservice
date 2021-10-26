package com.example.typestamagotchimicroservice.controller;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import com.example.typestamagotchimicroservice.repository.TypeTamagotchiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class TypeTamagotchiController {

    @Autowired
    private TypeTamagotchiRepository typeTamagotchiRepository;

    @GetMapping("/types")
    public List<TypeTamagotchi> getAll() {
        return typeTamagotchiRepository.vindAlles();
    }

    @RequestMapping(value="/types/{id}",
            method= RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public TypeTamagotchi getTypeById(@PathVariable int id){
        return typeTamagotchiRepository.findTypeTamagotchiById(id);
    }

    @GetMapping("/types/name/{typeName}")
    public List<TypeTamagotchi> getTypesByTypeName(@PathVariable String typeName){
        return typeTamagotchiRepository.findTypeTamagotchisByTypeNameContaining(typeName);
    }

    @PostMapping("/types")
    public TypeTamagotchi addType(@RequestBody TypeTamagotchi typeTamagotchi) {
        typeTamagotchiRepository.save(typeTamagotchi);
        return typeTamagotchi;
    }

    @PutMapping("/types")
    public TypeTamagotchi modifyType(@RequestBody TypeTamagotchi updatedTamagotchiType) {
        TypeTamagotchi retrievedTypeTamagotchi = typeTamagotchiRepository.findTypeTamagotchiByTypeName(updatedTamagotchiType.getTypeName());

        retrievedTypeTamagotchi.setDescription(updatedTamagotchiType.getDescription());
        retrievedTypeTamagotchi.setMaxWeight(updatedTamagotchiType.getMaxWeight());
        retrievedTypeTamagotchi.setMetabolism(updatedTamagotchiType.getMetabolism());
        retrievedTypeTamagotchi.setMinHappiness(updatedTamagotchiType.getMinHappiness());
        retrievedTypeTamagotchi.setMinHealth(updatedTamagotchiType.getMinHealth());
        retrievedTypeTamagotchi.setNeuroticism(updatedTamagotchiType.getNeuroticism());
        retrievedTypeTamagotchi.setMinWeight(updatedTamagotchiType.getMinWeight());

        typeTamagotchiRepository.save(retrievedTypeTamagotchi);

        return retrievedTypeTamagotchi;
    }

    @DeleteMapping("/types/name/{typename}")
    public ResponseEntity deleteTamgotchiType(@PathVariable String typename) {
        TypeTamagotchi typeTamagotchi = typeTamagotchiRepository.findTypeTamagotchiByTypeName(typename);
        if (typeTamagotchi!=null) {
            typeTamagotchiRepository.delete(typeTamagotchi);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
