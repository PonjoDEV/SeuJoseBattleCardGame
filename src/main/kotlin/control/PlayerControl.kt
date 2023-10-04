package control

import model.Card
import model.Player

class PlayerControl (){
    //Discarding a card from either hand or the field
    private fun discard(array: Array<Card?>): Boolean {
        var cardIndex = array.size
        println("Selecione uma carta entre 1 e $cardIndex para descartar: ")
        cardIndex = readln().toInt()-1

        while (cardIndex !in 0..<array.size ||array.get(cardIndex)==null) {
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
            aux = discard(player.hand)
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
        println("Pontos de vida atuais: ${player.lifePoints} / 10000")
    }

    //Checking if the player has equippment cards on its hand
    fun hasEquipmentCard(hand: Array<Card?>): Boolean {
        return hand.any { it!=null && it.cardClass == "equipamento"}
    }

    //Checking if the player have a monster card
    fun hasMonsterCard(hand: Array<Card?>): Boolean {
        return hand.any { it!=null && it.cardClass == "monstro"}
    }

    //Checking if the player has unequipped monsters, not working yet
    //Checks if there are any unequiped monsters in the player's field
    fun hasUnequippedMonster(player: Player):Boolean{
        for (card in player.field) if (card!=null){
            if (!card.equipmentOn) return true
        }
        return false
    }

    fun changeMode(field: Array<Card?>) {
        do {
            println("Selecione uma carta entre 1 e ${field.size} para alterar sua posição:\nDigite 0 para finalizar as alterações")
            var aux = readln().toInt()-1
            if (aux!=-1) {
                if (aux !in 0..<field.size || field.get(aux) == null) {
                    println("Digite uma posição válida")
                    aux = readln().toInt() - 1
                } else {
                    CardControl().turn(field.get(aux)!!)
                    FieldControl().printPlayerField(field)
                }
            }
        }while (aux!=-1)
    }
}