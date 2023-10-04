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