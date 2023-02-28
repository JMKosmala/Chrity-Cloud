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
               <a href="/"><img class="logo_img" src="https://static.vecteezy.com/system/resources/thumbnails/011/822/615/small_2x/cloud-care-logo-design-charity-group-logo-design-concept-free-vector.jpg" 
                alt="logoImage"></a>
            </a>
           </div>
          
         
        </div>
        <div class="main_box">
        	<form:form method="post" action="register" modelAttribute="user">
                        <div class="register_box">
	                            <div class="add_user_title">Create account</div>
	                            <div class="register_input-wrapper">
	                                <form:label class="register__input_label" path="name">Enter Name</form:label>
	                                <form:input class="register__input_label" type="name" name="name" path="name" placeholder="Enter Name"/>
	                            	<form:errors path="username" class="error_message"/>
	                            </div>
	                            <div class="register_input-wrapper">
	                            	<form:label class="register__input_label" path="surname">Enter Surname</form:label>
	                                <form:input class="register__input_label" type="surname" name="surname" path="surname" placeholder="Enter Surrname"/>
	                                
	                            	<form:errors path="username" class="error_message"/>
	                            </div>
	                            <div class="register_input-wrapper">
	                                <form:label class="register__input_label" path="email">Enter e-mail</form:label>
	                                <form:input class="register__input_label" type="email" name="email" path="email" placeholder="Enter e-mail"/>
	                                
	                            	<form:errors path="email" class="error_message"/>
	                            </div>
	                            <div class="register_input-wrapper">
	                                <form:label class="register__input_label" path="username">Enter Username</form:label>
	                                <form:input class="register__input_label" type="username" name="username" path="username" placeholder="Enter Username"/>
	                                
	                            	<form:errors path="username" class="error_message"/>
	                            </div>
	                            <div class="register_input-wrapper">
	                                <form:label class="register__input_label" path="password">Create password</form:label>
	                                <form:input class="register__input_label" type="password" id="password" name="password" path="password" placeholder="Create Password"/>
	                                
	                            	<form:errors path="password" class="error_message"/>
	                            </div>
	                            <div class="register_input-wrapper">
	                                <form:label class="register__input_label" path="confirmPassword">Confirm password</form:label>
	                                <form:input class="register__input_label" type="password" id="password" name="confirmPassword" path="confirmPassword" placeholder="Confirm Password"/>
	                                
	                        		<form:errors path="confirmPassword" class="error_message"/>
	                            </div>
	                            <div class="register_submit">
	                                <form:button class="register_submit_button" type="submit" path="submit">Create Account</form:button>
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