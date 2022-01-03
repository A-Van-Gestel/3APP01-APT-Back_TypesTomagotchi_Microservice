package com.example.typestamagotchimicroservice;


import com.example.typestamagotchimicroservice.model.TypeTamagotchi;
import com.example.typestamagotchimicroservice.repository.TypeTamagotchiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeTamagotchiControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build();

    @Autowired
    private TypeTamagotchiRepository typeTamagotchiRepository;

    private final TypeTamagotchi type1 = new TypeTamagotchi("Slijmie","Een slijmerig maar schattig dier",160,80,50,32,80,30);
    private final TypeTamagotchi type2 = new TypeTamagotchi("Slakkie","Een slak",120,70,60,98,120,40);

    @BeforeEach
    public void beforeAllTests() {
        //typeTamagotchiRepository.deleteAll();
        typeTamagotchiRepository.save(type1);
        typeTamagotchiRepository.save(type2);
    }

    @AfterEach
    public void afterAllTests() {

        typeTamagotchiRepository.deleteAll();
    }

    @Test
    public void givenType_whenGetAllTypes_thenReturnJsonTamagotchiTypes() throws Exception { //geeft lijst met alle types terug
        mockMvc.perform(get("/types"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].typeName",is("Slijmie"))) //type 1
                .andExpect(jsonPath("$[0].description",is("Een slijmerig maar schattig dier")))
                .andExpect(jsonPath("$[0].maxWeight",is(160)))
                .andExpect(jsonPath("$[0].minWeight",is(80)))
                .andExpect(jsonPath("$[0].minHealth",is(50)))
                .andExpect(jsonPath("$[0].neuroticism",is(32)))
                .andExpect(jsonPath("$[0].metabolism",is(80)))
                .andExpect(jsonPath("$[0].minHappiness",is(30)))
                .andExpect(jsonPath("$[1].typeName",is("Slakkie"))) //type 2
                .andExpect(jsonPath("$[1].description",is("Een slak")))
                .andExpect(jsonPath("$[1].maxWeight",is(120)))
                .andExpect(jsonPath("$[1].minWeight",is(70)))
                .andExpect(jsonPath("$[1].minHealth",is(60)))
                .andExpect(jsonPath("$[1].neuroticism",is(98)))
                .andExpect(jsonPath("$[1].metabolism",is(120)))
                .andExpect(jsonPath("$[1].minHappiness",is(40)));
    }

    @Test
    public void givenType_whenGetTamagotchiByTypeName_thenReturnJsonTamagotchiType() throws Exception {
        mockMvc.perform(get("/types/{typeName}","Slijmie")) //commando
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName",is("Slijmie"))) //type 1
                .andExpect(jsonPath("$.description",is("Een slijmerig maar schattig dier")))
                .andExpect(jsonPath("$.maxWeight",is(160)))
                .andExpect(jsonPath("$.minWeight",is(80)))
                .andExpect(jsonPath("$.minHealth",is(50)))
                .andExpect(jsonPath("$.neuroticism",is(32)))
                .andExpect(jsonPath("$.metabolism",is(80)))
                .andExpect(jsonPath("$.minHappiness",is(30)));

    }
    @Test
    public void givenType_whenGetTamagotchisByName_theReturnJsonType() throws Exception {
        mockMvc.perform(get("/types/name/{typeName}","Sl")) //commando
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].typeName",is("Slijmie"))) //type 1
                .andExpect(jsonPath("$[0].description",is("Een slijmerig maar schattig dier")))
                .andExpect(jsonPath("$[0].maxWeight",is(160)))
                .andExpect(jsonPath("$[0].minWeight",is(80)))
                .andExpect(jsonPath("$[0].minHealth",is(50)))
                .andExpect(jsonPath("$[0].neuroticism",is(32)))
                .andExpect(jsonPath("$[0].metabolism",is(80)))
                .andExpect(jsonPath("$[0].minHappiness",is(30)))
                .andExpect(jsonPath("$[1].typeName",is("Slakkie"))) //type 2
                .andExpect(jsonPath("$[1].description",is("Een slak")))
                .andExpect(jsonPath("$[1].maxWeight",is(120)))
                .andExpect(jsonPath("$[1].minWeight",is(70)))
                .andExpect(jsonPath("$[1].minHealth",is(60)))
                .andExpect(jsonPath("$[1].neuroticism",is(98)))
                .andExpect(jsonPath("$[1].metabolism",is(120)))
                .andExpect(jsonPath("$[1].minHappiness",is(40)));
    }
    @Test
    public void whenPostType_thenReturnJsonType() throws Exception {
        TypeTamagotchi typePost = new TypeTamagotchi(
                "Fluffy","Een pluisbol",160,80,50,32,80,30);

        mockMvc.perform(post("/types")//commando
                .content(mapper.writeValueAsString(typePost))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName",is("Fluffy"))) //type 1
                .andExpect(jsonPath("$.description",is("Een pluisbol")))
                .andExpect(jsonPath("$.maxWeight",is(160)))
                .andExpect(jsonPath("$.minWeight",is(80)))
                .andExpect(jsonPath("$.minHealth",is(50)))
                .andExpect(jsonPath("$.neuroticism",is(32)))
                .andExpect(jsonPath("$.metabolism",is(80)))
                .andExpect(jsonPath("$.minHappiness",is(30)));
    }
    @Test
    public void givenType_whenPutType_thenReturnJsonType() throws Exception {
        TypeTamagotchi typePut = new TypeTamagotchi(
                "Slakkie","Een slak",160,70,60,98,120,40);


        mockMvc.perform(put("/types")//commando
                .content(mapper.writeValueAsString(typePut))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeName",is("Slakkie"))) //type 2
                .andExpect(jsonPath("$.description",is("Een slak")))
                .andExpect(jsonPath("$.maxWeight",is(160)))
                .andExpect(jsonPath("$.minWeight",is(70)))
                .andExpect(jsonPath("$.minHealth",is(60)))
                .andExpect(jsonPath("$.neuroticism",is(98)))
                .andExpect(jsonPath("$.metabolism",is(120)))
                .andExpect(jsonPath("$.minHappiness",is(40)));
    }
    @Test
    public void givenType_whenDeleteType_thenSatuesOk() throws Exception {
        mockMvc.perform(delete("/types/name/{typeName}","Slakkie")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void givenType_whenDeleteType_thenStatusNotfound() throws Exception {
        mockMvc.perform(delete("/types/name/{typeName}","Slakkie"));
        mockMvc.perform(delete("/types/name/{typeName}","Slakkie")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    

}
