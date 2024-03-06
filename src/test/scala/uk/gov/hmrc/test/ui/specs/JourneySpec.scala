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

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.pages.applicationDetails._
import uk.gov.hmrc.test.ui.pages.babysDetails._
import uk.gov.hmrc.test.ui.pages.paternityDetails._
import uk.gov.hmrc.test.ui.pages.personalDetails._
import uk.gov.hmrc.test.ui.pages.relationshipWithChild._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class JourneySpec extends BaseSpec {

  Feature("Journeys for completing form and generating PDF") {

    Scenario(
      "Biological parent in England " +
        "whose baby has been born, " +
        "who will be using leave to care for their child.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectNo
      BiologicalFather.selectYes
      CaringResponsibility.selectYes
      TimeOffToCareForChild.selectYes
      YourName.enterName
      YourNino.enterNino
      HasBabyBeenBornYet.selectYes
      WhenWasBabyBorn.enterBabyDOB
      WhenWasBabyDue.enterBabyDueDate
      HowLongForPaternityLeave.select1Week
      DateYouWantSPToStart.enterStartDateToday

      Then("I confirm my answers and will be given the option to download the form")
      CheckYourAnswers.confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Biological parent in England " +
        "in an enduring family relationship, " +
        "whose baby has not yet been born, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectNo
      BiologicalFather.selectNo
      MarriageCivilPartnershipWithMother.selectNo
      EnduringFamilyRelationship.selectYes
      CaringResponsibility.selectYes
      TimeOffToCareForChild.selectNo
      TimeOffToSupportOtherParent.selectYes
      YourName.enterName
      YourNino.enterNino
      HasBabyBeenBornYet.selectNo
      WhenIsBabyDue.enterBabyDueDate()
      HowLongForPaternityLeave.select1Week
      DateYouWantSPToStart.enterStartDateTomorrow

      Then("I confirm my answers and will be given the option to download the form")
      CheckYourAnswers.confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Adoptive parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner, " +
        "when the child has been placed already.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectAdopting
      MarriageCivilPartnershipAdopting.selectNo
      EnduringFamilyRelationshipAdopting.selectYes
      CaringResponsibility.selectYes
      TimeOffToCareForChild.selectYes
      YourName.enterName
      YourNino.enterNino
      AdoptionMatchDate.enterMatchDate()
      AdoptionPlaced.selectYes
      AdoptionPlacedDate.enterPlacedDate()
      HowLongForPaternityLeave.select1Week
      DateYouWantSPToStart.enterStartDateTomorrow

      Then("I confirm my answers and will be given the option to download the form")
      CheckYourAnswers.confirmAnswers
      Confirmation.result should be("Your application form is ready to send to your employer")
    }

    Scenario(
      "Supporting adoptive parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectSupportingAdopting
      MarriageCivilPartnershipSupportingAdopting.selectNo
      EnduringFamilyRelationshipSupportingAdopting.selectYes
      CaringResponsibility.selectYes
      TimeOffToCareForChild.selectYes

      Then("I will reach the 'What is your name' page")
      YourName.onPage(YourName.title)
    }

    Scenario(
      "Parental order parent in England " +
        "in an enduring family relationship, " +
        "who will be using leave to support their partner.",
      ZapTests
    ) {
      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I provide details")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectYes
      ApplyingForStatutoryAdoptionPay.selectNo
      AdoptingFromAbroad.selectNo
      ReasonForRequesting.selectParentalOrder
      MarriageCivilPartnershipParentalOrder.selectNo
      EnduringFamilyRelationshipParentalOrder.selectYes
      CaringResponsibility.selectYes
      TimeOffToCareForChild.selectYes

      Then("I will reach the 'What is your name' page")
      YourName.onPage(YourName.title)
    }

  }

  Feature("Kickout Journeys") {

    Scenario("Applicant is not eligible for Statutory Paternity Pay", ZapTests) {

      Given("I am on the Apply for SSP Home Page")
      StartPage.loadPage.startApplication

      When("I answer that I will not have responsibility for caring for the child")
      WhereDoYouLive.selectEngland
      AdoptingOrParentalOrder.selectNo
      BiologicalFather.selectYes
      CaringResponsibility.selectNo

      Then("I will be told I am not eligible for Statutory Paternity Pay ")
      NotEligible.result should be("You are not eligible for Statutory Paternity Pay and Statutory Paternity Leave")
    }
  }

}
