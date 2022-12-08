package it.gestione.gestionedegliutenti.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "TB_UTENTI")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NOME", updatable = false)
    String name;

    @Column(name = "COGNOME", updatable = false)
    String surname;

    @Column(name = "MAIL")
    String mail;

    @Column(name = "INDIRIZZO")
    String address;
}
