package io.ylab.ticTacToeGameRestApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "status")
    private String status;

    public GameStatus(Game game, String status) {
        this.game = game;
        this.status = status;
    }
}
