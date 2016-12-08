<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSING</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-2.2.4.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("a").on("click", function() {
		var url = $(this).text();
		console.log("POST '" + url + "'");
		
		$.ajax({
			url: url
		})
		.done(function(response) {
			console.log(response);
		});
		
		return false;
	});
});
</script>
</head>
<body>
<div class="container-fluid">
	<h3>반갑습니다. 저는 씽입니다.</h3>
	<hr/>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/connect</a>
	</p>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/login</a>
	</p>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/logout</a>
	</p>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/disconnect</a>
	</p>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/status</a>
	</p>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/account</a>
	</p>
	<hr/>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/jongmok</a>
	</p>
	<hr/>
	<p>
		<a class="btn btn-default" href="#" role="button">/trade/ctx</a>
	</p>
</div>
</body>
</html>
