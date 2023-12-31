<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<!-- 	<meta name="viewport" content="height=device-height, width=device-width, initial-scale=3.74, shrink-to-fit=no"> -->
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
 	<link href="/css/style.css" rel="stylesheet">
 	<link href="/css/loading.css" rel="stylesheet">
 	<link href="/css/glow_animations.css" rel="stylesheet">
 	<script src="/js/script.js" defer></script>
	<script src="/js/loading.js" defer></script>
	<script src="/js/screen_debug.js" defer></script>
		
	
	<title>Live Pok�dex</title>
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
									<th scope="col">Pok�mon</th> <!-- pull from SearchHistory Card relationship -->
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
		               		<img onclick="cardSearchView()" id="search-card-icon" src="/images/pokemon_card_small.png" alt="CARD SEARCH" height="100%" />
				        </div>
   		                <div class="blueButton d-flex flex-column align-items-center">
		               		<img onclick="pokeSearchView()" id="search-pokemon-icon" src="/images/pokemon_search_pixel_ditto.png" alt="POKEMON SEARCH" height="100%" />
				        </div>
   		                <div class="blueButton d-flex flex-column align-items-center">
		               		<img onclick="loadDashboard(1)" id="dashboard-icon" class="align-self-center" src="/images/pokedex_dash.png" alt="DASHBOARD" height="100%" />
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
	 			<form:form id="searchHistoryForm" modelAttribute="newPokemon" method="post" action="/pokemon/new" onsubmit="(e)=>e.preventDefault(); console.log('Event 2: '+e);">
					<div>
<%-- 						<form:errors class="row btn-warning p-1 mt-2" path="pokemonName"/>
						<form:errors class="row btn-warning p-1 mt-2" path="dexId"/> --%>
					</div>
					<form:hidden id="pokemonName" path="pokemonName" value="MissingNo" />
					<form:hidden id="dexId" path="dexId" value="000" />
					<%-- <form:hidden id="searchString" path="searchString" value="MissingNo" /> --%>
					
					<input id="submit" type="submit" value="submit" />
				</form:form>
				<!-- END POKEMON HISTORY FORM -->
		        
		        
	        </div> <!-- end container for screens (id=left)-->
        </div> <!-- END LEFT SCREEN PANEL  --> <!-- #pokedex -->

</body>
</html>