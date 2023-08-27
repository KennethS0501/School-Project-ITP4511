
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
  <title>ITP4511</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/nav1.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/nav2-change.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/styles.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script type="text/javascript" src="https://davidlynch.org/projects/maphilight/jquery.maphilight.min.js"></script>
  <script type="text/javascript">
    $(function() {
      $('.map').maphilight({
        fill: true,
        fillColor: '92b3e8',
        fillOpacity: 0.2,
        stroke: true,
        strokeColor: '92b3e8',
        strokeOpacity: 1,
        strokeWidth: 1,
        fade: true,
        alwaysOn: true,
        neverOn: false,
        groupBy: false,
        wrapClass: true,
        shadow: true,
        shadowX: 1,
        shadowY: 1,
        shadowRadius: 6,
        shadowColor: '000000',
        shadowOpacity: 0.8,
        shadowPosition: 'outside',
        shadowFrom: false
      });



      $('area').mouseenter(function() {
        let area_title = $(this).attr('title');
        console.log(area_title);

        //$('#map_title_' + area_title).css('-webkit-text-stroke', '1px black');
        $('#map_title_' + area_title).animate({
          "top": "-=4px"
        });
      });

      $('area').mouseleave(function() {
        let area_title = $(this).attr('title');
        //$('#map_title_' + area_title).css('-webkit-text-stroke', '1px red');
        $('#map_title_' + area_title).animate({
          "top": "+=4px"
        });
      }).mouseleave();


      $('area').each(function() {
        var txt = $(this).attr('name');
        var title = $(this).attr('title');
        var coor = $(this).attr('coords');
        var coorA = coor.split(',');

        var left = parseInt(coorA[0]) + ((parseInt(coorA[2]) - parseInt(coorA[0])) / 2);
        var top = parseInt(coorA[1]) + ((parseInt(coorA[3]) - parseInt(coorA[1])) / 2)

        left += 800;
        top += 70;

        var $span = $('<span class="map_title" id="map_title_' + title + '">' + txt + '</span>');
        $span.css({
          top: top + 'px',
          left: left + 'px',
          position: 'absolute',
        });
        $span.appendTo('.content');
        $span.css({
          left: (left - Math.ceil($span.width() / 2) + 9) + 'px'
        })

      })

    });

  </script>
  <style type="text/css">
    .map_title {
      color: red;
      font-size: 26px;
      -webkit-text-stroke: 1px red;
      /* width and color */
    }

  </style>

</head>



<body>
    <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 27px;background: #cccccc;">
        <div class="container"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1" style="height: 25px;">
                <ul class="nav navbar-nav mr-auto">
                    <li class="nav-item"></li>
                    <li class="nav-item"></li>
                </ul><span class="navbar-text actions"> <a class="login" href="<%=request.getContextPath() %>/Login.jsp">Login</a><a class="btn btn-light action-button" role="button" href="<%=request.getContextPath() %>/Register.jsp" style="font-size: 14px;padding: 2px;height: 26px;width: 64px;margin-bottom: 2px;">Register</a></span></div>
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
  <div class="d-flex justify-content-center">
    <h1>Venue Select</h1>
    <div class="content">
      <img class="map" src="assets/img/favpng_hong-kong-vector-map-royalty-free.png" usemap="#features">
    </div>
    <map name="features">
      <area target="" alt="TuenMun" name="Tuen Mun" title="TuenMun" href="<%=request.getContextPath() %>/Venue.jsp" coords="69,189,55,203,78,255,172,259,210,214,171,212,158,188,96,181" shape="poly">
      <area target="" alt="ShaTin" name="Sha Tin" title="ShaTin" href="<%=request.getContextPath() %>/Venue.jsp" coords="333,189,357,207,329,258,297,223" shape="poly">
      <area target="" alt="TsingYi" name="Tsing Yi" title="TsingYi" href="<%=request.getContextPath() %>/Venue.jsp" coords="217,254,226,285,250,284,245,251" shape="poly">
      <area target="" alt="ChaiWan" name="Chai Wan" title="ChaiWan" href="<%=request.getContextPath() %>/Venue.jsp" coords="362,339,370,349,364,358,354,351" shape="poly">
      <area target="" alt="LiHuili" name="Li Huili" title="LiHuili" href="<%=request.getContextPath() %>/Venue.jsp" coords="371,297,389,297,386,315,373,316" shape="poly">
    </map>
  </div>
    <%@include file="footer.jsp" %>
</body>

</html>
