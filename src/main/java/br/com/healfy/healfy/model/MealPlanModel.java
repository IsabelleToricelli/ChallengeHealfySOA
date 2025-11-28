package br.com.healfy.healfy.model;

import br.com.healfy.healfy.enums.Goals;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "MealPlan")

public class MealPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O preenchimento do nome de usuário é obrigatório")
    private String userName;

    @NotNull(message = "O objetivo é obrigátorio (Ex: EMAGRECIMENTO)")
    @Enumerated(EnumType.STRING)
    private Goals goal;

    @Positive(message = "A quantidade de calorias deve ser positiva.")
    private int calories;

    private LocalDate planDate;

    @PrePersist //Esse metodo sera analisado antes de as informacoes irem para o banco de dados
    public void prePersist(){
        if (planDate == null){
            planDate = LocalDate.now();
        }
    }

}
