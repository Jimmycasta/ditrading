package com.jimmycasta.ditrading.utils;

import com.jimmycasta.ditrading.dto.StatisticsDTO;
import com.jimmycasta.ditrading.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
public class operationMath {

    private static TradeService tradeService = null;

    @Autowired
    public operationMath(TradeService tradeService) {
        operationMath.tradeService = tradeService;
    }


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
        LocalDate startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

        statisticsDTO.setTradesProfitCurrentMth(tradeService.getTradesProfitCurrentMth(startDate, endDate));
        statisticsDTO.setTradesLossCurrentMth(tradeService.getTradesLossCurrentMth(startDate, endDate));
        statisticsDTO.setAllTradesCurrentMonth(tradeService.getAllTradesCurrentMth(startDate, endDate));
        return statisticsDTO;
    }

}
