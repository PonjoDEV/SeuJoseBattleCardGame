package control

import model.Card
import model.Player

class PlayerControl (){
    //Discarding a card
    private fun discard(player: Player): Boolean {
        var cardIndex = player.hand.size
        println("Selecione uma carta entre 1 e $cardIndex para descartar: ")
        cardIndex = readln().toInt()-1
        var count:Int = 0

        while (count<=2|| cardIndex !in 0..<player.hand.size ||player.hand.get(cardIndex)==null) {
            println("Escolha uma posição válida")
            cardIndex= readln().toInt()-1
            count++
        }
        if (count<=5) {
            player.hand.set(cardIndex, value = null)
            return true
        }else{
            return false
        }
    }

    //Player has to draw a card
    fun drawCard(player: Player, deck: MutableList<Card>):Boolean {
        var aux:Boolean = false
        if (player.hand.any { it == null }){
            //Finding first null value
            val indexNull:Int = player.hand.indexOf(player.hand.find { it == null })
            player.hand.set(indexNull,deck.get(deck.size-1))
            player.hand.iterator()
            deck.removeLast()
            return true
        }else{
            println("Sua mão está cheia!")
            aux = discard(player)
            drawCard(player, deck)
            return aux
        }
    }



}