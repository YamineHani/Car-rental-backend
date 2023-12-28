package com.carrental.carrental.service;

import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.Branch;
import com.carrental.carrental.repo.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    private final OfficeRepo officeRepo;

    @Autowired
    public OfficeService(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    public ResponseEntity<?> addOffice(Office office) {
        if(officeRepo.existsById(office.getOfficeId()) == false)
        {
            officeRepo.save(office);
            return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Office with id " + office.getOfficeId() + " already exists", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> findAllOffices() {
        List<Office> offices = officeRepo.findAll();
        if(offices.isEmpty())
        {
            return new ResponseEntity<>("No offices currently exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offices, HttpStatus.FOUND);
    }

    public ResponseEntity<?> updateOffice(Office office) {
        if(officeRepo.existsById(office.getOfficeId()) == true)
        {
            officeRepo.save(office);
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("No office with id " + office.getOfficeId() + " was found", HttpStatus.NO_CONTENT);
    }

    @Transactional
    public void deleteOffice(Integer officeId) {
        officeRepo.deleteOfficeByOfficeId(officeId);
    }

    public ResponseEntity<?> findOfficeByOfficeId(Integer officeId) {
        Optional<Office> office = officeRepo.findOfficeByOfficeId(officeId);
        if(office.isPresent())
        {
            return new ResponseEntity<>(office, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No office with id " + officeId + " was found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findOfficesByCountry(String country) {
        Optional<List<Office>> offices = officeRepo.findOfficesByCountry(country);
        if(offices.isPresent())
        {
            return new ResponseEntity<>(offices, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No office in " + country + " was found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findOfficesByCity(String city) {
        Optional<List<Office>> offices = officeRepo.findOfficesByCity(city);
        if(offices.isPresent())
        {
            return new ResponseEntity<>(offices, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No office in " + city + " was found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findOfficesByBranch(Branch branch) {
        Optional<List<Office>> offices = officeRepo.findOfficesByBranch(branch);
        if(offices.isPresent())
        {
            return new ResponseEntity<>(offices, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No office in " + branch.getDisplayName() + " was found", HttpStatus.NOT_FOUND);
    }
}
