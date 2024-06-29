package com.alura.ForumHubChallenge.domain.topico;

import com.alura.ForumHubChallenge.domain.curso.Curso;
import com.alura.ForumHubChallenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// entidade JPA
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor   //lombok
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    private LocalDateTime data = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    //private Long idAutor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    //private Long idCurso;

//    @PrePersist
//    public void onCreate() {
//        data = LocalDateTime.now();
//    }

//    @OneToMany(mappedBy = "topico")
//    private List<Resposta> respostas;

    public Topico(DadosRegistroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.status = dados.status();
       // this.autor = new Usuario(dados.autor());
        //this.curso = new Curso(dados.curso());
        //this.idAutor = getIdAutor();
       // this.idCurso = getIdCurso();
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", autor=" + autor +
                ", curso=" + curso +
                '}';
    }
}
