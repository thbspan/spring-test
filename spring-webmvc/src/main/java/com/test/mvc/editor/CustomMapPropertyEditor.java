package com.test.mvc.editor;

import java.beans.PropertyEditorSupport;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomMapPropertyEditor extends PropertyEditorSupport {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null) {
            text = text.trim();
        }
        try {
            super.setValue(mapper.readValue(text, Map.class));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
