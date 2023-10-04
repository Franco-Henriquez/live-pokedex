//STEP 1: GRAB ELEMENTS
//STEP 2: HOOK UP EVENT-LISTENERS
//STEP 3: DECLARE FUNCTION & CALL API
//STEP 4: PACKAGE DATA
//STEP 5: LOOK THROUGH DATA
//STEP 6: MANIPULATE WEB-PAGE

var pokeSearch = document.getElementById('search-bar')
var spritePic = document.getElementById('sprite-pic')
var spritePicShiny = document.getElementById('sprite-pic-shiny')
var pokeStats = document.getElementById('stats-list');
var pokedexNumLimit = 1008;
var log = console.log.bind(log)
var pokemon
var pokeStats

function getVal(){
    pokemon = pokeSearch.value.toLowerCase() //to lowercase because the api only accepts lower case pokemon names
    if (pokemon > pokedexNumLimit) {
        pokeStats.innerHTML = `<li class="error-notice"><b>ERROR</b>&nbsp;<div>Pokédex number cannot</div></li>`
        pokeStats.innerHTML += `<li><div>exceed ${pokedexNumLimit}.</div></li>`;
        log(pokemon)
        pokemon = "invalid pokemon";
    } else {
        //do nothing
    }
}

async function iChooseU(){
    log("Function IchooseYou " + pokemon);
    if (pokemon && pokemon != "invalid pokemon") {
        var pokeResults = await fetch(`https://pokeapi.co/api/v2/pokemon/${pokemon}`)
        // log(pokeResults)
        pokeResults = await pokeResults.json()
        var pokemonName = capitalize(pokeResults.name);
        // log(pokeResults)
        var pokeTypes = pokeTypesCheck(pokeResults);
        log(pokeResults.height)
        var pokeHeight = parseInt(pokeResults.height) * 0.1; //convert to kg
        var pokeHeight = Math.round(pokeHeight * 10) / 10; //set to only 1 decimal place
        // var pokeWeight = parseInt(pokeResults.weight) ;
        var pokeWeight = parseInt(pokeResults.weight) * 0.1; //convert to kg
        var pokeWeight = Math.round(pokeWeight * 10) / 10; //set to only 1 decimal place
        // log(pokeStats)
        // listPokeStats(pokeStats,pokeTypes,pokemonName);
        pokeStats.innerHTML = `<li><b>Name:</b>&nbsp;<div>${pokemonName}&nbsp;&#8470;&nbsp;${pokeResults.id}</div></li>`
        // await sleep(3000);
        pokeStats.innerHTML += `<li><b>Type:</b>&nbsp;<div> ${pokeTypes}</div></li>`
        pokeStats.innerHTML += `<li><b>Height:</b>&nbsp;<div> ${pokeHeight}m</div></li>`
        pokeStats.innerHTML += `<li><b>Weight:</b>&nbsp;<div> ${pokeWeight}kg</div></li>`
        // spritePic.src = pokeResults.sprites.front_shiny
        spritePic.src = pokeResults.sprites.front_default;
        spritePicShiny.src = pokeResults.sprites.front_shiny;
    } else if (!pokemon) {
        pokeStats.innerHTML = `<li class="error-notice"><b>ERROR</b>&nbsp;<div>Please type a pokémon name</div></li>`
        pokeStats.innerHTML += `<li><div>or pokédex number first.</div></li>`;
    } else if (pokemon == "invalid pokemon") {
        pokeStats.innerHTML = `<li class="error-notice"><b>ERROR</b>&nbsp;<div>Pokédex number cannot</div></li>`
        pokeStats.innerHTML += `<li><div>exceed ${pokedexNumLimit}.</div></li>`;
    }
    return(pokeResults);
}

//don't know how to get json results without quering the api again so we'll make a display:none function instead
function seeShinySprite(){
    currentDisplay = spritePic.style.display;
    if (currentDisplay == 'block') {
        spritePic.style.display = "none";
        spritePicShiny.style.display = "block";
    } else {
        spritePic.style.display = "block";
        spritePicShiny.style.display = "none";
    }
}


function capitalize(s)
{
    return s[0].toUpperCase() + s.slice(1);
}

function pokeTypesCheck(pokeData){
    var pokeTypes = ""
    for (var i = 0; i<pokeData.types.length; i++) {
        var pokeTypes = pokeTypes + capitalize(pokeData.types[i].type.name) + " / ";
    }
    pokeTypes = pokeTypes.substring(0, pokeTypes.length-2); //remove the extra fowardslash and space at the end of iteration
    return(pokeTypes)
}

async function listPokeStats(pokeStats,pokeTypes,pokemonName){
   

}

async function sleep(ms) {
    return new Promise(val => setTimeout(val, ms));
}


//TO DO
//do a check for any null data and show an alert that the specified information is not available.
//add a index limit for pokdex number and return an alert or change display to show alert
//replace all alerts with in-screen/in-div text errors
//make one button able to switch between regular and shiny sprite or 3D non-sprite.
