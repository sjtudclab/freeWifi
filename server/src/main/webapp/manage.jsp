<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.grey.css'/>" class="skin-color" />
<link href="<c:url value='/resources/css/font-awesome.css'/>" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html;
	charset=utf-8" />
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootbox.min.js'/>"></script>

<script src="<c:url value='/resources/js/manage.js'/>"></script>
</head>
<body>
		<div id="header">
			<h1><a href="./dashboard.html">FreeWifiAdmin</a></h1>
		</div>

		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">个人资料</span></a></li>
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
                <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
            </ul>
        </div>

		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i> 首页</a>
			<ul>
				<li ><a href="index.html"><i class="icon
				icon-home"></i> <span>Home Page</span></a></li>
				<li ><a href="editor.html"><i class="icon
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
								<ul class="recent-posts"
                                                                    id="recent-posts">
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
