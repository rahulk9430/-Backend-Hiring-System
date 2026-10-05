package com.example.recruitment.service.impl;

import com.example.recruitment.models.Offer;
import com.example.recruitment.repository.OfferRepository;
import com.example.recruitment.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  @Override
  public Offer getMyOffer(String username) {

    return offerRepository
        .findByCandidateUsername(username)
        .orElseThrow(() -> new RuntimeException(
            "Offer not found"));
  }

  @Override
  public Offer createOffer(Offer offer) {

    if (offer.getStatus() == null) {
      offer.setStatus("DRAFT");
    }

    return offerRepository.save(offer);
  }

  @Override
  public List<Offer> getAllOffers() {
    return offerRepository.findAll();
  }

  @Override
  public Offer getOfferById(Long id) {

    return offerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Offer not found with id: " + id));
  }

  @Override
  public Offer getOfferByCandidateId(Long candidateId) {

    return offerRepository.findByCandidateId(candidateId)
        .orElseThrow(() -> new RuntimeException(
            "Offer not found for candidate: " + candidateId));
  }

  @Override
  public Offer updateOffer(Long id, Offer offer) {

    Offer existingOffer = getOfferById(id);

    existingOffer.setOfferedSalary(offer.getOfferedSalary());
    existingOffer.setOfferDate(offer.getOfferDate());
    existingOffer.setJoiningDate(offer.getJoiningDate());

    return offerRepository.save(existingOffer);
  }

  @Override
  public void sendOffer(Long id) {

    Offer offer = getOfferById(id);

    offer.setStatus("SENT");

    offerRepository.save(offer);
  }

  @Override
  public void acceptOffer(Long id) {

    Offer offer = getOfferById(id);

    offer.setStatus("ACCEPTED");

    offerRepository.save(offer);
  }

  @Override
  public void rejectOffer(Long id) {

    Offer offer = getOfferById(id);

    offer.setStatus("REJECTED");

    offerRepository.save(offer);
  }

  @Override
  public void expireOffer(Long id) {

    Offer offer = getOfferById(id);

    offer.setStatus("EXPIRED");

    offerRepository.save(offer);
  }
}