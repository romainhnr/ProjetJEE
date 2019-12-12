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
    public Message(String texteMessage, LocalDateTime dateTimeMessage, Boolean estLu) {
        this.idMessage = UUID.randomUUID();
        this.texteMessage = texteMessage;
        this.dateTimeMessage = dateTimeMessage;
        this.estLu = estLu;

    }

    /**
     * @param idUser
     */
    public void EnvoieMessage(UUID idUser) {
        // TODO implement here
    }

}