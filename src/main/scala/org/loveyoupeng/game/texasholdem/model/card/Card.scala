package org.loveyoupeng.game.texasholdem.model.card
import scala.util.Random

case class Card(val ranking: Ranking, val suit: Suit) extends Ordered[Card] {
  require(ranking != null, "Card's ranking can not be null")
  require(suit != null, "Card's suit can not be null")
  def compare(that: Card): Int = {
    ranking.compare(that.ranking)
  }
}

object Card {
  def suits: List[Suit] = List(Spade, Heart, Club, Diamond)
  def rankings: List[Ranking] = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  object CardOrderingWithSuit extends Ordering[Card] {
    def compare(left: Card, right: Card): Int = {
      val rankingResult = left.ranking.compare(right.ranking)
      if (rankingResult == 0)
        left.suit.compare(right.suit)
      else
        rankingResult
    }
  }
  def makeAPack(): Pack = {
    Pack(for {
      suit <- suits;
      ranking <- rankings
    } yield Card(ranking, suit))
  }

}