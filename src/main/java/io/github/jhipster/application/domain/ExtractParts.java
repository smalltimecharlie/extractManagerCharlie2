package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ExtractParts.
 */
@Entity
@Table(name = "extract_parts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractParts implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extract_part")
    private String extractPart;

    @Column(name = "retention_period")
    private Integer retentionPeriod;

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "compression")
    private String compression;

    @Column(name = "p_gp_cert")
    private String pGPCert;

    @Column(name = "s_ftp_pub_key")
    private String sFTPPubKey;

    @Column(name = "s_ftp_username")
    private String sFTPUsername;

    @Column(name = "encryption_type")
    private String encryptionType;

    @ManyToOne
    @JsonIgnoreProperties("extractParts")
    private ExtractConfig extractConfig;

    @ManyToOne
    @JsonIgnoreProperties("extractParts")
    private ExtractTables extractTables;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtractPart() {
        return extractPart;
    }

    public ExtractParts extractPart(String extractPart) {
        this.extractPart = extractPart;
        return this;
    }

    public void setExtractPart(String extractPart) {
        this.extractPart = extractPart;
    }

    public Integer getRetentionPeriod() {
        return retentionPeriod;
    }

    public ExtractParts retentionPeriod(Integer retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
        return this;
    }

    public void setRetentionPeriod(Integer retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public ExtractParts fileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public ExtractParts retryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getSchedule() {
        return schedule;
    }

    public ExtractParts schedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getFileType() {
        return fileType;
    }

    public ExtractParts fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCompression() {
        return compression;
    }

    public ExtractParts compression(String compression) {
        this.compression = compression;
        return this;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getpGPCert() {
        return pGPCert;
    }

    public ExtractParts pGPCert(String pGPCert) {
        this.pGPCert = pGPCert;
        return this;
    }

    public void setpGPCert(String pGPCert) {
        this.pGPCert = pGPCert;
    }

    public String getsFTPPubKey() {
        return sFTPPubKey;
    }

    public ExtractParts sFTPPubKey(String sFTPPubKey) {
        this.sFTPPubKey = sFTPPubKey;
        return this;
    }

    public void setsFTPPubKey(String sFTPPubKey) {
        this.sFTPPubKey = sFTPPubKey;
    }

    public String getsFTPUsername() {
        return sFTPUsername;
    }

    public ExtractParts sFTPUsername(String sFTPUsername) {
        this.sFTPUsername = sFTPUsername;
        return this;
    }

    public void setsFTPUsername(String sFTPUsername) {
        this.sFTPUsername = sFTPUsername;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public ExtractParts encryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
        return this;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public ExtractParts extractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
        return this;
    }

    public void setExtractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
    }

    public ExtractTables getExtractTables() {
        return extractTables;
    }

    public ExtractParts extractTables(ExtractTables extractTables) {
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
        ExtractParts extractParts = (ExtractParts) o;
        if (extractParts.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractParts.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractParts{" +
            "id=" + getId() +
            ", extractPart='" + getExtractPart() + "'" +
            ", retentionPeriod=" + getRetentionPeriod() +
            ", fileFormat='" + getFileFormat() + "'" +
            ", retryCount=" + getRetryCount() +
            ", schedule='" + getSchedule() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", compression='" + getCompression() + "'" +
            ", pGPCert='" + getpGPCert() + "'" +
            ", sFTPPubKey='" + getsFTPPubKey() + "'" +
            ", sFTPUsername='" + getsFTPUsername() + "'" +
            ", encryptionType='" + getEncryptionType() + "'" +
            "}";
    }
}
