/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card
import org.junit.runner.RunWith
import org.specs2.execute.Result
import org.specs2.runner.JUnitRunner
import org.specs2.specification._
import org.specs2.Specification
import scala.io.Source.fromString
/**
 * @author loveyoupeng
 *
 */
@RunWith(classOf[JUnitRunner])
class HandBuilderSpec extends Specification {
  def is =
    "HandBuilder construct specification" ^ br ^
      "Given a Community" ^ makeCommunity ^
      "And a Pocket" ^ makePocket ^
      "When invoke constructHands" ^ contructHandsByCommunityAndPocket ^
      "Then I should get: ${21} hands" ^ count ^
      "And they should be distinct" ^ distinct ^
      end ^
      "HandBuilder scoreOf specification" ^ br ^
      "Given a Hand" ^ extractHand ^
      "When invoke rankOf" ^ calculateHandRanking ^
      "Then I should get a score" ^ score ^
      end

  object extractHand extends Given[Hand] {
    def extract(text: String): Hand = Hand(List(Card(Ace, Spade), Card(King, Spade), Card(Queen, Spade), Card(Jack, Spade), Card(Ten, Spade)))
  }
  object calculateHandRanking extends When[Hand,HandRanking] {
    def extract(hand: Hand, text: String) = HandBuilder.rankOf(hand)
  }
  object score extends Then[HandRanking] {
    def extract(score:HandRanking, text: String): Result =
      score must not beNull
  }
  
  
  object makeCommunity extends Given[Community] {
    def extract(text: String): Community = {
      val flop = Flop(Card(Queen, Spade), Card(Jack, Spade), Card(Ten, Spade))
      val turn = Card(King, Club)
      val river = Card(Ace, Heart)
      Community(flop, turn, river)
    }
  }
  object makePocket extends When[Community, (Community, Pocket)] {
    def extract(community: Community, text: String) =
      (community, Pocket(Card(Ace, Spade), Card(King, Spade)))
  }
  object contructHandsByCommunityAndPocket extends When[(Community, Pocket), List[Hand]] {
    def extract(cards: (Community, Pocket), text: String) =
      HandBuilder.constructHands(cards._1, cards._2)
  }
  object count extends Then[List[Hand]] {
    def extract(hands: List[Hand], text: String): Result =
      hands must have size (extract1(text).toInt)
  }
  object distinct extends Then[List[Hand]] {
    def extract(hands: List[Hand], text: String): Result =
      hands.distinct must have size (hands.size)
  }

}