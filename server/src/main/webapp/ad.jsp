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
    <script src="<c:url value='/resources/js/bootstrap-wysiwyg.js'/>"></script>
  </head>
  <body>
  </body>
<script>
  function getUrlParameter(sParam)
  {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
  }
  $(function(){
      var id = getUrlParameter('id');
      $.ajax({
          method: "GET",
          url: "/freewifiserver/ad/show",
          data: {id: id}
      }).done(function(result) {
          if (result.code == 0) {
              $('body').html(result.data);
          } else {
              console.log('error');
          }
      })
  });
</script>
</html>
