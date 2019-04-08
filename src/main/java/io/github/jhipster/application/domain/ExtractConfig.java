package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ExtractConfig.
 */
@Entity
@Table(name = "extract_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extractname")
    private String extractname;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "requestingorg")
    private String requestingorg;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "email_contact")
    private String emailContact;

    @Column(name = "created_date")
    private Instant createdDate;

    @OneToMany(mappedBy = "extractConfig")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractOrganisation> extractOrganisations = new HashSet<>();
    @OneToMany(mappedBy = "extractConfig")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractParts> extractParts = new HashSet<>();
    @OneToMany(mappedBy = "extractConfig")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractDetails> extractDetails = new HashSet<>();
    @OneToMany(mappedBy = "extractConfig")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractOrganisationAudit> extractOrganisationAudits = new HashSet<>();
    @OneToMany(mappedBy = "extractConfig")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractAudit> extractAudits = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtractname() {
        return extractname;
    }

    public ExtractConfig extractname(String extractname) {
        this.extractname = extractname;
        return this;
    }

    public void setExtractname(String extractname) {
        this.extractname = extractname;
    }

    public String getType() {
        return type;
    }

    public ExtractConfig type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestingorg() {
        return requestingorg;
    }

    public ExtractConfig requestingorg(String requestingorg) {
        this.requestingorg = requestingorg;
        return this;
    }

    public void setRequestingorg(String requestingorg) {
        this.requestingorg = requestingorg;
    }

    public Boolean isActive() {
        return active;
    }

    public ExtractConfig active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ExtractConfig deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public ExtractConfig emailContact(String emailContact) {
        this.emailContact = emailContact;
        return this;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ExtractConfig createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Set<ExtractOrganisation> getExtractOrganisations() {
        return extractOrganisations;
    }

    public ExtractConfig extractOrganisations(Set<ExtractOrganisation> extractOrganisations) {
        this.extractOrganisations = extractOrganisations;
        return this;
    }

    public ExtractConfig addExtractOrganisation(ExtractOrganisation extractOrganisation) {
        this.extractOrganisations.add(extractOrganisation);
        extractOrganisation.setExtractConfig(this);
        return this;
    }

    public ExtractConfig removeExtractOrganisation(ExtractOrganisation extractOrganisation) {
        this.extractOrganisations.remove(extractOrganisation);
        extractOrganisation.setExtractConfig(null);
        return this;
    }

    public void setExtractOrganisations(Set<ExtractOrganisation> extractOrganisations) {
        this.extractOrganisations = extractOrganisations;
    }

    public Set<ExtractParts> getExtractParts() {
        return extractParts;
    }

    public ExtractConfig extractParts(Set<ExtractParts> extractParts) {
        this.extractParts = extractParts;
        return this;
    }

    public ExtractConfig addExtractParts(ExtractParts extractParts) {
        this.extractParts.add(extractParts);
        extractParts.setExtractConfig(this);
        return this;
    }

    public ExtractConfig removeExtractParts(ExtractParts extractParts) {
        this.extractParts.remove(extractParts);
        extractParts.setExtractConfig(null);
        return this;
    }

    public void setExtractParts(Set<ExtractParts> extractParts) {
        this.extractParts = extractParts;
    }

    public Set<ExtractDetails> getExtractDetails() {
        return extractDetails;
    }

    public ExtractConfig extractDetails(Set<ExtractDetails> extractDetails) {
        this.extractDetails = extractDetails;
        return this;
    }

    public ExtractConfig addExtractDetails(ExtractDetails extractDetails) {
        this.extractDetails.add(extractDetails);
        extractDetails.setExtractConfig(this);
        return this;
    }

    public ExtractConfig removeExtractDetails(ExtractDetails extractDetails) {
        this.extractDetails.remove(extractDetails);
        extractDetails.setExtractConfig(null);
        return this;
    }

    public void setExtractDetails(Set<ExtractDetails> extractDetails) {
        this.extractDetails = extractDetails;
    }

    public Set<ExtractOrganisationAudit> getExtractOrganisationAudits() {
        return extractOrganisationAudits;
    }

    public ExtractConfig extractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
        return this;
    }

    public ExtractConfig addExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.add(extractOrganisationAudit);
        extractOrganisationAudit.setExtractConfig(this);
        return this;
    }

    public ExtractConfig removeExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.remove(extractOrganisationAudit);
        extractOrganisationAudit.setExtractConfig(null);
        return this;
    }

    public void setExtractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
    }

    public Set<ExtractAudit> getExtractAudits() {
        return extractAudits;
    }

    public ExtractConfig extractAudits(Set<ExtractAudit> extractAudits) {
        this.extractAudits = extractAudits;
        return this;
    }

    public ExtractConfig addExtractAudit(ExtractAudit extractAudit) {
        this.extractAudits.add(extractAudit);
        extractAudit.setExtractConfig(this);
        return this;
    }

    public ExtractConfig removeExtractAudit(ExtractAudit extractAudit) {
        this.extractAudits.remove(extractAudit);
        extractAudit.setExtractConfig(null);
        return this;
    }

    public void setExtractAudits(Set<ExtractAudit> extractAudits) {
        this.extractAudits = extractAudits;
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
        ExtractConfig extractConfig = (ExtractConfig) o;
        if (extractConfig.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractConfig.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractConfig{" +
            "id=" + getId() +
            ", extractname='" + getExtractname() + "'" +
            ", type='" + getType() + "'" +
            ", requestingorg='" + getRequestingorg() + "'" +
            ", active='" + isActive() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", emailContact='" + getEmailContact() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
