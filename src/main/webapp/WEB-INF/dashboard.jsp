<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- 	<meta name="viewport" content="height=device-height, width=device-width, initial-scale=3.74, shrink-to-fit=no"> -->
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
 	<link href="/css/style.css" rel="stylesheet">
 	<link href="/css/loading.css" rel="stylesheet">
 	<link href="/css/glow_animations.css" rel="stylesheet">
 	<script src="/js/script.js" defer></script>
	<script src="/js/loading.js" defer></script>
	<script src="/js/screen_debug.js" defer></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
	
	<title>Live Pokédex</title>
</head>
<body onload="loadDashboard(<c:out value="${loadId}"/>),getWidthHeight(),getVal()" class="flex-container"> <!-- loadDashboard(), -->
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
				 <div id="header-spacer" class="d-flex">
				 	<div id="screen-tab" class="ms-auto m-0 p-2">
		                    <h1 class="">Search Pokémon</h1>
				 	</div> <!-- Half the space or 42.5% -->
				 </div>
				 <div id="screen" class=""> <!-- SCREEN 1 -->
				     <div id="topPicture">
				         <div id="buttontopPicture1"></div>
				         <div id="buttontopPicture2"></div>
				     </div>
				     <div id="picture" class="d-flex flex-column align-items-stretch">
				         <img id="sprite-pic" src="/images/pokeball.256x256.png" alt="Regular Front Sprite Not Available"
				             height="100%" />
				         <img id="sprite-pic-shiny" src="/images/pokeball.256x256.png" alt="Shiny Preview Not Available"
				             height="100%" />
				     </div>
				     <div id="pokeSearchContainer" class="d-flex justify-content-around">
					     <div id="buttonbottomPicture" class="align-self-center"></div>
					     <input id="search-bar" class="align-self-start" type="text" placeholder="Pokémon Name or ID" onchange="getVal()">			
					     <div id="speakers" class="align-self-center">
					         <div class="sp"></div>
					         <div class="sp"></div>
					         <div class="sp"></div>
					         <div class="sp"></div>
					     </div>
				     </div>
				 </div> <!-- END SCREEN 1 -->
				 <div id="simpleButtons" class="p-2 d-flex justify-content-around"> <!-- Buttons 1 -->
					  <div class="bg-transparent d-flex flex-column justify-content-between">
						  	<div class="align-self-center" onclick="iChooseU()" id="bigbluebutton"></div>
						  	<p class="emboss align-self-center">START SEARCH</p>
					  </div>
					  <div class="bg-transparent d-flex flex-column justify-content-between">
						  	<div class="align-self-center" id="barbutton1">
<%-- 						  	<!-- FORM - ADD TO POKEMON HISTORY -->
		 						<form:form id="searchHistoryForm" modelAttribute="newPokemon" method="post" action="/pokemon/new" onsubmit="(e)=>e.preventDefault(); console.log('Event 2: '+e);">
								<div>
							<form:errors class="row btn-warning p-1 mt-2" path="pokemonName"/>
								<form:errors class="row btn-warning p-1 mt-2" path="dexId"/>
								</div>
								<form:hidden id="pokemonName" path="pokemonName" value="MissingNo" />
								<form:hidden id="dexId" path="dexId" value="000" />
							<form:hidden id="searchString" path="searchString" value="MissingNo" />
						
								<input id="submit" type="submit" value=" " />
								</form:form>
 --%>							</div>
						  	<p class="emboss align-self-center">SAVE</p>
					  </div>
					  <div class="bg-transparent d-flex flex-column justify-content-between">
						  	<div class="align-self-center" onclick="seeShinySprite()" id="barbutton2"></div>
						  	<p class="emboss align-self-center">SHINY</p>
					  </div>
					  <div class="bg-transparent d-flex flex-column justify-content-between">
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
							  <p class="emboss align-self-center">NAV DEX</p>
						</div>
				 </div> <!-- END BUTTONS 1 -->
