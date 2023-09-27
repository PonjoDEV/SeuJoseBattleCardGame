package control

import model.Card
import model.Player

class PlayerControl (){
    private fun discard(player: Player){
        var cardIndex = player.hand.size
        println("Selecione uma carta entre 1 e $cardIndex para descartar: ")
        cardIndex= readln().toInt()-1
        if ((cardIndex in 0..4)){
            while (player.hand.get(cardIndex)==null){
                println("Escolha uma posição válida")
                cardIndex= readln().toInt()-1
            }
            player.hand.set(cardIndex,value = null)
        }
    }

    fun drawCard(player: Player, deck: List<Card>):Boolean {
        if (player.hand.size<10){
            player.hand.plus(deck.get(deck.size-1))
            // TODO retirar a ultima carta do deck deck.reduce(?)
            return true
        }else{
            println("Sua mão está cheia\nSelecione um número de 1 a 5 para descartar de sua mão")
            //discard(player) TODO select the card to be discarded then call drawCard again
            drawCard(player,deck)
            return false
        }
    }



}