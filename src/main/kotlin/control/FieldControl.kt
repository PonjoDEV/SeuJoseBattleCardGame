package control
import model.Card
import model.Field
import model.Player

//var remainingCards = 254;
class FieldControl () {
    //Initializing Battle Field and players
    fun createField(name1:String, name2:String, deck: MutableList<Card>): Field {
        val field:Field = Field(player1 = Player(name1), player2 = Player(name2), deck = deck)
        println("O jogo vai começar! \nJogadores:\n${field.player1.name} X ${field.player2.name}\n É HORA DO DUELO!\n\n")
        return field
    }

    //Checks if the game finished
    fun victory(player: Player,enemy: Player): Boolean {
        if (player.lifePoints<=0){
            println("${enemy.name} VENCEU!!!")
            return true
        }else if(enemy.lifePoints<=0){
            println("${player.name} VENCEU!!!")
            return true
        }else{
            println("EMPATE")
            return false
        }
    }

    //Inverting position of the field ** PLAYER 2 IS ALWAYS THE ENEMY IN THIS CASE
    fun invertField(field:Field){
        val tempPlayer = field.player1
        field.player1 = field.player2
        field.player2 = tempPlayer
    }

    //Checking what the  selected monster is attacking and the outcome
    fun attackAction(player:Player, attacker:Card, enemy:Player, defender:Card?=null):Boolean{
        var damage:Int=0
        println("${attacker.name} ataca seu alvo!")
        if (fieldIsEmpty(enemy)&&defender==null){
            enemy.lifePoints-=attacker.attack
            println("${enemy.name} está agora com ${enemy.lifePoints} pontos de vida")
            return true
        }else if (defender!!.attackMode){
            if (attacker.attack>defender.attack) {
                damage =  (attacker.attack - defender.attack)
                damageToLP(enemy,damage)
                destroyMonster(enemy, defender)
                println("Você destruiu ${defender.name} e inflingiu $damage de dano aos pontos de vida inimigos")
                CardControl().attacked(attacker)
                return true
            }else if (attacker.attack==defender.attack){
                println("Ambos os monstros foram destruídos")
                destroyMonster(player,attacker,enemy,defender)
                CardControl().attacked(attacker)
                return true
            }else{
                println("Seu monstro é mais fraco que o do inimigo")
                destroyMonster(player,attacker)
                damage=defender.attack-attacker.attack
                damageToLP(player,damage)
                CardControl().attacked(attacker)
                return false
            }
        }else{
            if (defender.defense>=attacker.attack){
                println("Seu monstro não penetrou a defesa do inimigo")
                damage=defender.defense-attacker.attack
                damageToLP(player,damage)
                CardControl().attacked(attacker)
                return true
            }else{
                destroyMonster(enemy, defender)
                println("Você destruiu o monstro inimigo")
                CardControl().attacked(attacker)
                return true
            }
        }
    }

    //Removes targets LifePoints by "damage" amount
    fun damageToLP(player:Player, damage: Int) {
        player.lifePoints-=damage
    }

    //Destroy the target monster, and also the attacking monster if its parameter is used
    fun destroyMonster(enemy: Player, defender:Card, player: Player?=null, attacker:Card?=null){
        enemy.field[enemy.field.indexOf(defender)] = null
        if (attacker!=null){
            player!!.field[player!!.field.indexOf(attacker)] = null
        }
    }

    //Checks if there are no more cards, its parameter is the deck's size
    fun noMoreCards(remainingCards: Int): Boolean {
        if (remainingCards == 0) return true else return false
    }

    //checks if the player has reached 0 LP
    fun zeroLifePoints(player: Player): Boolean {
        if (player.lifePoints <= 0) return true else return false
    }

    //Prints a single player's field
    fun printPlayerField(field: Array<Card?>) {
        for (i in field.indices){
            print("(${i+1})")
            if (field[i] == null){
                println("#######################################################################################")
            }else{
                val atkMode:String = if (field[i]!!.attackMode) "ATK" else "DEF"
                println(atkMode+" "+ field[i].toString())
            }
        }
    }

    //Prints the Whole field's content
    fun printWholeField(field: Field) {
        printPlayerField(field.player2.field)
        println("\n##############################################################################################################################################################################\n")
        printPlayerField(field.player1.field)
        println("\n##############################################################################################################################################################################\n\n")
    }

    //Checks if the player's field is full
    fun fieldIsFull(player: Player): Boolean {
        if (player.field.all { it !=null}) return true else return false    }

    //Checks if the player's field is full
    fun fieldIsEmpty(player: Player): Boolean {
        if (player.field.all { it ==null}) return true else return false
    }

    //Summoning a new monster and excluding it from the players hand TODO PLAYER SHOULD BE ABLE TO PUT MORE THAN ONE MONSTER
    fun placeMonster(player: Player, entry:Int?=null): Boolean {
        var aux =0
        if (entry==null) aux = readln().toInt() else aux = entry
        if (aux!=0) {
            aux--
            while (aux !in 0..<player.hand.size || player.hand[aux] ==null || CardControl().isEquipment(player.hand[aux])) {
                println("Digite um número válido\nDigite 0 para pular essa etapa\n")
                aux = readln().toInt()
                if (aux==0) {
                    return false
                }
                aux--
            }
            val indexNull: Int = player.field.indexOf(player.field.find { it == null })
            player.field.set(indexNull, player.hand.get(aux))
            player.hand.set(aux, null)
            println("Deseja que o monstro seja invocado em modo de ataque ou defesa?\n1-Ataque\n0-Defesa")
            aux = readln().toInt()
            if (aux == 1){
                println(player.field.get(indexNull)!!.name+" Invocado em modo de ataque!")
                CardControl().turnAttack(player.field.get(indexNull)!!)
            }else{
                if (aux !=0){
                    println("Escolha inválida, invocando em modo de defesa")
                }
                print(" "+player.field.get(indexNull)!!.name+" Invocado em modo de defesa!\n")
            }
            return true
        }else{
            return false
        }
    }

    //Equipping a monster and deleting the equipment card from the players hand
    fun placeEquipment(player: Player): Boolean {
        println("Digite o número do equipamento que deseja usar\nDigite 0 para pular essa etapa\n")
        var equip:Int = readln().toInt()
        if (equip!=0) {
            equip--
            while (equip !in 0..player.hand.size - 1 || player.hand.get(equip)==null || !CardControl().isEquipment(player.hand.get(equip))) {
                println("Digite um número válido")
                equip = readln().toInt()
                if (equip==0) {
                    return false
                }
                equip--
            }
            println("Digite o número do monstro que deseja equipar\n")
            var monster: Int = readln().toInt()
            monster--
            while (monster !in 0..player.field.size - 1 || player.field.get(monster)==null || !CardControl().isMonster(player.field.get(monster))) {
                println("Digite um número válido")
                monster = readln().toInt()
                if (monster==0) {
                    return false
                }
                monster--
            }
            var aux: Boolean = CardControl().equipInto(player, player.hand.get(equip)!!, player.field.get(monster)!!)
            player.hand[equip] = null
            return true
        }else{
            return false
        }
    }

    //This function should reset all the player's card at the start of the round
    fun resetField(field: Array<Card?>) {
        for (card in field){
            if(card!=null){
                CardControl().resetCard(card)
            }
        }
    }
}