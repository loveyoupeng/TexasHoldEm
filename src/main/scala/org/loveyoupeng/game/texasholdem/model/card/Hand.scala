/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card

/**
 * @author loveyoupeng
 *
 */
case class Hand(chosenCards:List[Card]) 
	extends DistinctCardSet(chosenCards.sorted(Card.CardOrderingWithSuit),5)