package fr.adservio.banque.services;

import java.sql.SQLException;
import fr.adservio.banque.entities.Confirmation;
import fr.adservio.banque.entities.MonetaryAmount;


public interface TransferService {

 public Confirmation transfer (MonetaryAmount ma , String senderID,String receiverID)throws SQLException;
}
