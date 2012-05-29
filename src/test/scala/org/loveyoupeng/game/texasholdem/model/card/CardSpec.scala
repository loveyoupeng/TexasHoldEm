/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card

import org.scalatest.Specs
import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.matcher.Matchers
import scala.util.Random
/**
 * @author loveyoupeng
 *
 */
@RunWith(classOf[JUnitRunner])
class CardSpec extends Specification {
  "The Card Set" should {
    "contains specified cards" in {
      new CardSet(List(Card(Ace, Spade)), 1) {}.cards must have size (1)
      new CardSet(Nil, 2) {} must throwA[Exception]
    }
  }

  "The Distinct Card Set" should {
    "not contains duplicated cards" in {
      new DistinctCardSet(List(Card(Ace, Spade), Card(Ace, Spade)), 2) {} must throwA[Exception]
    }
  }

  "The Suits" should {
    "contains 4 types" in {
      Card.suits must have size (4);
    }
    "Spade is greater than others" in {
      forall(Card.suits)((_: Suit) must be_<=(Spade))
    }
  }

  "The Ranking" should {
    "contains 13 values" in {
      Card.rankings must have size (13);
    }
    "Ace is greater than others" in {
      forall(Card.rankings)((_: Ranking) must be_<=(Ace))
    }
  }

  "The Card" should {
    "contains a Ranking and a Suit" in {
      for {
        ranking <- Card.rankings;
        suit <- Card.suits
      } {
        val card = Card(ranking, suit)
        card.ranking must equalTo(ranking)
        card.suit must equalTo(suit)
      }
      Card(null, Spade) must throwA[Exception]
      Card(Ace, null) must throwA[Exception]
      Card(null, null) must throwA[Exception]
    }

    "Ace should be greater than all" in {
      val cards = for {
        ranking <- Card.rankings;
        suit <- Card.suits
      } yield Card(ranking, suit)
      forall(cards)((_: Card) must be_<=(Card(Ace, Spade)))
      forall(cards)((_: Card) must be_<=(Card(Ace, Heart)))
      forall(cards)((_: Card) must be_<=(Card(Ace, Diamond)))
      forall(cards)((_: Card) must be_<=(Card(Ace, Club)))
    }

    "Ace Spade should be greater than all if consider suit" in {
      val cards = for {
        ranking <- Card.rankings;
        suit <- Card.suits
      } yield Card(ranking, suit)
      val aceSpade = Card(Ace, Spade)
      forall(cards.filter(_ != aceSpade))((card: Card) =>
        Card.CardOrderingWithSuit.compare(card, aceSpade) must be_<(0))
    }
  }

  "The Pack" should {
    "contains 52 cards" in {
      Card.makeAPack.cards must have size (52)
    }

    "Ace should be greater than others" in {
      forall(Card.makeAPack.cards)((_: Card) must be <= (Card(Ace, Spade)))
      forall(Card.makeAPack.cards)((_: Card) must be <= (Card(Ace, Heart)))
      forall(Card.makeAPack.cards)((_: Card) must be <= (Card(Ace, Diamond)))
      forall(Card.makeAPack.cards)((_: Card) must be <= (Card(Ace, Club)))
    }

    "shuffle the cards must not change the pack size" in {
      Card.makeAPack.shuffle.distinct must have size (52)
    }
  }

  "The Pocket" should {
    "contains 2 cards" in {
      Pocket(Card(Ace, Spade), Card(King, Spade)).cards must have size (2)
    }
  }

  "The Flop" should {
    "contains 3 cards" in {
      Flop(Card(Queen, Spade), Card(Jack, Spade), Card(Ten, Spade)).cards must have size (3)
    }
  }

  "The Community" should {
    "contains 1 Flop, 1 turn and 1 river and in total 5 cards" in {
      val flop = Flop(Card(Queen, Spade), Card(Jack, Spade), Card(Ten, Spade))
      val turn = Card(King, Spade)
      val river = Card(Ace, Spade)
      val community = Community(flop, turn, river)
      community.flop must equalTo(flop)
      community.turn must equalTo(turn)
      community.river must equalTo(river)
    }
  }

  "The Hand" should {
    "contains 5 cards" in {
      Hand(List(Card(Ace, Spade), Card(King, Spade), Card(Queen, Spade), Card(Jack, Spade), Card(Ten, Spade))).cards must have size (5)
      Hand(List(Card(Ace, Spade), Card(King, Spade), Card(Queen, Spade))).cards must throwA[Exception]
    }
  
    "cards shold be sorted" in {
      val sorted = List(Card(Ten, Spade), Card(King, Diamond), Card(King, Club), Card(Ace, Heart), Card(Ace, Spade))
      val hand = Hand(sorted.reverse)
      hand.cards must equalTo(sorted)
    }
  }
}