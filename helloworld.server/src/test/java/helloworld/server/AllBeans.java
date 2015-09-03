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

import static helloworld.server.CollectionMatchers.matchesAnyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.text.ITextProviderService;
import org.eclipse.scout.rt.shared.services.common.text.ScoutTextProviderService;
import org.junit.Test;

import helloworld.shared.TextProviderServiceReplacement;

public class AllBeans {

  /**
   * TODO 4.1: {@link BEANS#all(Class)} finds all instances of an interface (except replaced classes). <br>
   * This is useful for resource providers such as image and text services.
   * <br>
   * Add a new TextProviderservice.
   */
  @Test
  public void findingAllInstances() {
    List<ITextProviderService> allTexts = BEANS.all(ITextProviderService.class);
    assertThat(BEANS.get(ScoutTextProviderService.class), isIn(allTexts));
    assertThat(allTexts.size(), is(3));
  }

  /**
   * TODO 4.2: Replaced instances are not found with BEANS.all
   * Replace TextProviderService with TextProviderServiceReplacement.
   */
  @Test
  public void findingAllInstancesReplace() {
    List<ITextProviderService> allTexts = BEANS.all(ITextProviderService.class);
    assertThat(BEANS.get(ScoutTextProviderService.class), isIn(allTexts));
    assertThat((o) -> o instanceof TextProviderServiceReplacement, matchesAnyOf(allTexts));
    assertThat(allTexts.size(), is(3));
  }

  /**
   * TODO 4.3: Beans are ordered with {@link Order} annotation.
   */
  @Test
  public void orderdBeans() {
    List<ITextProviderService> allTexts = BEANS.all(ITextProviderService.class);
    assertThat(allTexts.get(allTexts.size() - 1), not(instanceOf(ScoutTextProviderService.class)));
  }

}
