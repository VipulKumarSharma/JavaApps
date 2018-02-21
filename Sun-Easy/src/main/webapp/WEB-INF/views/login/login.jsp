<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE HTML>

<html lang="en">
	<%-- <head>
		<title>Easy-Sun</title>
	</head>
	
	<body>
		<h1>Easy-Sun Home</h1>
	
		<p>The time on the server is ${serverTime}.</p>
	
		<form action="login" method="POST">
			<input type="text" name="userName">
			<br>
			<input type="submit" value="Login">
		</form>
	</body> --%>
	
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Sun-Easy</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" type="image/png" href="resources/login/images/icons/favicon.ico"/>
	
		<link rel="stylesheet" type="text/css" href="resources/login/vendor/bootstrap/css/bootstrap.min.css">
	
		<link rel="stylesheet" type="text/css" href="resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	
		<link rel="stylesheet" type="text/css" href="resources/login/vendor/animate/animate.css">
		
		<link rel="stylesheet" type="text/css" href="resources/login/vendor/css-hamburgers/hamburgers.min.css">
	
		<link rel="stylesheet" type="text/css" href="resources/login/vendor/select2/select2.min.css">
	
		<link rel="stylesheet" type="text/css" href="resources/login/css/util.css">
		<link rel="stylesheet" type="text/css" href="resources/login/css/main.css">
	
		<style type="text/css">
			.wrap-login100 {
				padding : 40px !important;
				width: 750px;
				background: rgba(0,0,0,0.4);
			}
			.login100-pic {
				width: 50%;
			}
			.login100-pic img {
				padding-top: 35px;
			}
			.login100-form-title {
				padding-top: 10px;
			}
			.p-t-136 {
				padding-top: 50px;
			}
			.container-login100 {
				background : linear-gradient(-135deg, #da2127, #4158d0);
			}
			.login100-form-title, .txt1, .txt2 {
				color : #ffffff;
			}
		</style>
	</head>
	<body>
		
		<div class="limiter">
			<div class="container-login100">
				<div class="wrap-login100">
					<div class="login100-pic js-tilt" data-tilt>
						<img src="resources/login/images/img-01.png" alt="IMG">
					</div>
	
					<form name="frm" id="frm" class="login100-form validate-form" action="home" method="POST">
						<span class="login100-form-title">
							emPro-Easy Login
						</span>
	
						<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
							<input class="input100" type="text" name="userName" placeholder="Username">
							<span class="focus-input100"></span>
							<span class="symbol-input100">
								<i class="fa fa-user" aria-hidden="true"></i>
							</span>
						</div>
	
						<div class="wrap-input100 validate-input" data-validate = "Password is required">
							<input class="input100" type="password" name="password" placeholder="Password">
							<span class="focus-input100"></span>
							<span class="symbol-input100">
								<i class="fa fa-lock" aria-hidden="true"></i>
							</span>
						</div>
						
						<div class="container-login100-form-btn">
							<input type="submit" class="login100-form-btn" value="Login">
						</div>
	
						<div class="text-center p-t-12">
							<span class="txt1">
								Forgot
							</span>
							<a class="txt2" href="javascript:void(0);">
								Username / Password?
							</a>
						</div>
	
						<div class="text-center p-t-136">
							<a class="txt2" href="javascript:void(0);">
								Create your Account
								<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
							</a>
						</div>
					</form>
				</div>
			</div>
		</div>

		
		<script src="resources/login/vendor/jquery/jquery-3.2.1.min.js"></script>
	
		<script src="resources/login/vendor/bootstrap/js/popper.js"></script>
		<script src="resources/login/vendor/bootstrap/js/bootstrap.min.js"></script>
	
		<script src="resources/login/vendor/select2/select2.min.js"></script>
	
		<script src="resources/login/vendor/tilt/tilt.jquery.min.js"></script>
		<script >
			$('.js-tilt').tilt({
				scale: 1.1
			})
		</script>
	
		<script src="resources/login/js/main.js"></script>
	
	</body>
</html>