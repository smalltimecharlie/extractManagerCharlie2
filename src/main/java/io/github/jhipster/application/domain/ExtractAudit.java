package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ExtractAudit.
 */
@Entity
@Table(name = "extract_audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExtractAudit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extract_part")
    private String extractPart;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "message")
    private String message;

    @Column(name = "extract_start_time")
    private String extractStartTime;

    @Column(name = "extract_end_time")
    private String extractEndTime;

    @Column(name = "first_extract_point")
    private String firstExtractPoint;

    @Column(name = "last_extract_point")
    private String lastExtractPoint;

    @Column(name = "output_file_name")
    private String outputFileName;

    @Column(name = "record_count")
    private Integer recordCount;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "airflow_url")
    private String airflowUrl;

    @Column(name = "success")
    private Boolean success;

    @ManyToOne
    @JsonIgnoreProperties("extractAudits")
    private ExtractConfig extractConfig;

    @ManyToOne
    @JsonIgnoreProperties("extractAudits")
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

    public ExtractAudit extractPart(String extractPart) {
        this.extractPart = extractPart;
        return this;
    }

    public void setExtractPart(String extractPart) {
        this.extractPart = extractPart;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ExtractAudit createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public ExtractAudit message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtractStartTime() {
        return extractStartTime;
    }

    public ExtractAudit extractStartTime(String extractStartTime) {
        this.extractStartTime = extractStartTime;
        return this;
    }

    public void setExtractStartTime(String extractStartTime) {
        this.extractStartTime = extractStartTime;
    }

    public String getExtractEndTime() {
        return extractEndTime;
    }

    public ExtractAudit extractEndTime(String extractEndTime) {
        this.extractEndTime = extractEndTime;
        return this;
    }

    public void setExtractEndTime(String extractEndTime) {
        this.extractEndTime = extractEndTime;
    }

    public String getFirstExtractPoint() {
        return firstExtractPoint;
    }

    public ExtractAudit firstExtractPoint(String firstExtractPoint) {
        this.firstExtractPoint = firstExtractPoint;
        return this;
    }

    public void setFirstExtractPoint(String firstExtractPoint) {
        this.firstExtractPoint = firstExtractPoint;
    }

    public String getLastExtractPoint() {
        return lastExtractPoint;
    }

    public ExtractAudit lastExtractPoint(String lastExtractPoint) {
        this.lastExtractPoint = lastExtractPoint;
        return this;
    }

    public void setLastExtractPoint(String lastExtractPoint) {
        this.lastExtractPoint = lastExtractPoint;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public ExtractAudit outputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
        return this;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public ExtractAudit recordCount(Integer recordCount) {
        this.recordCount = recordCount;
        return this;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public ExtractAudit fileSize(Integer fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getAirflowUrl() {
        return airflowUrl;
    }

    public ExtractAudit airflowUrl(String airflowUrl) {
        this.airflowUrl = airflowUrl;
        return this;
    }

    public void setAirflowUrl(String airflowUrl) {
        this.airflowUrl = airflowUrl;
    }

    public Boolean isSuccess() {
        return success;
    }

    public ExtractAudit success(Boolean success) {
        this.success = success;
        return this;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public ExtractAudit extractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
        return this;
    }

    public void setExtractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
    }

    public ExtractTables getExtractTables() {
        return extractTables;
    }

    public ExtractAudit extractTables(ExtractTables extractTables) {
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
        ExtractAudit extractAudit = (ExtractAudit) o;
        if (extractAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), extractAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExtractAudit{" +
            "id=" + getId() +
            ", extractPart='" + getExtractPart() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", message='" + getMessage() + "'" +
            ", extractStartTime='" + getExtractStartTime() + "'" +
            ", extractEndTime='" + getExtractEndTime() + "'" +
            ", firstExtractPoint='" + getFirstExtractPoint() + "'" +
            ", lastExtractPoint='" + getLastExtractPoint() + "'" +
            ", outputFileName='" + getOutputFileName() + "'" +
            ", recordCount=" + getRecordCount() +
            ", fileSize=" + getFileSize() +
            ", airflowUrl='" + getAirflowUrl() + "'" +
            ", success='" + isSuccess() + "'" +
            "}";
    }
}
