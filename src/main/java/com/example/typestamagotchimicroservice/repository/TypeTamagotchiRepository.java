package com.example.typestamagotchimicroservice.repository;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTamagotchiRepository extends JpaRepository<TypeTamagotchi, Integer> {
}
