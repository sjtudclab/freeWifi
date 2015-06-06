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
		<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.grey.css'/>" class="skin-color" />
	<meta http-equiv="Content-Type" content="text/html;
		charset=utf-8" />

<link rel="apple-touch-icon" href="//mindmup.s3.amazonaws.com/lib/img/apple-touch-icon.png" />
    <link rel="shortcut icon"
          href="http://mindmup.s3.amazonaws.com/lib/img/favicon.ico" >
<link href="<c:url value='/resources/css/prettify.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/bootstrap-responsive.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/font-awesome.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-multiselect.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/index.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/editor.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css'/>" rel="stylesheet">
    <script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/resources/js/jquery.hotkeys.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/js/prettify.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-multiselect.js'/>"></script>
    <script src="<c:url value='/resources/js/utility.js'/>"></script>
    <script src="<c:url value='/resources/js/editor.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-wysiwyg.js'/>"></script>
	
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
				<li class="active"><a href="index.html"><i class="icon
				icon-edit"></i> <span>Edit</span></a></li>
				<li>
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



<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b class="caret"></b></a>
          <ul class="dropdown-menu">
          </ul>
        </div>
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="icon-text-height"></i>&nbsp;<b class="caret"></b></a>
          <ul class="dropdown-menu">
          <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
          <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
          <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
          </ul>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
        <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
        <a class="btn" data-edit="strikethrough" title="Strikethrough"><i class="icon-strikethrough"></i></a>
        <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="icon-list-ul"></i></a>
        <a class="btn" data-edit="insertorderedlist" title="Number list"><i class="icon-list-ol"></i></a>
        <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
        <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="icon-indent-right"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
        <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
        <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
        <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
      </div>
      <div class="btn-group">
		  <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="icon-link"></i></a>
		    <div class="dropdown-menu input-append">
			    <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
			    <button class="btn" type="button">Add</button>
        </div>
        <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="icon-cut"></i></a>

      </div>

      <div class="btn-group">
        <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
      </div>
      <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
    </div>

<div id="editor">
    </div>
    <div>
      <p class="text-center btn-toolbar">
        <span class="btn-group">
          <button id='preview' type="button" class="btn-primary btn-block" data-toggle="modal" data-target="#previewModal">Preview</button>
        </span>
        <span class="btn-group">
          <button id='save' type="button" class="btn-primary btn-block" data-toggle="modal" data-target="#saveModal">Save</button>
        </span>
      </p>
    </div>
<!-- Trigger the modal with a button -->


<!-- Modal -->
<div id="previewModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ad Preview</h4>
      </div>
      <div class="modal-body" id='modal-body'>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div id="saveModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ad Save</h4>
      </div>
      <div class="modal-body" id='modal-body'>
          <div class="form-group">
            <input id="adName" type="text" class="form-control input-lg required" id="user-name" name="user[name]" maxlength="50" value="" placeholder="Ad Name" data-placement="top" data-title="Enter your first and last name." data-error-title="You must enter your full name.">
          </div>
          <div class="input-append date" id="datetimepicker1">
            <input id="startDate" placeholder="Start Date" data-format="yyyy-MM-dd" type="text">
            <span class="add-on">
              <i data-date-icon="icon-calendar" data-time-icon="icon-time" class="icon-calendar">
              </i>
            </span>
          </div>
          <div class="input-append date" id="datetimepicker2">
            <input id="endDate" placeholder="End Date" data-format="yyyy-MM-dd" type="text">
            <span class="add-on">
              <i data-date-icon="icon-calendar" data-time-icon="icon-time" class="icon-calendar">
              </i>
            </span>
          </div>
          <div class="input-append date" id="datetimepicker3">
            <input id="startTime" placeholder="Start Time" data-format="hh:mm:ss" type="text">
            <span class="add-on">
              <i data-date-icon="icon-calendar" data-time-icon="icon-time" class="icon-time">
              </i>
            </span>
          </div>
          <div class="input-append date" id="datetimepicker4">
            <input id="endTime" placeholder="Start Date" data-format="hh:mm:ss" type="text">
            <span class="add-on">
              <i data-date-icon="icon-calendar" data-time-icon="icon-time" class="icon-time">
              </i>
            </span>
          </div>
          <div style="padding-top: 5px;">
            <select id="sex" multiple="multiple">
              <option value="0">Male</option>
              <option value="1">Female</option>
            </select>
            <select id="age" multiple="multiple">
              <option value="0" >< 8</option>
              <option value="1">20-35</option>
              <option value="2">35-50</option>
              <option value="3">> 50</option>
            </select>
            <select id="edu" multiple="multiple">
              <option value="0" > < High School</option>
              <option value="1">High School</option>
              <option value="2">junior college</option>
              <option value="3">Bachelor</option>
              <option value="4"> > master</option>
            </select>
            <select id="income" multiple="multiple">
              <option value="0" >< 3000</option>
              <option value="1">3000-7000</option>
              <option value="2">7000-10000</option>
              <option value="3">> 10000</option>
            </select>

          </div>
          <div class="radio" style="padding-top: 5px;">
            <label><input type="radio" name="optradio" id="isLaunch">isLaunch</label>
          </div>
      </div>
      <div class="modal-footer">
        <button id="adSave" type="button" class="btn btn-default" data-dismiss="modal">save</button>
      </div>
    </div>

  </div>
</div>














							</div>
						</div>
					</div>
				</div>
			</div>

<script>
  $(function(){
    function initToolbarBootstrapBindings() {
      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
            'Times New Roman', 'Verdana'],
            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
      $.each(fonts, function (idx, fontName) {
          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
      });
      $('a[title]').tooltip({container:'body'});
    	$('.dropdown-menu input').click(function() {return false;})
		    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
        .keydown('esc', function () {this.value='';$(this).change();});

      $('[data-role=magic-overlay]').each(function () {
        var overlay = $(this), target = $(overlay.data('target'));
        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
      });
      if ("onwebkitspeechchange"  in document.createElement("input")) {
        var editorOffset = $('#editor').offset();
        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
      } else {
        $('#voiceBtn').hide();
      }
	};
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	};
    initToolbarBootstrapBindings();
	$('#editor').wysiwyg({ fileUploadError: showErrorAlert} );
    window.prettyPrint && prettyPrint();


  });
</script>

	</body>
</html>
