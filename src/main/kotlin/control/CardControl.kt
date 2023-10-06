package control

import model.Card
import model.Player

class CardControl {
    //Changing into attack mode
    fun turnAttack(card: Card){
        card.attackMode = true
    }

    //Alternates between modes, independently of what already is
    fun turn(card: Card){
        card.attackMode = !card.attackMode
    }

    //Changing into defense mode
    fun turnDefense(card: Card){
        card.attackMode = false
    }

    //Equiping a equippment card into a monster
    fun equipInto (player: Player, equip:Card,monster:Card):Boolean{
            monster.name += " usando " + equip.name
            monster.attack += equip.attack
            monster.defense += equip.defense
            monster.equipmentOn = true
            println("Combinação concluída!\nSurge o "+monster.name)
            return true
    }

    //Check if it's a monster can actually just use isEquipment and expect false to validate
    fun isMonster(card: Card?): Boolean {
        if (card!!.cardClass=="monstro") {
            return true
        } else {
            return false
        }

    }

    //Checms if it's an equipmento
    fun isEquipment(card: Card?):Boolean{
        if (card!!.cardClass=="equipamento") {
            return true
        } else {
            return false
        }
    }

    //With this the program should be able to tell if the monster already attacked, preventing it from defending on the same turn
    fun attacked(attacker: Card) {
        attacker.hasAttacked=true
    }

    //Function to reset the cards at the start of the round
    fun resetCard(attacker: Card) {
        attacker.hasAttacked=false
    }
}
