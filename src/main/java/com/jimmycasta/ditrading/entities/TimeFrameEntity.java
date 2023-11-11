package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_frames")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeFrameEntity {

    @Id
    @Column(name = "id_frame", nullable = false, length = 15)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFrame;

    @NotEmpty(message = "Ingrese un intervalo de tiempo")
    @Column(name = "name_frame", nullable = false, length = 50, unique = true)
    private String nameFrame;

    @Column(name = "detail_frame", length = 50)
    private String detailFrame;


}