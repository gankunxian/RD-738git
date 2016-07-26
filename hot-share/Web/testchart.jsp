<html>
<head>
<title>My first chart using FusionCharts Suite XT</title>
<script type="text/javascript" src="${basePath}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${basePath}/js/fusioncharts/themes/fusioncharts.theme.zune.js"></script>
<script type="text/javascript">
FusionCharts.ready(function(){
    var revenueChart = new FusionCharts({
      type: "pie3d",
      renderAt: "chartContainer",
      width: "500",
      height: "300",
      dataFormat: "json",
      dataSource: {
       "chart": {
          "caption": "Sales Per Employee for 2014",
	       "palette": "2",
	       "animation": "1",
	       "formatnumberscale": "1",
	       "decimals": "0",
	       "numberprefix": "$",
	       "pieslicedepth": "30",
	       "startingangle": "125",
	       "showBorder": "0"
       },
       "data": [
          {
            "label": "Leverling",
            "value": "100524",
            "issliced": "1"
        },
        {
            "label": "Fuller",
            "value": "87790",
            "issliced": "1"
        },
        {
            "label": "Davolio",
            "value": "81898",
            "issliced": "0"
        },
        {
            "label": "Peacock",
            "value": "76438",
            "issliced": "0"
        },
        {
            "label": "King",
            "value": "57430",
            "issliced": "0"
        },
        {
            "label": "Callahan",
            "value": "55091",
            "issliced": "0"
        },
        {
            "label": "Dodsworth",
            "value": "43962",
            "issliced": "0"
        },
        {
            "label": "Suyama",
            "value": "22474",
            "issliced": "0"
        },
        {
            "label": "Buchanan",
            "value": "21637",
            "issliced": "0"
        }
        ]
      }
 
  });
  revenueChart.render("chartContainer");
}); 
 
</script>
</head>
<body>
<div id="chartContainer">FusionCharts XT will load here!</div>
</body>
</html>