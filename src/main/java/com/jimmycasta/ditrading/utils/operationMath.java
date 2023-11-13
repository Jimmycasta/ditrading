package com.jimmycasta.ditrading.utils;

import java.text.DecimalFormat;

public class operationMath {
    //Calcula la diferencia porcentual que hay entre dos números
    //Ejemplo: lastValue = 120, firstValue = 100 la diferencia es 20%
    public static Double diffPercent(Double takeProfit, Double entryPrice) {
        DecimalFormat df = new DecimalFormat("0.0");
        Double input = ((takeProfit / entryPrice) * 100) - 100;
        return Double.parseDouble(df.format(input));
    }

    //Calcula el pnlBalance o sea la ganancia o perdida en un trade.
    public static Double calculatePnl(Double takeProfit, Double entryPrice, Double assetsQuantity) {
        DecimalFormat df = new DecimalFormat("0.00");
        Double input = (takeProfit - entryPrice ) * assetsQuantity;
        return Double.parseDouble(df.format(input));
    }

}
