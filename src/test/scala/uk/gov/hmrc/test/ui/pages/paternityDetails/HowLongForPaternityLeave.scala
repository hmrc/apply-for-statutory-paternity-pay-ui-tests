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

import uk.gov.hmrc.test.ui.pages.{BasePage, CheckYourAnswers}

object HowLongForPaternityLeave extends BasePage {

  val title = "How long will you be on Paternity Leave for?"

  def select1Week: CheckYourAnswers.type = {
    onPage(title)
    click("value_0")
    submitPage()
    CheckYourAnswers
  }

  def select2Week: CheckYourAnswers.type = {
    onPage(title)
    click("value_1")
    submitPage()
    CheckYourAnswers
  }

}
