package main.java.Driver;

import main.java.CreditCard.*;
import main.java.Helpers.*;

public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method

			FakeData http = new FakeData();
			String[] cardnumbers = {};
			try {
				cardnumbers = http.sendGet(10);
				System.out.println("############# CHEDKING RANDOM CREDIT CARD NUMBER FROM THE GET REUQETS WITH RANDOM DATE ####################");
				for (int i = 0; i < cardnumbers.length; i++) {
					if (i % 2 != 0) {
						CreditCard card = new CreditCard(cardnumbers[i].replaceAll("[\\D]", ""), RandomDate.GetRandomDate());
						card.isValid();
					}
				}
				System.out.println("###################################################################################################");
				System.out.println("\n\n############# CHECKING BLACKLISTED CREDIT CARD NUMBER  WITH RANDOM DATE ###########################");
				CreditCard card = new CreditCard("4788 3845 3855 2446", RandomDate.GetRandomDate());
				card.isValid();
				CreditCard card1 = new CreditCard("5144 3854 3852 3845", RandomDate.GetRandomDate());
				card1.isValid();
				System.out.println("###################################################################################################");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
	

	
}
