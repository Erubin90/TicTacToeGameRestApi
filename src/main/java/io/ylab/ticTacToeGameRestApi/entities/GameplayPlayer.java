package io.ylab.ticTacToeGameRestApi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "gameplay_players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameplayPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameplay_id")
    private Gameplay gameplay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "num")
    private Integer num;
}
