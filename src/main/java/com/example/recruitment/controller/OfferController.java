package com.example.recruitment.controller;

import com.example.recruitment.models.Offer;
import com.example.recruitment.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
@CrossOrigin("*")

public class OfferController {

  private final OfferService offerService;

  @PostMapping
  public ResponseEntity<Offer> createOffer(
      @RequestBody Offer offer) {

    return ResponseEntity.ok(
        offerService.createOffer(offer));
  }

  @GetMapping("/my")
  public ResponseEntity<Offer> getMyOffer(
      @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok(
        offerService.getMyOffer(
            userDetails.getUsername()));
  }

  @GetMapping
  public ResponseEntity<List<Offer>> getAllOffers() {

    return ResponseEntity.ok(
        offerService.getAllOffers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Offer> getOfferById(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        offerService.getOfferById(id));
  }

  @GetMapping("/candidate/{candidateId}")
  public ResponseEntity<Offer> getOfferByCandidate(
      @PathVariable Long candidateId) {

    return ResponseEntity.ok(
        offerService.getOfferByCandidateId(candidateId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Offer> updateOffer(
      @PathVariable Long id,
      @RequestBody Offer offer) {

    return ResponseEntity.ok(
        offerService.updateOffer(id, offer));
  }

  @PutMapping("/{id}/send")
  public ResponseEntity<String> sendOffer(
      @PathVariable Long id) {

    offerService.sendOffer(id);

    return ResponseEntity.ok("Offer sent successfully");
  }

  @PutMapping("/{id}/accept")
  public ResponseEntity<String> acceptOffer(
      @PathVariable Long id) {

    offerService.acceptOffer(id);

    return ResponseEntity.ok("Offer accepted successfully");
  }

  @PutMapping("/{id}/reject")
  public ResponseEntity<String> rejectOffer(
      @PathVariable Long id) {

    offerService.rejectOffer(id);

    return ResponseEntity.ok("Offer rejected successfully");
  }

  @PutMapping("/{id}/expire")
  public ResponseEntity<String> expireOffer(
      @PathVariable Long id) {

    offerService.expireOffer(id);

    return ResponseEntity.ok("Offer expired successfully");
  }
}