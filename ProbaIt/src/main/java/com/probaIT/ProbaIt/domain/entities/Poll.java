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
@Table(name="polls")
public class Poll {

    @Id
    @SequenceGenerator(
            name = "polls_id_seq",
            sequenceName = "polls_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "polls_id_seq"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    private String question;

    @Column(name = "is_multiple_choice")
    private boolean isMultipleChoice;


}
