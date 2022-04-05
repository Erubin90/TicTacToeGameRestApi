package io.ylab.ticTacToeGameRestApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bord_size")
    private Integer bordSize = 3;

    @Column(name = "amount_symbol_line")
    private Integer amountSymbolLine = 3;

    @Column(name = "type_game")
    private Integer typeGame;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "game")
    private Gameplay gameplay;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "game")
    private List<Step> steps;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "game")
    private List<GameStatus> gameStatusList;

    public Game(Integer bordSize, Integer amountSymbolLine, Integer typeGame) {
        this.bordSize = bordSize;
        this.amountSymbolLine = amountSymbolLine;
        this.typeGame = typeGame;
    }
}
