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

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.IBeanManager;
import org.junit.Test;

/**
 * Tests to demonstrate usage of {@link IBeanManager}
 */
public class BeanManagerTest {

  /**
   * TODO 4: The {@link IBeanManager} allows register classes, generating instances of these classes and querying the
   * classes
   * and instances. This is useful for resolving dependencies.
   * <p>
   * By default, all classes annotated with {@link Bean} are automatically registered.
   * </p>
   * Make sure the company service is found by the bean manager
   */
  @Test
  public void getBean() {
    CompanyService service = BEANS.get(CompanyService.class);
  }

}
