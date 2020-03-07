package bdc.db;

import bdc.domain.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reviews {

    private HashMap<String, Review> reviews;

    public Reviews() {
        reviews = new HashMap<>();

        Review review = new Review("Voor herhaling vatbaar", "Leuven is een mooie historische stad, met veel aandacht voor de fietsers onder ons,fijne winkels, leuke pleinen, mooi stadhuis,veel leuke cafeetjes en terrasjes. Zeker de moeite waard om tijdens het eindejaar eens te bezoeken.", "Leuven");
        this.addReview(review);
        review = new Review("Een groot feest", "Bij Leuven Leisure in de Tiendstraat kan je mooie wandelingen en fietsroutes met een gids bespreken. Je kan er ook een fiets huren om zelf op pad te gaan. Het is een klein leuk bierwinkeltje wat gerund wordt door vrijwilligers. Is een stuk voordeliger dan de VVV", "Leuven");
        this.addReview(review);
        review = new Review("Mooi stad, wel snel gezien", "Leuven is een mooie plaats maar het centrum is erg klein zodat je het al snel helemaal hebt gezien. Of je moet erg van de binnenkant van kerken houden want die zijn er een paar grote. De gebouwen en huizen zijn wel erg mooi en als het mooi weer is kun je echt genieten als je op de markt op een terras zit om alleen al naar het vol te kijken. De kruidtuin is wel leuk om door te wandelen, nu was het niet zo mooi als ik denk dat het in het voorjaar kan zijn als alles in bloei staat.", "Leuven");
        this.addReview(review);
        review = new Review("Geweldig", "Heerlijk genoten van Leuven\n" +
                "We hadden 1overnachting in St Martinsolooster om gezellig in het oude centrum even bij te kletsen net vrienden. We hebben spontaan een nacht bijgeboekt!\n" +
                "Heerlijke ambiance in de stad!\n" +
                "Genoeg plekjes om te genieten van een terras/ café/bar", "Blanden");
        this.addReview(review);
        review = new Review("De 5de Belgische stad om te bezoeken", "Allereerst dit is een studentenstad en de grootste studentenstad van België. Winkelaanbod is goed voor een stad van deze grootte, twee winkelstraten een voor voetgangers en de ander voor alle buslijnen. Als je het goed aanpakt is de loop naar het stadhuis en het zien ervan een wow-factor. ", "Blanden");
        this.addReview(review);
        review = new Review("Gezellige stad", "Leuven is een hele leuke stad met veel pleintjes en terrassen\n" +
                "\n" +
                "Hele mooie gebouwen", "Blanden");
        this.addReview(review);
        review = new Review("Prachtige stad.", "Leuven is een leuke studenten stad, genoeg bezienswaardigheden voor een lang weekend, in de Muntstraat genoeg keus aan restaurants.\n" +
                "\n" +
                "Het is wel veel lopen, het ligt allemaal wat uit elkaar en de stad is heuvelachtig.", "Haasrode");
        this.addReview(review);
        review = new Review("Zeer mooie en interessante stad", "Uitgangsleven is een belevenis op zichzelf, prachtig vertoeven op de oude Markt, massa volk en super gezellig. Is soms wel moeilijk met éénrichtingsverkeer om de juiste locatie zoals hotel te zoeken", "Haasrode");
        this.addReview(review);
        review = new Review("Leuke stad, al meerdere malen geweest.", "Prachtig Begijnhof, een hele mooi Hortus Botanicus ( een echte aanraader) geweldig mooi stadhuis, div mooie gebouwen, grootste biercafe van ..de wereld? met wel ruim 2000 soorten bier, langste horeca straat ter wereld. Ook een bezoek aan de brouwerij is een aanrader.", "Haasrode");
        this.addReview(review);
    }

    public void addReview(Review review) {

        if (review == null || reviews.containsKey(review.getID())) {
            throw new DBException();
        }

        reviews.put(review.getID(), review);
    }


    public void removeReview(String id) {

        if (id == null || id.trim().isEmpty() || !reviews.containsKey(id)) {
            throw new DBException();
        }

        reviews.remove(id);
    }


    public void updateReview(Review review) {

        if (review == null || !reviews.containsKey(review.getID())) {
            throw new DBException();
        }

        reviews.put(review.getID(), review);
    }

    public Review getReview(String id) {
        return reviews.get(id);
    }

    public List<Review> getReviewsFromCity(String city) {

        ArrayList<Review> cityReviews = new ArrayList<>();

        for (Review review: reviews.values()) {
            if (review.getCity().equals(city)) {
                cityReviews.add(review);
            }
        }
        return cityReviews;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(this.reviews.values());
    }

}
