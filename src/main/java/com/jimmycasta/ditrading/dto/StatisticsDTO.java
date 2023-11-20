package com.jimmycasta.ditrading.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {

    private int tradesProfitCurrentMth;

    private int tradesLossCurrentMth;

    private Double pnlBalanceCurrentMth;

    private int allTradesCurrentMonth;

    private List<String> topStrategiesCurrentMth;

    private List<String> topInstrumentCurrentMth;




}
