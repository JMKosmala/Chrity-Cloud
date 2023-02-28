<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <style><%@include file="/resources/style/PPChar.css"%></style> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charity Cloud</title>
           
        
</head>
<body>
    <div class="page_wrapper">
        <div class="header">
           <div class="logo_box">
            <a href="/"><img class="logo_img" src="https://static.vecteezy.com/system/resources/thumbnails/011/822/615/small_2x/cloud-care-logo-design-charity-group-logo-design-concept-free-vector.jpg" 
                alt="logoImage">
            </a>
           </div>
           <div class="banner_box">
                <div class="banner">Welcome on Charity Cloud!</div>
           </div>
           <div class="buttons_box">
           	 <%
            Object loggedInObj = request.getAttribute("loggedIn");
            if (loggedInObj == null || !(Boolean) loggedInObj) {
            %>
                <div class="navbar_buttons-login">
                    <a href="/login">
                        <button class="login-button">Login</button>
                    </a>
                </div>
                <div class="navbar_buttons-register">
                <a href="/register">
                    <button class="register-button">Register</button>
                    </a>
                </div>
            <%
            } else {
            %>
            <div class="navbar_buttons-profile">
                <a href="/profile">
                    <button class="profile-button">Profile</button>
                </a>
            </div>
            <div class="navbar_buttons-logout">
                <a href="/myCollections/${user.username}">
                    <button class="logout-button">My Collections</button>
                </a>
            </div>
            <div class="navbar_buttons-logout">
                <a href="/logout">
                    <button class="logout-button">Logout</button>
                </a>
            </div>
            <%
            }
            %>
           </div>
        </div>
        <div class="sc_box">
	            <div class="single_collection_box">
		            
		                <div class="single_collection_details">
		                    <div class="single_collection_img"><img src="${collection.img}" alt="rainforest" style="width: 100%; height: 100%;"></div>
		                    <div class="single_collection_title">${collection.title}</div>
		                    <div class="single_collection_description">${collection.description}</div>
		                    <div class="single_collection_values">Collected: ${collection.collected}/${collection.amount}</div>
		                    

		                </div>    
				         
				          <%
				            if (loggedInObj == null || !(Boolean) loggedInObj) {
				         %>
				         	<h3> You need to be logged in to donate</h3>
				         	<div class="navbar_buttons-login">
                    			<a href="/login">
                        		<button class="login-button">Login</button>
                   				 </a>
                				</div>
                			
				         
				         <%
				            } else {
				         %> 
			                    <div class="more_button">
			                    	<form method="GET" action="/collections/${collection.id}/donate">
			                        <button class="donate_button" type="submit">Donate!</button>
			                        </form>
			                    </div>
		                  
		                 <%
			             }	
			             %>
		       </div> 
		       <div class="comments_section">
						    <h2>Comments:</h2>
						<ul style="height: 100px; overflow: auto;">
						    <c:forEach items="${comments}" var="comment">
						        <li><b>${comment.user.username}:</b> <br> ${comment.text}</li>
						    </c:forEach>
						</ul>
						</div>
	   </div>
	
						
        
         <div class="footer">
            <div class="navbar_buttons-profile">
            	<a href="/help">
                    <button class="profile-button">FAQ</button>
                </a>
            </div>
            <div class="navbar_buttons-profile">
            	<a href="/aboutUs">
                    <button class="profile-button">About US</button>
                </a>
            </div>
            <div class="navbar_buttons-profile">
            <a href="/contact">
                    <button class="profile-button">Contact</button>
                </a>
            </div>
        </div>
   
     </div>  
    
</body>
</html>