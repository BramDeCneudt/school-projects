/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import service.Service;

/**
 *
 * @author Bram
 */
public class StringToProjectConverter implements Converter<String, Project> {
    
    @Autowired
    public Service service;
    
    public StringToProjectConverter(@Autowired Service service) {
        this.service = service;
    }

    @Override
    public Project convert(String id) {
        
        if (id.trim().isEmpty()) {
            return null;
        }
        
        if (id == null) {
            throw new ConverterException("id is kleiner of gelijk aan 0");
        }
        long number = 0;
        try {
            number = Long.parseLong(id);
        } catch(NumberFormatException e) {
            throw new ConverterException("nummer was niet legaal");
        }

        return this.service.getProject(number);
    }
    
}
