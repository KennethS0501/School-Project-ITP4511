<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>ITP4511</title>
        <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700">
        <link rel="stylesheet" href="../assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="../assets/css/Community-ChatComments.css">
        <link rel="stylesheet" href="../assets/css/dash-1.css">
        <link rel="stylesheet" href="../assets/css/Google-Style-Login.css">
        <link rel="stylesheet" href="../assets/css/nav1.css">
        <link rel="stylesheet" href="../assets/css/nav2-change.css">
        <link rel="stylesheet" href="../assets/css/Product-Details.css">
        <link rel="stylesheet" href="../assets/css/select.css">
        <link rel="stylesheet" href="../assets/css/styles.css">
        <link rel="stylesheet" href="../assets/css/Table-with-search.css">
    </head>

    <body>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 27px;background: #cccccc;">
            <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="height: 25px;">
                    <ul class="nav navbar-nav mr-auto">
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul><span class="navbar-text actions"> <a class="login" href="../Login.jsp">Login</a><a class="btn btn-light action-button" role="button" href="../Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 64px;margin-bottom: 2px;">Register</a></span></div>
            </div>
        </nav>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-search">
            <div class="container"><a class="navbar-brand" href="<%=request.getContextPath() %>/index.jsp" style="font-size: 24px;"><strong>Event Point Limited</strong></a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div
                    class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/index.jsp">Venue Select</a></li>
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
                            <div class="col-lg-4 col-xl-12" style="margin-left: 99px;">
                                <div class="list-group">
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'Account.jsp';" style="width: 250px;height: 49px;">Account Details</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'Reminder.jsp';"  style="width: 250px;">Reminder</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'BookingRecordFunction/BookingRecordManagement.jsp';"  style="width: 250px;">Booking Records</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'GuestListFunction/GuestListManagement.jsp';"  style="width: 250px;">Guest List Management</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'CommentSelectBooking.jsp';" style="width: 250px;">Comment</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-xl-8" style="padding-left: 43px;">
                        <div style="text-align:center;">
                            <h2 class="divider-style" style="margin-top: -1px;"><span>Account Details<br></span></h2>
                        </div><div class="bootstrap_datatables">
                            <div class="container py-0">
                                <div class="row py-1">
                                    <div class="col-lg-12 mx-auto">
                                        <div class="card rounded shadow border-0">
                                            <div class="card-body p-5 bg-white rounded">
                                                <dl class="row">
                                                    <jsp:useBean id="userInfo" class="ict.bean.User" scope="session"/>
                                                    <dt class="col-sm-2">Name:</dt>
                                                    <dd class="col-sm-9"><jsp:getProperty name="userInfo" property="name" /></dd>

                                                    <dt class="col-sm-2">E-mail:</dt>
                                                    <dd class="col-sm-9"><jsp:getProperty name="userInfo" property="email" /></dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div></div>
                </div>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
        <script src="../assets/js/jquery.min.js"></script>
        <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="../assets/js/Gruntfile.js"></script>
        <script src="../assets/js/jquery-3.6.4.min.js"></script>
        <script src="../assets/js/jquery.maphilight.min.js"></script>
        <script src="../assets/js/Table-with-search.js"></script>
    </body>

</html>