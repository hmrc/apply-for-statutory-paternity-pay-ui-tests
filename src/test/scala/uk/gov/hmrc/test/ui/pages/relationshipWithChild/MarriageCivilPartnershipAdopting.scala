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

package uk.gov.hmrc.test.ui.pages.relationshipWithChild

import uk.gov.hmrc.test.ui.pages.BasePage

object MarriageCivilPartnershipAdopting extends BasePage {

  val title = "Are you in a marriage or civil partnership with the other person adopting or intending to adopt the child?"

  def selectYes: CaringResponsibility.type = {
    onPage(title)
    click("value")
    submitPage()
    CaringResponsibility
  }

  def selectNo: EnduringFamilyRelationshipAdopting.type = {
    onPage(title)
    click("value-no")
    submitPage()
    EnduringFamilyRelationshipAdopting
  }

}
