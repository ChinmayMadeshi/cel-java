// Copyright 2022 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package dev.cel.testing;

import dev.cel.expr.Decl;
import dev.cel.expr.Decl.IdentDecl;
import dev.cel.expr.Type;
import dev.cel.common.types.CelProtoTypes;
import dev.cel.common.types.CelType;
import dev.cel.compiler.CelCompilerBuilder;

/** Wrapper for CEL native type based variable declarations */
class TestCelVariableDeclWrapper extends TestDecl {
  private final String name;
  private final Type type;

  TestCelVariableDeclWrapper(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  @Override
  void loadDeclsToCompiler(CelCompilerBuilder compiler) {
    CelType celType = CelProtoTypes.typeToCelType(type);
    compiler.addVar(name, celType);
  }

  @Override
  Decl getDecl() {
    return Decl.newBuilder().setName(name).setIdent(IdentDecl.newBuilder().setType(type)).build();
  }
}
