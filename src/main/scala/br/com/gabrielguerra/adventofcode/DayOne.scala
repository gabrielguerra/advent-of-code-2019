package br.com.gabrielguerra.adventofcode

import scala.annotation.tailrec
import scala.io.Source


object DayOne extends App {

  def calculateFuel(mass: Int): Int = {
    mass / 3 - 2
  }

  @tailrec
  def calculateFuelOfFuel(acc: Int, mass: Int): Int = {
    val fuel = calculateFuel(mass)
    if (fuel <= 0)
      acc
    else
      calculateFuelOfFuel(acc + fuel, fuel)
  }

  val moduleList = Source.fromResource("day_1_input.txt").getLines().toList
  val fuelList = moduleList.map(s => s.toInt).map(calculateFuel)

  println("Simple Fuel (part 1)")
  println(fuelList.sum)

  val fuelFuelList = fuelList.map(f => calculateFuelOfFuel(0, f))

  println("Total Fuel (part 2)")
  println(fuelList.sum + fuelFuelList.sum)
}

