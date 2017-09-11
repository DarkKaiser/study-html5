<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>JBOSS WebSocket 테스트</title>
		<link href="./js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="./js/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="./js/bootstrap/css/docs.css" rel="stylesheet">
		<style>
				/*body {*/
				/*padding-top: 60px;*/
				/*}*/
		</style>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<a class="brand" href="#">JBOSS WebSocket 테스트</a>
			</div>
		</div>
		<header class="jumbotron subhead" id="overview">
			<div class="container">
				<h1>JBOSS WebSocket 테스트</h1>
				<p class="lead">WebSocket 기반 ECHO 프로그램<p>
			</div>
		</header>
		<br>
		<div class="container">
			<div class="row">
				<input type="text" id="name" value="TEST1" />
				<input type="button" value="접속" onclick="connect()" />
				<br>
				<h4>ECHO 메시지</h4>
				<input type="text" id="msgbox"/><br>
				<span id="msgs"></span>
			</div>
		</div>
		<script src="./js/jquery-1.7.2.min.js"></script>
		<script src="./js/bootstrap/js/bootstrap.min.js"></script>
		<script>
			var websocket = null;
		
			function connect() {
				$("#name").attr("readonly", true);

		        websocket = new WebSocket("ws://localhost:8080/testPage/websocket/chat");
		        websocket.onmessage = function(event) {
		        	$('#msgs').prepend(event.data + "<BR>");
		        };

		        $("#msgbox").keyup(function(event) {
		            if (event.which == 13/* 리턴 */) {
		            	websocket.send($("#name").val() + ":" + $('#msgbox').val());
		                $("#msgbox").val("");
		            }
		        });
			}
    	</script>
	</body>
</html>