package com.infosys.surveillance.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A SurveillanceTask.
 */
@Entity
@Table(name = "surveillance_task")
public class SurveillanceTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "version_id")
    private String versionId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public SurveillanceTask type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelName() {
        return modelName;
    }

    public SurveillanceTask modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVersionId() {
        return versionId;
    }

    public SurveillanceTask versionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SurveillanceTask)) {
            return false;
        }
        return id != null && id.equals(((SurveillanceTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveillanceTask{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", modelName='" + getModelName() + "'" +
            ", versionId='" + getVersionId() + "'" +
            "}";
    }
}
