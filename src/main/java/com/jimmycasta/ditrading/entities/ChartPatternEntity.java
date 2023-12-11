package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chart_patterns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChartPatternEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pattern", nullable = false, length = 15, unique = true)
    private Integer idPattern;

    @NotBlank(message = "Ingrese un nombre de patron")
    @Column(name = "name_pattern",nullable = false, length = 100, unique = true)
    private String namePattern;

    @Column(name = "detail_pattern", length = 100)
    private String detailPattern;
}
