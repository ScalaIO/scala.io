type test = 1 | 2
case class Foo(x: Int)

def test(a: Any) =
  a match
    case oneOrTwo: test => println("one or two")
    case i: Int => println(f"an int: $i%02d")
    case s"$firstName $lastName" => println(s"two strings: $firstName $lastName")
    case s"${Foo(x)}" => println(s"Foo($x)")
    case _ => println("something else")

test(1)
test("str")
test("str1 str2")
test(3)

