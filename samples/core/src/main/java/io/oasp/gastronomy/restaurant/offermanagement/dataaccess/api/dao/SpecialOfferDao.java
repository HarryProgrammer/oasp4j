package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationRevisionedDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author DPAPCIAK
 *
 */
public interface SpecialOfferDao extends ApplicationRevisionedDao<SpecialEntity>, MasterDataDao<SpecialEntity> {

  PaginatedListTo<SpecialEntity> findOfferss(SpecialSearchCriteriaTo criteria);

  Money findBestActiveSpecial(SpecialSearchCriteriaTo criteria);

}
