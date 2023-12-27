package com.example.Diplomna.services;

import com.example.Diplomna.classValid.AccessStatusCrm;
import com.example.Diplomna.classValid.VideoCategoryCrm;
import com.example.Diplomna.model.AccessStatus;
import com.example.Diplomna.model.VideoCategory;
import com.example.Diplomna.repo.AccessStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessStatusService {
    private final AccessStatusRepo accessStatusRepo;

    @Autowired
    public AccessStatusService(AccessStatusRepo accessStatusRepo) {
        this.accessStatusRepo = accessStatusRepo;
    }

    private static AccessStatusCrm convert(AccessStatus status) {
        AccessStatusCrm repr = new AccessStatusCrm();

        repr.setId(status.getId());
        repr.setStatus(status.getStatus());
        return repr;
    }

    public List<AccessStatusCrm> findAll() {
        return accessStatusRepo.findAll().stream()
                .map(AccessStatusService::convert)
                .collect(Collectors.toList());
    }

    public Optional<AccessStatusCrm> findById(Long id) {
        return accessStatusRepo.findById(id)
                .map(AccessStatusService::convert);
    }
}
