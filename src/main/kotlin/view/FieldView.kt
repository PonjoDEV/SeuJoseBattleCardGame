package view

import control.FieldControl
import control.PlayerControl
import model.Field

class FieldView {
    fun roundStart(field: Field) {
        FieldControl().printWholeField(field)
        PlayerControl().drawCard(field.player1,field.deck)
        PlayerControl().printHand(field.player1)
    }

}