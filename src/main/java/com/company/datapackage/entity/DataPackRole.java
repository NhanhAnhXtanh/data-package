package com.company.datapackage.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "DATA_PACK_ROLE", indexes = {
        @Index(name = "IDX_DATA_PACK_ROLE_DATA_PACKAGE", columnList = "DATA_PACKAGE_ID"),
        @Index(name = "IDX_DATA_PACK_ROLE_ROLE", columnList = "ROLE_ID")
})
@Entity
public class DataPackRole {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "DATA_PACKAGE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DataPackage dataPackage;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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