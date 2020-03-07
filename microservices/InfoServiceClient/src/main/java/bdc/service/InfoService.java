package bdc.service;

import bdc.db.Informations;
import bdc.domain.Information;

import java.util.List;

public class InfoService {

    private Informations informations;

    public InfoService() {
        informations = new Informations();
    }

    public void addInformation(Information information) {
        informations.addInformation(information);
    }

    public Information getInformation(String city) {
        return informations.getInformation(city);
    }

    public void removeInformation(String city) {
        informations.removeInformation(city);
    }

    public void updateInformation(Information information) {
        informations.updateInformation(information);
    }

    public List<Information> getInformations() {
        return informations.getInformations();
    }

}
