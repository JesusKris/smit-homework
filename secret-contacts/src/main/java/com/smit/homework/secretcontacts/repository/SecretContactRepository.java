package com.smit.homework.secretcontacts.repository;


import com.smit.homework.secretcontacts.entity.SecretContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SecretContactRepository extends JpaRepository<SecretContactEntity, Long> {


    /**
     * Save and flush a secret contact entity.
     *
     * @param secretContact The secret contact entity to be saved and flushed.
     * @return The saved and flushed secret contact entity.
     */
    SecretContactEntity saveAndFlush(SecretContactEntity secretContact);


    /**
     * Delete a secret contact by its ID.
     *
     * @param id The ID of the secret contact to be deleted.
     */
    void deleteById(Long id);


    /**
     * Retrieve all secret contacts without the updated_at field.
     *
     * @return A list of secret contact entities without the updated_at field.
     */
    List<SecretContactEntity> findAll();

}