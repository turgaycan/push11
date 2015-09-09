package com.push11.model.version;


import java.io.Serializable;

public class Version implements Serializable{

    private static final long serialVersionUID = -1990169851840070241L;
    private String version;
    private double versionNumber;
    private boolean supported;

    public Version(String version, double versionNumber, boolean supported) {
        this.version = version;
        this.versionNumber = versionNumber;
        this.supported = supported;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(double versionNumber) {
        this.versionNumber = versionNumber;
    }

    public boolean isSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }
}
