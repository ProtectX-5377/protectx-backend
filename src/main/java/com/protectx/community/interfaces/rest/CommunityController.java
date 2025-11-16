package com.protectx.community.interfaces.rest;

import com.protectx.community.domain.model.Community;
import com.protectx.community.infrastructure.persistence.CommunityRepository;
import com.protectx.community.interfaces.rest.dto.CommunityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/communities")
@CrossOrigin(origins = "http://localhost:4200")
public class CommunityController {

    private final CommunityRepository communityRepository;

    public CommunityController(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    // ---------------------
    // GET ALL
    // ---------------------
    @GetMapping
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    // ---------------------
    // GET BY ID
    // ---------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommunityByID(@PathVariable Long id) {
        return communityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------
    // GET BY NAME
    // ---------------------
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCommunityByName(@PathVariable String name) {
        return communityRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------
    // CREATE
    // ---------------------
    @PostMapping
    public ResponseEntity<?> addCommunity(@RequestBody CommunityRequest request) {

        if (communityRepository.existsByName(request.getName())) {
            return ResponseEntity.badRequest().body("El nombre elegido ya está en uso");
        }

        Community newCommunity = Community.builder()
                .name(request.getName())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .createdAt(request.getCreatedAt())
                .ownerId(request.getOwnerID())
                .build();

        Community savedCommunity = communityRepository.save(newCommunity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCommunity);
    }

    // ---------------------
    // DELETE
    // ---------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long id) {
        if (communityRepository.existsById(id)) {
            communityRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
