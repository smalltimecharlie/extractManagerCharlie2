package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ExtractOrganisationAudit.
 */
@Entity
@Table(name = "extract_organisation_audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractOrganisationAudit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "success")
    private Boolean success;

    @ManyToOne
    @JsonIgnoreProperties("extractOrganisationAudits")
    private Organisation organisation;

    @ManyToOne
    @JsonIgnoreProperties("extractOrganisationAudits")
    private ExtractConfig extractConfig;

    @ManyToOne
    @JsonIgnoreProperties("extractOrganisationAudits")
    private ExtractTables extractTables;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ExtractOrganisationAudit createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean isSuccess() {
        return success;
    }

    public ExtractOrganisationAudit success(Boolean success) {
        this.success = success;
        return this;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public ExtractOrganisationAudit organisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public ExtractOrganisationAudit extractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
        return this;
    }

    public void setExtractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
    }

    public ExtractTables getExtractTables() {
        return extractTables;
    }

    public ExtractOrganisationAudit extractTables(ExtractTables extractTables) {
        this.extractTables = extractTables;
        return this;
    }

    public void setExtractTables(ExtractTables extractTables) {
        this.extractTables = extractTables;
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
        ExtractOrganisationAudit extractOrganisationAudit = (ExtractOrganisationAudit) o;
        if (extractOrganisationAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractOrganisationAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractOrganisationAudit{" +
            "id=" + getId() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", success='" + isSuccess() + "'" +
            "}";
    }
}
