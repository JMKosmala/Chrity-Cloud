<?xml version="1.0" encoding="ISO-8859-1" ?>
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
            <a href="/">
               <a href="/"><img class="logo_img" src="https://static.vecteezy.com/system/resources/thumbnails/011/822/615/small_2x/cloud-care-logo-design-charity-group-logo-design-concept-free-vector.jpg" 
                alt="logoImage"></a>
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
        <div class="main_box">
        	
	            <div class="error_box">
		            
		                <div class="payment"> 
		                Here you should be redirected to a payment page such as Paypal etc...
		                </div>
		                <div>
		                Back to main page
		                </div>
		                <div class="navbar_buttons-login">
                    		<a href="/">
                        	<button class="login-button">Back</button>
                    		</a>
                		 </div>
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