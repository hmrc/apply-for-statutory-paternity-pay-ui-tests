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

package uk.gov.hmrc.test.ui.pages.applicationDetails

import uk.gov.hmrc.test.ui.pages.relationshipWithChild.{MarriageCivilPartnershipAdopting, MarriageCivilPartnershipParentalOrder, MarriageCivilPartnershipSupportingAdopting}
import uk.gov.hmrc.test.ui.pages.BasePage

object ReasonForRequesting extends BasePage {

  val title = "Why are you asking for Statutory Paternity Pay and/or Paternity Leave from your employer?"

  def selectAdopting: MarriageCivilPartnershipAdopting.type = {
    onPage(title)
    click("value_0")
    submitPage()
    MarriageCivilPartnershipAdopting
  }

  def selectSupportingAdopting: MarriageCivilPartnershipSupportingAdopting.type = {
    onPage(title)
    click("value_1")
    submitPage()
    MarriageCivilPartnershipSupportingAdopting
  }

  def selectParentalOrder: MarriageCivilPartnershipParentalOrder.type = {
    onPage(title)
    click("value_2")
    submitPage()
    MarriageCivilPartnershipParentalOrder
  }

}
