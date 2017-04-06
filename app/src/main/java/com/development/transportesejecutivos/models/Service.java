package com.development.transportesejecutivos.models;

/**
 * Created by william.montiel on 25/09/2015.
 */
public class Service {
    int idService;
    String reference;
    String createDate;
    String startDate;
    String fly;
    String aeroline;
    String company;
    String paxCant;
    String pax;
    String source;
    String destiny;
    String observations;
    String status;
    int shareLocation;

    public Service() {

    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setFly(String fly) {
        this.fly = fly;
    }

    public void setAeroline(String aeroline) {
        this.aeroline = aeroline;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPaxCant(String paxCant) {
        this.paxCant = paxCant;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShareLocation(int shareLocation) {
        this.shareLocation = shareLocation;
    }

    public int getIdService() {
        return this.idService;
    }

    public String getReference() {
        return this.reference;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getFly() {
        return this.fly;
    }

    public String getAeroline() {
        return this.aeroline;
    }

    public String getCompany() {
        return this.company;
    }

    public String getPaxCant() {
        return this.paxCant;
    }

    public String getPax() {
        return this.pax;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestiny() {
        return this.destiny;
    }

    public String getObservations() {
        return this.observations;
    }

    public String getStatus() {
        return this.status;
    }

    public int getShareLocation() {
        return this.shareLocation;
    }
}
