package com.infosys.surveillance.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Camera.
 */
@Entity
@Table(name = "camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "location")
    private String location;

    @Column(name = "surveillance_task")
    private String surveillanceTask;

    @Column(name = "ip")
    private String ip;

    @Column(name = "fps")
    private String fps;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public Camera make(String make) {
        this.make = make;
        return this;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getLocation() {
        return location;
    }

    public Camera location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSurveillanceTask() {
        return surveillanceTask;
    }

    public Camera surveillanceTask(String surveillanceTask) {
        this.surveillanceTask = surveillanceTask;
        return this;
    }

    public void setSurveillanceTask(String surveillanceTask) {
        this.surveillanceTask = surveillanceTask;
    }

    public String getIp() {
        return ip;
    }

    public Camera ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFps() {
        return fps;
    }

    public Camera fps(String fps) {
        this.fps = fps;
        return this;
    }

    public void setFps(String fps) {
        this.fps = fps;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Camera)) {
            return false;
        }
        return id != null && id.equals(((Camera) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Camera{" +
            "id=" + getId() +
            ", make='" + getMake() + "'" +
            ", location='" + getLocation() + "'" +
            ", surveillanceTask='" + getSurveillanceTask() + "'" +
            ", ip='" + getIp() + "'" +
            ", fps='" + getFps() + "'" +
            "}";
    }
}
