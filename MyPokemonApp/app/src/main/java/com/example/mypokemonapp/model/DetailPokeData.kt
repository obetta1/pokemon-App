package com.example.mypokemonapp.model

import android.os.Parcelable
import android.provider.MediaStore
import com.google.gson.annotations.JsonAdapter
import kotlinx.android.parcel.Parcelize
import java.sql.Types

data class Pokemon(val results: List<PokemonCharacters>)

@Parcelize
data class PokemonCharacters(val name: String, val url: String) : Parcelable {
    var imageUrl: String = ""
    var index: String = ""}

data class DetailPokeData(

    val abilities: List<Abilities>,
    val baseExperience: Long,
    val gameIndices: List<GameIndex>,
    val height: Long,
    val id: Long,
    val moves: List<MoveStyle>,
    val species: General,
    val sprites: Images,
    val stats: List<Stats>,
    val types: List<Types>,
    val weight: Long
)





data class VersionGroup(
     val levelLearnedAt: Long,
    val moveLearnMethod: General,
  val versionGroup: General
)


// A model of the name property of any of the pokemon.
// This is used whenever only the name property needs to taken from the json data format
// hence it's called General because, it models no specific class/object
data class General(val name: String)

// Model's the abilities object of each pokemon character
data class Abilities(val ability: General, val isHidden: Boolean, val slot: Long)

// Model of the game index of each pokemon character
data class GameIndex( val gameIndex: Long, val version: General)

// Model's the move styles of each pokemon character
data class MoveStyle(val move: General,   val versionGroupDetails: List<VersionGroup>)

// Model of the sprites properties of each pokemon. This is the source of the little images used
// in populating the UI of the details page of each pokemon character.
data class Images(
    val backDefault: String,
   val backShiny: String,
     val frontDefault: String,
     val frontShiny: String
)

// Model's the stats of each pokemon character
data class Stats( val baseStat: Long, val effort: Long, val stat: General)

// Model's the types of each pokemon character
data class Types(val slot: Long, val type: General)

