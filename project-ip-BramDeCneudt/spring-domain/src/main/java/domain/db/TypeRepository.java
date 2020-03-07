/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Type;
import java.util.List;

/**
 *
 * @author Bram
 */
public interface TypeRepository {
    
    void addType(Type type);
    void removeType(String name);
    Type getType(String name);
    boolean typeExists(String name);
    List<Type> getTypes();
    
    void closeConnection();
    
}
