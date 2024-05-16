/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.pages.babysDetails

import uk.gov.hmrc.test.ui.pages.BasePage
import uk.gov.hmrc.test.ui.pages.paternityDetails.DateYouWantSPToStart

import java.time.LocalDate

object WhenWasBabyDue extends BasePage {

  val title = "What date was the baby due?"

  def enterBabyDueDate: DateYouWantSPToStart.type = {
    onPage(title)
    enterTodaysDate()
    submitPage()
    DateYouWantSPToStart
  }

  def enterBabyDueDate(date: LocalDate): DateYouWantSPToStart.type = {
    onPage(title)
    enterDate(date)
    submitPage()
    DateYouWantSPToStart
  }
}
