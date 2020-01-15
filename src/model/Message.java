package model;
import java.util.*;
import java.time.*;

/**
 * 
 */
public class Message {

    private UUID idMessage;
    private String texteMessage;
    private LocalDateTime dateTimeMessage;
    private Boolean estLu;


    /**
     * Default constructor
     */
    public Message(String texteMessage) {
        this.idMessage = UUID.randomUUID();
        this.texteMessage = texteMessage;
        //this.dateTimeMessage = LocalDateTime.of(2020, 1, 2, 5, 5, 42);
        this.dateTimeMessage = LocalDateTime.now();
        this.estLu = false;

    }

    public UUID getIdMessage() { return idMessage; }
    public String getTexteMessage() { return texteMessage; }
    public void setTexteMessage(String texteMessage) { this.texteMessage = texteMessage; }
    public LocalDateTime getDateTimeMessage() { return dateTimeMessage; }
    public void setDateTimeMessage(LocalDateTime dateTimeMessage) { this.dateTimeMessage = dateTimeMessage; }
    public Boolean getEstLu() { return estLu; }
    public void setEstLu(Boolean estLu) { this.estLu = estLu; }

    /**
     * @param idUser
     */
    public void EnvoieMessage(UUID idUser) {
        // TODO implement here
    }

}