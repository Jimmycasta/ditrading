package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<TradeEntity, Integer> {

    //Cuenta los trades > 0 ó positivos en la columna pnl_percentage, para estadísticas de la vista home.
    @Query(value = "SELECT COUNT(pnl_percentage) FROM trades WHERE pnl_percentage > 0 " +
            "and entry_date and exit_date between :startDate and :endDate", nativeQuery = true)
    int getTradesProfitCurrentMth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //Cuenta los trades < 0 o negativos en la columna pnl_percentage, para estadísticas de la vista home.
    @Query(value = "SELECT COUNT(pnl_percentage) FROM trades WHERE pnl_percentage < 0 " +
            "and entry_date and exit_date between :startDate and :endDate", nativeQuery = true)
    int getTradesLossCurrentMth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //Cuenta todos los trades del mes actual
    @Query(value = "SELECT COUNT(id_symbol) FROM trades WHERE entry_date BETWEEN  :startDate AND :endDate", nativeQuery = true)
    int getAllTradesCurrentMth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Consulta el Top de los instrumentos o symbols más usados.
    @Query(value = "SELECT name_symbol FROM ditradingdb.symbols INNER JOIN ditradingdb.trades" +
            " ON symbols.id_symbol = trades.id_symbol\n" +
            "WHERE symbols.id_symbol = trades.id_symbol GROUP BY symbols.id_symbol" +
            " ORDER BY sum(trades.id_symbol) desc limit 3", nativeQuery = true)
    List<String> getTopInstrumentCurrentMth();

    // Consulta el Top de las estrategias más usados.
    @Query(value = "SELECT name_strategy FROM ditradingdb.strategy INNER JOIN ditradingdb.trades" +
            " ON strategy.id_strategy = trades.id_strategy\n" +
            "WHERE strategy.id_strategy = trades.id_strategy GROUP BY strategy.id_strategy" +
            " ORDER BY sum(trades.id_strategy) desc limit 3;", nativeQuery = true)
    List<String> getTopStrategiesCurrentMth();


    //Retorna el último balance (last_balance) es la suma de todos los trades cerrados
    @Query(value = "SELECT last_balance FROM ditradingdb.trades WHERE date_time = (SELECT MAX(date_time) from ditradingdb.trades" +
            " WHERE entry_date AND exit_date BETWEEN :startDate AND :endDate);", nativeQuery = true)
    Optional<Double> getLastBalance(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    //Retorna lista con todos los trades abiertos y cerrados para las estadísticas de home.html
    @Query(value = "SELECT is_open FROM ditradingdb.trades" +
            " WHERE entry_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Boolean> getOpenAndCloseCurrentMth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
