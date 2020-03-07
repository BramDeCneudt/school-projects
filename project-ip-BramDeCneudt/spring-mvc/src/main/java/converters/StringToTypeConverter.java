/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import service.Service;

/**
 *
 * @author Bram
 */
public class StringToTypeConverter implements Converter<String, Type>  {
    
    @Autowired Service service;
    
    public StringToTypeConverter(@Autowired Service service) {
        this.service = service;
    }

    @Override
    public Type convert(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new ConverterException("type is empty");
        }
        return this.service.getType(id);
    }
    
}
