package com.smit.homework.secretcontacts.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.smit.homework.secretcontacts.entity.SecretContactEntity;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SecretContactRepositoryTests {

    @Autowired
    private SecretContactRepository secretContactRepository;

    @Test
    void saveAndFlushSecretContact() {
   
        SecretContactEntity secretContact = new SecretContactEntity();
        secretContact.setName("John Doe");
        secretContact.setCode("123");
        secretContact.setPhoneNumber("555-1234");

        SecretContactEntity savedContact = secretContactRepository.saveAndFlush(secretContact);

        assertNotNull(savedContact.getId());
        assertEquals("John Doe", savedContact.getName());
        assertEquals("123", savedContact.getCode());
        assertEquals("555-1234", savedContact.getPhoneNumber());
    }

    @Test
    void deleteSecretContactById() {
     
        SecretContactEntity secretContact = new SecretContactEntity();
        secretContact.setName("Jane Doe");
        secretContact.setCode("456");
        secretContact.setPhoneNumber("555-5678");
        SecretContactEntity savedContact = secretContactRepository.saveAndFlush(secretContact);

        secretContactRepository.deleteById(savedContact.getId());

        assertEquals(0, secretContactRepository.count());
    }

    @Test
    void findAllSecretContacts() {
   
        SecretContactEntity contact1 = new SecretContactEntity();
        contact1.setName("Alice");
        contact1.setCode("789");
        contact1.setPhoneNumber("555-1111");
        secretContactRepository.saveAndFlush(contact1);

        SecretContactEntity contact2 = new SecretContactEntity();
        contact2.setName("Bob");
        contact2.setCode("012");
        contact2.setPhoneNumber("555-2222");
        secretContactRepository.saveAndFlush(contact2);

        List<SecretContactEntity> secretContacts = secretContactRepository.findAll();

        assertEquals(2, secretContacts.size());
    }
}