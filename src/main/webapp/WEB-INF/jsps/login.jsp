
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
        <div class="login_main_box">
        	 <form:form method="post" action="/login" modelAttribute="user">
                        <div class="login_box">
                        	<h2 class="login__title">Sign In</h2>
                            <div class="login__input--wrapper">
                                <form:input class="login_input label" type="text" name="login" path="username" placeholder="Login" />
                                <form:label class="login_input label" path="username">Login</form:label>
                            </div>
                            <div class="login__input--wrapper">
                                <form:input class="login_input label" type="password" id="password" name="password" path="password" placeholder="Password" />
                                <form:label class="login_input label" path="password">Password</form:label>
                            </div>
                            <div class="login__submit">
                                <form:button class="login_submit_button" type="submit" path="submit">Login</form:button>
                            </div>
                        </div>
                    </form:form>
                    <div class="login_box--options">
                    	<a class="login_box-registerAnchor" href="/recovery">
                        	<p class="login__form--option-password">Forgot password?</p>
                        </a>
                        <a class="login_box-registerAnchor" href="/register">
                        	<p class="login__form--option-register">Register account.</p>
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