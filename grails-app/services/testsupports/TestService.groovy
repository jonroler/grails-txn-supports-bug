package testsupports

import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.annotation.Propagation

import javax.sql.DataSource

import groovy.sql.Sql

@Transactional
public class TestService {
  DataSource dataSource

  @Transactional(propagation=Propagation.SUPPORTS)
  public void testMethod1() {
    log.error("In testMethod1")
    Sql sql = new Sql(dataSource)
    sql.eachRow("select 1") {
      log.error(it)
    }
    sql.close()
  }

  public void testMethod2() {
    log.error("In testMethod2")
    Sql sql = new Sql(dataSource)
    sql.eachRow("select 1") {
      log.error(it)
    }
    sql.close()
  }
}