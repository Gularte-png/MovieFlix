package br.com.movieflix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String titulo;

    @Column(length = 100, nullable = false)
    private String descricao;

    @Column(name = "data_de_lançamento")
    private LocalDate dataDeLançamento;

    private Double avaliacao;

    @CreationTimestamp
    private LocalDateTime criado;

    @UpdateTimestamp
    private LocalDateTime atualizado;

    @ManyToMany
    @JoinTable(name = "filme_categoria",
    joinColumns = @JoinColumn (name ="filme_id"),
    inverseJoinColumns = @JoinColumn (name = "categoria_id"))
    private List<Categoria> categorias;

    @ManyToMany
    @JoinTable(name = "filme_streaming",
    joinColumns = @JoinColumn(name = "filme_id"),
    inverseJoinColumns = @JoinColumn(name = "streaming_id"))
    private List<Streaming> streamings;
}
