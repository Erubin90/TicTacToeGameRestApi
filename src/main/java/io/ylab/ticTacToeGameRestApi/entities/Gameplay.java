package io.ylab.ticTacToeGameRestApi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "gameplay_players",
            joinColumns = @JoinColumn(name = "gameplay_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_result_id")
    private GameResult result;
}
