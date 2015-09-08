/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package helloworld.server;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.platform.BEANS;
import org.junit.Test;

import helloworld.server.city.CityService1;
import helloworld.server.city.CityService2;
import helloworld.server.city.ICityService;

public class OptionalBeans {

  /**
   * TODO 5.1: It is possible to find beans that are optionally available or not with {@link BEANS#opt(Class)}.
   */
  @Test
  public void NonExistingOptional() {
    assertNull(BEANS.get(ITestService.class)); //fix
  }

  /**
   * TODO 5.2: Optional ordered beans
   * If there are multiple beans that are not ordered (or replaced), {@link BEANS#opt(Class)} fails.
   * If the services are ordered with {@link Order}, {@link BEANS#opt(Class)} returns the one with the highest order.
   */
  @Test
  public void optionalMultipleBeans() {
    assertThat(BEANS.opt(ICityService.class), is(instanceOf(CityService1.class))); // fix using  Order annotation.
    assertThat(BEANS.opt(CityService1.class), is(instanceOf(CityService1.class)));
    assertThat(BEANS.opt(CityService2.class), is(instanceOf(CityService2.class)));
  }

  //helpers
  interface ITestService {
  }

}
