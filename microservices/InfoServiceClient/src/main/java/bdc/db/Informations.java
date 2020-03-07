package bdc.db;

import bdc.domain.Information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Informations {

    HashMap<String, Information> informations;

    public Informations() {
        informations = new HashMap<>();

        Information information = new Information("Leuven", " is een stad en gemeente in de Belgische provincie Vlaams-Brabant. Het is de hoofdstad van deze provincie en tevens de hoofdplaats van het bestuurlijke en gerechtelijke arrondissement Leuven. Leuven heeft een oppervlakte van 5.663 ha en rondde in de loop van 2016 voor het eerst de kaap van 100.000 inwoners");
        this.addInformation(information);
        information = new Information("Blanden", "is een deelgemeente van Oud-Heverlee. Ten zuiden van het dorp ligt het Meerdaalbos en ten noordwesten het Heverleebos. ");
        this.addInformation(information);
        information = new Information("Haasrode", "is een deelgemeente van Oud-Heverlee. De inwoners van Haasrode worden de 'pootefretters' genoemd. Aan de Gemeentelijke Basisschool in de Armand Verheydenstraat staat een standbeeldje van 'De Pootefretter' om dit te illustreren. Het beeld werd gemaakt door kunstenaar Ad Wouters.");
        this.addInformation(information);
    }

    public void addInformation(Information information) {

        if (information == null) {
            throw new DBException();
        }
        if (informations.containsKey(information.getCity())) {
            throw new AlreadyExistException();
        }

        informations.put(information.getCity(), information);
    }

    public Information getInformation(String city) {

        if (city == null) {
            throw new DBException();
        }


        return informations.get(city);
    }

    public void removeInformation(String city) {

        if (city == null) {
            throw new DBException();
        }

        informations.remove(city);
    }

    public void updateInformation(Information information) {

        if (information == null) {
            throw new DBException();
        }

        informations.put(information.getCity(), information);
    }

    public List<Information> getInformations() {
        return new ArrayList<>(informations.values());
    }

}
