package io.ylab.ticTacToeGameRestApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "gameplay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gameplay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
        mappedBy = "gameplay")
    private List<GameplayPlayer> gameplayPlayerList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_result_id")
    private GameResult gameResult;

    public Gameplay(Game game, GameResult gameResult) {
        this.game = game;
        this.gameResult = gameResult;
    }
}
