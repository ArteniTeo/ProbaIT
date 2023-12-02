package com.probaIT.ProbaIt.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @SequenceGenerator(name = "votes_id_seq",
            sequenceName = "votes_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "votes_id_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "voted_option_id")
    private Option option;

    public Vote(Long id) {
        this.id = id;
    }

}
