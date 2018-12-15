package be.kdg.deliDish.business.domain.user;


import be.kdg.foundation.contact.ContactInfo;
import be.kdg.foundation.contact.Position;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//TODO Je mag zaken toevoegen aan deze klasse. Bestaande attributen en methodes wijzigen doe je beter niet.
public class Courier extends User {

    private Position currentPosition;
    private boolean isAvailable;
    private List<DeliveryPointEvent> pointEvents = new ArrayList<>();
    private Partner partner;
    private ContactInfo contactInfo;

    public Courier(String firstName, String lastName, ContactInfo contactInfo, Position currentPosition, Partner partner) {
        super(firstName, lastName, contactInfo);
        this.currentPosition = currentPosition;
        this.partner = partner;
        this.isAvailable = true;
        this.contactInfo = contactInfo;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public boolean switchAvailability() {
        isAvailable = !isAvailable;
        return isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void addPointEvent(DeliveryPointEvent e) {
        pointEvents.add(e);
    }

    public Collection<DeliveryPointEvent> getDeliveryPointEvents() {
        return pointEvents;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setAvailable(boolean available) {

        isAvailable = available;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public int getDeliveryPoints(){
        int totalPoints = 0;

        for(DeliveryPointEvent event : pointEvents){
            totalPoints += event.getPoints();
        }

        return totalPoints;
    }

    public String getCountry(){
        return contactInfo.getAdress().getCity().getCountry();
    }
}
