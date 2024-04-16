// Copyright 2024 Google LLC
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

package dev.cel.common.navigation;

import static com.google.common.truth.Truth.assertThat;

import dev.cel.common.ast.CelConstant;
import dev.cel.common.ast.CelMutableAst;
import dev.cel.common.ast.CelMutableExpr;
import dev.cel.compiler.CelCompiler;
import dev.cel.compiler.CelCompilerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CelNavigableMutableAstTest {

  @Test
  public void construct_success() throws Exception {
    CelCompiler celCompiler = CelCompilerFactory.standardCelCompilerBuilder().build();
    CelMutableAst ast = CelMutableAst.fromCelAst(celCompiler.compile("'Hello World'").getAst());

    CelNavigableMutableAst navigableAst = CelNavigableMutableAst.fromAst(ast);

    assertThat(navigableAst.getAst()).isEqualTo(ast);
    assertThat(navigableAst.getRoot().expr())
        .isEqualTo(CelMutableExpr.ofConstant(1, CelConstant.ofValue("Hello World")));
    assertThat(navigableAst.getRoot().parent()).isEmpty();
    assertThat(navigableAst.getRoot().depth()).isEqualTo(0);
  }
}
