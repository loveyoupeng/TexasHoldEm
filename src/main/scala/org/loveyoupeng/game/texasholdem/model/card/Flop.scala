/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card

/**
 * @author loveyoupeng
 *
 */
case class Flop(val first:Card,val second:Card,val third:Card) extends DistinctCardSet(List(first,second,third),3)