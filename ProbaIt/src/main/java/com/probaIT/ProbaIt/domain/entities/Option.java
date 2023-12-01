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
@Table(name = "options")
public class Option {

    @Id
    @SequenceGenerator(
            name = "options_id_seq",
            sequenceName = "options_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "options_id_seq"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    private String optionText;

}
