<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
               <img class="logo_img" src="https://static.vecteezy.com/system/resources/thumbnails/011/822/615/small_2x/cloud-care-logo-design-charity-group-logo-design-concept-free-vector.jpg" 
                alt="logoImage">
            </a>
           </div>
          
         
        </div>
        <div class="main_box">
        	<form:form method="post" action="createCollection" modelAttribute="collection">
                       <div class="addCollection_form">
                        
                            <div class="addCollection_input-wrapper">
                                <form:label class="addCollection__input_label" path="title">Collection Title:</form:label>
                                <form:input class="addCollection__input" type="text" name="title" path="title" placeholder="title"/>
                                <form:errors path="title" class="error_message"/>
                            </div>
                            <div class="addCollection__input--wrapper">
                                <form:label class="addCollection__input_label" path="description">Description</form:label>
                                <form:textarea class="addCollection__input" name="description" path="description" placeholder="description"></form:textarea>
                                <form:errors path="description" class="error_message"/>
                            </div>
                           
                            <div class="addCollection__input--wrapper">
                             <form:label class="addCollection__input_label" path="img">Collection Picture URL</form:label>
                                <form:input class="addCollection__input" type="text" path="img" name="img" placeholder="img"/>
                                <form:errors path="img" class="error_message"/>
                            </div>
                            <div class="addCollection__input--wrapper">
                                <form:label class="addCollection__input_label" path="amount">Amount Price</form:label>
                                <form:input class="addCollection__input" type="number" min="0" name="amount" path="amount" placeholder="amount"/>
                                <form:errors path="amount" class="error_message"/>
                            </div>
                            <div class="login__submit">
                                <form:button class="addCollection__submit--button" type="submit" path="submit">Add new collection!</form:button>
                            </div>
                            </div>
                    </form:form>
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