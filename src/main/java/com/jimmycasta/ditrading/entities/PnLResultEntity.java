package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pnl_result")
public class PnLResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_result",nullable = false,length = 15)
    private Integer idPnlResult;

    @Column(name = "pnl_result", length = 50)
    private String pnlResult;

    @Column(length = 50)
    private String pnlDetail;
}

