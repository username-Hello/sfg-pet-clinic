package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {
    
    OwnerMapService ownerMapService;
    
    final Long ownerId = 1L;
    final String lastName = "Smith";
    
    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetServiceMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }
    
    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }
    
    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }
    
    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }
    
    @Test
    void findById() {
        assertEquals(1L, ownerMapService.findById(ownerId).getId());
    }
    
    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner savedOwner = ownerMapService.save(Owner.builder().id(2L).build());
        assertEquals(id, savedOwner.getId());
    }
    
    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }
    
    @Test
    void findByLastName() {
        assertEquals(ownerId, ownerMapService.findByLastName(lastName).getId());
    }
}