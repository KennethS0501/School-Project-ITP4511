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
                        <div class="col-lg-4 col-xl-12" style="margin-left: 99px;">
                            <div class="list-group">
                                <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../Account.jsp';" style="width: 250px;height: 49px;">Account Details</a>
                                <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = '../BookingFunction/BookingManagement.jsp';" style="width: 250px;">Booking Management</a>
                                <a class="list-group-item list-group-item-action" data-toggle="list" onclick="window.location.href = 'VenueManagement.jsp';" style="width: 250px;">Venue Management</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-xl-8" style="padding-left: 43px;">
                    <div style="text-align:center;">
                        <h2 class="divider-style" style="margin-top: -1px;"><span>Venue Management<br></span></h2>
                    </div>
                    <div class="card">
                        <div class="card-body" style="margin-top: 10px;border-style: solid;border-color: #dfe8ee;">
                            <h4 class="card-title"><strong>Add Venue</strong><br></h4>
                            <h6 class="text-muted card-subtitle mb-2"><strong>Add Venue</strong><br></h6>
                            <ul class="list-group"></ul>
                            <button class="btn btn-primary" onclick="window.location.href = 'AddVenue.jsp';"><strong>Go</strong><br></button>
                        </div>
                        <div class="card-body" style="margin-top: 10px;border-style: solid;border-color: #dfe8ee;">
                            <h4 class="card-title"><strong>Edit Venue</strong><br></h4>
                            <h6 class="text-muted card-subtitle mb-2"><strong>Edit Venue</strong><br></h6>
                            <ul class="list-group"></ul>
                            <button class="btn btn-primary" onclick="window.location.href = 'EditVenue.jsp';"><strong>Go</strong><br></button>
                        </div>
                        <div class="card-body" style="margin-top: 10px;border-style: solid;border-color: #dfe8ee;">
                            <h4 class="card-title"><strong>Delete Venue</strong><br></h4>
                            <h6 class="text-muted card-subtitle mb-2"><strong>Delete Venue</strong><br></h6>
                            <ul class="list-group"></ul>
                            <button class="btn btn-primary" onclick="window.location.href = 'DeleteVenue.jsp';"><strong>Go</strong><br></button>
                        </div>
                        <div class="card-body" style="margin-top: 10px;border-style: solid;border-color: #dfe8ee;">
                            <h4 class="card-title"><strong>Enable or Disable listing of venue on booking system</strong><br></h4>
                            <h6 class="text-muted card-subtitle mb-2"><strong>Enable or Disable listing of venue on booking system</strong><br></h6>
                            <ul class="list-group"></ul>
                            <button class="btn btn-primary" onclick="window.location.href = 'EnableOrDisableVenue.jsp';"><strong>Go</strong><br></button>
                        </div>
                    </div>
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