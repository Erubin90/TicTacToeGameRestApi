package io.ylab.ticTacToeGameRestApi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "num")
    private Integer num;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "column")
    private Integer column;

    @Column(name = "line")
    private Integer row;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;
}
