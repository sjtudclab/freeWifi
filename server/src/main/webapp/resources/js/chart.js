$(function() {
    var id = getUrlParameter('id');
    var colors = ["220,220,220", "151,187,205", "151,111,147", "151,111,52", "151,169,52", "23,169,52"];

    function report() {
        var type = $( "#show option:selected" ).text();
        $.ajax({
            url: "/freewifiserver/ad/stats/" + id,
            method: 'GET',
            data: {type: type},
        }).done(function(re) {
            var ctx = $("#myChart").get(0).getContext("2d");
            var data = {};
            var labels = [];
            var datasets = [];
            var data = re.data;
            for (var i in data) {
                var item = data[i];
                labels.push(item.orientation)
                var obj = {};
                obj.fillColor = 'rgba(' + colors[i] + ', 0.5)';
                obj.strokeColor = 'rgba(' + colors[i] + ', 1)';
	        obj.data = [item.click, item.push, item.impression];
                datasets.push(obj);
                
            }
            data.labels = labels;
            data.datasets = datasets;
            var myNewChart = new Chart(ctx).Bar(data);
        })
    }

    $('#show').change(function() {
        report();
    });
    if (id) {
        report();
    }
});
