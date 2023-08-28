<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/venues.tld" prefix="venue" %>
<%@taglib uri="/WEB-INF/tlds/guestLists.tld" prefix="guestList" %>
<%@taglib uri="/WEB-INF/tlds/notiTemplates.tld" prefix="notiTemplates" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>ITP4511</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/Community-ChatComments.css">
        <link rel="stylesheet" href="assets/css/dash-1.css">
        <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
        <link rel="stylesheet" href="assets/css/nav1.css">
        <link rel="stylesheet" href="assets/css/nav2-change.css">
        <link rel="stylesheet" href="assets/css/Product-Details.css">
        <link rel="stylesheet" href="assets/css/select.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/Table-with-search.css">
    </head>
    <script>

    </script>
    <body>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 27px;background: #cccccc;">
            <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="height: 25px;">
                    <ul class="nav navbar-nav mr-auto">
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul><span class="navbar-text actions"> 
                        <c:choose>
                            <c:when test="${not empty sessionScope.userInfo.getId()}">
                                <a class="btn btn-light action-button" role="button" href="Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 90px;margin-bottom: 2px;">My Account</a>
                            </c:when>    
                            <c:otherwise>
                                <a class="login" href="Login.jsp">Login</a>
                                <a class="btn btn-light action-button" role="button" href="Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 64px;margin-bottom: 2px;">Register</a>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
            </div>
        </nav>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
            <div class="container"><a class="navbar-brand" href="index.jsp" style="font-size: 24px;"><strong>Event Point Limited</strong></a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div
                    class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="index.jsp">Venue Select</a></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul>
                </div>
                <c:if test="${not empty sessionScope.userInfo.getId()}">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#modal1" type="button">Booking Cart</button>
                </c:if>

            </div>
        </nav>
        <div class="container"><h1 class="text-center">Venue Details</h1>
            <div class="row">
                <div class="col-md-7">
                    <div class="row">
                        <div class="col-md-12"><img class="img-thumbnail img-fluid center-block" src="assets/img/videomicme.png"></div>
                    </div>
                </div>
                <div class="col-md-5">
                    <h1></h1><dl class="row">
                        <dt class="col-sm-12">Name</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="name" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Type</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="type" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Capacity</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="capacity" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Location</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="location" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Description</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="description" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Person-In-Charge</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="person_in_charge" venueId="${param.venueId}" /></dd>

                        <dt class="col-sm-12">Booking Fee</dt>
                        <dd class="col-sm-12"><venue:showVenue tagType="booking_fee" venueId="${param.venueId}" /></dd>
                    </dl>
                    <c:if test="${not empty sessionScope.userInfo.getId()}">
                        <form action="BookingCart" method="post">
                            <input type="hidden" name="action" value="addToCart" required>
                            <input type="hidden" name="id_venue" value="${param.venueId}" required>
                            <input type="hidden" name="memberId" value="${sessionScope.userInfo.getId()}" required>
                            <label>Booking Date</label><input type="date" name="booking_date" required><br/>
                            <label>Start Time</label><input type="time" name="start_time" required><br/>
                            <label>End Time</label><input type="time" name="end_time" required><br/>
                            <label>Guest List</label>
                            <select name="guest_list" required>
                                <guestList:showGuestList memberId="${sessionScope.userInfo.getId()}" tagType="ShowNameByOption" />
                            </select>
                            <br/>
                            <label>Notification Template</label>                        
                            <select name="notification_template" required>
                                <notiTemplates:showNotiTemplates tagType="showByOption" />
                            </select>
                            <br/>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#modal1" type="submit">Add to cart</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/Gruntfile.js"></script>
        <script src="assets/js/jquery-3.6.4.min.js"></script>
        <script src="assets/js/jquery.maphilight.min.js"></script>
        <script src="assets/js/Table-with-search.js"></script>
    </body>

</html>