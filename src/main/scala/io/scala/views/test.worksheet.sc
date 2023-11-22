type test = 1 | 2

def test(a: Any) =
  a match
    case oneOrTwo: test => println("one or two")
    case _ => println("something else")

test(1)
test("str")
test(3)
