package lab3

import lab3.Lab03.findFirst


/*
 * This file aims to help you with getting familiar with Scala.
 * To finish this tutorial, you need to fill up 20 functions.
 * The type and specification of each function are provided, 
 * you are suggested to read them before coding!
 * 
 * To install Scala, please refer to https://www.scala-lang.org/download/
 * 
 * Tips:
 * 1) Try to avoid mutable values;
 * 2) Try to use built-in functions, such as fold, map, filter, etc.
 * 3) Better to use recursion functions instead of loops.
 * 4) Try to reuse the functions you have already implemented.
 * 
 * Have fun with Scala!
 * 
 ************************************************* 
 * Author:  Yahui Song  (e0210374@u.nus.edu)     *
 * Date:    22/1/2019                            *
 * Purpose: Tutorial for CS4215 (2018/2019 sem2) *
 *************************************************
 * 
 * Submission: please rename this file to: "Lab03_<ID>_<Name>.scala"
 * eg. Lab03_A12345678_Yahui.scala
 * 
 * Deadline: 29/01/2019 23:59pm
 * 
 */

// object => a class with a singleton
// class => fields and methods

object Lab03 {
  
  def main(args: Array[String]): Unit = {
    /*
     * There are four main parts for you to practice with: 
     *     Lists, Numbers, Trees and Higher Order functions
     */
    testCasesLists ()
    testCasesNumbers ()
    testCasesTrees ()
    testCasesHigherOrder () 
  }
  
  def testCasesLists ():Unit = {
    // There are 6 test cases for Lists exercises
    println ("01): " + last_snd  ( ls1)) // Some(c)
    println ("02): " + compress  ( ls2)) // List(a, b, c, a, d, e)
    println ("03): " + removeDupl( ls2)) // List(a, b, c, d, e)
    println ("04): " + findFirst (((x:Int)  => x % 2 == 0), List(3,6,7,3,4,8,3,3,3)))// Some(6)
    println ("05): " + findLast  (((x:Int)  => x % 2 == 0), List(3,6,7,3,4,8,3,3,3)))// Some(8)
    println ("06): " + genPairs  (6) ) // List((1,5), (2,4), (3,3), (4,2), (5,1))
  }
  def testCasesNumbers ():Unit = {
    // There are 5 test cases for Numbers exercises
    println ("07): " + isPrime   (13))  // true
    println ("08): " + allPrimes (1, 100)) //List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)
    println ("09): " + pfactors  (315))  // List(3, 3, 5, 7)
    println ("10): " + pfactorsM (315)) // List((3,2), (5,1), (7,1))
    println ("11): " + goldbach  (28)) // (5, 23)
  }

  def testCasesTrees ():Unit = {
    // There are 4 test cases for Trees exercises
    println ("12): " + countL   ( rt1) ) // 3
    println ("13): " + prefixBT ( rt1) ) // List(1, 3, 4, 5, 2)
    println ("14): " + infixBT  ( rt1) ) // List(4, 3, 5, 1, 2)
    println ("15): " + perfectTree (3) ) //Node(1,Node(1,Leaf(1),Leaf(1)),Node(1,Leaf(1),Leaf(1)))    
  }
  
  def testCasesHigherOrder ():Unit = {
    // There are 5 test cases for higher order functions
    println ("16): " + prod (List(1,2), List('a','b','c'))) // List((1,a), (1,b), (1,c), (2,a), (2,b), (2,c))
    println ("17): " + prefixRT    ( rt2))  // List(1, 2, 3, 4, 5)
    println ("18): " + prefixRTHO  ( rt2))  // List(1, 2, 3, 4, 5)
    println ("19): " + postfixRTHO ( rt2))  // List(2, 4, 3, 5, 1) 
    println ("20): " + string_of_RT( rt2))  // 1(2,3(4),5)
  }
  
  val ls1 = List('a' , 'b' , 'c' , 'd' ) 
  val ls2 = List('a','a','a','a','b','c','c','a','a','d','e','e','e','e')
    
  def last_snd [A] (xs: List[A]) : Option [A] = xs match {
    /* # 01
     * Implement a function that would return the 
     * 2nd last element. If only one element exist,
     * return that element. For example:
     * 	last_two (List (1,2,3,4,5)) ===> Some (4)
     * 	last_two (List (5)) ===> Some (5)
     * 	last_two (List ()) ===> None
     */
    case Nil => None
    case x :: Nil => Some(x)
    case _ => Some(xs(xs.length - 2))
  }

  def compress [A] (xs: List[A]) : List [A] = {
    /* # 02
       * Implement a recursive function that would remove
       * duplicates that occur consecutively.
       * For example:
       * 	compress (List(1,1,2,2,1)) ==> List(1,2,1)
       */
    xs.foldLeft(List[A]()){
      case (acc, el) if acc.isEmpty => List(el)
      case (acc, el) if acc.last == el => acc
      case (acc, el) => acc ::: List(el)
    }
  }
    
