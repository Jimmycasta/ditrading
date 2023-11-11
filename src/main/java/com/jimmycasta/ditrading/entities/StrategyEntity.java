package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "strategy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_strategy", nullable = false, length = 15)
    private Integer idStrategy;

    @Column(name = "name_strategy", length = 50, unique = true)
    private String nameStrategy;

    @Column(name = "detail_strategy", length = 50)
    private String detailStrategy;
}

