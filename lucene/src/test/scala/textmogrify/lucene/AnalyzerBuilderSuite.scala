/*
 * Copyright 2022 Pig.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package textmogrify
package lucene

import munit.CatsEffectSuite
import cats.effect._

class AnalyzerBuilderSuite extends CatsEffectSuite {

  test("analyzer with stopWords should filter them out") {
    val tokenizer = AnalyzerBuilder.default.withStopWords(Set("I")).tokenizer[IO]
    val actual = tokenizer.use { f =>
      f("I Like Jalapeños")
    }
    assertIO(actual, Vector("Like", "Jalapeños"))
  }

}
