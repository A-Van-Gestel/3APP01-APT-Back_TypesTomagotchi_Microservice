package com.example.typestamagotchimicroservice;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import com.example.typestamagotchimicroservice.repository.TypeTamagotchiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeTamagotchiUnitTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @MockBean
//    private TypeTamagotchiRepository typeTamagotchiRepository;
//
//    private TypeTamagotchi type1 = new TypeTamagotchi("Slijmie","Een slijmerig maar schattig dier",160,80,50,32,80,30);
//    private TypeTamagotchi type2 = new TypeTamagotchi("Slakkie","Een slak",120,70,60,98,120,40);
//
//    List<TypeTamagotchi> typeList = Arrays.asList(type1, type2);;
//
//    @Test
//    public void givenType_whenGetAllTypes_thenReturnJsonTypes() throws Exception { //geeft lijst met alle types terug
//        given(typeTamagotchiRepository.vindAlles()).willReturn(typeList);
//
//        mockMvc.perform(get("/types"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].typeName",is("Slijmie"))) //type 1
//                .andExpect(jsonPath("$[0].description",is("Een slijmerig maar schattig dier")))
//                .andExpect(jsonPath("$[0].maxWeight",is(160)))
//                .andExpect(jsonPath("$[0].minWeight",is(80)))
//                .andExpect(jsonPath("$[0].minHealth",is(50)))
//                .andExpect(jsonPath("$[0].neuroticism",is(32)))
//                .andExpect(jsonPath("$[0].metabolism",is(80)))
//                .andExpect(jsonPath("$[0].minHappiness",is(30)))
//                .andExpect(jsonPath("$[1].typeName",is("Slakkie"))) //type 2
//                .andExpect(jsonPath("$[1].description",is("Een slak")))
//                .andExpect(jsonPath("$[1].maxWeight",is(120)))
//                .andExpect(jsonPath("$[1].minWeight",is(70)))
//                .andExpect(jsonPath("$[1].minHealth",is(60)))
//                .andExpect(jsonPath("$[1].neuroticism",is(98)))
//                .andExpect(jsonPath("$[1].metabolism",is(120)))
//                .andExpect(jsonPath("$[1].minHappiness",is(40)));
//    }
//    @Test
//    public void givenType_whenGetTamagotchiById_thenReturnJsonType() throws Exception { //geeft één type terug, gezocht op id
//
//        given(typeTamagotchiRepository.findTypeTamagotchiById(1)).willReturn(type1);
//        mockMvc.perform(get("/types/{id}","1")) //commando
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.typeName",is("Slijmie"))) //type 1
//                .andExpect(jsonPath("$.description",is("Een slijmerig maar schattig dier")))
//                .andExpect(jsonPath("$.maxWeight",is(160)))
//                .andExpect(jsonPath("$.minWeight",is(80)))
//                .andExpect(jsonPath("$.minHealth",is(50)))
//                .andExpect(jsonPath("$.neuroticism",is(32)))
//                .andExpect(jsonPath("$.metabolism",is(80)))
//                .andExpect(jsonPath("$.minHappiness",is(30)));
//    }
//    @Test
//    public void givenType_whenGetTamagotchiByName_theReturnJsonType() throws Exception {
//        given(typeTamagotchiRepository.findTypeTamagotchisByTypeNameContaining("Sl")).willReturn(typeList);
//
//        mockMvc.perform(get("/types/name/{typeName}","Sl")) //commando
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].typeName",is("Slijmie"))) //type 1
//                .andExpect(jsonPath("$[0].description",is("Een slijmerig maar schattig dier")))
//                .andExpect(jsonPath("$[0].maxWeight",is(160)))
//                .andExpect(jsonPath("$[0].minWeight",is(80)))
//                .andExpect(jsonPath("$[0].minHealth",is(50)))
//                .andExpect(jsonPath("$[0].neuroticism",is(32)))
//                .andExpect(jsonPath("$[0].metabolism",is(80)))
//                .andExpect(jsonPath("$[0].minHappiness",is(30)))
//                .andExpect(jsonPath("$[1].typeName",is("Slakkie"))) //type 2
//                .andExpect(jsonPath("$[1].description",is("Een slak")))
//                .andExpect(jsonPath("$[1].maxWeight",is(120)))
//                .andExpect(jsonPath("$[1].minWeight",is(70)))
//                .andExpect(jsonPath("$[1].minHealth",is(60)))
//                .andExpect(jsonPath("$[1].neuroticism",is(98)))
//                .andExpect(jsonPath("$[1].metabolism",is(120)))
//                .andExpect(jsonPath("$[1].minHappiness",is(40)));
//    }
//    @Test
//    public void whenPostType_thenReturnJsonType() throws Exception {
//        TypeTamagotchi typePost = new TypeTamagotchi(
//                "Fluffy","Een pluisbol",160,80,50,32,80,30);
//
//        mockMvc.perform(post("/types")//commando
//                .content(mapper.writeValueAsString(typePost))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.typeName",is("Fluffy"))) //type 1
//                .andExpect(jsonPath("$.description",is("Een pluisbol")))
//                .andExpect(jsonPath("$.maxWeight",is(160)))
//                .andExpect(jsonPath("$.minWeight",is(80)))
//                .andExpect(jsonPath("$.minHealth",is(50)))
//                .andExpect(jsonPath("$.neuroticism",is(32)))
//                .andExpect(jsonPath("$.metabolism",is(80)))
//                .andExpect(jsonPath("$.minHappiness",is(30)));
//    }
//    @Test
//    public void givenType_whenPutType_thenReturnJsonType() throws Exception {
//        TypeTamagotchi typePut = new TypeTamagotchi(
//                "Slakkie","Een kleine slak",202,14,70,9999999,110,120);
//
//        given(typeTamagotchiRepository.findTypeTamagotchiByTypeName("Slakkie")).willReturn(type2);
//
//        mockMvc.perform(put("/types")//commando
//                .content(mapper.writeValueAsString(typePut))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.typeName",is("Slakkie"))) //type 2
//                .andExpect(jsonPath("$.description",is("Een kleine slak")))
//                .andExpect(jsonPath("$.maxWeight",is(202)))
//                .andExpect(jsonPath("$.minWeight",is(14)))
//                .andExpect(jsonPath("$.minHealth",is(70)))
//                .andExpect(jsonPath("$.neuroticism",is(9999999)))
//                .andExpect(jsonPath("$.metabolism",is(110)))
//                .andExpect(jsonPath("$.minHappiness",is(120)));
//    }
//    @Test
//    public void givenType_whenDeleteType_thenSatuesOk() throws Exception {
//        TypeTamagotchi typeDelete = new TypeTamagotchi("Fifi","Een hond",160,80,50,32,80,30);
//
//        given(typeTamagotchiRepository.findTypeTamagotchiByTypeName("Fifi")).willReturn(typeDelete);
//
//        mockMvc.perform(delete("/types/name/{typeName}","Fifi")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void givenType_whenDeleteType_thenStatusNotfound() throws Exception {
//
//        given(typeTamagotchiRepository.findTypeTamagotchiByTypeName("Fifi")).willReturn(null);
//
//        mockMvc.perform(delete("/types/name/{typeName}","Fifi")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
}
