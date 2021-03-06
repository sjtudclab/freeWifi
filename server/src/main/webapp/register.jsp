<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="nojs pending">

    <head>
        <!-- Meta, title, CSS, fav icons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="Register with Desk.com Free!">
        <meta name="author" content="Desk.com">
        <title>
            Get Started with Desk.com | Desk.com
        </title>


        <!-- Bootstrap core -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
        <script src="<c:url value='/resources/js/jquery.min.js'/>"
        type="text/javascript"></script>
        <script src="<c:url value='/resources/js/bootstrap.min.js'/>" type="text/javascript"></script>
        <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/register.css'/>">
        <!-- modernizer -->
        <script type="text/javascript" src="<c:url value='/resources/js/modernizr.custom.32032.js'/>">
        </script>
        <!-- typekit -->
        <script type="text/javascript" src="<c:url value='/resources/js/jgm1qhs.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/bootstrap-multiselect.js'/>"></script>
        <link href="<c:url value='/resources/css/bootstrap-multiselect.css'/>" rel="stylesheet">
        <script src="<c:url value='/resources/js/register.js'/>"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media
        queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js">
            </script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js">
            </script>
        <![endif]-->
        <!-- Custom fonts -->
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic,700italic"
        rel="stylesheet" type="text/css">
        <!-- Core style -->
        <style type="text/css">
            /** Basics **/ html, body, #all { /* height: 100%; */ } body { font: 16px/1.5
            'open sans', helvetica, arial, sans-serif; overflow-x: hidden; /* background:
            #f0f0ed; */ background: white; color: #777; font-weight: 300; -webkit-background-size:
            cover; -moz-background-size: cover; -o-background-size: cover; background-size:
            cover; } a { color: #674099; -moz-transition-duration: 0.2s; -webkit-transition-duration:
            0.2s; transition-duration: 0.2s; } a:hover { text-decoration: none; } /*
            h2 { color: #fff; background: #333; width: 100%; padding: 30px; margin:
            -30px 0 20px -30px; -webkit-border-radius: 6px 6px 0 0; -moz-border-radius:
            6px 6px 0 0; border-radius: 6px 6px 0 0; -moz-box-sizing: content-box;
            -webkit-box-sizing: content-box; box-sizing: content-box; } */ /** Containers
            **/ #all { width: 100%; min-height: 800px; } header { position:relative;
            float:left; width:100%; background:url(http://help.desk.com/l/26172/2013-10-15/245k9/26172/10108/the_big_girl.png)
            center center no-repeat #333333; background-size: cover; color: #ffffff;
            height:130px; overflow-y: hidden; } .col-flex-width { width: auto; max-width:
            400px; margin: 0 auto; } @media (min-width: 768px) { } /** Buttons **/
            .btn { line-height: 1em; text-shadow: none; border: 0; text-transform:
            uppercase; color: #FFF; padding: 1em 1.5em; font-weight: 600; } .btn-primary
            { background: #26b160; } .btn-primary:hover { color: #FFF; background:
            #179650; } /** logo **/ #logo { display: block; position: relative; bottom:
            -15px; left: 0; } /** Reg Form **/ #form_register { background: #fff; margin:
            0 0; } html.placeholder label.placeholder { display: none; } .form-control:focus
            { outline: none; color: #674397; background: #f9f7fb; border-color: #674397;
            } .form-control { box-shadow: none; } .submit-group { width: 100%; padding:
            30px; margin: 20px 0 -30px -30px; -moz-box-sizing: content-box; -webkit-box-sizing:
            content-box; box-sizing: content-box; } /**.tooltip-inner { padding: 8px
            15px; background-color: #674397; } .tooltip .tooltip-arrow { color: #674397;
            }**/ .has-error input { background: #ffeaea; } .fine-print { margin-top:
            1em; font-size: 85%; } #error-url { display: none; font-size: .9em; padding:
            5px 15px 0 15px; color: #b94a48; } .agreement-error:after { content: "Please
            check the box to continue"; color: #b94a48; width: 200px; font-size: 14px;
            margin-left: 25px; } @media screen and (min-width: 320px) and (max-width:
            480px) { #step1 { width: 90%; padding:10px; } } #form_register { width:
            342px;}
        </style>
    </head>

    <body id="top" class="index guest">
        <div id="stage" class="">
            <header pardot-region="header-image">
                <div class="col-md-6 col-md-offset-5">
                    <a href="index.html" title="Learn More About freewifi.com"
                    id="logo">
                        <img src="<c:url value='/resources/img/Desk_logo_85H_sfdc_Desk_Current_logo_KO_rgb_1.0.png'/>"
                        alt="Customer Service from Desk.com">
                    </a>
                </div>
            </header>
            <section id="regform" class="white">
                <div class="container">
                    <div class="half no-background col-md-5 col-md-offset-1" style="margin-top: 40px;">
                        <div pardot-region="page_headline" class="pardot" data-id="7948">
                            <p>
                                <span style="color:#696969;">
                                    <span style="font-size:33px;">
                                        Try freewifi.com
                                    </span>
                                </span>
                            </p>
                        </div>
                        <div pardot-region="left_column" data-id="7950">
                            <p>
                                freewifi.com is to push Ad to
                                customers by proving free wifis.
                            </p>
                            <ul>
                                <li>
                                    Cheap
                                </li>
                                <li>
                                    Friendly
                                </li>
                                <li>
                                    Easy to use
                                </li>
                            </ul>
                            <p>
                                <img alt="" border="0" height="215" src="http://help.desk.com/l/26172/2014-12-19/2y8m57/26172/54612/FreeTrialImage.png"
                                style="width: 400px; height: 215px; border-width: 0px; border-style: solid;"
                                width="400">
                            </p>
                            <p>
                                <span style="font-size:18px;">
                                    Try freewifi.com
                                    <strong>
                                        FREE
                                    </strong>
                                    for 14 days.
                                </span>
                            </p>
                            <p>
                                &nbsp;
                            </p>

                        </div>
                        <div pardot-region="customer-quote" class="quote" data-id="7952">
                        </div>
                    </div>
                    <div class="col-md-4" style="margin-top: 40px;border-left: 1px solid #ccc; padding-left: 20px;">
                        <form action="http://help.desk.com/l/26172/2014-09-04/k4qxp" method="post"
                        class="form_register nomargin-bottom" data-server="desk" id="form_register">
                            <!-- Necessary hidden fields -->
                            <input name="user[site_attributes][signup_size]" type="hidden" value="1">
                            <input name="authenticity_token" type="hidden" value="1">
                            <input type="hidden" value="50" name="bill_subscription[bill_plan_id]">
                            <!-- Source Fields for marketing -->
                            <input id="source_1" name="user[site_attributes][site_source_attributes][source_1]"
                            value="home--" type="hidden">
                            <input id="source_2" name="user[site_attributes][site_source_attributes][source_2]"
                            value="" type="hidden">
                            <input id="source_3" name="user[site_attributes][site_source_attributes][source_3]"
                            value="" type="hidden">
                            <input id="source_4" name="user[site_attributes][site_source_attributes][source_4]"
                            value="" type="hidden">
                            <input id="source_5" name="user[site_attributes][site_source_attributes][source_5]"
                            value="" type="hidden">
                            <input id="source_6" name="user[site_attributes][site_source_attributes][source_6]"
                            value="" type="hidden">
                            <input id="source_7" name="user[site_attributes][site_source_attributes][source_7]"
                            value="" type="hidden">
                            <input id="source_8" name="user[site_attributes][site_source_attributes][source_8]"
                            value="" type="hidden">
                            <input id="source_9" name="user[site_attributes][site_source_attributes][source_9]"
                            value="" type="hidden">
                            <input id="source_10" name="user[site_attributes][site_source_attributes][source_10]"
                            value="" type="hidden">
                            <!-- STEP 1 -->
                            <fieldset id="step1" class="step">
                                <h3 style="font-size: 16px;">
                                    Register for your
                                    <i>
                                        free
                                    </i>
                                    Desk.com trial.
                                </h3>
                                <hr>
                                <div class="form-group">
                                    <label for="user-name" class="placeholder">
                                        User Name
                                    </label>
                                    <input type="text" class="form-control input-lg required" id="user-name"
                                    name="user[name]" maxlength="50" value="" placeholder="User Name" data-placement="top"
                                    data-title="Enter your first and last name." data-error-title="You must enter your full name.">
                                </div>
                                <div class="form-group">
                                    <label for="user-email" class="placeholder">
                                        Password
                                    </label>
                                    <input type="password" class="form-control input-lg required" id="Password"
                                    name="user[email]" maxlength="50"
                                    value="" placeholder="Password" data-placement="top"
                                    data-title="Enter your email address." data-error-title="You must provide a valid email address.">
                                </div>
                                <div class="form-group">
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                        Tel
                                    </label>
                                    <input type="text" class="form-control input-lg" id="tel"
                                    name="user[site_attributes][contact_phone]"
                                    maxlength="30" value=""
                                    placeholder="Tel"
                                    data-placement="top" data-title="Enter your phone number." data-error-title="">
                                </div>
                                <div class="form-group">
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                      ssid
                                    </label>
                                    <input type="text" class="form-control input-lg" id="ssid"
                                    name="ssid"
                                    maxlength="30" value=""
                                    placeholder="ssid"
                                    data-placement="top" data-title="Enter your phone number." data-error-title="">
                                </div>
                                <div class="form-group">
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                        Wifi Password
                                    </label>
                                    <input type="password" class="form-control input-lg" id="wifiPassword"
                                    name="user[site_attributes][contact_phone]"
                                    maxlength="30" value=""
                                    placeholder="Wifi Password"
                                    data-placement="top" data-title="Enter your phone number." data-error-title="">
                                </div>
                                <div class="form-group">
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                      Name
                                    </label>
                                    <input id="name" type="text" class="form-control input-lg" id="user_site_attributes_contact_phone"
                                    name="user[site_attributes][contact_phone]"
                                    maxlength="30" value=""
                                    placeholder="Name"
                                    data-placement="top" data-title="Enter your phone number." data-error-title="">
                                </div>
                                <div class="form-group">
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                      Name
                                    </label>
                                    <select id="industry" multiple="multiple">
                                      <option value="0">Household Electrical</option>
                                      <option value="1">IT</option>
                                      <option value="2">Daily Commodies</option>
                                      <option value="3">Recreation</option>
                                      <option value="4">Gift Accessories</option>
                                      <option value="5">Restaurant</option>
                                      <option value="6">Clothing and Shoes</option>
                                    </select>
                                </div>
                                <div class="form-group input-group">
                                  <span class="input-group-btn">
                                    <span class="btn btn-primary btn-file">
                                      Browse… <input type="file" id="imgInp" placeholder="Image">
                                    </span>
                                  </span>
                                  <img id="blah" src="#" alt="your image" />
                                </div>

                                <div >
                                    <label for="user_site_attributes_contact_phone" class="placeholder">
                                      Location
                                    </label>
                                    <input id="location" type="text" class="form-control input-lg" id="user_site_attributes_contact_phone"
                                    name="user[site_attributes][contact_phone]"
                                    maxlength="30" value=""
                                    placeholder="Location"
                                    data-placement="top"
                                    data-title="Enter your phone
                                    number." data-error-title="">
                                </div>
<div id="error-url" style="display: none;">Please input more specific location</div>
                                <div class="submit-group" id='start'>
                                    <a href="#step2" class="btn btn-primary btn-lg next" id="form_register_step1">
                                        Start your cheap Ad.
                                    </a>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
                <!-- .row -->
                <div class="row">
                    <div id="footer" class="col-md-12 text-center" style="margin-top:80px">
                        <hr>
                        <span class="copyright">
                            &copy;2015 freewifi.com, inc. All Rights reserved.
                            <a href="http://www.desk.com/terms">
                                Terms of Service
                            </a>
                            &amp;
                            <a href="http://www.desk.com/privacy">
                                Privacy Policy
                            </a>
                        </span>
                        <br>
                        <span style="font-size:12px;color:#777;font-style:italic;">
                            Cheap Ad with Free Wifi.
                        </span>
                    </div>
                </div>
            </section>
        </div>
        <!-- .container -->
        <!-- #regform -->
        <!-- #all -->
        <!-- JS -->


    </body>

</html>
