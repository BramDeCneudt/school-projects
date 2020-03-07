package main;

import domain.Movie;
import domain.Product;
import db.DerbyDb;
import domain.Game;
import domain.Klant;
import domain.MailService;
import domain.ShopTest;
import domain.enums.ProductTypes;

public class Main {

	public static void main(String[] args) {
		
		ProductTypes test = ProductTypes.GAME;
		
		System.out.println(test.toString().toLowerCase());


	}

}
