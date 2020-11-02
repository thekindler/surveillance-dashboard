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

    @Column(name = "modename")
    private String modename;

    @Column(name = "version")
    private Integer version;

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

    public String getModename() {
        return modename;
    }

    public SurveillanceTask modename(String modename) {
        this.modename = modename;
        return this;
    }

    public void setModename(String modename) {
        this.modename = modename;
    }

    public Integer getVersion() {
        return version;
    }

    public SurveillanceTask version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
            ", modename='" + getModename() + "'" +
            ", version=" + getVersion() +
            "}";
    }
}
