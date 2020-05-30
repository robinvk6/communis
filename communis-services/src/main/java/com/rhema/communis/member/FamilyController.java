package com.rhema.communis.member;

import com.rhema.communis.common.CommunisError;
import com.rhema.communis.common.CommunisResponse;
import com.rhema.communis.mission.domain.family.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService){
        this.familyService = familyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunisResponse> find(@PathVariable String id){
        Family existingFamily = familyService.find(id);
        if(existingFamily == null){
            return new ResponseEntity<CommunisResponse>(new CommunisResponse(
                    new CommunisError("Unable to find family with id - " + id)),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CommunisResponse>(new CommunisResponse(existingFamily),
                HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommunisResponse> update(@PathVariable String id,
                                                         @RequestBody Family family){
        Family existingFamily = familyService.find(id);
        if(existingFamily == null){
            return new ResponseEntity<CommunisResponse>(new CommunisResponse(
                    new CommunisError("Unable to find family with id - " + id)),
                    HttpStatus.NOT_FOUND);
        }
        family.setId(id);
        family.setMembers(existingFamily.getMembers());
        return new ResponseEntity<CommunisResponse>(new CommunisResponse(
                familyService.saveOrUpdate(family)), HttpStatus.OK);
    }
}
