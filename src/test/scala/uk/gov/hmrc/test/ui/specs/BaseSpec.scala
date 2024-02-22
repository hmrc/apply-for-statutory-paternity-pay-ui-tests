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

import org.mongodb.scala.MongoClient
import org.mongodb.scala.model.Filters
import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

trait BaseSpec
    extends AnyFeatureSpec
    with GivenWhenThen
    with BeforeAndAfterEach
    with Matchers
    with WebBrowser
    with Browser
    with ScreenshotOnFailure
    with Eventually {

  override def beforeEach(): Unit = {
    super.beforeEach()
    dropMongo()
    startBrowser()
  }

  override def afterEach(): Unit = {
    quitBrowser()
    super.afterEach()
  }

  private def dropMongo(): Unit = {

    val mongoClient: MongoClient = MongoClient()

    try {
      println("....... Dropping user-answers.......")
      dropCollection("apply-for-statutory-paternity-pay-frontend", "user-answers")

    } finally mongoClient.close()

    def dropCollection(dbName: String, collectionName: String): Unit =
      Await.result(
        mongoClient
          .getDatabase(dbName)
          .getCollection(collectionName)
          .deleteMany(Filters.empty())
          .toFuture(),
        10 seconds
      )
  }
}
