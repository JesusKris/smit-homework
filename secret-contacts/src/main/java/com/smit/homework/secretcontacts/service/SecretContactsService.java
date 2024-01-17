package com.smit.homework.secretcontacts.service;


import java.lang.System.Logger.Level;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smit.homework.secretcontacts.entity.SecretContactEntity;
import com.smit.homework.secretcontacts.model.ProjectLogger;
import com.smit.homework.secretcontacts.repository.SecretContactRepository;


/**
 * Service class for managing secret contacts.
 * 
 * 
 * <p>
 * <strong>Author:</strong> Robert Kris Laur
 * </p>
 */
@Service
public class SecretContactsService {

    /**
     * Logger for recording service activity.
     */
    @Autowired
    private ProjectLogger logger;

    /**
     * Repository for handling secret contact data access.
     */
    @Autowired
    private SecretContactRepository secretContactRepository;


    /**
     * Adds a new secret contact with the specified details.
     *
     * @param name         The name of the secret contact.
     * @param code         The code associated with the secret contact.
     * @param phoneNumber  The phone number of the secret contact.
     */
    public void addNewSecretContact(String name, String code, String phoneNumber) {
        logger.log(Level.INFO, "Attempting to create a new secret contact..");

        SecretContactEntity secretContactEntity = new SecretContactEntity();
        secretContactEntity.setName(name);
        secretContactEntity.setCode(code);
        secretContactEntity.setPhoneNumber(phoneNumber);
        secretContactRepository.saveAndFlush(secretContactEntity);

        logger.log(Level.INFO, "Successfully created a new secret contact..");
    }
    

    /**
     * Retrieves a list of all secret contacts.
     *
     * @return A list of {@link SecretContactEntity} representing all secret contacts.
     */
    public List<SecretContactEntity> getSecretContacts() {
        logger.log(Level.INFO, "Attempting to get all secret contacts..");

        List<SecretContactEntity> secretContacts = secretContactRepository.findAll();

        logger.log(Level.INFO, "Successfully got all secret contacts..");

        return secretContacts;
    }


    /**
     * Deletes a secret contact with the specified ID.
     *
     * @param id The ID of the secret contact to be deleted.
     */
    public void deleteSecretContact(Long id) {
        logger.log(Level.INFO, "Attempting to delete a secret contact..");

        secretContactRepository.deleteById(id);

        logger.log(Level.INFO, "Successfully deleted a secret contact..");
    }
}