package patmat

class HuffmanSuite extends munit.FunSuite:
  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }


  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a','b','d'))
  }

  test("string2chars hello world") {
    assertEquals(string2Chars("hello, world"), List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))), List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list (15pts)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(combine(leaflist), List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
  }

  test("times function with a small list") {
    val small = List('a', 'b', 'c', 'a')
    val pairs = times(small)
    assert(pairs.contains(('a', 2)))
    assert(pairs.contains(('b', 1)))
    assert(pairs.contains(('c', 1)))
    assert(pairs.size == 3)
  }

  test("encode and decode separately") {
    new TestTrees:
      assertEquals(encode(t1)("ab".toList), List(0, 1))
      assertEquals(decode(t1, List(0, 1)), List('a', 'b'))
  }

  test("convert") {
    new TestTrees:
      assertEquals(convert(t1), List(('a',List(0)), ('b',List(1))))
      assertEquals(convert(t2), List(('a',List(0, 0)), ('b',List(0, 1)), ('d',List(1))))
  }

  test("quickencode") {
    new TestTrees:
      assertEquals(quickEncode(t1)("ab".toList), List(0, 1))
  }

//
//  test("decode secret") {
//    println(decodedSecret)
//  }


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
