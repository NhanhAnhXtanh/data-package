package com.company.datapackage.entity;

import com.company.datapackage.enums.Object;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "DATA_PACKAGE")
@Entity
public class DataPackage {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "OBJECT", nullable = false)
    @NotNull
    private String object;

    @Composition
    @OneToMany(mappedBy = "dataPackage")
    private List<DataPackOrg> datapackorg;

    @Composition
    @OneToMany(mappedBy = "dataPackage")
    private List<DataPackRole> datapackrole;

    @Composition
    @OneToMany(mappedBy = "dataPackage")
    private List<DataPackUser> datapackuser;

    public List<DataPackUser> getDatapackuser() {
        return datapackuser;
    }

    @JmixProperty
    @Transient
    private String displayValue;

    public String getDisplayValue() {
        StringBuilder builder = new StringBuilder();
        Object enumObject = getObject();

        if (enumObject == null) {
            return "";
        }

        switch (enumObject) {
            case UNIT:
                if (datapackorg != null) {
                    datapackorg.stream()
                            .map(dataPackOrg -> dataPackOrg.getOrganization() != null ? dataPackOrg.getOrganization().getName() : "")
                            .filter(name -> !name.isEmpty())
                            .forEach(name -> {
                                if (builder.length() > 0) builder.append(", ");
                                builder.append(name);
                            });
                }
                break;

            case ROLE:
                if (datapackrole != null) {
                    datapackrole.stream()
                            .map(dataPackRole -> dataPackRole.getRole() != null ? dataPackRole.getRole().getName() : "")
                            .filter(name -> !name.isEmpty())
                            .forEach(name -> {
                                if (builder.length() > 0) builder.append(", ");
                                builder.append(name);
                            });
                }
                break;

            case SPECIFIC_USER:
                if (datapackuser != null) {
                    datapackuser.stream()
                            .map(dataPackUser -> dataPackUser.getUser() != null ? dataPackUser.getUser().getUsername() : "")
                            .filter(name -> !name.isEmpty())
                            .forEach(name -> {
                                if (builder.length() > 0) builder.append(", ");
                                builder.append(name);
                            });
                }
                break;
        }

        displayValue = builder.toString();
        return displayValue;
    }


    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public void setDatapackuser(List<DataPackUser> datapackuser) {
        this.datapackuser = datapackuser;
    }

    public List<DataPackRole> getDatapackrole() {
        return datapackrole;
    }

    public void setDatapackrole(List<DataPackRole> datapackrole) {
        this.datapackrole = datapackrole;
    }

    public List<DataPackOrg> getDatapackorg() {
        return datapackorg;
    }

    public void setDatapackorg(List<DataPackOrg> datapackorg) {
        this.datapackorg = datapackorg;
    }

    public Object getObject() {
        return object == null ? null : Object.fromId(object);
    }

    public void setObject(Object object) {
        this.object = object == null ? null : object.getId();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}