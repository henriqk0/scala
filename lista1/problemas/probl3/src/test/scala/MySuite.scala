import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class MySuite extends AnyFlatSpec with Matchers {

  "A is_perf_number function" should "identify 6 as a perfect number" in {
    val result = is_perf_number(6)
    result shouldEqual "6 é um número perfeito."
  }

  it should "identify 28 as a perfect number" in {
    val result = is_perf_number(28)
    result shouldEqual "28 é um número perfeito."
  }

  it should "identify 496 as a perfect number" in {
    val result = is_perf_number(496)
    result shouldEqual "496 é um número perfeito."
  }

  it should "identify 12 as not a perfect number" in {
    val result = is_perf_number(12)
    result shouldEqual "12 não é um número perfeito."
  }

  it should "identify 0 as not a perfect number" in {
    val result = is_perf_number(0)
    result shouldEqual "0 não é um número perfeito."
  }

  it should "identify 1 as not a perfect number" in {
    val result = is_perf_number(1)
    result shouldEqual "1 não é um número perfeito."
  }
}
