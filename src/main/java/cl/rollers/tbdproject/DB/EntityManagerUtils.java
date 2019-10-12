package cl.rollers.tbdproject.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class EntityManagerUtils {

  @Autowired
  @Qualifier("firstEntityManager")
  private EntityManager firstDatabase;

  @Autowired
  @Qualifier("secondEntityManager")
  private EntityManager secondDatabase;

  public EntityManager getEm(String url){

    if(url.contains("first"))
      return firstDatabase;
    if(url.contains("second"))
      return secondDatabase;
    return firstDatabase;
  }

  public JpaRepositoryFactory getJpaFactory(String url){
    return new JpaRepositoryFactory( getEm(url) );
  }

}