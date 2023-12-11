package com.example.Diplomna.model;

import jakarta.persistence.*;

@Entity
public class UserPremiumSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "premiumSubscriptionId")
    private PremiumSubscription premiumSubscription;

}
