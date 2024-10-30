package forcomp

class AnagramsSuite extends munit.FunSuite:
  import Anagrams.*

  test("wordOccurrences: abcd (3pts)") {
    assertEquals(wordOccurrences("abcd"), List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert (3pts)") {
    assertEquals(wordOccurrences("Robert"), List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }


  test("sentenceOccurrences: abcd e (5pts)") {
    assertEquals(sentenceOccurrences(List("abcd", "e")), List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  test("sentenceOccurrences: I love you") {
    assertEquals(sentenceOccurrences(List("I", "love", "you"))
      , List(('e', 1), ('i', 1), ('l', 1), ('o', 2), ('u', 1), ('v', 1), ('y', 1)))
  }


  test("dictionaryByOccurrences.get: eat (10pts)") {
    assertEquals(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet), Some(Set("ate", "eat", "tea")))
  }


  test("wordAnagrams married (2pts)") {
    assertEquals(wordAnagrams("married").toSet, Set("married", "admirer"))
  }

  test("wordAnagrams player (2pts)") {
    assertEquals(wordAnagrams("player").toSet, Set("parley", "pearly", "player", "replay"))
  }



  test("subtract: lard - r (10pts)") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assertEquals(subtract(lard, r), lad)
  }


  test("subtract: hello - l") {
    val hello = List(('h', 1), ('e', 1), ('l', 2), ('o', 1))
    val l = List(('l', 1))
    val helo = List(('h', 1), ('e', 1), ('l', 1), ('o', 1))
    assertEquals(subtract(hello, l), helo)
    val heo = List(('h', 1), ('e', 1), ('o', 1))
    assertEquals(subtract(helo, l), heo)
  }


  test("combinations: [] (8pts)") {
    assertEquals(combinations(Nil), List(Nil))
  }

  test("combinations: abba (8pts)") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assertEquals(combinations(abba).toSet, abbacomb.toSet)
  }

  test("combinations: abbaccc") {
    val abbaccc = List(('a', 2), ('b', 2), ('c', 3))
    val abbaccccomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2)),

      List(('a', 1)), ('c', 1),
      List(('a', 2)), ('c', 1),
      List(('b', 1)), ('c', 1),
      List(('a', 1), ('b', 1)), ('c', 1),
      List(('a', 2), ('b', 1)), ('c', 1),
      List(('b', 2)), ('c', 1),
      List(('a', 1), ('b', 2)), ('c', 1),
      List(('a', 2), ('b', 2)), ('c', 1),

      List(('a', 1)), ('c', 2),
      List(('a', 2)), ('c', 2),
      List(('b', 1)), ('c', 2),
      List(('a', 1), ('b', 1)), ('c', 2),
      List(('a', 2), ('b', 1)), ('c', 2),
      List(('b', 2)), ('c', 2),
      List(('a', 1), ('b', 2)), ('c', 2),
      List(('a', 2), ('b', 2)), ('c', 2),

      List(('a', 1)), ('c', 3),
      List(('a', 2)), ('c', 3),
      List(('b', 1)), ('c', 3),
      List(('a', 1), ('b', 1)), ('c', 3),
      List(('a', 2), ('b', 1)), ('c', 3),
      List(('b', 2)), ('c', 3),
      List(('a', 1), ('b', 2)), ('c', 3),
      List(('a', 2), ('b', 2)), ('c', 3)
    )
  }

  test("combinations: aaabbbcc (8pts)") {
    val combs = combinations(List(('a', 3), ('b', 3), ('c', 2)))
    assert(combs.contains(List(('b', 2))))
    assert(combs.contains(List(('c', 1))))
    assert(combs.contains(List(('a', 3))))

  }

//  test("combinations: aabb (8pts)") {
//    val combs = combinations(List(('a', 2), ('b', 2)))
//    println(combs.length)
//    println(combs)
//  }

//  test("combinations: aa (8pts)") {
//    val combs = combinations(List(('a', 4)))
//    println(combs.length)
//    println(combs)
//  }

  test("sentence anagrams: [] (10pts)") {
    val sentence = List()
    assertEquals(sentenceAnagrams(sentence), List(Nil))
  }

  test("Exercise test: I love you") {
    val sentence = List("I", "love", "you")
    assertEquals(sentenceAnagrams(sentence),
      List(List("you", "I", "love"), List("you", "Io", "Lev"),
        List("you", "Lev", "Io"), List("you", "love", "I"), List("you", "olive"),
        List("I", "you", "love"), List("I", "love", "you"), List("Io", "you", "Lev"),
        List("Io", "Lev", "you"), List("Lev", "you", "Io"), List("Lev", "Io", "you"),
        List("love", "you", "I"), List("love", "I", "you"), List("olive", "you")))
  }

  test("sentence anagrams: Linux rulez (10pts)") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assertEquals(sentenceAnagrams(sentence).toSet, anas.toSet)
  }


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
