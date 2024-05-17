import sbt._

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"         %% "ui-test-runner"     % "0.29.0"  % Test,
    "org.scalatest"       %% "scalatest"          % "3.2.17"   % Test,
    "org.scalatestplus"   %% "selenium-4-12"      % "3.2.17.0" % Test,
    "com.vladsch.flexmark" % "flexmark-all"       % "0.62.2" % Test,
    "org.slf4j"            % "slf4j-simple"       % "1.7.36" % Test,
    "com.typesafe"         % "config"             % "1.4.2"   % Test,
    "org.mongodb.scala"   %% "mongo-scala-driver" % "4.11.1",
    "uk.gov.hmrc"         %% "domain-play-30"     % "9.0.0"
  )
}