<!-- 				 <div id="simpleButtonLabels" class="p-2 d-flex justify-content-between align-items-start">BUTTON 1 LABELS
				 		<p class="emboss ">START SEARCH</p>
				 		<p class="emboss ">???</p>
					  	<p class="emboss ">SHINY</p>
					  	<p class="emboss ">INDEX NAV</p>
					  	
				 		</div> -->
			</div>
		            <!-- DETAIL SCREEN -->
		        <div id="detailScreen" class="d-flex flex-column align-items-center "> <!-- class name 'row' might need to be added back -->
		            <div id="stats">
		            	<div id="stand-by" class="console-waiting">Awaiting Search Instruction
		            		<div class="d-inline-flex dot-elastic"></div>
		            	</div>	
	                	<ul id="stats-list" class="typewriter"></ul>
						<table id="search-history" class="table table-sm">
							<thead>
								<tr>
									<th scope="col">ID</th> <!-- pull from SearchHistory -->
									<th scope="col">Pokémon</th> <!-- pull from SearchHistory Card relationship -->
									<th scope="col">Trainer</th> <!-- pull from SearchHistory Trainer relationship -->
									<th scope="col">Type</th> <!-- pull from SearchHistory -->
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
							<!-- forEach needs to be placed here to go through each book post and list them  -->			 				
			 				<c:forEach var="thisEntry" items="${allHistory}">
									<tr>
									 	<c:if test="${thisEntry.searchType == 'CARD'}">

											<td class="typewriter"><c:out value="${thisEntry.card.cardName}"/></td>
											<td class="typewriter"><c:out value="${thisEntry.trainer.trainerName}"/></td>
											<td class="typewriter"><c:out value="${thisEntry.searchType}"/></td>
										</c:if>
 										<c:if test="${thisEntry.searchType == 'DEX'}">
 											<td>
											<a class="typewriter" href="/search_pokeid/${thisEntry.pokemon.dexId}"><c:out value="${thisEntry.pokemon.dexId}"/></a>
											</td>
											<td class="typewriter"><c:out value="${thisEntry.pokemon.pokemonName}"/></td>
											<td class="typewriter"><c:out value="${thisEntry.trainer.trainerName}"/></td>
											<td class="typewriter"><c:out value="${thisEntry.searchType}"/></td>
											<td>
												<form class="d-inline" action="/history/delete" method="post">
													<input type="hidden" name="_method" value="delete"/>
													<input type="hidden" name="history_id" value="${thisEntry.id}"/>
													<input type="submit" class="btn btn-danger" value="X"/>
													
													
												</form>
											</td>
										</c:if>												
									</tr>
							</c:forEach> 
							</tbody>
						</table>
		            </div>
		            <div id="blueButtons1" class="d-flex align-items-center justify-content-around">
		                <div class="blueButton d-flex flex-column align-items-center">
		                	<a id="logout" class="align-self-center"  href="/card/">
		               			<img onclick="cardSearchView()" id="search-card-icon" src="/images/pokemon_card_small.png" alt="CARD SEARCH" height="100%" />
		               		</a>
				        </div>
   		                <div class="blueButton d-flex flex-column align-items-center">
		               		<img onclick="pokeSearchView()" id="search-pokemon-icon" src="/images/pokemon_search_pixel_ditto.png" alt="POKEMON SEARCH" height="100%" />
				        </div>
   		                <div class="blueButton d-flex flex-column align-items-center">
   		                	<a id="dashboard-icon" class="align-self-center"  href="/dashboard">
		               			
		               			<img class="align-self-center" src="/images/pokedex_dash.png" alt="DASHBOARD" height="50%" />
		               			
		               		</a>
		               		
		               		<!-- onclick="loadDashboard(1)" -->
				        </div>
		                <div class="blueButton"></div>
   		                <div class="blueButton d-flex flex-column">
		               		<a id="logout" class="align-self-center"  href="/logout">
		               			<img onclick="loadDashboard()" id="logout-icon" src="/images/log_out_icon.png" alt="LOG OUT" height="100%" />
							</a>
				        </div>
		            </div>
		            <div id="blueButtons2" class="d-flex align-items-center justify-content-around">
<!-- 		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div>
		                <div class="blueButton"></div> -->
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
			            <div id="yellowBox1" class="d-flex align-items-center"><button class="w-100 btn">More Poke Stats Here</button></div>
			            <div id="yellowBox2"  class="d-flex align-items-center"><button class="w-100 btn">Debug dashboard loadId: <c:out value="${loadId}"/></button></div>
		            </div>
		        </div><!-- END DETAIL SCREEN -->
		        <!-- FORM - ADD TO POKEMON HISTORY -->
	 			<form id="searchHistoryForm"> <!-- onsubmit="(e)=>e.preventDefault(); console.log('Event 2: '+e);" -->
					<input hidden="true" id="pokemonName" path="pokemonName" value="MissingNo" />
					<input hidden="true" id="dexId" path="dexId" value="000" />
					<input id="submit" type="submit" value="submit" />
				</form>
				<!-- END POKEMON HISTORY FORM -->
		        
		        
	        </div> <!-- end container for screens (id=left)-->
        </div> <!-- END LEFT SCREEN PANEL  --> <!-- #pokedex -->
<script>
//Using jQuery
$(document).ready(function() {
    $('#searchHistoryForm').submit(function(event) {
        // Prevent the form from submitting the traditional way
        event.preventDefault();

        // Get form data
        //var formData = $(this).serialize();
        var formData = {
        	pokemonName: $('#pokemonName').val(),
        	dexId: $('#dexId').val()
        };

        // Send AJAX request
        $.ajax({
            type: 'POST',
            url: '/pokemon/new', // Specify your backend endpoint URL here
            contentType: 'application/json', // Set content type to JSON
            data: JSON.stringify(formData), // Convert data to JSON string
            success: function(response) {
                // Handle the response from the server
                console.log('Data sent successfully:', response);
                // You can update your UI here if needed
            },
            error: function(error) {
                console.error('AJAX REPONSE ERROR:', error);
            }
        });
    });
});

</script>
</body>
</html>