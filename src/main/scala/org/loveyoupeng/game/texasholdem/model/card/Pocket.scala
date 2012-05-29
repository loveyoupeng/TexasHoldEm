/**
 *
 */
package org.loveyoupeng.game.texasholdem.model.card


/**
 * @author loveyoupeng
 *
 */
case class Pocket(val first:Card,val second:Card) extends DistinctCardSet(List(first,second),2)