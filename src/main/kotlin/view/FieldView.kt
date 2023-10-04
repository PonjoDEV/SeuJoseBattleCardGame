package view

import control.FieldControl
import control.PlayerControl
import model.Field
import model.Player

class FieldView {
    //Every beginning of round it prints the field, and draw a card for whoever player the turn is
    fun roundStart(field: Field) {
        FieldControl().printWholeField(field)
        PlayerControl().drawCard(field.player1,field.deck)
    }

    //Checks if the battlefield is full or not before calling the summon methods
    fun placePhase(player: Player){
        if (PlayerControl().hasMonsterCard(player.hand) && !FieldControl().fieldIsFull(player)){
            PlayerControl().printHand(player)
            FieldControl().placeMonster(player)
            FieldControl().printPlayerField(player)
        }else{
            println("Campo cheio, pulando fase de invocação de monstro")
        }
        if(PlayerControl().hasUnequippedMonster(player)&&PlayerControl().hasEquipmentCard(player.hand)){
            PlayerControl().printHand(player)
            FieldControl().placeEquipment(player)
        }else{
            if (PlayerControl().hasUnequippedMonster(player)) {
                println("Não existem monstros que possam receber equipamentos, pulando fase de equipamentos")
            }else{
                println("Sem equipamentos em sua mão, pulando fase de equipamentos")
            }
        }
    }
    
    //Last phase of each round, after the player has attacked and summoned another creature/equipment if they want to do so
    fun endPhase(field: Field) {
        FieldControl().invertField(field)
    }


    //Lets players attack other monsters or the enemy player directly
    fun battlePhase(field: Field) {
        FieldControl().printWholeField(field)
    }

    //Lets players change the card state from attack to defense and vice-versa
    fun changeMode(field: Field) {

    }

}