package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.repo.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeService {
    private final OfficeRepo officeRepo;

    @Autowired
    public OfficeService(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    //todo Try to alert the user
    public Office addOffice(Office office) {
        if(officeRepo.existsById(office.getOfficeId()) == false)
        {
            return officeRepo.save(office);
        }
        else
        {
            return findOfficeByOfficeId(office.getOfficeId());
        }
    }

    //todo Try to alert the user
    public Office updateOffice(Office office) {
        if(officeRepo.existsById(office.getOfficeId()) == true)
        {
            return officeRepo.save(office);
        }
        else
        {
            return findOfficeByOfficeId(office.getOfficeId());
        }
    }

    public List<Office> findAllOffices() {
        return officeRepo.findAll();
    }

    public Office findOfficeByOfficeId(Integer officeId) {
        return officeRepo.findOfficeByOfficeId(officeId)
                .orElseThrow(() -> new UserNotFoundException("Office by id "+ officeId + "was not found"));
    }

    public List<Office> findOfficesByCountry(String country) {
        return officeRepo.findOfficesByCountry(country)
                .orElseThrow(() -> new UserNotFoundException("Office by country "+ country + "was not found"));
    }

    @Transactional
    public void deleteOffice(Integer officeId) {
        officeRepo.deleteOfficeByOfficeId(officeId);
    }

    public List<Office> findOfficesByCity(String city) {
        return officeRepo.findOfficesByCity(city)
                .orElseThrow(() -> new UserNotFoundException("Office by city "+ city + " was not found"));
    }
}
