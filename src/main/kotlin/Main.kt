
import control.FieldControl
import control.PlayerControl
import jdk.swing.interop.SwingInterOpUtils
import model.Card
import model.Field
import tools.CardReader
import view.FieldView

fun main(args: Array<String>) {

    /*
    *leia atentamente o enunciado
    * você pode criar quantas classes precisar
    * recomendo que você separe minimamente sua solução em MVC
    * tente criar uma classe que seja capaz de expressar as regras que serão aplicadas no jogo
    * tente criar um classe que seja capaz de representar o status atual do jogo
     */

    //Initializing the deck and shuffling the cards
    //Don't know why it needs the toMutableList function
    val deck:MutableList<Card> = CardReader.getCards().shuffled().toMutableList()

    for (card in deck ){
        println(card.name+"  ATK "+card.attack+" DEF "+card.defense+ " TYP "+card.cardClass)
    }

    println(deck.size)

    var field: Field = FieldControl().createField("Yugi","Kaiba",deck)

    /*
    var player = field.player1
    var enemy = field.player2
    */


    for ( i in 1..5){
        PlayerControl().drawCard(field.player1,deck)
        PlayerControl().drawCard(field.player2,deck)
    }
    println("PRIMEIRA RODADA, ATAQUES PROIBIDOS")
    //First round, the player is not supposed to attack, so we don't use battlephase yet
    FieldView().roundStart(field)
    FieldView().placePhase(field.player1)
    FieldView().placePhase(field.player1)
    FieldView().changeMode(field)
    FieldView().endPhase(field)
    println("FIM DA PRIMEIRA RODADA, PRÓXIMAS RODADAS ATAQUES PERMITIDOS")

    do {
        FieldView().roundStart(field)
        FieldView().placePhase(field.player1)
        FieldView().changeMode(field)
        while (field.player1.field.any { it != null && !it.hasAttacked && it.attackMode}){
            println("Deseja atacar ?\n1-Sim\n0-Não")
            var aux:Int = readln().toInt()
            if (aux==0){
                break
            }else{
                FieldView().battlePhase(field)
                if (FieldControl().hasZeroLifePoints(field.player2)||FieldControl().hasZeroLifePoints(field.player1)){
                    break
                }
            }
        }

        if (FieldControl().gameEnd(field.player1, field.player2, deck)) break

        FieldView().placePhase(field.player1)
        FieldView().changeMode(field)
        FieldView().endPhase(field)

        println("${deck.size} Cartas restantes\n\n")

    }while (!FieldControl().noMoreCards(deck.size)&&!FieldControl().hasZeroLifePoints(field.player1)&&!FieldControl().hasZeroLifePoints(field.player2))

    if(deck.size>0) FieldControl().gameEnd(field.player1,field.player2,deck)

}

