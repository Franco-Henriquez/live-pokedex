/**
 * 
 */
 

var getWidthHeight=function() {
    var height=window.innerHeight;
    var width=window.innerWidth;
    let wh="width: "+width+"<br> height: "+height;
    url="dashboard?rows="+wh;
    fstats=document.getElementById('floating-stats');
    fstats.innerHTML = fstats.innerHTML + wh;
}

var hideAddressBar=function() {
	const documentBody = document.getElementById("junction");
	console.log("height debug: "+documentBody.scrollHeight);
	documentBody.scrollIntoView(true);
	documentBody.scrollBy(0, documentBody.scrollHeight);
	documentBody.scrollTo({
	  top: documentBody.scrollHeight,
	  left: 0,
	  behavior: "smooth",
	});
	//    setTimeout(function(){
    //    // This hides the address bar:
    //    window.scrollTo(0, 1);
    //}, 0);

}

window.addEventListener("scroll", preventMotion, false);
window.addEventListener("touchmove", preventMotion, false);

function preventMotion(event)
{
    window.scrollTo(0, 0);
    event.preventDefault();
    event.stopPropagation();
}

//setTimeout(function(){ // Hide the address bar! window. scrollTo(0, 1); }, 0); });