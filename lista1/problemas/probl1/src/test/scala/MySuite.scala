import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class MySuite extends AnyFlatSpec with Matchers {

  "convert_to_another_base" should "convert from binary to decimal" in {
    val result = convert_to_another_base("1010", 2, 10)
    result shouldEqual "base2(1010) = base10(10)"
  }

  it should "convert from decimal to binary" in {
    val result = convert_to_another_base("10", 10, 2)
    result shouldEqual "base10(10) = base2(1010)"
  }

  it should "convert from octal to hexadecimal" in {
    val result = convert_to_another_base("12", 8, 16)
    result shouldEqual "base8(12) = base16(A)"
  }

  it should "convert from hexadecimal to decimal" in {
    val result = convert_to_another_base("A", 16, 10)
    result shouldEqual "base16(A) = base10(10)"
  }

  it should "handle invalid input gracefully" in {
    assertThrows[IllegalArgumentException] {
      convert_to_another_base("XYZ", 16, 10)
    }
  }

  it should "convert from base 20 to base 10" in {
    val result = convert_to_another_base("A", 20, 10)
    result shouldEqual "base20(A) = base10(10)"
  }

  it should "convert from base 10 to base 20" in {
    val result = convert_to_another_base("10", 10, 20)
    result shouldEqual "base10(10) = base20(A)"
  }
}