// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html


import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.util.Try


class MySuite extends AnyFlatSpec with Matchers {
  "SetAlgebra" should "evaluate union correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluate_expression("A | B", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("1", "2", "3", "4", "5"))
  }

  it should "evaluate intersection correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluate_expression("A & B", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("3"))
  }

  it should "evaluate difference correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluate_expression("A - B", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("1", "2"))
  }

  it should "evaluate symmetric difference correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluate_expression("A ^ B", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("1", "2", "4", "5"))
  }

  it should "evaluate complement correctly version 1" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val allElements = sets.values.flatten.toSet
    val result = Try(SetAlgebra.evaluate_expression("~A", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(allElements diff Set("1", "2", "3"))
  }

  it should "evaluate complement correctly version 2" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val allElements = sets.values.flatten.toSet
    val result = Try(SetAlgebra.evaluate_expression("~(A)", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(allElements diff Set("1", "2", "3"))
  }

  it should "evaluate complement correctly version 3" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5"),
      "C" -> Set("5", "6", "7")
    )
    val allElements = sets.values.flatten.toSet
    val result = Try(SetAlgebra.evaluate_expression("~(A | B)", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(allElements diff Set("1", "2", "3", "4", "5"))
  }

  it should "evaluate cartesian product correctly" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("3", "4")
    )
    val result = Try(SetAlgebra.evaluate_expression("A * B", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("(1, 3)", "(1, 4)", "(2, 3)", "(2, 4)"))
  }

  it should "evaluate power set correctly version 1" in {
    val sets = Map(
      "A" -> Set("1", "2")
    )
    val result = Try(SetAlgebra.evaluate_expression("P(A)", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.PowerSet(Set(Set(), Set("1"), Set("2"), Set("1", "2")))
  }

  it should "evaluate power set correctly version 2" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("2", "3")
    )
    val result = Try(SetAlgebra.evaluate_expression("P(A) - P(B)", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.PowerSet(Set(Set("1"), Set("1", "2")))
  }

  it should "evaluate complex expressions correctly" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("2", "3"),
      "C" -> Set("3", "4")
    )
    val result = Try(SetAlgebra.evaluate_expression("A | (B & C)", sets)).getOrElse(Set())
    result shouldEqual SetAlgebra.StringSet(Set("1", "2", "3"))
  }
}