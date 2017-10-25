package co.com.springdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import co.com.springdata.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends MongoRepository<Item, String>, QueryDslPredicateExecutor<Item>{

}
