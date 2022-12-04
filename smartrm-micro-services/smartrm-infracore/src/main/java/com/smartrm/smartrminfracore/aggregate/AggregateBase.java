package com.smartrm.smartrminfracore.aggregate;

import com.smartrm.smartrminfracore.api.CommonError;
import com.smartrm.smartrminfracore.event.DomainEvent;
import com.smartrm.smartrminfracore.event.DomainEventBus;
import com.smartrm.smartrminfracore.exception.DomainException;

/**
 * @author: yoda
 * @description:
 */
public abstract class AggregateBase {

  protected long version;

  private boolean versionInc = false;

  protected DomainEventBus eventBus;

  protected void incVersion() {
    version++;
    versionInc = true;
  }

  public long getVersion() {
    return version;
  }

  public boolean isVersionInc() {
    return versionInc;
  }

  protected void setEventBus(DomainEventBus eventBus) {
    this.eventBus = eventBus;
  }

  /** 
   * protected void emitEvent(DomainEvent event) {
   * if (eventBus != null) {
   * eventBus.post(event);
   * } else {
   * throw new DomainException(CommonError.NoEventBus);
   * }
   * }
   * */


}
