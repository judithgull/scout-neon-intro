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

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.scout.rt.platform.inventory.ClassInventory;
import org.eclipse.scout.rt.platform.inventory.IClassInfo;
import org.junit.Test;

import helloworld.shared.company.ICompanyOutlineService;

/**
 * Tests to demonstrate usage of {@link ClassInventory}
 */
public class ClassInventoryTest {

  @Test
  public void testFindKnownSubclasses() {
    Set<IClassInfo> coServices = ClassInventory.get().getAllKnownSubClasses(ICompanyOutlineService.class);
    assertTrue(coServices.size() == 1);
    assertTrue(coServices.stream().anyMatch(ci -> CompanyOutlineService.class.equals(ci.resolveClass())));
  }

}
