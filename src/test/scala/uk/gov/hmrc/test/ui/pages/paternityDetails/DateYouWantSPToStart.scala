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

package uk.gov.hmrc.test.ui.pages.paternityDetails

import uk.gov.hmrc.test.ui.pages.BasePage
import uk.gov.hmrc.test.ui.pages.babysDetails.WhenWasBabyDue

object DateYouWantSPToStart extends BasePage {

  val title =
    "What date would you like your Statutory Paternity Pay and/or paternity leave to start?"

  def enterStartDate: WhenWasBabyDue.type = {
    onPage(title)
    enterTodaysDate()
    submitPage()
    WhenWasBabyDue
  }

  def enterStartDateDue: WhenWasBabyDue.type = {
    onPage(title)
    enterTomorrowDate()
    submitPage()
    WhenWasBabyDue
  }
}
