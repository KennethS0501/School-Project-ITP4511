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
        window.onload = function () {
            form.addEventListener('submit', e => {

                let newp = document.getElementById("inputPwd").value;
                let oldp = document.getElementById("inputCofirmPwd").value;
                if (newp != oldp) {
                    event.preventDefault();
                    alert("old and new password must same")
                    return flase;
                }
            });
        };
    </script>
    <body>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 27px;background: #cccccc;">
            <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="height: 25px;">
                    <ul class="nav navbar-nav mr-auto">
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul><span class="navbar-text actions"> <a class="login" href="Login.jsp">Login</a><a class="btn btn-light action-button" role="button" href="Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 64px;margin-bottom: 2px;">Register</a></span></div>
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
                </div><button class="btn btn-primary" data-toggle="modal" data-target="#modal1" type="button">Booking Cart</button></div>
        </nav>
        <div class="login-card">
            <form class="form-signin" method="post" action="Register" id="form">
                <span class="reauth-email"> </span>
                <input class="form-control" type="name" name="name" id="inputName" required="" placeholder="Name" autofocus="">
                <input class="form-control" type="email" name="email"inputEmail" required="" placeholder="Email address" autofocus="">
                <input class="form-control" type="password" name="password"id="inputPwd" required="" placeholder="Password" autofocus="">
                <input class="form-control" type="password"  name="Oldpassword" id="inputCofirmPwd" required="" placeholder="Confirm Password">
                <div class="checkbox"></div><button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Register</button>
            </form>
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