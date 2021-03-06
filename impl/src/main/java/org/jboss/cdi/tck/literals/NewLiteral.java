/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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
package org.jboss.cdi.tck.literals;

import javax.enterprise.inject.New;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Annotation literal for {@link New}
 */
@SuppressWarnings("all")
public class NewLiteral extends AnnotationLiteral<New> implements New {

    private static final long serialVersionUID = 1L;

    /**
     * The container defaults the value to the declared type of the injection point
     */
    public static final New INSTANCE = new NewLiteral(New.class);

    private Class<?> value;

    public Class<?> value() {
        return value;
    }

    public NewLiteral(Class<?> value) {
        this.value = value;
    }

}
