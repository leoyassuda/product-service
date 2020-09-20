package br.com.lny.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class ProductFilter {

    private final PropertyFilter basicProperties = new SimpleBeanPropertyFilter() {
        @Override
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider provider, PropertyWriter writer) throws Exception {
            if (!writer.getName().equals("parent") && !writer.getName().equals("children") && !writer.getName().equals("images")) {
                writer.serializeAsField(obj, jsonGenerator, provider);
            } else if (!jsonGenerator.canOmitFields()) {
                writer.serializeAsOmittedField(obj, jsonGenerator, provider);
            }
        }

        @Override
        protected boolean include(BeanPropertyWriter writer) {
            return true;
        }

        @Override
        protected boolean include(PropertyWriter writer) {
            return true;
        }
    };

    private final PropertyFilter allProperties = new SimpleBeanPropertyFilter() {
        @Override
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider provider, PropertyWriter writer) throws Exception {
            if (!writer.getName().equals("parent")) {
                writer.serializeAsField(obj, jsonGenerator, provider);
            } else if (!jsonGenerator.canOmitFields()) {
                writer.serializeAsOmittedField(obj, jsonGenerator, provider);
            }
        }

        @Override
        protected boolean include(BeanPropertyWriter writer) {
            return true;
        }

        @Override
        protected boolean include(PropertyWriter writer) {
            return true;
        }
    };

    public PropertyFilter getBasicProperties() {
        return basicProperties;
    }

    public PropertyFilter getAllProperties() {
        return allProperties;
    }
}
