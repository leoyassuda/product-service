package br.com.lny.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module module = new Hibernate5Module();
        module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(module);
    }
}
