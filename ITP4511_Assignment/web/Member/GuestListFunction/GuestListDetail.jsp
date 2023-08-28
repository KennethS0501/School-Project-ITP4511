<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/guestLists.tld" prefix="guestList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>ITP4511</title>
        <link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700">
        <link rel="stylesheet" href="../../assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="../../assets/css/Community-ChatComments.css">
        <link rel="stylesheet" href="../../assets/css/dash-1.css">
        <link rel="stylesheet" href="../../assets/css/Google-Style-Login.css">
        <link rel="stylesheet" href="../../assets/css/nav1.css">
        <link rel="stylesheet" href="../../assets/css/nav2-change.css">
        <link rel="stylesheet" href="../../assets/css/Product-Details.css">
        <link rel="stylesheet" href="../../assets/css/select.css">
        <link rel="stylesheet" href="../../assets/css/styles.css">
        <link rel="stylesheet" href="../../assets/css/Table-with-search.css">
    </head>

    <body>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 27px;background: #cccccc;">
            <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="height: 25px;">
                    <ul class="nav navbar-nav mr-auto">
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul><span class="navbar-text actions"> <a class="login" href="../../Login.jsp">Login</a><a class="btn btn-light action-button" role="button" href="../../Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 64px;margin-bottom: 2px;">Register</a></span></div>
            </div>
        </nav>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
            <div class="container"><a class="navbar-brand" href="../../index.jsp" style="font-size: 24px;"><strong>Event Point Limited</strong></a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div
                    class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="../../index.jsp">Venue Select</a></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul>
                </div><button class="btn btn-primary" data-toggle="modal" data-target="#modal1" type="button">Booking Cart</button></div>
        </nav>
        <div>
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-xl-4">
                        <div class="row">
                            <div class="col-lg-4 col-xl-12" style="margin-left: 99px;width: 356px;">
                                <div class="list-group">
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../Account.jsp';" style="width: 250px;height: 49px;">Account Details</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../Reminder.jsp';"  style="width: 250px;">Reminder</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../BookingRecordFunction/BookingRecordManagement.jsp';"  style="width: 250px;">Booking Records</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'GuestListManagement.jsp';"  style="width: 250px;">Guest List Management</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../CommentSelectBooking.jsp';" style="width: 250px;">Comment</a></div>            
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-xl-8" style="padding-left: 43px;">
                        <div style="text-align:center;">
                            <h2 class="divider-style" style="margin-top: -1px;"><span>Guest List Detail<br></span></h2>
                        </div><dl class="row">
                            <dt class="col-sm-0">Guest List Id:</dt>
                            <dd class="col-sm-10">${param.id}</dd>
                        </dl><div class="form-group pull-right">
                            <input type="text" class="search form-control" placeholder="What you looking for?">
                        </div>
                        <span class="counter pull-right"></span>
                        <table class="table table-hover table-bordered results">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                </tr>
                                <tr class="warning no-result">
                                    <td colspan="4"><i class="fa fa-warning"></i> No result</td>
                                </tr>
                            </thead>
                            <tbody>
                                <guestList:showGuestList memberId="${sessionScope.userInfo.getId()}" tagType="ShowDetailByTable"  guestListIndex="${param.id}" />
                            </tbody>
                        </table>
                        <h2 class="divider-style" style="margin-top: -1px;text-align: center;"><span>Add Guest<br></span></h2>
                        <form action="GuestListDetail" method="get">
                            <input type="hidden" name="action" value="AddGuest">
                            <input type="hidden" name="guestListIndex" value="${param.id}">
                            <input type="hidden" name="memberId" value="${sessionScope.userInfo.getId()}">
                            <input class="form-control" type="text" style="width: 315px;" placeholder="Name" name="name">
                            <input class="form-control" type="text" placeholder="Email" style="margin-top: 10px;width: 315px;" name="email">
                            <button class="btn btn-primary" type="submit" style="margin-top: 10px;">Add</button>
                        </form>
                        <h2 class="divider-style" style="margin-top: -1px;text-align: center;"><span>Delete Guest<br></span></h2>
                        <form action="GuestListDetail" method="get">
                            <input type="hidden" name="action" value="DeleteGuest">
                            <input type="hidden" name="guestListIndex" value="${param.id}">
                            <input type="hidden" name="memberId" value="${sessionScope.userInfo.getId()}">
                            <input class="form-control" type="number" style="width: 315px;" placeholder="Id" name="id">
                            <button class="btn btn-primary" type="submit" style="margin-top: 10px;">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../../footer.jsp" %>
        <script src="../../assets/js/jquery.min.js"></script>
        <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../assets/js/Gruntfile.js"></script>
        <script src="../../assets/js/jquery-3.6.4.min.js"></script>
        <script src="../../assets/js/jquery.maphilight.min.js"></script>
        <script src="../../assets/js/Table-with-search.js"></script>
    </body>

</html>