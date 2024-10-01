package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    def itFn(c: Int, r: Int): Int =
      if r == 0 then 1
      else
        val left = if c == 0 then 0 else itFn(c - 1, r - 1)
        val right = if c == r then 0 else itFn(c, r - 1)
        left + right

    itFn(c, r)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
    def it(cs: List[Char], acc: Int): Boolean =
      if cs.isEmpty then acc == 0
      else
        if cs.head == '(' then it(cs.tail, acc + 1)
        else if cs.head == ')' then
          acc!=0 && it(cs.tail, acc - 1)
        else it(cs.tail, acc)
    it(chars, 0)

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    def itCoins(mon: Int, coinsLeft: List[Int], acc: Int): Int =
      if coinsLeft.isEmpty || mon < coinsLeft.head then acc else
        if mon == coinsLeft.head then acc + 1
        else itCoins(mon - coinsLeft.head, coinsLeft, acc) + itCoins(mon, coinsLeft.tail, acc)

    def sortCoins(unsorted: List[Int]): List[Int] =
      if unsorted.size <= 1 then unsorted else
        val mid: Int      = unsorted.size / 2
        val left          = unsorted.take(mid)
        val right         = unsorted.drop(mid)
        val sortedLeft    = sortCoins(left)
        val sortedRight   = sortCoins(right)
        mergeCoins(sortedLeft, sortedRight, List.empty)

    def mergeCoins(left: List[Int], right: List[Int], merged: List[Int]): List[Int] =
      if left.isEmpty then merged ::: right else
        if right.isEmpty then merged ::: left else
          if left.head < right.head then mergeCoins(left.tail, right, merged :+ left.head)
          else mergeCoins(left, right.tail, merged :+ right.head)

    itCoins(money, sortCoins(coins), 0)
