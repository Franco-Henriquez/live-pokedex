<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <meta name="mobile-web-app-capable" content="yes"> -->
	  <meta name="viewport" content="user-scalable=no, shrink-to-fit=yes" />
<!-- 	<meta name="viewport" content="height=device-height, width=device-width, initial-scale=3.74, shrink-to-fit=no">
 -->	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">	<link href="css/style.css" rel="stylesheet">
	<script src="js/script.js" defer></script>
	<script src="js/screen_debug.js" defer></script>	
	
	<title>Live Pokédex</title>
</head>
<body onload="getWidthHeight(),getVal(),loadPokeSearch()" class="flex-container">
<!-- 	<div class="d-flex p-1 justify-content-around"> -->	
	<div id="pokedex">
        <div id="floating-stats" >STATS:<br></div>
		<!-- LEFT SCREEN PANEL -->
        <div id="left">
        	<div id="topNav">
	            <!-- <div id="logo"></div> -->
	            <div id="bg_curve1_left"></div>
	            <div id="bg_curve2_left">
	                <div id="curve2_left">
		            </div>
	            </div>
	            <div id="curve1_left">
	                <div id="buttonGlass">
	                    <div id="reflect"> </div>
	                </div>
	                <div id="miniButtonGlass1"></div>
	                <div id="miniButtonGlass2"></div>
	                <div id="miniButtonGlass3"></div>

	            </div>

                <div id="junction">
                   <div id="junction1"></div>
                   <div id="junction2"></div>
   	    		</div>
            </div> <!-- end top nav --> 
			<div id="screen-one" class="d-flex flex-column align-items-center ">
				 <div id="screen" class=""> <!-- SCREEN 1 -->
				     <div id="topPicture">
				         <div id="buttontopPicture1"></div>
				         <div id="buttontopPicture2"></div>
				     </div>
				     <div id="picture">
				         <img id="sprite-pic" src="./img/pokeball.png" alt="Regular Front Sprite Not Available"
				             height="170" />
				         <img id="sprite-pic-shiny" src="./img/pokeball.png" alt="Shiny Preview Not Available"
				             height="170" />
				     </div>
				     <div id="buttonbottomPicture"></div>
				     <div id="speakers">
				         <div class="sp"></div>
				         <div class="sp"></div>
				         <div class="sp"></div>
				         <div class="sp"></div>
				     </div>
				 </div> <!-- END SCREEN 1 -->
				 <div id="simpleButtons" class="p-2 d-flex justify-content-around"> <!-- Buttons 1 -->
					  <div onclick="iChooseU()" id="bigbluebutton"></div>
					  <div id="barbutton1"></div>
					  <div onclick="seeShinySprite()" id="barbutton2"></div>
					  <div id="cross">
					      <div id="leftcross">
					          <div id="leftT"></div>
					      </div>
					      <div id="topcross">
					          <div id="upT"></div>
					      </div>
					      <div id="rightcross">
					          <div id="rightT"></div>
					      </div>
					      <div id="midcross">
					          <div id="midCircle"></div>
					      </div>
					      <div id="botcross">
					          <div id="downT"></div>
					      </div>
					  </div>
				 </div> <!-- END BUTTONS 1 -->
			</div>
		            <!-- DETAIL SCREEN -->
		        <div id="detailScreen" class="d-flex flex-column align-items-center "> <!-- class name 'row' might need to be added back -->
		            <div id="stats">
			            <div id="pokeSearchContainer">
		                    <label>Poké Search:</label>
		                    <input id="search-bar" type="text" placeholder="Pokémon or Pokédex ID" onchange="getVal()">
	                	</div>
	                	<ul id="stats-list" class="typewriter"></ul>
						<table class="table table-sm ">
							<thead>
								<tr>
									<th scope="col">ID</th> <!-- pull from SearchHistory -->
									<th scope="col">Pokémon</th> <!-- pull from SearchHistory Card relationship -->
									<th scope="col">Trainer</th> <!-- pull from SearchHistory Trainer relationship -->
									<th scope="col">Type</th> <!-- pull from SearchHistory -->
								</tr>
							</thead>
							<tbody>
							<!-- forEach needs to be placed here to go through each book post and list them  -->
			 				<c:forEach var="thisEntry" items="${allHistory}">
									<tr>
										<td>
											<c:if test="${thisEntry.searchType} == 'CARD'">
											<a href="/search_card/${thisEntry.id}"><c:out value="${thisEntry.searchString}"/></a> 
											</c:if>
											<c:if test="${thisEntry.searchType} == 'DEX'">
											<a href="/search_pokeid/${thisEntry.id}"><c:out value="${thisEntry.searchString}"/></a> 
											</c:if>
										</td>
										<td><c:out value="${thisEntry.card.cardName}"/></td>
										<td><c:out value="${thisEntry.trainer.trainerName}"/></td>
										<td><c:out value="${thisEntry.searchType}"/></td>														
										</tr>
							</c:forEach> 
							</tbody>
						</table>
		            </div>
		            <div id="blueButtons1" class="d-flex align-items-center justify-content-around">
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		            </div>
		            <div id="blueButtons2" class="d-flex align-items-center justify-content-around">
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		            </div>
		            <div id="miniButtonGroup" class="d-flex align-items-center justify-content-between">
		            	<div id="miniButtons" class="d-flex align-items-center justify-content-around">
				            <div id="miniButtonGlass4"></div>
				            <div id="miniButtonGlass5"></div>
			            </div>
			            <div id="barButtons" class="d-flex align-items-center justify-content-between">
				            <div id="barbutton3"></div>
				            <div id="barbutton4"></div>
			            </div>
		            </div>
		            <div id="yellowBoxes" class="d-flex align-items-center justify-content-between " >
			            <div id="yellowBox1" class="d-flex align-items-center"><button class="w-100 btn">CARD SEARCH</button></div>
			            <div id="yellowBox2" class="d-flex align-items-center"><button class="w-100 btn">POKÉ SEARCH</button></div>
		            </div>
		        </div><!-- END DETAIL SCREEN -->
	        </div> <!-- end container for screens (id=left)-->
        </div> <!-- END LEFT SCREEN PANEL  --> <!-- #pokedex -->

</body>
</html>