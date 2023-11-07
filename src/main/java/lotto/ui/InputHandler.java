package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.message.Error;
import lotto.message.Inquire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private final OutputHandler outputHandler;

    public InputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public int getValidPurchaseAmount() throws IllegalArgumentException {
        outputHandler.printMessage(Inquire.PURCHASE_AMOUNT);
        String userInput = getUserInput();
        return validatePurchaseAmount(userInput);
    }

    private String getUserInput() {
        return Console.readLine();
    }

    private int validatePurchaseAmount(String userInput) {
        if (!isPositiveInteger(userInput)) {
            throw new IllegalArgumentException(Error.POSITIVE_INTEGER.getMessage());
        }

        int purchaseAmount = Integer.parseInt(userInput);
        if (!isThousandUnits(purchaseAmount)) {
            throw new IllegalArgumentException(Error.THOUSAND_UNITS.getMessage());
        }
        return purchaseAmount;
    }

    public boolean isPositiveInteger(String str) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private boolean isThousandUnits(int purchaseAmount) {
        return purchaseAmount % 1000 == 0;
    }


    public String getWinnerNumbers() {
        outputHandler.printMessage(Inquire.WINNER_NUMBERS);
        return getUserInput();
    }

    public int getBonusNumber() {
        outputHandler.printMessage(Inquire.BONUS_NUMBER);
        return Integer.parseInt(getUserInput());
    }

}
