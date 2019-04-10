package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ExtractDetails.
 */
@Entity
@Table(name = "extract_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_key")
    private String key;

    @Lob
    @Column(name = "jhi_value")
    private byte[] value;

    @Column(name = "jhi_value_content_type")
    private String valueContentType;

    @ManyToOne
    @JsonIgnoreProperties("extractDetails")
    private ExtractConfig extractConfig;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public ExtractDetails key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public ExtractDetails value(byte[] value) {
        this.value = value;
        return this;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getValueContentType() {
        return valueContentType;
    }

    public ExtractDetails valueContentType(String valueContentType) {
        this.valueContentType = valueContentType;
        return this;
    }

    public void setValueContentType(String valueContentType) {
        this.valueContentType = valueContentType;
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public ExtractDetails extractConfig(ExtractConfig extractConfig) {
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
        ExtractDetails extractDetails = (ExtractDetails) o;
        if (extractDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractDetails{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", valueContentType='" + getValueContentType() + "'" +
            "}";
    }
}
