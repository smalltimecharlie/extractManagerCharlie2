package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Organisation.
 */
@Entity
@Table(name = "organisation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_db")
    private Integer cDB;

    @Column(name = "organisation_name")
    private String organisationName;

    @Column(name = "national_practice_code")
    private String nationalPracticeCode;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "organisation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractOrganisation> extractOrganisations = new HashSet<>();
    @OneToMany(mappedBy = "organisation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractOrganisationAudit> extractOrganisationAudits = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getcDB() {
        return cDB;
    }

    public Organisation cDB(Integer cDB) {
        this.cDB = cDB;
        return this;
    }

    public void setcDB(Integer cDB) {
        this.cDB = cDB;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public Organisation organisationName(String organisationName) {
        this.organisationName = organisationName;
        return this;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getNationalPracticeCode() {
        return nationalPracticeCode;
    }

    public Organisation nationalPracticeCode(String nationalPracticeCode) {
        this.nationalPracticeCode = nationalPracticeCode;
        return this;
    }

    public void setNationalPracticeCode(String nationalPracticeCode) {
        this.nationalPracticeCode = nationalPracticeCode;
    }

    public Boolean isActive() {
        return active;
    }

    public Organisation active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<ExtractOrganisation> getExtractOrganisations() {
        return extractOrganisations;
    }

    public Organisation extractOrganisations(Set<ExtractOrganisation> extractOrganisations) {
        this.extractOrganisations = extractOrganisations;
        return this;
    }

    public Organisation addExtractOrganisation(ExtractOrganisation extractOrganisation) {
        this.extractOrganisations.add(extractOrganisation);
        extractOrganisation.setOrganisation(this);
        return this;
    }

    public Organisation removeExtractOrganisation(ExtractOrganisation extractOrganisation) {
        this.extractOrganisations.remove(extractOrganisation);
        extractOrganisation.setOrganisation(null);
        return this;
    }

    public void setExtractOrganisations(Set<ExtractOrganisation> extractOrganisations) {
        this.extractOrganisations = extractOrganisations;
    }

    public Set<ExtractOrganisationAudit> getExtractOrganisationAudits() {
        return extractOrganisationAudits;
    }

    public Organisation extractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
        return this;
    }

    public Organisation addExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.add(extractOrganisationAudit);
        extractOrganisationAudit.setOrganisation(this);
        return this;
    }

    public Organisation removeExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.remove(extractOrganisationAudit);
        extractOrganisationAudit.setOrganisation(null);
        return this;
    }

    public void setExtractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organisation organisation = (Organisation) o;
        if (organisation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Organisation{" +
            "id=" + getId() +
            ", cDB=" + getcDB() +
            ", organisationName='" + getOrganisationName() + "'" +
            ", nationalPracticeCode='" + getNationalPracticeCode() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
}
