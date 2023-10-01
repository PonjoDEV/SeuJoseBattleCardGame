package control
import model.Card
import model.Field
import model.Player

//var remainingCards = 254;
class FieldControl () {
    //Initializing Battle Field and players
    fun createField(name1:String, name2:String, deck: MutableList<Card>): Field {
        var field:Field = Field(player1 = Player(name1), player2 = Player(name2), deck = deck)
        println("O jogo vai começar! \nJogadores:\n${field.player1.name} X ${field.player2.name}\n Comecem!\n\n")
        return field
    }

    fun victory(player: Player,enemy: Player){
        //TODO HOW'S THE GAME SUPPOSED TO FINISH ?
    }

    //Inverting position of the field ** PLAYER 2 IS ALWAYS THE ENEMY IN THIS CASE
    fun invertField(field:Field){
        val tempPlayer = field.player1
        field.player1 = field.player2
        field.player2 = tempPlayer
    }

    //Checking what the  selected monster is attacking and the outcome
    fun attackAction(player:Player, attacker:Card, enemy:Player, defender:Card?=null):Boolean{
        if (enemyFielisdEmpty(enemy)){
            enemy.lifePoints-=attacker.attack
            return true
        }else if (defender!!.attackMode){
            if (attacker.attack>defender.attack) {
                val damage:Int =  (attacker.attack - defender.attack)
                damageToLP(enemy,damage)
                destroyMonster(defender)
                println("Você destruiu ${defender.name} e inflingiu $damage de dano aos pontos de vida inimigos")
                return true
            }else if (attacker.attack==defender.attack){
                println("Ambos os monstros foram destruídos")
                destroyMonster(attacker,defender)
                return true
            }else{
                println("Seu monstro é mais fraco que o do inimigo")
                return false
            }
        }else{
            if (defender.defense>=attacker.attack){
                println("Seu monstro não penetrou a defesa do inimigo")
                return true
            }else{
                destroyMonster(defender)
                println("Você destruiu o monstro inimigo")
                return true
            }
        }
    }

    fun damageToLP(enemy:Player,damage: Int) {
        enemy.lifePoints-=damage
    }



    fun enemyFielisdEmpty(enemyPlayer:Player):Boolean{
        //TODO check if the enemy player's field is empty

        return false
    }

    fun destroyMonster(monster:Card,enemyMonster:Card?=null){
        //TODO if the monster hit has lower stat than its attacker, destroy it, if its both the same,
        // destroy both monsters the moster location in the players field will become null

    }

    //Checks if there are no more cards, its parameter is the deck's size
    fun noMoreCards(remainingCards: Int): Boolean {
        if (remainingCards == 0) {
            return true
        } else {
            return false
        }
    }

    //checks if the player has reached 0 LP
    fun zeroLifePoints(player: Player): Boolean {
        if (player.lifePoints <= 0) {
            return true
        } else {
            return false
        }
    }

    //Prints a single player's field
    fun printPlayerField(player: Player) {

        for (i in 0..player.field.size-1){
            print("(${i+1})")
            if (player.field.get(i) == null){
                println("#######################################################################################")
            }else{
                println(player.field.get(i).toString())
            }
        }
    }

    //Prints the Whole field's content
    fun printWholeField(field: Field) {
        FieldControl().printPlayerField(field.player2)
        println("\n##############################################################################################################################################################################\n")
        FieldControl().printPlayerField(field.player1)
    }


}


