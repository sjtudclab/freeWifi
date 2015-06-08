$(function() {
    function createPublish(i) {
        var aPublish = document.createElement('a');
        $(aPublish).addClass('btn btn-success btn-mini');
        $(aPublish).text('Publish');
        $(aPublish).click(function(){
            $.ajax({
                url: "/freewifiserver/ad/publish",
                method: 'GET',
                data: {id:i},
            }).done(function( data ) {
                if (data.code == -1) {
                    console.log('error');
                } else {
                	bootbox.alert("Publish Successfully!", function() {
                        console.log("Publish Successfully");
                    });
                    console.log('succeed');
                }
            });
        });
        return aPublish;
    }

    function createUnpublish(i) {
        var aUnpublish = document.createElement('a');
        $(aUnpublish).addClass('btn btn-success btn-mini');
        $(aUnpublish).text('Unpublish');
        $(aUnpublish).click(function(){
            $.ajax({
                url: "/freewifiserver/ad/unpublish",
                method: 'GET',
                data: {id: i},
            }).done(function( data ) {
                if (data.code == -1) {
                    console.log('error');
                } else {
                    bootbox.alert("unpublish Successfully!", function() {
                        console.log("unpublish Successfully");
                    });
                    console.log('succeed');
                }
            })                        
        });
        return aUnpublish;
    }
    
    function createPreview(i) {
        var aPreview = document.createElement('a');
        $(aPreview).addClass('btn btn-success btn-mini');
        $(aPreview).text('Preview');
        $(aPreview).click(function(){
            location.href = '/freewifiserver/ad/mobile?id=' + i;
        });
        return aPreview;
    }

    function createReport(i) {
        var aReport = document.createElement('a');
        $(aReport).addClass('btn btn-success btn-mini');
        $(aReport).text('Report');
        $(aReport).click(function(){
            location.href = '/freewifiserver/ad/stats/detail?id=' + i;
        });
        return aReport;
    }

    function createDelete(i) {
        var aDelete = document.createElement('a');
        $(aDelete).addClass('btn btn-danger btn-mini');
        $(aDelete).text('Delete');
        $(aDelete).click(function() {
            $.ajax({
                url: "/freewifiserver/ad/delete",
                method: 'GET',
                data: {id: i},
            }).done(function( data ) {
                if (data.code == -1) {
                    console.log('error');
                } else {
                    $('#recent-posts').empty();
                    getADs();
                }
            })                 
        });
        return aDelete;
    }

    function getADs() {
        $.ajax({
            url: "/freewifiserver/ad/get",
        }).done(function( data ) {
            if (data.code == 0) {
                var adList = data.data;
                for (var i in adList) {
                    var ad = adList[i];
                    var li = document.createElement('li');
                    var div = document.createElement('div');
                    $(div).addClass('article-post');
                    var span = document.createElement('span');
                    $(span).addClass('user-info');
                    $(span).text(ad.name + ' Add date:' + ad.addDate)
                    var p = document.createElement('p');
                    var a = document.createElement('a');
                    var startHour = ad.startHour.toString();
                    if (startHour.length == 5) {
                        startHour = '0' + startHour;
                    }
                    startHour = startHour.substr(0, 2) + ':' + startHour.substr(2, 2) +
                        ':' + startHour.substr(4, 2);
                    var endHour = ad.endHour.toString();
                    if (endHour.length == 5) {
                        endHour = '0' + endHour;
                    }
                    endHour = endHour.substr(0, 2) + ':' + endHour.substr(2, 2) +
                        ':' + endHour.substr(4, 2);
                    $(a).text(ad.startDate + '--' + ad.endDate + '    ' + startHour + '--' + endHour);
                    // var aEdit = document.createElement('a');
                    // $(aEdit).addClass('btn btn-primary btn-mini');
                    // $(aEdit).text('Edit');
                    // $(aEdit).click(function(){
                    //     location.href = document.location.hostname + '?id=' + ad.id;
                    // });
                    var aPublish = createPublish(ad.id);
                    var aUnpublish = createUnpublish(ad.id);
                    var aPreview = createPreview(ad.id);
                    var aReport = createReport(ad.id);
                    var aDelete = createDelete(ad.id);
                    $(li).append(div);
                    $(div).append(span);
                    $(div).append(p);

                    $(p).append(a);
                    
                    $('#recent-posts').append(li);
                    // $(div).append(aEdit);
                    $(div).append(aPublish);
                    $(div).append(aUnpublish);
                    $(div).append(aPreview);
                    $(div).append(aReport);
                    $(div).append(aDelete);
                    
                }
            } else {
                console.log('error');
            }
        });
    }
    getADs();
});
