package com.example.typestamagotchimicroservice.repository;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeTamagotchiRepository extends JpaRepository<TypeTamagotchi, Integer> {
    @Query(value= "SELECT * FROM TypeTamagotchis ", nativeQuery = true)
    List<TypeTamagotchi> vindAlles();
    TypeTamagotchi findTypeTamagotchiById(int id);
    TypeTamagotchi findTypeTamagotchiByTypeName(String typeName);
    List<TypeTamagotchi> findTypeTamagotchisByTypeNameContaining(String typeName);
}
