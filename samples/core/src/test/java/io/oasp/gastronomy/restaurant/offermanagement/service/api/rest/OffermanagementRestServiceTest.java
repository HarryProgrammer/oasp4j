package io.oasp.gastronomy.restaurant.offermanagement.service.api.rest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.oasp.gastronomy.restaurant.general.common.api.builders.TableEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.tablemanagement.common.api.datatype.TableState;
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.to.TableEto;
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.to.TableSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.tablemanagement.service.api.rest.TablemanagementRestService;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;
import io.oasp.module.service.common.api.client.ServiceClientFactory;
import io.oasp.module.service.common.api.client.config.ServiceClientConfigBuilder;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })
// , locations = {"file:src/test/resources/config" })

public class OffermanagementRestServiceTest extends AbstractRestServiceTest {

  private static final String LOGIN_WAITER = "waiter";

  private static final String LOGIN_CHIEF = "chief";

  @Inject
  private ServiceClientFactory serviceClientFactory;

  @Inject
  private SpecialEto specialEto;

  /**
   * Provides initialization previous to the creation of the text fixture.
   */
  @Override
  public void doSetUp() {

    super.doSetUp();
    getDbTestHelper().resetDatabase();
  }

  private OffermanagementRestService createService(String login) {

    return this.serviceClientFactory.create(OffermanagementRestService.class,
        new ServiceClientConfigBuilder().userLogin(login).buildMap());
  }

  @Test
  public void testDeleteSpecial() {

    // setup
    OffermanagementRestService service = createService(LOGIN_CHIEF);

    // given
    int deleteSpecialNumber = 1;
    assertThat(service.findSpecial(deleteSpecialNumber)).isNotNull();

    // when
    service.deleteTable(deleteTableNumber);

    // then
    assertThat(service.getTable(deleteTableNumber)).isNull();
  }

  /**
   * This test method serves as an example of how to use {@link AbstractRestServiceTest} in practice.
   */
  @Test
  public void testFindSpecials() {

    // given
    SpecialSearchCriteriaTo criteria = 1;

    // when

    SpecialEto special = createService(LOGIN_CHIEF).findActiveSpecials(criteria);

    // then
    assertThat(table).isNotNull();
    assertThat(table.getId()).isEqualTo(id);

  }

  /**
   * This test method deletes a table. As a waiter (default login defined in application.properties) does not have the
   * permission to do so, a workaround is needed to login as member "chief". Do not try to delete table 101 as is has an
   * attached order and will fail with error code 400.
   */
  @Test
  public void testDeleteTable() {

    // setup
    TablemanagementRestService service = createService(LOGIN_CHIEF);

    // given
    int deleteTableNumber = 102;
    assertThat(service.getTable(deleteTableNumber)).isNotNull();

    // when
    service.deleteTable(deleteTableNumber);

    // then
    assertThat(service.getTable(deleteTableNumber)).isNull();
  }

  /**
   * This test method creates a table using {@link TableEtoBuilder} and saves it into the database. As a waiter (default
   * login defined in application.properties) does not have the permission to do so, a workaround is needed to login as
   * member "chief".
   */
  @Test
  public void testSaveTable() {

    // given
    long tableNumber = 7L;
    long waiterId = 2L;
    TableEto table = new TableEtoBuilder().number(tableNumber).waiterId(waiterId).createNew();
    assertThat(table.getId()).isNull();

    // when
    TableEto savedTable = createService(LOGIN_CHIEF).saveTable(table);

    // then
    assertThat(savedTable).isNotNull();
    assertThat(savedTable.getId()).isNotNull();
    assertThat(savedTable.getState()).isEqualTo(TableState.FREE);
    assertThat(savedTable.getNumber()).isEqualTo(tableNumber);
    assertThat(savedTable.getWaiterId()).isEqualTo(waiterId);
  }

  /**
   * This test method demonstrates a simple usage of {@link TableSearchCriteriaTo} for searching a table by post with
   * {@link TableState} {@code RESERVED} that is created prior to the search job.
   */
  @Test
  public void testFindTablesByPost() {

    // given
    long tableNumber = 7L;
    long waiterId = 2L;
    TableEto table = new TableEtoBuilder().number(tableNumber).waiterId(waiterId).state(TableState.RESERVED)
        .createNew();
    assertThat(table).isNotNull();
    TablemanagementRestService service = createService(LOGIN_WAITER);
    TableEto savedTable = service.saveTable(table);
    assertThat(savedTable).isNotNull();
    TableSearchCriteriaTo criteria = new TableSearchCriteriaTo();
    assertThat(criteria).isNotNull();
    criteria.setState(TableState.RESERVED);

    // when
    PaginatedListTo<TableEto> tables = service.findTablesByPost(criteria);
    List<TableEto> result = tables.getResult();

    // then
    assertThat(result).isNotEmpty();
    assertThat(result).hasAtLeastOneElementOfType(TableEto.class).extracting("state").contains(TableState.RESERVED);
    assertThat(result).extracting("state").doesNotContain(TableState.FREE);
    assertThat(result).extracting("state").doesNotContain(TableState.OCCUPIED);
    assertThat(result).extracting("waiterId").contains(waiterId);
    assertThat(result).extracting("number").contains(tableNumber);
  }

}
