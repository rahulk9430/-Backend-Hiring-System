package com.example.recruitment.service;

import com.example.recruitment.models.Offer;

import java.util.List;

public interface OfferService {

    Offer createOffer(Offer offer);

    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer getOfferByCandidateId(Long candidateId);

    Offer updateOffer(Long id, Offer offer);

    void sendOffer(Long id);

    void acceptOffer(Long id);

    void rejectOffer(Long id);

    void expireOffer(Long id);

    Offer getMyOffer(String username);
}