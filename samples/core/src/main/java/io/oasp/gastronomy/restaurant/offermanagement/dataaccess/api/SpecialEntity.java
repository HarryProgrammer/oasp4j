package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.dataaccess.api.ApplicationPersistenceEntity;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.Special;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.WeeklyPeriod;

/**
 * @author DPAPCIAK
 *
 */
@Entity
@Table(name = "Special")
public class SpecialEntity extends ApplicationPersistenceEntity implements Special {

  private static final long serialVersionUID = 1L;

  private String name;

  private OfferEntity offer;

  private WeeklyPeriod activePeriod;

  private Money specialPrice;

  /**
   * @return name
   */
  @Column(name = "name", unique = true)
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  @Override
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return offer
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "offerId")
  public OfferEntity getOffer() {

    return this.offer;
  }

  /**
   * @param offer new value of {@link #getoffer}.
   */
  @Override
  public void setOffer(OfferEntity offer) {

    this.offer = offer;
  }

  /**
   * @return activePeriod
   */
  @Embedded
  @Column(name = "activePeriod")
  public WeeklyPeriod getActivePeriod() {

    return this.activePeriod;
  }

  /**
   * @param weeklyPeriod
   * @param activePeriod new value of {@link #getactivePeriod}.
   */
  @Embedded
  @Override
  public void setActivePeriod(WeeklyPeriod activePeriod) {

    WeeklyPeriodEmbeddable period = new WeeklyPeriodEmbeddable();

    period.setEndingDay(activePeriod.getEndingDay());
    period.setEndingHour(activePeriod.getEndingHour());
    period.setStartingDay(activePeriod.getStartingDay());
    period.setStartingHour(activePeriod.getStartingHour());
    this.activePeriod = period;
  }

  /**
   * @return specialPrice
   */
  public Money getSpecialPrice() {

    return this.specialPrice;
  }

  /**
   * @param specialPrice new value of {@link #getspecialPrice}.
   */
  @Override
  public void setSpecialPrice(Money specialPrice) {

    this.specialPrice = specialPrice;
  }

}
