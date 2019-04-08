package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ExtractOrganisation.
 */
@Entity
@Table(name = "extract_organisation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractOrganisation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organisation")
    private String organisation;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne
    @JsonIgnoreProperties("extractOrganisations")
    private Organisation organisation;

    @ManyToOne
    @JsonIgnoreProperties("extractOrganisations")
    private ExtractConfig extractConfig;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public ExtractOrganisation organisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public ExtractOrganisation modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ExtractOrganisation createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public ExtractOrganisation organisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public ExtractOrganisation extractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
        return this;
    }

    public void setExtractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
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
        ExtractOrganisation extractOrganisation = (ExtractOrganisation) o;
        if (extractOrganisation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractOrganisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractOrganisation{" +
            "id=" + getId() +
            ", organisation='" + getOrganisation() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
