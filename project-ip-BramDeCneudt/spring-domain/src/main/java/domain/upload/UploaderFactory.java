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
public class UploaderFactory {
    
    public static Uploader createUploader(String type, String cloud_name, String api_key, String api_secret) {
        if (type == null) {return new UploaderLocal();}
        switch(type) {
            case "CDN": return new UploaderCDN(cloud_name, api_key, api_secret);
            default: return new UploaderLocal();
        }
    }
    
}
