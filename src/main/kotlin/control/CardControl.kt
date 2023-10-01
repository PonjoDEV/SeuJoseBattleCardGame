package control

import model.Card
import model.Player

class CardControl {
    //Changing into attack mode
    fun turnAttack(card: Card){
        card.attackMode = true
    }

    //Changing into defense mode
    fun turnDefense(card: Card){
        card.attackMode = false
    }

    //Equiping a equippment card into a monster
    fun equipInto (player: Player, equip:Card,monster:Card):Boolean{
        if (!isEquipment(equip)||!isMonster(monster)){
            println("Combinação inválida!")
            return false
        }else{
            if (!monster.equipmentOn) {
                monster.name += " usando " + equip.name
                monster.attack += equip.attack
                monster.defense += equip.defense
                monster.equipmentOn = true
                println("Combinação concluída!\n Surge o "+monster.name)
                return true
            }else{
                println("Montro já possui equipamento")
                return false
            }
        }
    }

    //Check if its a monster
    private fun isMonster(card: Card): Boolean {
        if (card.cardClass=="monster") return true else return false
    }

    //Checms if its an equipmento
    fun isEquipment(card: Card?):Boolean{
        if (card != null) {
            if (card.cardClass=="equipamento") {
                return true
            } else {
                return false
            }
        }else{
            return false
        }
    }
}