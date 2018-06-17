package main.java.CardValidators;

public class LuhnValidator implements Validator {
	/**
	 * <h3>
	 * The Luhn algorithm is used for validating credit card numbers.
	 * </h3>
	 * 
	 * <p>
	 * see <a href=https://en.wikipedia.org/wiki/Luhn_algorithm>
	 * https://en.wikipedia.org/wiki/Luhn_algorithm</a>

	 * @param digits a credit card number as an array of integers.
	 * @return whether the card passes the Luhn check or not.
	 */
	@Override
	public boolean check(int[] digits) {
		if (digits.length <= 1 || digits == null) {
			return false;
		}
		int total = 0;
		int check = digits[digits.length - 1]; // check digit
		boolean alternate = true;

		for (int i = digits.length - 2; i >= 0; i--) {

			// double the value of every second digit
			if (alternate) {
				digits[i] *= 2;
			}

			if (digits[i] > 9) {
				digits[i] -= 9;
			}

			alternate = !alternate;
			total += digits[i];
		}

		total += check; // add check digit to total

		return total % 10 == 0;
	}

}
