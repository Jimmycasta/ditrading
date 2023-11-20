package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "trades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trade", nullable = false, length = 15)
    private Integer idTrade;

    @Column(name = "trade_number", length = 15)
    private Integer tradeNumber;

    @NotNull(message = "Ingrese cantidad")
    @Column(name = "assets_quantity", length = 15)
    private Double assetsQuantity;

    @Column(name = "entry_date", length = 15)
    private LocalDate entryDate;

    @Column(name = "exit_date", length = 15)
    private LocalDate exitDate;

    @NotNull(message = "Ingrese Precio/Compra")
    @Column(name = "entry_price", length = 15)
    private Double entryPrice;

    //@NotNull(message = "Ingrese Precio/Venta!")
    @Column(name = "take_profit", length = 15)
    private Double takeProfit;

    @Column(name = "entry_balance", length = 50)
    private Double entryBalance;

    @Column(name = "exit_balance", length = 50)
    private Double exitBalance;

    @Column(name = "return_investment", length = 15)
    private Double returnInvestment;

    @NotNull(message = "Ingrese Stop/Loss")
    @Column(name = "stop_loss", length = 15)
    private Double stopLoss;

    @Column(name = "pnl_balance", length = 15)
    private Double pnlBalance;

    @Column(name = "pnl_percentage", length = 15)
    private Double pnlPercentage;

    @NotEmpty(message = "Ingrese R/R")
    @Column(name = "risk_reward", length = 15)
    private String riskReward;

    @Column(name = "entry_length",length = 15)
    private int entryLength;

    @Column(name = "stop_length", length = 15)
    private int stopLength;

    @Column(name = "profit_length", length = 15)
    private int profitLength;

    @Column(name = "pnl_length", length = 15)
    private int pnlLength;

    @Column(length = 200)
    private String notes;

    private String images;

    @NotNull(message = "Ingrese símbolo")
    @ManyToOne
    @JoinColumn(name = "id_symbol")
    private SymbolEntity symbol;

    @NotNull(message = "Intervalo")
    @ManyToOne
    @JoinColumn(name = "id_frame")
    private TimeFrameEntity timeFrame;

    @NotNull(message = "Posición")
    @ManyToOne
    @JoinColumn(name = "id_position")
    private PositionEntity position;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "id_trader")
    private TraderEntity trader;

    @ManyToOne
    @JoinColumn(name = "id_pnl_result")
    private PnLResultEntity pnlResult;

    @NotNull(message = "Ingrese estrategia")
    @ManyToOne
    @JoinColumn(name = "id_strategy")
    private StrategyEntity strategy;

}
