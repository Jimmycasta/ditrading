package com.jimmycasta.ditrading.utils;

import com.jimmycasta.ditrading.dto.StatisticsDTO;
import com.jimmycasta.ditrading.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class operationMath {

    private static TradeService tradeService = null;

    @Autowired
    public operationMath(TradeService tradeService) {
        operationMath.tradeService = tradeService;
    }

    static LocalDate startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    static LocalDate endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

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
        Double input = (takeProfit - entryPrice) * assetsQuantity;
        return Double.parseDouble(df.format(input));
    }

    //Envía instancia statisticsDTO con los datos de estadísticas del trading mes actual a la vista home.
    public static StatisticsDTO getAllStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setPnlBalanceCurrentMth(tradeService.getLastBalance(startDate, endDate));
        statisticsDTO.setTradesProfitCurrentMth(tradeService.getTradesProfitCurrentMth(startDate, endDate));
        statisticsDTO.setTradesLossCurrentMth(tradeService.getTradesLossCurrentMth(startDate, endDate));
        statisticsDTO.setAllTradesCurrentMonth(tradeService.getAllTradesCurrentMth(startDate, endDate));
        statisticsDTO.setTopInstrumentCurrentMth(tradeService.getTopInstrumentCurrentMth());
        statisticsDTO.setTopStrategiesCurrentMth(tradeService.getTopStrategiesCurrentMth());

        List<Boolean> openAndCloseList = tradeService.getOpenAndCloseCurrentMth(startDate, endDate);
        int openQuantity = 0;
        int closeQuantity = 0;
        for (boolean isOpen : openAndCloseList) {
            if (isOpen) {
                openQuantity++;
            } else {
                closeQuantity++;
            }
        }
        statisticsDTO.setOpenTradesCurrentMth(openQuantity);
        statisticsDTO.setCloseTradesCurrentMth(closeQuantity);
        return statisticsDTO;
    }

    //Devuelve la cantidad de decimales que tiene un número doble.
    public static int getDecimalQuantity(double number) {
        String numberString = String.valueOf(number);
        return Integer.parseInt(String.valueOf(numberString.substring(numberString.indexOf('.') + 1).length()));
    }

    //Retorna el último balance(last_balance) que es la suma de los Pnl positivos y resta de los negativos
    // de cada unos de los trades cerrados hasta ese momento.
    public static double getLastBalance(double takeProfit, double entryPrice, double assetsQuantity) {
        return (tradeService.getLastBalance(startDate, endDate)) + ((takeProfit - entryPrice) * assetsQuantity);
    }

    //Calcula el Risk/Reward
    public static String getRiskReward(double entryPrice, double takeProfit, double stopLoss, double quantity) {
        double reward = (takeProfit - entryPrice) * quantity;
        double risk = (entryPrice - stopLoss) * quantity;
        double riskReward = (reward / risk);
        return "1:" + Math.round(riskReward);
    }

}
