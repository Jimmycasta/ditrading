package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_position", nullable = false, length = 15)
    private Integer idPosition;

    @NotEmpty(message = "Ingrese la posición")
    @Column(name = "name_position", nullable = false, length = 100, unique = true)
    private String namePosition;

    @Column(name = "detail_position", length = 100)
    private String detailPosition;
}
