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
        	
	            <div class="contact_box"> 
		                <div class="faq_msg">
		                    <div class="faq-sub">
		                    	Contact us!
		                    </div>
		                    <div class="faq-abus">
			                     "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore 
			                     magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo 
			                     consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
			                     Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
			                </div>
			            </div>
			                     
			                     <form action="mailto:support@charcloud.com" method="post">
                            		<div class="contact_message_box">
                                		<div class="faq_message">
                                			<h2><align=center>Can't find an answer? Contact us</h2></align>
	                                        <div class="login__input--wrapper">
                                            	<textarea class="faq__input" type="text" name="faqbox" placeholder="Write your question here"></textarea>
                                       		 </div>
                                    		<div class="faq__submit">
                                    			<button class="faq__submit--button" type="submit">Send message</button>
                                       		</div>
                            			 </div>
                            		</div>
                            	</form>
		                         
		                         
		             </div>        
		                         
		                       
		                 
		                 <div class="navbar_buttons-login">
                    <a href="/">
                        <button class="login-button">Back</button>
                    </a>
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