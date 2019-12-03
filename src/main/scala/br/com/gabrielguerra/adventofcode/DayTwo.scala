package br.com.gabrielguerra.adventofcode

import scala.io.Source
import scala.util.control.Breaks._

object DayTwo extends App {

  val originalMemory = Source.fromResource("day_2_input.txt").getLines().flatMap(s => s.split(",")).map(s => s.toInt).toArray

  val result = for {
    noun <- 0 to 99
    verb <- 0 to 99
    if program(noun, verb) == 19690720
  } yield 100 * noun + verb

  println(result)

  def program(noun: Int, verb: Int): Int = {
    val memory = originalMemory.clone

    memory(1) = noun
    memory(2) = verb

    val sum = (a: Int, b: Int) => a + b
    val multiply = (a: Int, b: Int) => a * b
    val halt = (_: Int, _: Int) => break

    val instructions = Map(
      1 -> sum,
      2 -> multiply,
      99 -> halt
    )

    breakable {
      var i = 0
      while (i < memory.length) {
        val operation = instructions(memory(i))
        val firstArgIndex = memory(i + 1)
        val secondArgIndex = memory(i + 2)
        val result = operation(memory(firstArgIndex), memory(secondArgIndex))
        val resultIndex = memory(i + 3)
        memory(resultIndex) = result
        i += 4
      }
    }

    memory(0)
  }

}
