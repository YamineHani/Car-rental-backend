package com.carrental.carrental.controller;

import com.carrental.carrental.model.Office;
import com.carrental.carrental.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeResource {
    private final OfficeService officeService;

    public OfficeResource(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = officeService.findAllOffices();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("/find/id/{officeId}")
    public ResponseEntity<Office> getOfficeByOfficeId(@PathVariable("officeId") Integer officeId) {
        Office office = officeService.findOfficeByOfficeId(officeId);
        return new ResponseEntity<>(office, HttpStatus.OK);
    }

    @GetMapping("/find/country/{country}")
    public ResponseEntity<List<Office>> getOfficesByCountry(@PathVariable("country") String country) {
        List<Office> offices = officeService.findOfficesByCountry(country);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping("/find/city/{city}")
    public ResponseEntity<List<Office>> getOfficesByCity(@PathVariable("city") String city) {
        List<Office> offices = officeService.findOfficesByCity(city);
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Office> addOffice(@RequestBody Office office) {
        Office newOffice = officeService.addOffice(office);
        return new ResponseEntity<>(newOffice, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Office> updateOffice(@RequestBody Office office) {
        Office updatedOffice = officeService.updateOffice(office);
        return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{officeId}")
    public ResponseEntity<Office> deleteOffice(@PathVariable("officeId") Integer officeId) {
        officeService.deleteOffice(officeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
