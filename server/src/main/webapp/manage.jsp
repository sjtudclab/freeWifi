<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>FreeWifi</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap2.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap-responsive.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.main.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.grey.css'/>"
		class="skin-color" />
<link href="<c:url value='/resources/css/font-awesome.css'/>" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<body>


		<div id="header">
			<h1><a href="./dashboard.html">FreeWifiAdmin</a></h1>
		</div>

		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">ä¸ªäººèµæ</span></a></li>
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">è®¾ç½®</span></a></li>
                <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">éåº</span></a></li>
            </ul>
        </div>

		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i> é¦é¡µ</a>
			<ul>
				<li ><a href="index.html"><i class="icon
				icon-home"></i> <span>Home Page</span></a></li>
				<li ><a href="index.html"><i class="icon
				icon-edit"></i> <span>Edit</span></a></li>
				<li class="active">
					<a href="widgets.html"><i class="icon icon-dashboard"></i> <span>Manage</span></a>
				</li>
			</ul>

		</div>


		<div id="content">
			<div id="content-header">
				<h1>Manage</h1>
			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Manage</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
								<h5>Recent Ads</h5>
							</div>
							<div class="widget-content nopadding">
								<ul class="recent-posts">
									<li>
										<div class="user-thumb">
											<img width="40" height="40" alt="User" src="img/av2.jpg" />
										</div>
										<div class="article-post">
											<span class="user-info"> By: neytiri on 2 Aug 2012, 09:27 AM, IP: 186.56.45.7 </span>
											<p>
												<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
											</p>
											<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
										</div>
									</li>
									<li>
										<div class="user-thumb">
											<img width="40" height="40" alt="User" src="img/av3.jpg" />
										</div>
										<div class="article-post">
											<span class="user-info"> By: john on on 24 Jun 2012, 04:12 PM, IP: 192.168.24.3 </span>
											<p>
												<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
											</p>
											<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
										</div>
									</li>
									<li>
										<div class="user-thumb">
											<img width="40" height="40" alt="User" src="img/av1.jpg" />
										</div>
										<div class="article-post">
											<span class="user-info"> By: michelle on 22 Jun 2012, 02:44 PM, IP: 172.10.56.3 </span>
											<p>
												<a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
											</p>
											<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>
										</div>
									</li>

								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



            <script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
            <script src="<c:url value='/resources/js/jquery.ui.custom.js'/>"></script>
            <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
            <script src="<c:url value='/resources/js/unicorn.js'/>"></script>
	</body>
</html>