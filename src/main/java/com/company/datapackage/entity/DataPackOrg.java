package com.company.datapackage.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "DATA_PACK_ORG", indexes = {
        @Index(name = "IDX_DATA_PACK_ORG_DATA_PACKAGE", columnList = "DATA_PACKAGE_ID"),
        @Index(name = "IDX_DATA_PACK_ORG_ORGANIZATION", columnList = "ORGANIZATION_ID")
})
@Entity
public class DataPackOrg {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "DATA_PACKAGE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private DataPackage dataPackage;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "ORGANIZATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public DataPackage getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(DataPackage dataPackage) {
        this.dataPackage = dataPackage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}