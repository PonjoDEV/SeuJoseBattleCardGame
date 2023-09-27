package control

import model.Card

class CardControl {
    //Changing int o attack mode
    fun turnAttack(card: Card){
        card.attackMode = true
    }

    //Changing int o defense mode
    fun turnDefense(card: Card){
        card.attackMode = false
    }

    fun equipInto (equip:Card,monster:Card):Boolean{
        if (equip.cardClass!="equipamento"||monster.cardClass!="monstro"){
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

}