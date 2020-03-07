/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bram
 */
public class TypeRepositoryInMemory implements TypeRepository{
    
    private static final Map<String, Type> types = new HashMap<>();
    
    public TypeRepositoryInMemory() {
    }

    
    @Override
    public void addType(Type type) {
        if (type == null || types.containsKey(type.getName())) {
            throw new DbException("error");
        }
        types.put(type.getName(), type);
    }

    @Override
    public void removeType(String name) {
        if (name.equals("foto")) {
            throw new DbException("default waarde mag niet verwijderd worden");
        }
        types.remove(name);
    }

    @Override
    public Type getType(String name) {
        return types.get(name);
    }

    @Override
    public boolean typeExists(String name) {
        return types.containsKey(name);
    }

    @Override
    public List<Type> getTypes() {
        return new ArrayList<>(types.values());
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
