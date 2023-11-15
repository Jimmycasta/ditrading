package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

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
    @Query(value = "SELECT COUNT(id_symbol) FROM trades WHERE entry_date AND exit_date BETWEEN  :startDate AND :endDate", nativeQuery = true)
    int getAllTradesCurrentMth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
