package main.java.CreditCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import main.java.CardValidators.LuhnValidator;
import main.java.CardValidators.MasterCardValidator;
import main.java.CardValidators.VisaValidator;

/**
 * This class represents a credit card.
 *
 */
public class CreditCard {
	private String ccNumber;
	private String ccExpirationDate;
	private int[] digits;
	private CardType type;
	private String[] blacklist = {"4788384538552446", "5144385438523845"};

	/**
	 * @param ccNumber A credit card number as a string.
	 */
	public CreditCard(String ccNumber) {
		this.ccNumber = ccNumber;
		this.digits = parseDigits(ccNumber);
		this.type = getCardType();

	}
	
	public CreditCard(String ccNumber, String ccExpirationDate) {
		this.ccNumber = ccNumber;
		this.digits = parseDigits(ccNumber);
		this.type = getCardType();
		this.ccExpirationDate = ccExpirationDate;

	}

	/**
	 * @param ccNumber A credit card number as a string.
	 * @return An integer array containing the credit card digits.
	 */
	private int[] parseDigits(String ccNumber) {
		return ccNumber.chars().filter(i -> (i >= '0' && i <= '9'))
				               .map(i -> i - '0').toArray();
	}

	/**
	 * @return Whether or not the credit card is valid
	 * @throws ParseException 
	 */
	public boolean isValid() throws ParseException {
		String tempNum = ccNumber.replaceAll("\\s+", "");
		if (blacklist[0].equals(tempNum) || blacklist[1].equals(tempNum)) {
			System.out.println(ccNumber + " - credit card is blacklisted");
			return false;
		}
		
		if (ccExpirationDate != null) {
			if (!isValidDate(ccExpirationDate)) {
				System.out.println(ccNumber + " - credit card is expired. Please use different credit card. Date on the card is " + ccExpirationDate);
				return false;
			}
		}
		return new LuhnValidator().check(parseDigits(ccNumber));
	}

	/**
	 * @return Credit Card Network Type
	 */
	public CardType getCardType() {
		if (digits.length <= 1 || digits == null) {
			System.out.println(ccNumber + " - is an invalid card number");
			return CardType.INVALID;
		}
		if (new VisaValidator().check(digits)) {
			System.out.println(ccNumber + " - is a VISA");
			return CardType.VISA;
		}

		if (new MasterCardValidator().check(digits)) {
			System.out.println(ccNumber + " - is a MASTERCARD");
			return CardType.MASTERCARD;
		}
		else {
			System.out.println(ccNumber + " - is an invalid card number");
			return CardType.INVALID;
		}
	}
	
	/**
	 * 
	 * @param pDateString
	 * @return Checks for future date
	 * @throws ParseException
	 */
	public static boolean isValidDate(String pDateString) throws ParseException {
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(pDateString);
        return new Date().before(date);
}

	/**
	 * @return [{Card Number: []} {Invalid/Valid Card} {Type: CardType}]
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("{Card Number: " + Arrays.toString(digits) + "} ");
		try {
			if (isValid()) {
				sb.append("{Valid Card} ");
			} else {
				sb.append("{Invalid Card} ");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.append("{Type: " + type + "}");
		sb.append("]");
		return sb.toString();
	}

}
