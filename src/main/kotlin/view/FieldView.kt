package view

import control.FieldControl
import control.PlayerControl
import model.Field
import model.Player

class FieldView {
    //Every beginning of round it prints the field, and draw a card for whoever player the turn is
    fun roundStart(field: Field) {
        FieldControl().resetField(field.player1.field)
        FieldControl().printWholeField(field)
        PlayerControl().drawCard(field.player1,field.deck)
    }

    //Checks if the battlefield is full or not before calling the summon methods
    fun placePhase(player: Player){
        if (PlayerControl().hasMonsterCard(player.hand) && !FieldControl().fieldIsFull(player)){
            PlayerControl().printHand(player)
            FieldControl().placeMonster(player)
            FieldControl().printPlayerField(player.field)
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
        if (FieldControl().fieldIsEmpty(field.player1)){
            println("Seu campo está vazio, impossível atacar")
        }else {
            FieldControl().printWholeField(field)
            var attacker: Int = 10
            var defender: Int = 0
            while (field.player1.field.any { it!=null && !it.hasAttacked } && attacker!=0) {
                println("Escolha um de seus monstros para atacar\nDigite 0 para encerrar o turno de ataque")
                attacker = readln().toInt()-1
                if (attacker !in 0..field.player1.field.size-1){
                    if(attacker!=0) println("Digite um número válido")
                }else{
                    if (!FieldControl().fieldIsEmpty(field.player2)) {
                        println("Escolha um alvo ")
                        defender = readln().toInt()-1
                        FieldControl().attackAction(field.player1, field.player1.field.get(attacker)!!,field.player2,field.player2.field.get(defender))
                    }else{
                        FieldControl().attackAction(field.player1, field.player1.field.get(attacker)!!,field.player2)
                    }
                }
            }
        }
    }

    //Lets players change the card state from attack to defense and vice-versa
    fun changeMode(field: Field) {
        PlayerControl().changeMode(field.player1.field)
        FieldControl().printWholeField(field)
    }
}