package com.saber.multiplication_service.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.ToNumberPolicy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attempts")
public class ChallengeAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserDto user;
//    private Integer factorA;
//    private Integer factorB;
    @JoinColumn(name = "challengeId")
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private ChallengeDto challenge;
    private Integer resultAttempt;
    private boolean correct;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
                .create().toJson(this, ChallengeAttempt.class);
    }
}
