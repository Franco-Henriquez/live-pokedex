/**
 * 
 */
 
//var deviceScreenHeight = window.innerWidth;
var deviceScreenHeight = window.innerHeight;
 
var setHeight=function() {
    //document.getElementById('pokedex').style.height = deviceScreenHeight+"px";
    document.getElementById('pokedex').style.backgroundColor = "#c00d0d";
    document.documentElement.style.setProperty('--deviceScreenHeight', deviceScreenHeight+'px');


}