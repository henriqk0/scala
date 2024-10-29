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
    val result = Try(SetAlgebra.evaluateExpression("A | B", sets)).getOrElse(Set())
    result should equal(Set("1", "2", "3", "4", "5"))
  }

  it should "evaluate intersection correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluateExpression("A & B", sets)).getOrElse(Set())
    result should equal(Set("3"))
  }

  it should "evaluate difference correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluateExpression("A - B", sets)).getOrElse(Set())
    result should equal(Set("1", "2"))
  }

  it should "evaluate symmetric difference correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val result = Try(SetAlgebra.evaluateExpression("A ^ B", sets)).getOrElse(Set())
    result should equal(Set("1", "2", "4", "5"))
  }

  it should "evaluate complement correctly" in {
    val sets = Map(
      "A" -> Set("1", "2", "3"),
      "B" -> Set("3", "4", "5")
    )
    val allElements = sets.values.flatten.toSet
    val result = Try(SetAlgebra.evaluateExpression("~A", sets)).getOrElse(Set())
    result should equal(allElements diff Set("1", "2", "3"))
  }

  it should "evaluate cartesian product correctly" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("3", "4")
    )
    val result = Try(SetAlgebra.evaluateExpression("A * B", sets)).getOrElse(Set())
    result should equal(Set("(1, 3)", "(1, 4)", "(2, 3)", "(2, 4)"))
  }

  it should "evaluate power set correctly" in {
    val sets = Map(
      "A" -> Set("1", "2")
    )
    val result = Try(SetAlgebra.evaluateExpression("P(A)", sets)).getOrElse(Set())
    result should equal(Set(Set(), Set("1"), Set("2"), Set("1", "2")))
  }

  it should "handle unknown tokens gracefully" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("3", "4")
    )
    val result = Try(SetAlgebra.evaluateExpression("A | unknown", sets)).getOrElse(Set())
    result should equal(Set()) // Assuming it returns empty set for invalid tokens
  }

  it should "evaluate complex expressions correctly" in {
    val sets = Map(
      "A" -> Set("1", "2"),
      "B" -> Set("2", "3"),
      "C" -> Set("3", "4")
    )
    val result = Try(SetAlgebra.evaluateExpression("A | (B & C)", sets)).getOrElse(Set())
    result should equal(Set("1", "2", "3"))
  }
}
