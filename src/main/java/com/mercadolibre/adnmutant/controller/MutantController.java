package com.mercadolibre.adnmutant.controller;

import com.mercadolibre.adnmutant.service.DetectMutantService;
import com.mercadolibre.adnmutant.service.StatsService;
import com.mercadolibre.adnmutant.service.dto.DnaRequest;
import com.mercadolibre.adnmutant.service.dto.StatsDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    @Autowired
    private DetectMutantService detectMutantService;

    @Autowired
    private StatsService statsService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation("validates whether a DNA belongs to a mutant or a human")
    @ApiResponses({
            @ApiResponse(code = 200, message ="OK -> Is mutant"),
            @ApiResponse(code = 403, message ="Is human"),
            @ApiResponse(code = 417, message ="invalid dna")
    })
    public ResponseEntity<String> isMutant(
            @ApiParam(  value = "DNA to validate",
                    required = true)
            @RequestBody DnaRequest dnaRequest) {
        if(detectMutantService.validateAdn(dnaRequest)){
            if (detectMutantService.isMutant(dnaRequest)) {
                return ResponseEntity.status(HttpStatus.OK).body("Is mutant");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Is human");
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("invalid dna");
    }

    @GetMapping("/stats")
    @ApiOperation("Generate a state of records between mutants and humans")
    @ApiResponse(code = 200, message ="OK")
    public ResponseEntity<StatsDTO> getStats(){
        return new ResponseEntity<>(statsService.generateStatistics(), HttpStatus.OK);
    }
}
