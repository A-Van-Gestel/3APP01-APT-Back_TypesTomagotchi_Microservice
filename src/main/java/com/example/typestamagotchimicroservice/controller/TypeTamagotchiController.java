package com.example.typestamagotchimicroservice.controller;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import com.example.typestamagotchimicroservice.model.TypeTamagotchiDTO;
import com.example.typestamagotchimicroservice.repository.TypeTamagotchiRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class TypeTamagotchiController {

    @Autowired
    private TypeTamagotchiRepository typeTamagotchiRepository;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    @GetMapping("/types")
    public List<TypeTamagotchi> getAll() {
        return typeTamagotchiRepository.vindAlles();
    }

    @GetMapping("/types/{typeName}")
    public TypeTamagotchi getTypeById(@PathVariable String typeName){
        return typeTamagotchiRepository.findTypeTamagotchiByTypeName(typeName);
    }

    @GetMapping("/types/name/{typeName}")
    public List<TypeTamagotchi> getTypesByTypeName(@PathVariable String typeName){
        return typeTamagotchiRepository.findTypeTamagotchisByTypeNameContaining(typeName);
    }

    @PostMapping("/types")
    public TypeTamagotchi addType(@RequestBody TypeTamagotchiDTO typeTamagotchiDTO) {
        TypeTamagotchi typeTamagotchi = mapper.map(typeTamagotchiDTO, TypeTamagotchi.class);
        typeTamagotchiRepository.save(typeTamagotchi);

        return typeTamagotchi;
    }

    @PutMapping("/types")
    public TypeTamagotchi modifyType(@RequestBody TypeTamagotchiDTO typeTamagotchiDTO) {
        TypeTamagotchi retrievedTypeTamagotchi = typeTamagotchiRepository.findTypeTamagotchiByTypeName(typeTamagotchiDTO.getTypeName());

        int id = retrievedTypeTamagotchi.getId();
        retrievedTypeTamagotchi = mapper.map(typeTamagotchiDTO, TypeTamagotchi.class);
        retrievedTypeTamagotchi.setId(id);

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

//    public TypeTamagotchi mapTypeTamagotchDTO_To_TypeTamgotchi(TypeTamagotchiDTO typeTamagotchiDTO) {
//
//        return  typeTamagotchi;
//    }

}
