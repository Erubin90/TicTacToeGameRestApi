package io.ylab.ticTacToeGameRestApi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "gameResult")
    private Gameplay gameplay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "win_player_id")
    private Player winPlayer;
}
