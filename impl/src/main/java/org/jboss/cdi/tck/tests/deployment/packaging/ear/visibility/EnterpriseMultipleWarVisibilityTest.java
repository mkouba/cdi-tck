/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.cdi.tck.tests.deployment.packaging.ear.visibility;

import static org.jboss.cdi.tck.TestGroups.JAVAEE_FULL;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.cdi.tck.AbstractTest;
import org.jboss.cdi.tck.shrinkwrap.EnterpriseArchiveBuilder;
import org.jboss.cdi.tck.shrinkwrap.WebArchiveBuilder;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * @author Martin Kouba
 */
@Test(groups = JAVAEE_FULL)
public class EnterpriseMultipleWarVisibilityTest extends AbstractTest {

    @Deployment(testable = false)
    public static EnterpriseArchive createTestArchive() {

        EnterpriseArchive enterpriseArchive = new EnterpriseArchiveBuilder()
                .withTestClassDefinition(EnterpriseMultipleWarVisibilityTest.class).noDefaultWebModule()
                .withBeanLibrary(LibraryBean.class, PingService.class).build();

        WebArchive fooArchive = new WebArchiveBuilder().notTestArchive().withName("foo.war")
                .withClasses(FooPingService.class, FooPingServlet.class).build();
        enterpriseArchive.addAsModule(fooArchive);

        WebArchive barArchive = new WebArchiveBuilder().notTestArchive().withName("bar.war")
                .withClasses(BarPingService.class, BarPingServlet.class).build();
        enterpriseArchive.addAsModule(barArchive);

        return enterpriseArchive;
    }

    @Test(dataProvider = ARQUILLIAN_DATA_PROVIDER)
    public void testVisibility(@ArquillianResource(FooPingServlet.class) URL fooContext,
            @ArquillianResource(BarPingServlet.class) URL barContext) throws Exception {

        WebClient webClient = new WebClient();
        webClient.getPage(fooContext + "/fooping");
        webClient.getPage(barContext + "/barping");
    }

}
