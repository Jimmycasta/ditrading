package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status",nullable = false,length = 15)
    private Integer idStatus;

    @Column(name = "name_status",length = 15, unique = true)
    private String nameStatus;

    @Column(name = "detail_status", length = 50)
    private String detailStatus;
}

