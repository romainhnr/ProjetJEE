package model;
import java.util.*;
import java.time.*;

// Classe Message pour la Messagerie
public class Message {

    private UUID idMessage;
    private String texteMessage;
    private LocalDateTime dateTimeMessage;
    private Boolean estLu;

    // getter & setter
    public UUID getIdMessage() { return idMessage; }
    public String getTexteMessage() { return texteMessage; }
    public void setTexteMessage(String texteMessage) { this.texteMessage = texteMessage; }
    public LocalDateTime getDateTimeMessage() { return dateTimeMessage; }
    public void setDateTimeMessage(LocalDateTime dateTimeMessage) { this.dateTimeMessage = dateTimeMessage; }
    public Boolean getEstLu() { return estLu; }
    public void setEstLu(Boolean estLu) { this.estLu = estLu; }


    // constructeur
    public Message(String texteMessage) {
        this.idMessage = UUID.randomUUID();
        this.texteMessage = texteMessage;
        this.dateTimeMessage = LocalDateTime.now();
        this.estLu = false;

    }

    // constructeur permettant de donner les champs : date et estLu
    public Message(String texteMessage, LocalDateTime dateTimeMessage, Boolean estLu) {
        this.idMessage = UUID.randomUUID();
        this.texteMessage = texteMessage;
        this.dateTimeMessage = dateTimeMessage;
        this.estLu = estLu;

    }




}