   def removeDupl [A] (xs: List[A]) : List [A] = {
      /* # 03
       * Implement a function that would remove
       * all duplicates in a list.
       * For example:
       * 	 removeDupl (List(1,1,2,2,1)) ==> List(1,2)
       */
     xs.foldLeft(List[A]())((acc, x) => {
       if (acc contains x) {
         acc
       } else {
         x :: acc
       }
     }).reverse
    }
   
   def findFirst[A] (fx: A => Boolean, xs:List[A]): Option [A] = {
     /* # 04
      * Implement a function that would return the
      * first element in a list that satisfies a given predicate
      * For example:
      * 	 findFirst (((x:Int)  => x > 1), List(1,1,2,1,4,1))  ==> Some (2)
      * 	 findFirst (((x:Int)  => x > 4), List(1,1,2,1,4,1))) ==> None
      */
     xs find fx
   }
   
   def findLast[A] (fx: A => Boolean, xs:List[A]): Option [A] = {
     /* # 05
      * Implement a function that would return the
      * last element in a list that satisfies a given predicate
      * For example:
      * 	 findFirst (((x:Int)  => x > 1), List(1,1,2,1,4,1))  ==> Some (4)
      * 	 findFirst (((x:Int)  => x > 4), List(1,1,2,1,4,1))) ==> None
      */
     xs.reverse find fx
   }
   
   def genPairs (num:Int):List [(Int, Int)] = {
     /* # 06
      * Given a number n>1, generate all possible
      * pairs of positive numbers (a,b) such that n=a+b 
      * For example:
      * 		genPairs (3) ===> List((1,2), (2,1))
      */
     (1 until num).foldLeft(List[(Int, Int)]())((accum, el) => accum:::List((el, num - el)))
   }
   
   def isPrime(num:Int):Boolean = {
     /* # 07
      * Given a number n, return true if it is a prime number
      * otherwise return false
      * For example:
      * 		isPrime (2) ==> true
      * 		isPrime (4) ==> false
      */
     (num > 1) && ((2 to math.sqrt(num).toInt) forall(num % _ != 0))
   }
   
   def allPrimes(start:Int, end:Int):List [Int] = {
     /* # 08
      * Given a range of integers by its lower and upper limit, 
      * construct a list of all prime numbers in that range. 
      * For example:
      * 		allPrimes (10, 2) ==> List()
      * 		allPrimes (2, 10) ==> List(2, 3, 5, 7)
      */
     def divides(primes: List[Int], num: Int): Boolean = primes.exists(num % _ == 0)
     (2 to end).foldLeft(List[Int]())((primes, x) => {
       val max = scala.math.sqrt(x).toInt
       if (!divides(primes.takeWhile((p:Int) => p <= max), x)) {
         primes ::: List(x)
       } else {
         primes
       }
     })
   }

  def pfactors (num: Int):List [Int] = {
     /* # 09
      * Given a number, return its prime factors.
      * For example:
      * 		pfactors (6)  ==> List(2,3))
      * 		pfactors (12) ==> List(2,2,3))
      */

     def pfactor(lst: List[Int], n: Int, primes: List[Int]): List[Int] = {
       n match {
         case n if n == 1 => lst
         case n if n % primes.head == 0 => pfactor(lst:::List(primes.head), n / primes.head, primes)
         case _ => pfactor(lst, n, primes.drop(1))
       }
     }
    val primesF = allPrimes(1, num)
    pfactor(List[Int](), num, primesF)
   }


   def pfactorsM (num: Int):List [(Int, Int)] = {
     /* # 10
      * Given a number, return a list of tuples, representing
      * unique prime factors and their occurrences.
      * For example:
      * 		pfactorsM 6  ==> List((2,1), (3,1))
      * 		pfactorsM 12 ==> List((2,2), (3,1))
      */
     val pfacts = pfactors(num)
     removeDupl(pfacts.map(x => (x, pfacts.count(_ == x))))
   }

   def goldbach (num : Int) : (Int, Int) = {
     /* # 11
      * Goldbach's conjecture says that every positive even number greater 
      * than 2 is the sum of two prime numbers. Example: 28 = 5 + 23. It is 
      * one of the most famous facts in number theory that has not been proved 
      * to be correct in the general case. It has been numerically confirmed 
      * up to very large numbers. Write a function to find the two prime 
      * numbers that sum up to a given even integer.
      * For example:
      * 		goldbach 4 ==> (2,2)
      * 		goldbach 8 ==> (3,5)
      */
     val primesToNum = allPrimes(2, num)
     val res = genPairs(num).filter(p => primesToNum.contains(p._1) && primesToNum.contains(p._2))
     res match {
       case Nil => (-1, -1) // should not be possible
       case o => o.head
     }
   }
   
   

