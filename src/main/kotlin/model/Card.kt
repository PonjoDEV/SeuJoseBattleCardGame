package model
//Considering attackMode True when attacking and False when defending
class Card (var name:String, val description:String, var attack:Int, var defense:Int, val cardClass:String, var attackMode:Boolean=false, var equipmentOn:Boolean=false, var hasAttacked:Boolean = false) {

    fun toCarta(name: String, description: String, attack: String, defense: String, cardClass: String, attackMode: String):Card{
        var mode:Boolean = attackMode.toBoolean()
        var card = Card(name,description,attack.toInt(),defense.toInt(),cardClass,mode)
        return card
    }

    override fun toString(): String {
        return "'$name', $attack, $defense"
    }

}