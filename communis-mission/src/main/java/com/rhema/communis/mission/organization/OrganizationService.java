package com.rhema.communis.mission.organization;

import com.rhema.communis.common.AbstractService;
import com.rhema.communis.domain.Address;
import com.rhema.communis.mission.domain.program.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService extends AbstractService<Organization, String> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrganizationRepository regionRepository;

    @Autowired
    public OrganizationService(OrganizationRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Organization> event) {
        Address address = event.getSource().getAddress();
        if (address != null && address.getId() == null) {
            getMongoOperations().save(event.getSource().getAddress());
        }
    }
}
