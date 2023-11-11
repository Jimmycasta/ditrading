package com.jimmycasta.ditrading.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "traders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trader", nullable = false, length = 15)
    private Integer idTrader;

    @NotEmpty(message = "Debe de ingresar el nombre")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotEmpty(message = "Debe de ingresar el apellido")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotEmpty(message = "Ingrese el nombre de usuario")
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @NotEmpty(message = "Ingrese la contraseña")
    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String celular;

    @Column(name = "is_active",nullable = false,  columnDefinition = "boolean default true")
    private boolean isActive;

}
