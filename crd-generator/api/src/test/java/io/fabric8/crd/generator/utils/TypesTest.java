/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.crd.generator.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.fabric8.crd.example.basic.Basic;
import io.fabric8.crd.example.webserver.WebServerWithStatusProperty;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class TypesTest {

  @Test
  public void shouldFindStatusProperty() {
    TypeDef def = ClassTo.TYPEDEF.apply(WebServerWithStatusProperty.class);
    Optional<Property> p = Types.findStatusProperty(def);
    assertTrue(p.isPresent());

    def = ClassTo.TYPEDEF.apply(Basic.class);
    p = Types.findStatusProperty(def);
    assertTrue(p.isPresent());
  }
  
  @Test
  public void findingSuperClassesShouldWork() {
    TypeDef def = ClassTo.TYPEDEF.apply(Basic.class);
    Set<ClassRef> superClasses = Types.getSuperClasses(def);
    assertTrue(superClasses.stream().anyMatch(c -> c.getName().contains("CustomResource")));
  }

}