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
            
            <div class="navbar_buttons-profile">
                <a href="/profile">
                    <button class="profile-button">Profile</button>
                </a>
            </div>
            <div class="navbar_buttons-logout">
                <a href="/logout">
                    <button class="logout-button">Logout</button>
                </a>
            </div>
          
           </div>
        </div>
        <div class="main_box">
        	<c:forEach items="${myCollections}" var="collection">
	            <div class="box">
		            
		                <div class="collection_details">
		                    <div class="collection_img"><img src="${collection.img}" alt="rainforest" style="width: 100%; height: 100%;"></div>
		                    <div class="collection_title">${collection.title}</div>
		                    <div class="collection_description">${collection.description}</div>
		                    <div class="collection_values">${collection.collected}/${collection.amount}</div>
		                    <div class="more_button">
		                    	<form method="POST" action="/collections/${collection.id}">
		                        <button class="more_info_button" type="submit">More info</button>
		                        </form>
		                    </div>     
		                </div> 
		                </div> 
	       	</c:forEach>
	        		
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