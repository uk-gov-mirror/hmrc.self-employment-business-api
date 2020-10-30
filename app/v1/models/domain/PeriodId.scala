/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.models.domain

import v1.controllers.requestParsers.validators.validations.dateFormat

import scala.util.{Failure, Success, Try}

//noinspection ScalaStyle
case class PeriodId(value: String) {
  override def toString: String = value

  val from: String = value.substring(0, 10)
  val to: String = value.substring(11, 21)

  assert(value.length == 21)
  assert(
    Try {
      dateFormat.parse(from)
      dateFormat.parse(to)
    } match {
      case Success(_) => true
      case Failure(_) => false
    }
  )
}

object PeriodId {
  def fromSeparate(from: String, to: String): PeriodId = PeriodId(s"${from}_$to")
}
