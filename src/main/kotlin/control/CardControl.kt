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
        if (!isEquipment(equip)||isEquipment(monster)){
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
    /*
    //Check if it's a monster can actually just use isEquipment and expect false to validate
    private fun isMonster(card: Card): Boolean {
        if (card != null) {
            if (card.cardClass=="monster") {
                return true
            } else {
                return false
            }
        }else{
            return false
        }
    }
     */

    //Checms if it's an equipmento
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