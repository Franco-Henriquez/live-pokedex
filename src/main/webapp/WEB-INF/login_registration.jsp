<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
<script src="js/set_height.js" defer></script>
<script src="js/screen_debug.js" defer></script>



<title>Live Pokédex</title>
</head>
<body onload="setHeight(),getWidthHeight()">
		<div id="pokedex">
		
        <div id="floating-stats" >STATS:<br></div>
		<!-- LEFT SCREEN PANEL -->
        <div id="left">
        	<div id="topNav">
	            <!-- <div id="logo"></div> -->
	            <div id="bg_curve1_left"></div>
	            <div id="bg_curve2_left">
	                <div id="curve2_left">
	                    <div id="pokeSearchContainer" class="p-2 flex-column align-items-baseline d-flex flex-column align-items-center">
		                   
	                	</div>
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
			<div id="screen-one" class="d-flex flex-column align-items-center flex-nowrap flex-fill">
				 <div id="header-spacer" class="d-flex">
				 	<h1 id="screen-tab" class="ms-auto m-0 p-2">Register / Login</h1> <!-- Half the space or 42.5% -->
				 </div>
				 <div id="screen" class=""> <!-- SCREEN 1 -->
				 <!-- INSIDE SCREEN 1 -->
				 	<div class="d-flex flex-column px-5 justify-content-between ">
						<!-- REGISTER FORM -->
						<form:form class="mb-5" modelAttribute="registerTrainer" method="post" action="/register">
							<h2>New Trainer</h2>
							<p>
								<form:errors class="p-1" path="favoritePokemon"/>	
							</p>
							<p>
								<form:errors class="p-1" path="favoriteCard"/>
							</p>
							<form:hidden path="favoritePokemon"/>
							<form:hidden path="favoriteCard"/>
							<p class="fs-2 d-flex flex-column justify-content-between">
								<form:label class="m-1 .flex-grow-1" path="trainerName">Name:</form:label>
								<form:errors class="p-1" path="trainerName"/>
								<form:input class="p-1 w-45" path="trainerName"/>
							</p>
							<p class="fs-2 d-flex flex-column justify-content-between">
								<form:label class="m-1 .flex-grow-1" path="email">Email:</form:label>
								<form:errors class="p-1" path="email"/>
								<form:input class="p-1 w-45" type="email" path="email"/>
							</p>
							<p class="fs-2 d-flex flex-column justify-content-between">
								<form:label class="m-1 .flex-grow-1" path="password">Password:</form:label>
								<form:errors class="p-1" path="password"/>
								<form:password class="p-1 w-45" path="password"/>
							</p>
							<p class="fs-2 d-flex flex-column justify-content-between">
								<form:label class="m-1 .flex-grow-1" path="confirmPassword">Confirm PW:</form:label>
								<form:errors class="p-1" path="confirmPassword"/>
								<form:password class="p-1 w-45" path="confirmPassword"/>
							</p>
							<input type="submit" value="Resgiter" />			
						</form:form>
				
							
						<!-- LOGIN FORM  -->
						<form:form class="mb-5" modelAttribute="newLogin" method="post" action="/login">
						<h2>Log in</h2>
							<!-- BOOTSTRAP NOTE: can use gap-3 with flex parent to add a gap around each col/row  -->
							<p>
								<form:errors class="p-1" path="email"/>
							
							</p>
							<p>
							
								<form:errors class="p-1" path="password"/>
							</p>
							<p class="fs-2 d-flex flex-column justify-content-around gap-3">
								<form:label class=".flex-grow-1" path="email">Email:</form:label>
								
								<form:input class="p-1 w-45" type="email" path="email"/> <!-- type="email" forces check of @ sign -->
							</p>
							<p class="fs-2 d-flex flex-column justify-content-around gap-3">
								<form:label class=".flex-grow-1" path="password">Password:</form:label>
				
								<form:password class="p-1 w-45" path="password"/>
							</p>
							<input type="submit" value="Login" />
						</form:form>
				
					</div>
				     <div id="pokeSearchContainer" class="d-flex justify-content-around">
					     <div id="buttonbottomPicture" class="align-self-center"></div>
					     <div id="speakers" class="align-self-center">
					         <div class="sp"></div>
					         <div class="sp"></div>
					         <div class="sp"></div>
					         <div class="sp"></div>
					     </div>
				     </div>
				 </div> <!-- END SCREEN 1 -->
				 <div id="simpleButtons" class="p-2 d-flex justify-content-around"> <!-- Buttons 1 -->

				 </div> <!-- END BUTTONS 1 -->

			</div>
		            <!-- DETAIL SCREEN -->
		        <div id="detailScreen" class="d-flex flex-column "> <!-- class name 'row' might need to be added back -->


		        </div><!-- END DETAIL SCREEN -->
		        
		        
	        </div> <!-- end container for screens (id=left)-->
        </div> <!-- END LEFT SCREEN PANEL  --> <!-- #pokedex -->
	

</body>
</html>