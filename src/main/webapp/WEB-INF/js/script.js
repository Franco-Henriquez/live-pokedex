//STEP 1: GRAB ELEMENTS
//STEP 2: HOOK UP EVENT-LISTENERS
//STEP 3: DECLARE FUNCTION & CALL API
//STEP 4: PACKAGE DATA
//STEP 5: LOOK THROUGH DATA
//STEP 6: MANIPULATE WEB-PAGE

var pokeSearch = document.getElementById('search-bar');
var spritePic = document.getElementById('sprite-pic');
var spritePicShiny = document.getElementById('sprite-pic-shiny');
var pokeStats = document.getElementById('stats-list');
var searchContainer = document.getElementById('pokeSearchContainer');
var searchHistoryTable = document.getElementById('search-history');
var standByMsg = document.getElementById('stand-by');
var firstScreenControls = document.getElementById('simpleButtons');
var firstScreenControlsHeight = firstScreenControls.offsetHeight;
var firstScreenControlsHeight = Math.floor(100 * firstScreenControlsHeight / window.innerHeight);
let statsScreenHeight = getComputedStyle(document.documentElement).getPropertyValue('--statsScreenHeight');
var newStatsScreenHeight = (+statsScreenHeight + +firstScreenControlsHeight);
var dexIdField = document.getElementById('dexId');
var pokemonNameField = document.getElementById('pokemonName');

var pokedexNumLimit = 1008;
var log = console.log.bind(log);
var pokemon;
var pokeStats;

function getVal(){
    pokemon = pokeSearch.value.toLowerCase() //to lowercase because the api only accepts lower case pokemon names
    if (pokemon > pokedexNumLimit) {
        pokeStats.innerHTML = `<li class="error-notice"><b>ERROR</b>&nbsp;<div>Pokédex number cannot</div></li>`
        pokeStats.innerHTML += `<li><div>exceed ${pokedexNumLimit}.</div></li>`;
        log(pokemon)
        pokemon = "invalid pokemon";
    } 
}

async function iChooseU(){
    log("Function IchooseYou " + pokemon);
   	standByMsg.style.display = 'none';
    if (pokemon && pokemon != "invalid pokemon") {
        var pokeResults = await fetch(`https://pokeapi.co/api/v2/pokemon/${pokemon}`)
        // log(pokeResults)
        pokeResults = await pokeResults.json()
        var wordCount = 0;
		for (const key in pokeResults){
		  if(pokeResults.hasOwnProperty(key)){
		    //console.log(`${key} : ${pokeResults[key]}`)
		    var words = pokeResults[key].toString().split(' '); //any spaces between them, means they are words, store them
		    wordCount += words.length;
		  }
		}
        log("Word count: "+wordCount);
        receivingData(wordCount);
        //SUBMIT THE AUTOFILLED FORM WHEN DATA RETURNS - use #submit for id

        //submitThisForm();
        

        var pokemonName = capitalize(pokeResults.name);
        // log(pokeResults)
        var pokeTypes = pokeTypesCheck(pokeResults);
        dexIdField.value = pokeResults.id;
	    pokemonNameField.value = pokemonName;

        //log(pokeResults.height)
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
    
    //build the form:
    
            document.getElementById('submit').click();


    document.getElementById('searchHistoryForm').addEventListener("submit",(e)=>e.preventDefault())
    //submitThisForm();
    
    return(pokeResults);
}

function submitThisForm() {
	let form = document.getElementById("searchHistoryForm");
   	
	form.onsubmit = function (e) {
	    e.preventDefault();
		console.log("Event 2: "+e);
	    //form data class
	    fetch(form.action, {
	        method: "post", 
	        body: new FormData(form)
	    }).then(response => {
	        document.getElementById('submit').click();
	        console.log("Event 2: "+e);  
	    });
	}
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


function capitalize(word)
{
    return word[0].toUpperCase() + word.slice(1);
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


//DASHBOARD ONLY (HIDE POKEMON SEARCH STATS)
function loadDashboard() {
	searchContainer.classList.remove("d-flex"); //flex uses !important and it can't be undone, so we'll just remove the class
	searchContainer.style.display = 'none';
	
	firstScreenControls.classList.remove("d-flex");
	firstScreenControls.style.display = 'none';
	
	//log('1st screen: '+firstScreenControlsHeight);
	//log('stats screen: '+statsScreenHeight);
	document.documentElement.style
    .setProperty('--statsScreenHeight', (newStatsScreenHeight)+'vh');
		
	standByMsg.style.display = 'none';
	pokeStats.style.display = 'none';
	
	searchHistoryTable.style.display = '';
	
}

//POKESEARCH ONLY
function pokeSearchView() {
	//resets
	pokeStats.innerHTML = "";
	
	searchContainer.style.display = 'block';
	searchContainer.classList.add("d-flex");
	standByMsg.style.display = 'block';
	pokeStats.style.display = 'block';
	searchHistoryTable.style.display = 'none';
	
	
	
	firstScreenControls.classList.add("d-flex");
	
	
	
	
	
	//log('1st screen: '+firstScreenControlsHeight);
	//log('stats screen: '+statsScreenHeight);
	document.documentElement.style
    .setProperty('--statsScreenHeight', (statsScreenHeight)+'vh');
	
	
}

function receivingData(pings){
	log(getComputedStyle(document.documentElement)
    	.getPropertyValue('--api-data-pings')); // #999999
    log(getComputedStyle(document.documentElement)
    	.getPropertyValue('--api-millisecs'));
    var pingFrequency = Math.floor(Math.random() * (pings*.10)) + 40; //make up a random ping glow pattern number from pings
    log("pingFrequency: "+pingFrequency);
    document.documentElement.style
    .setProperty('--api-millisecs', pingFrequency+'ms');
    document.documentElement.style
    .setProperty('--api-data-pings', pings*.60);
    resetDataPings();
}

function resetDataPings(){
	log("Data Ping Reset Timer Started")
	  var greenButton = document.getElementById('miniButtonGlass3');
	  var redButton = document.getElementById('buttonbottomPicture');
	  greenButton.style.animation = 'none';
	  greenButton.offsetHeight; /* trigger reflow */
	  greenButton.style.animation = null; 
	  redButton.style.animation = 'none';
	  redButton.offsetHeight; /* trigger reflow */
	  redButton.style.animation = null; 
  	//delay function, but no use here
  	//setTimeout(function(){

	 //   log("Data Ping Reset Completed")
	//}, 6000);
}


//add height of 196px to screen1 to compensate for removing #simpleButtons



//TO DO
//do a check for any null data and show an alert that the specified information is not available.
//add a index limit for pokdex number and return an alert or change display to show alert
//replace all alerts with in-screen/in-div text errors
//make one button able to switch between regular and shiny sprite or 3D non-sprite.
