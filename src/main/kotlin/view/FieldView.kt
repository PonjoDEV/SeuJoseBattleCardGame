package view

import control.FieldControl
import control.PlayerControl
import model.Field

class FieldView {
    //Every beginning of round it prints the field, and draw a card for whoever player the turn is
    fun roundStart(field: Field) {
        FieldControl().printWholeField(field)
        PlayerControl().drawCard(field.player1,field.deck)
        PlayerControl().printHand(field.player1)
    }

    //Checks if the battlefield is full or not before calling the summon methods
    fun placePhase(field: Field) {
        if (FieldControl().fieldIsFull(field)) {
            if (PlayerControl().hasEquipmentCard(field.player1.hand)&&!FieldControl().fieldIsEmpty(field)){
                FieldControl().placeEquipment(field.player1)
            }
        }else{
            if (PlayerControl().hasMonsterCard(field.player1.hand)) {
                FieldControl().placeMonster(field.player1)
            }
            if (PlayerControl().hasEquipmentCard(field.player1.hand)){
                FieldControl().placeEquipment(field.player1)
            }
        }
    }

}