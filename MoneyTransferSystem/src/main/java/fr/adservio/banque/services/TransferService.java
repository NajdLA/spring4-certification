package fr.adservio.banque.services;

import java.sql.SQLException;
import org.springframework.transaction.annotation.Transactional;
import fr.adservio.banque.beans.Confirmation;
import fr.adservio.banque.beans.MonetaryAmount;

@Transactional
public interface TransferService {

 public Confirmation transfer (MonetaryAmount ma , String senderID,String receiverID)throws SQLException;
}
