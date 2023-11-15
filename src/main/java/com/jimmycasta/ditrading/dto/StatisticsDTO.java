package com.jimmycasta.ditrading.dto;

import lombok.Data;

@Data
public class StatisticsDTO {

    private int tradesProfitCurrentMth;

    private int tradesLossCurrentMth;

    private Double pnlPercentageCurrentMth;

    private Double pnlBalanceCurrentMth;

    private String topStrategiesCurrentMth;

    private String topInstrumentCurrentMth;

    private int allTradesCurrentMonth;


}
