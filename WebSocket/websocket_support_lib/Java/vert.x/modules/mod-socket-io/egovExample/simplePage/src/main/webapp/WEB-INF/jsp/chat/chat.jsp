<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>mod-socket-io 테스트</title>
		<link href="<c:url value='/js/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
		<link href="<c:url value='/js/bootstrap/css/bootstrap-responsive.min.css' />" rel="stylesheet">
		<link href="<c:url value='/js/bootstrap/css/docs.css' />" rel="stylesheet">
		<style>
				/*body {*/
				/*padding-top: 60px;*/
				/*}*/
		</style>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<a class="brand" href="#">mod-socket-io 테스트</a>
			</div>
		</div>
		<header class="jumbotron subhead" id="overview">
			<div class="container">
				<h1>mod-socket-io 테스트</h1>
				<p class="lead">mod-socket-io 기반 채팅 프로그램<p>
			</div>
		</header>
		<br>
		<div class="container">
			<div class="row">
				<input type="text" id="name" value="TEST1" />
				<input type="button" value="접속" onclick="connect()" />
				<br>
				<h4>대화메시지</h4>
				<input type="text" id="msgbox"/><br>
				<span id="msgs"></span>
			</div>
		</div>
		<script src="<c:url value='/js/jquery-1.7.2.min.js' />"></script>
		<script src="<c:url value='/js/bootstrap/js/bootstrap.min.js' />"></script>
		<script type="text/javascript" src="<c:url value='/js/socket.io-0.9.10.js' />"></script>
		<script>
			function connect() {
				$("#name").attr("readonly", true);
				
		        var socket = io.connect('http://localhost:19999');
		        socket.on('toClient', function (data) {
		            $('#msgs').prepend(data.msg + "<BR>");
		        });

		        $("#msgbox").keyup(function(event) {
		            if (event.which == 13/* ë¦¬í´ */) {
		                socket.emit("fromClient", {msg:$("#name").val() + ":" + $('#msgbox').val()});
		                $("#msgbox").val("");
		            }
		        });
			}
    	</script>
	</body>
</html>