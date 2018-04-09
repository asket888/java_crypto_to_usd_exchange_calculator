package gui;

import api.CoinCalculator;
import api.CoinValueData;
import api.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CryptoCalcGui implements CoinValueData{

    private HttpClientUtil httpClientUtil = new HttpClientUtil();

    private Box createCoinBox(JLabel coinNameLabel, JLabel coinPriceLabel, JLabel coinValueLabel) {

        final Box coinBox = Box.createHorizontalBox();
        coinBox.add(coinNameLabel);
        coinBox.add(Box.createHorizontalGlue());
        coinBox.add(Box.createHorizontalStrut(20));
        coinBox.add(coinPriceLabel);
        coinBox.add(Box.createHorizontalGlue());
        coinBox.add(Box.createHorizontalStrut(20));
        coinBox.add(coinValueLabel);

        return coinBox;
    }

    public void createCalculatorMainBox() {

        final JFrame frame = new JFrame("Altcoin portfolio calculator v.1.01");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // create horizontal panel for coin/price/value column label
        JLabel coinLabel = new JLabel(StringUtils.rightPad("COIN",25," "));
        JLabel priceLabel = new JLabel("PRICE");
        JLabel valueLabel = new JLabel("VALUE");
        final Box titleBox = createCoinBox(coinLabel, priceLabel, valueLabel);

        // create horizontal panel for DOGECOIN price input
        final JLabel dogecoinNameLabel = new JLabel("Dogecoin (DOGE):");
        final JLabel dogecoinPriceLabel = new JLabel("********");
        final JLabel dogecoinValueLabel = new JLabel(String.valueOf(DOGECOIN_VALUE));
        final Box dogecoinBox = createCoinBox(
                dogecoinNameLabel, dogecoinPriceLabel, dogecoinValueLabel);

        // create horizontal panel for DIGIBYTE price input
        final JLabel digibyteNameLabel = new JLabel(StringUtils.rightPad("Digibyte (DGB):",18," "));
        final JLabel digibytePriceLabel = new JLabel("********");
        final JLabel digibyteValueLabel = new JLabel(String.valueOf(DIGIBYTE_VALUE));
        final Box digibyteBox = createCoinBox(
                digibyteNameLabel, digibytePriceLabel, digibyteValueLabel);

        // create horizontal panel for STELLAR price input
        final JLabel stellarNameLabel = new JLabel(StringUtils.rightPad("Stellar (XLM):",18," "));
        final JLabel stellarPriceLabel = new JLabel("********");
        final JLabel stellarValueLabel = new JLabel(String.valueOf(STELLAR_VALUE));
        final Box stellarBox = createCoinBox(
                stellarNameLabel, stellarPriceLabel, stellarValueLabel);

        // create horizontal panel for RIPPLE price input
        final JLabel rippleNameLabel = new JLabel(StringUtils.rightPad("Ripple (XRP):",18," "));
        final JLabel ripplePriceLabel = new JLabel("********");
        final JLabel rippleValueLabel = new JLabel(String.valueOf(RIPPLE_VALUE));
        final Box rippleBox = createCoinBox(
                rippleNameLabel, ripplePriceLabel, rippleValueLabel);

        // create horizontal panel for NEM price input
        final JLabel nemNameLabel = new JLabel(StringUtils.rightPad("NEM (XEM):",18," "));
        final JLabel nemPriceLabel = new JLabel("********");
        final JLabel nemValueLabel = new JLabel(String.valueOf(NEM_VALUE));
        final Box nemBox = createCoinBox(
                nemNameLabel, nemPriceLabel, nemValueLabel);

        // create horizontal panel for calculation results
        Box resultsBox = Box.createHorizontalBox();
        final JLabel resultsLabel = new JLabel(" ");
        resultsBox.add(resultsLabel);

        // create horizontal panel for buttons
        Box buttonsBox = Box.createHorizontalBox();
        final JButton acceptButton = new JButton("Calculate");
        final JButton cancelButton = new JButton("Cancel");
        buttonsBox.add(acceptButton);
        buttonsBox.add(Box.createHorizontalGlue());
        buttonsBox.add(cancelButton);

        // set all panels on one vertical
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(50, 50, 50, 50));
        mainBox.add(titleBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(dogecoinBox);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(digibyteBox);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(stellarBox);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(rippleBox);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(nemBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(resultsBox);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(buttonsBox);

        // set frame settings
        frame.setContentPane(mainBox);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        acceptButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double dogecoinPrice, digibytePrice, stellarPrice, ripplePrice, nemPrice;

                dogecoinPriceLabel.setText(httpClientUtil.getApiResponse("dogecoin").substring(0,6));
                digibytePriceLabel.setText(httpClientUtil.getApiResponse("digibyte").substring(0,6));
                stellarPriceLabel.setText(httpClientUtil.getApiResponse("stellar").substring(0,6));
                ripplePriceLabel.setText(httpClientUtil.getApiResponse("ripple").substring(0,6));
                nemPriceLabel.setText(httpClientUtil.getApiResponse("nem").substring(0,6));

                dogecoinPrice = Double.parseDouble(dogecoinPriceLabel.getText());
                digibytePrice = Double.parseDouble(digibytePriceLabel.getText());
                stellarPrice = Double.parseDouble(stellarPriceLabel.getText());
                ripplePrice = Double.parseDouble(ripplePriceLabel.getText());
                nemPrice = Double.parseDouble(nemPriceLabel.getText());

                CoinCalculator calculator = new CoinCalculator();

                Font resultsFont = new Font("Georgia", Font.BOLD, 34);
                resultsLabel.setFont(resultsFont);
                resultsLabel.setForeground(Color.GREEN);
                resultsLabel.setText(calculator.convertCoinToUsd(dogecoinPrice, digibytePrice,
                        stellarPrice, ripplePrice, nemPrice));
            }
        });
    }
}