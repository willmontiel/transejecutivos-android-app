package com.development.transejecutivos.models;

/**
 * Created by william.montiel on 25/09/2015.
 */
public class Service {
    int idService;
    String reference;
    String createDate;
    String startDate;
    String endDate;
    String fly;
    String aeroline;
    String company;
    String passengerType;
    String paxCant;
    String represent;
    String source;
    String destiny;
    String observations;

    /**
     * Create a service object
     * @param idService
     * @param reference
     * @param createDate
     * @param startDate
     * @param endDate
     * @param fly
     * @param aeroline
     * @param company
     * @param passengerType
     * @param paxCant
     * @param represent
     * @param source
     * @param destiny
     * @param observations
     */
    public Service(int idService, String reference, String createDate, String startDate, String endDate, String fly, String aeroline, String company, String passengerType, String paxCant, String represent, String source, String destiny, String observations) {
        this.idService = idService;
        this.reference = reference;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fly = fly;
        this.aeroline = aeroline;
        this.company = company;
        this.passengerType = passengerType;
        this.paxCant = paxCant;
        this.represent = represent;
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

    public String getEndDate() {
        return this.endDate;
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

    public String getPassengerType() {
        return this.passengerType;
    }

    public String getPaxCant() {
        return this.paxCant;
    }

    public String getRepresent() {
        return this.represent;
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
