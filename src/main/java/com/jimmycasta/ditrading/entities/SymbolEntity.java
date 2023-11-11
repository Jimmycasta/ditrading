package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "symbols")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SymbolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_symbol", nullable = false, length = 15)
    private Integer idSymbol;

    @Column(name = "name_symbol", length = 50, unique = true)
    private String nameSymbol;

    @Column(name = "detail_symbol", length = 50)
    private String detailSymbol;

}
