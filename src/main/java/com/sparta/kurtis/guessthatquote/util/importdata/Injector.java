package com.sparta.kurtis.guessthatquote.util.importdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.kurtis.guessthatquote.entities.AuthorsEntity;
import com.sparta.kurtis.guessthatquote.entities.QuotesEntity;
import com.sparta.kurtis.guessthatquote.repositories.AuthorsRepository;
import com.sparta.kurtis.guessthatquote.repositories.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Injector {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<QuotablePOJOItem> quotablePOJO;

    public List<QuotablePOJOItem> jsonToPOJO() {
        try {
            quotablePOJO = Arrays.asList(objectMapper.readValue(new FileReader("src/main/resources/templates/json/quotable.json"), QuotablePOJOItem[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quotablePOJO;
    }
}
