package school.sptech.cursos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String nome;

    @Email
    @NotEmpty
    @NotBlank
    private String email;

    @Past
    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private LocalDate dataInscricao;

    @ManyToOne
    private Curso curso;


}
