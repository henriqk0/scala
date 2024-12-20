import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class MySuite extends AnyFlatSpec with Matchers {

  "A well_formed_parenthesis function" should "return that the expression is well formed for balanced parentheses" in {
    val input = "()(())"
    val result = well_formed_parenthesis(input)
    result shouldEqual "A expressão de parêntesis está bem formada."
  }

  it should "return that the expression is not well formed when there are more opening parentheses" in {
    val input = "((()"
    val result = well_formed_parenthesis(input)
    result shouldEqual "A expressão de parêntesis não está bem formada: 2 parêntesis abertos a mais."
  }

  it should "return that the expression is nt well formed when there are more closing parentheses" in {
    val input = "())"
    val result = well_formed_parenthesis(input)
    result shouldEqual "A expressão de parêntesis não está bem formada: há parêntese fechado não-relacionado com aberto."
  }

  it should "return that the expression is well formed for an empty string" in {
    val input = ""
    val result = well_formed_parenthesis(input)
    result shouldEqual "A expressão de parêntesis está bem formada."
  }
}
