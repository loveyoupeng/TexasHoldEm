/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card


/**
 * @author loveyoupeng
 *
 */
case class Community(val flop:Flop,val turn:Card,val river:Card) extends DistinctCardSet(flop.cards ++ (turn :: river :: Nil),5)