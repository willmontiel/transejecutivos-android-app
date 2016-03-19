package com.development.transejecutivos.models;

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

    /**
     *
     * @param idService
     * @param reference
     * @param createDate
     * @param startDate
     * @param fly
     * @param aeroline
     * @param company
     * @param paxCant
     * @param pax
     * @param source
     * @param destiny
     * @param observations
     */
    public Service(int idService, String reference, String createDate, String startDate, String fly, String aeroline, String company, String paxCant, String pax, String source, String destiny, String observations) {
        this.idService = idService;
        this.reference = reference;
        this.createDate = createDate;
        this.startDate = startDate;
        this.fly = fly;
        this.aeroline = aeroline;
        this.company = company;
        this.paxCant = paxCant;
        this.pax = pax;
        this.source = source;
        this.destiny = destiny;
        this.observations = observations;
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
}
