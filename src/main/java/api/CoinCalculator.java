package api;

public class CoinCalculator implements CoinValueData {

    private static String calculationResult;

    public String convertCoinToUsd(double dogeCoin_Price, double digiByte_Price, double stellar_Price,
                                   double ripple_Price, double nem_Price) {
        calculationResult = String.format("%.2f",
                (DOGECOIN_VALUE*dogeCoin_Price +
                DIGIBYTE_VALUE*digiByte_Price +
                STELLAR_VALUE*stellar_Price +
                RIPPLE_VALUE*ripple_Price +
                NEM_VALUE*nem_Price));
        return "$ " + calculationResult;
    }
}
