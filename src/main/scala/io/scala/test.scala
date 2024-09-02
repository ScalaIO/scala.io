import scala.util.Random
object nextDouble {
  private class RandomGenerator(seed: Long) {
    Random.setSeed(seed)
    private var size          = 1
    private var randomNumbers = Array.empty[Double]
    private var index         = 0
    rebuild
    private def rebuild = {
      size *= 2
      println("Generating random numbers of size: " + size)
      randomNumbers = Array.fill(size)(Random.nextDouble())
      index = 0
    }
    def get: Double = {
      try {
        val res = randomNumbers(index)
        index += 1
        res
      } catch {
        case e: Exception =>
          rebuild
          get
      }
    }
  }
  private val generator = new RandomGenerator(0)
  def apply(): Double = synchronized {
    generator.get
  }
}