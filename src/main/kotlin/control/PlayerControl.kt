package control

import model.Card
import model.Player

class PlayerControl (){
    //Discarding a card
    private fun discard(player: Player): Boolean {
        var cardIndex = player.hand.size
        println("Selecione uma carta entre 1 e $cardIndex para descartar: ")
        cardIndex = readln().toInt()-1


        while (cardIndex !in 0..<player.hand.size ||player.hand.get(cardIndex)==null) {
            println("Escolha uma posição válida")
            cardIndex= readln().toInt()-1
        }
        return true
    }

    //Player has to draw a card
    fun drawCard(player: Player, deck: MutableList<Card>):Boolean {
        var aux:Boolean = false
        if (player.hand.any { it == null }){
            //Finding first null value
            val indexNull:Int = player.hand.indexOf(player.hand.find { it == null })
            player.hand.set(indexNull,deck.get(deck.size-1))
            deck.removeLast()

            return true
        }else{
            printHand(player)
            println("Sua mão está cheia!")
            aux = discard(player)
            drawCard(player, deck)

            return aux
        }
    }
    //Printing the player's name after It's hand is shown
    fun printHand(player: Player){
        for (i:Int in 0..player.hand.size-1){
            println("(${i+1})${player.hand.get(i)?.toString()}")
        }
        println("#######################################################################################\n############################# Mão de ${player.name} #############################")
    }

    //Checkign if the player has equippment cards on its hand
    fun hasEquipmentCard(hand: Array<Card?>): Boolean {
        return hand.any { it!=null && it.cardClass == "equipamento"}
    }

    //Checking if the player have a monster card
    fun hasMonsterCard(hand: Array<Card?>): Boolean {
        return hand.any { it!=null && it.cardClass == "monstro"}
    }

    //Checking if the player has unequipped monsters, not working yet
    fun hasUnequippedMonsters(field: Array<Card?>): Boolean {
        return field.any {
            it!=null && !it.equipmentOn
        }
    }
}