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
 * A ExtractTables.
 */
@Entity
@Table(name = "extract_tables")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractTables implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tablename")
    private String tablename;

    @Column(name = "is_extractable")
    private Boolean isExtractable;

    @OneToMany(mappedBy = "extractTables")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractParts> extractParts = new HashSet<>();
    @OneToMany(mappedBy = "extractTables")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractOrganisationAudit> extractOrganisationAudits = new HashSet<>();
    @OneToMany(mappedBy = "extractTables")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExtractAudit> extractAudits = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public ExtractTables tablename(String tablename) {
        this.tablename = tablename;
        return this;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Boolean isIsExtractable() {
        return isExtractable;
    }

    public ExtractTables isExtractable(Boolean isExtractable) {
        this.isExtractable = isExtractable;
        return this;
    }

    public void setIsExtractable(Boolean isExtractable) {
        this.isExtractable = isExtractable;
    }

    public Set<ExtractParts> getExtractParts() {
        return extractParts;
    }

    public ExtractTables extractParts(Set<ExtractParts> extractParts) {
        this.extractParts = extractParts;
        return this;
    }

    public ExtractTables addExtractParts(ExtractParts extractParts) {
        this.extractParts.add(extractParts);
        extractParts.setExtractTables(this);
        return this;
    }

    public ExtractTables removeExtractParts(ExtractParts extractParts) {
        this.extractParts.remove(extractParts);
        extractParts.setExtractTables(null);
        return this;
    }

    public void setExtractParts(Set<ExtractParts> extractParts) {
        this.extractParts = extractParts;
    }

    public Set<ExtractOrganisationAudit> getExtractOrganisationAudits() {
        return extractOrganisationAudits;
    }

    public ExtractTables extractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
        return this;
    }

    public ExtractTables addExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.add(extractOrganisationAudit);
        extractOrganisationAudit.setExtractTables(this);
        return this;
    }

    public ExtractTables removeExtractOrganisationAudit(ExtractOrganisationAudit extractOrganisationAudit) {
        this.extractOrganisationAudits.remove(extractOrganisationAudit);
        extractOrganisationAudit.setExtractTables(null);
        return this;
    }

    public void setExtractOrganisationAudits(Set<ExtractOrganisationAudit> extractOrganisationAudits) {
        this.extractOrganisationAudits = extractOrganisationAudits;
    }

    public Set<ExtractAudit> getExtractAudits() {
        return extractAudits;
    }

    public ExtractTables extractAudits(Set<ExtractAudit> extractAudits) {
        this.extractAudits = extractAudits;
        return this;
    }

    public ExtractTables addExtractAudit(ExtractAudit extractAudit) {
        this.extractAudits.add(extractAudit);
        extractAudit.setExtractTables(this);
        return this;
    }

    public ExtractTables removeExtractAudit(ExtractAudit extractAudit) {
        this.extractAudits.remove(extractAudit);
        extractAudit.setExtractTables(null);
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
        ExtractTables extractTables = (ExtractTables) o;
        if (extractTables.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractTables.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractTables{" +
            "id=" + getId() +
            ", tablename='" + getTablename() + "'" +
            ", isExtractable='" + isIsExtractable() + "'" +
            "}";
    }
}
