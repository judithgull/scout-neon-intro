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

import static helloworld.server.SetMatchers.hasEntryMatching;
import static helloworld.server.SetMatchers.hasNoEntryMatching;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.inventory.ClassInventory;
import org.eclipse.scout.rt.platform.inventory.IClassInfo;
import org.eclipse.scout.rt.platform.inventory.IClassInventory;
import org.junit.Test;

import helloworld.shared.company.ICompanyOutlineService;

/**
 * Tests to demonstrate usage of {@link ClassInventory}
 */
public class ClassInventoryTest {

  /**
   * It is possible to find all subclasses with {@link IClassInventory#getAllKnownSubClasses(Class)}
   */
  @Test
  public void testFindKnownSubclasses() {
    Set<IClassInfo> coServices = ClassInventory.get().getAllKnownSubClasses(ICompanyOutlineService.class);

    assertThat("getAllKnownSubClasses should not return any interfaces", coServices, hasNoEntryMatching(IClassInfo::isInterface));

    Class<?> expectedClass = null; // TODO 1: specify the expected class
    assertThat(coServices, hasEntryMatching(e -> e.resolveClass().equals(expectedClass)));
    assertThat(coServices.size(), is(1));
  }

  /**
   * TODO 2: Create a new class 'MyService' annotated with {@link Bean}.
   * <p>
   * Clean the workspace before running the test,
   * because the jandex index is cached in projectName/target/classes/META-INF/jandex.idx.
   * <br>
   * Alternatively, use option -Djandex.rebuild=true
   * </p>
   */
  @Test
  public void testFindAnnotations() {
    Set<IClassInfo> beanClasses = ClassInventory.get().getKnownAnnotatedTypes(Bean.class);
    assertThat(beanClasses, hasEntryMatching(e -> "MyService".equals(e.name())));
  }

  //TODO 3: The ClassInventory only collects classes in projects with a resource called META-INF/scout.xml.
  //Scanning all classes would unnecessary slow and consume too much memory.

  //The file scout.xml is just an empty xml file (see src/main/resources/META-INF/scout.xml).
  //Scout itself also has scout.xml files in all its projects.
  //The format XML, because it may be required later to add exclusions, but this is not possible right now.

  //See what happens, if you delete the scout.xml file.

}
