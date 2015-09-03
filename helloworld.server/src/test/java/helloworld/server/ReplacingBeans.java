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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.eclipse.scout.commons.annotations.Replace;
import org.eclipse.scout.rt.platform.BEANS;
import org.junit.Test;

import helloworld.server.person.IPersonService;
import helloworld.server.person.PersonService;

public class ReplacingBeans {

  /**
   * TODO 3.1: A bean can be replaced, by using the annotation {@link Replace}. A bean annotated with replace replaces
   * its superclass.<br>
   * Fix the test without changing {@link PersonService} or {@link IPersonService}
   */
  @Test
  public void replaceBean() {
    assertThat(BEANS.get(IPersonService.class).getName(), is("SpecialPersonService"));
    assertThat(BEANS.get(PersonService.class).getName(), is("SpecialPersonService"));
  }

}
