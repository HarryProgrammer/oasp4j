package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import java.time.LocalDateTime;

import io.oasp.gastronomy.restaurant.offermanagement.common.api.WeeklyPeriod;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

/**
 * This is the {@link SearchCriteriaTo search criteria} {@link net.sf.mmm.util.transferobject.api.TransferObject TO}
 * used to find {@link io.oasp.gastronomy.restaurant.salesmanagement.common.api.Order}s.
 *
 */
public class SpecialSearchCriteriaTo extends SearchCriteriaTo {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  private String name;

  private OfferEntity offer;

  private WeeklyPeriod activePeriod;

  private LocalDateTime dateOfCheckingOffers;

  /**
   * The constructor.
   */
  public SpecialSearchCriteriaTo() {

    super();
  }

  /**
   * @return dateOfCheckingOffers
   */
  public LocalDateTime getDateOfCheckingOffers() {

    return this.dateOfCheckingOffers;
  }

  /**
   * @param dateOfCheckingOffers new value of {@link #getdateOfCheckingOffers}.
   */
  public void setDateOfCheckingOffers(LocalDateTime dateOfCheckingOffers) {

    this.dateOfCheckingOffers = dateOfCheckingOffers;
  }

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return offer
   */
  public OfferEntity getOffer() {

    return this.offer;
  }

  /**
   * @param offer new value of {@link #getoffer}.
   */
  public void setOffer(OfferEntity offer) {

    this.offer = offer;
  }

  /**
   * @return activePeriod
   */
  public WeeklyPeriod getActivePeriod() {

    return this.activePeriod;
  }

  /**
   * @param activePeriod new value of {@link #getactivePeriod}.
   */
  public void setActivePeriod(WeeklyPeriod activePeriod) {

    this.activePeriod = activePeriod;
  }

  /**
   * @return serialversionuid
   */
  public static long getSerialversionuid() {

    return serialVersionUID;
  }

}
