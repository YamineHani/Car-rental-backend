package com.carrental.carrental.controller;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.Branch;
import com.carrental.carrental.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
@CrossOrigin("*")
public class OfficeResource {
    private final OfficeService officeService;

    public OfficeResource(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffices() {
        return officeService.findAllOffices();
    }

    @GetMapping("/find/id/{officeId}")
    public ResponseEntity<?> getOfficeByOfficeId(@PathVariable("officeId") Integer officeId) {
        return officeService.findOfficeByOfficeId(officeId);
    }

    @GetMapping("/find/country/{country}")
    public ResponseEntity<?> getOfficesByCountry(@PathVariable("country") String country) {
        return officeService.findOfficesByCountry(country);
    }

    @GetMapping("/find/city/{city}")
    public ResponseEntity<?> getOfficesByCity(@PathVariable("city") String city) {
        return officeService.findOfficesByCity(city);
    }

    @GetMapping("/find/branch/{branch}")
    public ResponseEntity<?> getOfficesByBranch(@PathVariable("branch") Branch branch) {
        return officeService.findOfficesByBranch(branch);
    }

    /*@GetMapping("/find/cars/{officeId}")
    public ResponseEntity<List<Integer>> getOfficePlateIds(@PathVariable("officeId") Integer officeId) {
        List<Integer> cars = officeService.findOfficePlateIds(officeId);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }*/

    @PostMapping("/add")
    public ResponseEntity<?> addOffice(@RequestBody Office office) {
        return officeService.addOffice(office);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOffice(@RequestBody Office office) {
        return officeService.updateOffice(office);
    }

    @DeleteMapping("/delete/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable("officeId") Integer officeId) {
        officeService.deleteOffice(officeId);
        return new ResponseEntity<>(HttpStatus.valueOf("Deleted successfully."));
    }
}
