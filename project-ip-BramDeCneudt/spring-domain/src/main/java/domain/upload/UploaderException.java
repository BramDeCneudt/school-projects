/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.upload;

/**
 *
 * @author Bram
 */
public class UploaderException extends RuntimeException {
    
    public UploaderException() {
        super();
    }
    public UploaderException(String message) {
        super(message);
    } 
    
}
