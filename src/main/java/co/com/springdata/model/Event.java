package co.com.springdata.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
  
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
  
}
