package co.com.springdata.springdata_mongodb.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.querydsl.core.types.Predicate;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.springdata.model.Event;
import co.com.springdata.model.Item;
import co.com.springdata.model.QItem;
import co.com.springdata.repository.ItemRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ItemRepositoryTest {
  
  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private MongoTemplate mongoTemplate;
  
  @Before
  public void setUp() {
    Event event = new Event();
    event.setId("ev1234");
    mongoTemplate.save(event);
    
    Item item = new Item();
    item.setEvent(mongoTemplate.findOne(new Query(), Event.class));
    item.setId("id1234");
    mongoTemplate.save(item);
  }
  
  @Test
  public void testFindByIdUsingEq() {
    Predicate predicate = QItem.item.event.id.eq("ev1234") ;
    
    Item itemFound = itemRepository.findOne(predicate);
    
    assertTrue(itemFound != null);
    assertEquals("id1234", itemFound.getId());
  }
  
  
  /*
   * This test is failing
   */
  @Test
  public void testFindByIdUsingIn() {
    List<String> listOfIds = Arrays.asList("ev1234","ev12345");
    Predicate predicate = QItem.item.event.id.in(listOfIds) ;
    
    Item itemFound = itemRepository.findOne(predicate);
    
    assertTrue(itemFound != null);
    assertEquals("id1234", itemFound.getId());
  }
  
}