   // Here is the definition of Trees
   sealed trait Tree[A]
   case class Leaf[A](value: A) extends Tree[A]
   case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]
   
   val rt1 = Node (1,(Node (3,(Leaf (4)),(Leaf( 5)))), Leaf (2))
   
   
   def countL[A] (tree: Tree[A]) : Int = {
     /* # 12
      * Write a function that would count the number of leaves
      * in a given binary tree
      * For example:
      * 		countL (Leaf (0)) ==> 1
      * 		countL (Node(0,(Leaf (0)),Node(0,Leaf( 0),Leaf (0)))) ==> 3
      */
      tree match {
        case Node(_, l, r) => countL(l) + countL(r)
        case Leaf(_) => 1
      }
   }
   
   def prefixBT [A] (tree: Tree[A]):List [A]= {
     /* # 13
      * We can flatten a tree into a list in prefix fashion
      * by putting value at node, then values of left subtreee,
      * followed by values of right subtrees.
      * For example:
      * 		prefixBT (Node(4,Leaf (1), Leaf (2))) ==> List(4, 1, 2)
      */
     tree match {
       case Node(v, l, r) => List(v) ++ prefixBT(l) ++ prefixBT(r)
       case Leaf(v) => List(v)
     }
   }
   
   def infixBT [A] (tree: Tree[A]):List [A]= {
     /* # 14
      * We can flatten a tree into a list in infix fashion
      * by putting values of left subtreee, value at node,
      * followed by values of right subtrees.
      * For example:
      * 		infixBT (Node(4,Leaf (1), Leaf (2))) ==> List(1, 4, 2)
      */
     tree match {
       case Node(v, l, r) =>  infixBT(l) ++ List(v) ++ infixBT(r)
       case Leaf(v) => List(v)
     }
   }
   
   def perfectTree (num : Int) : Tree [Int] = {
     /* # 15
      * A tree is perfectly balanced if either it is a leaf
      * or it is a node with two subtrees of the same height and also
      * perfectly balanced. Write a function that takes a height
      * value and then returning a perfect tree of that height with 
      * all its elements set to 1 
      * For example:
      * 		perfectTree (2) ==> Node(1,Leaf(1),Leaf(1))
      * 
      */
     num match {
       case 1 => Leaf(1)
       case n => Node(1, perfectTree(n - 1), perfectTree(n - 1))
     }
   }
   def prod [A, B] (xs: List [A], ys:List [B]): List [(A,B)] = {
     /* # 16 
      * Given two lists, return a list of all possible
      * pairs of the two lists.
      * For example 
      * 		  prod (List(1,2), List('a','b')) ===> 
      * 				List((1,a), (1,b), (2,a), (2,b))
      * Use higher-order function 'map' to help you in this task.
      */

    def f(x: A, ys: List[B]): List[(A,B)] = ys.map(y => (x, y))
     xs flatMap(x => ys map(y => (x, y)))
   }
   
   
   // polymorphic rose tree 
   sealed trait roseTree[A]
   case class NodeR[A] (value:A, list: List[roseTree[A]]) extends roseTree[A]

   val rt2 = NodeR (1, List ( NodeR (2,List()),NodeR (3,List(NodeR (4,List()))),NodeR (5,List())))
   
   /* # 17
    * We can flatten a rosetree into a list in prefix fashion
    * by putting value at node, followed by values of each 
    * of the subtrees.
    * Implement a first-order version of this prefixRT
    * method without using any higher-order functions.
    * For example:
    * 		prefixRT (NodeR(4,List(NodeR (1,List()), NodeR (2,List()))))  ===>
    * 		List(4, 1, 2)
    * Below is a first-order implementation.
    */
   def prefixRT [A] (xs :roseTree[A] ) : List[A] = xs match{
     case NodeR(v,res) => List (v) ::: comb_prefixRT (res)
   }
   
   def comb_prefixRT [A] (xs : List[roseTree[A]] ) : List[A] = xs match{
     case List() => List ()
     case _ => prefixRT (xs.head) ::: comb_prefixRT (xs.tail)
   }
   
   def prefixRTHO [A] (xs :roseTree[A] ) : List[A] = xs match {
     /* # 18
      * write a higher-order counterpart for prefixRT 
      * Use higher-order function "foldRight" to help
      * you in this method.
      */
     case NodeR(v, children) => v :: children.foldRight(List[A]())((x: roseTree[A], acc: List[A]) => {
       prefixRTHO(x) ::: acc
     })
   }

   def postfixRTHO [A] (xs :roseTree[A] ) : List[A] = xs match {
     /* # 19
      * write a higher-order counterpart for postfixRT 
      * Use higher-order function 'foldRight' to help
      * you in this method.
      */
//     xs match {
//       case NodeR(v, children) => children.foldLeft(List[A]()){
//         case (accum, el) => accum ::: prefixRTHO(el)
//       } ::: List(v)
//     }
//     post(List(xs))
     case NodeR(v, children) => children.foldRight(List[A]())((x: roseTree[A], acc: List[A]) => {
       postfixRTHO(x) ::: acc
     }) ::: List(v)
   }
   
   def string_of_RT[A] ( xs :roseTree[A] ) : String =  {
     /* # 20
      * Write a function which generates such a string representation
      * for rose tree.
      * which prints a list of items separated by comma. 
      */
     xs match {
       case NodeR(v, children) =>
         if (children.isEmpty) {
           v.toString
         } else {
           v.toString + "(" + children.tail.foldLeft(string_of_RT(children.head))((acc, el) => acc + ","+string_of_RT(el)) + ")"
         }
     }
   }
}

