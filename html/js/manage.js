$(function() {
    function getADs() {
        $.ajax({
            url: "ad/get",
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
                    var startHour = ad.startHour.substr(0, 2) + ':' + ad.startHour.substr(2, 4) +
                        ':' + ad.startHour.substr(4, 6);
                    var endHour = ad.endHour.substr(0, 2) + ':' + ad.endHour.substr(2, 4) +
                        ':' + ad.endHour.substr(4, 6);
                    $(a).text(ad.startDate + '--' + ad.endDate + '    ' + startHour + '--' + endHour);
                    var aEdit = document.createElement('a');
                    $(aEdit).addClass('btn btn-primary btn-mini');
                    $(aEdit).text('Edit');
                    var aPublish = document.createElement('a');
                    $(aPublish).addClass('btn btn-success btn-mini');
                    $(aPublish).text('Publish');
                    $(aPublish).click(function(){
                        $.ajax({
                            url: "ad/publish",
                            method: 'GET',
                            data: ad.id,
                        }).done(function( data ) {
                            if (data.code == -1) {
                                console.log('error');
                            } else {
                                console.log('succeed');
                            }
                        });
                    });
                    var aUnpublish = document.createElement('a');
                    $(aUnpublish).addClass('btn btn-success btn-mini');
                    $(aUnpublish).text('Unpublish');
                    $(aUnpublish).click(function(){
                        $.ajax({
                            url: "ad/unpublish",
                            method: 'GET',
                            data: ad.id,
                        }).done(function( data ) {
                            if (data.code == -1) {
                                console.log('error');
                            } else {
                                console.log('succeed');
                            }
                        })                        
                    });
                    var aPreview = document.createElement('a');
                    $(aPreview).addClass('btn btn-success btn-mini');
                    $(aPreview).text('Preview');
                    var aDelete = document.createElement('a');
                    $(aDelete).addClass('btn btn-danger btn-mini');
                    $(aDelete).text('Delete');
                    $(aDelete).click(function() {
                        $.ajax({
                            url: "ad/delete",
                            method: 'GET',
                            data: ad.id,
                        }).done(function( data ) {
                            if (data.code == -1) {
                                console.log('error');
                            } else {
                                $('#recent-posts').empty();
                                getADs();
                            }
                        })                        
                    });
                    $(li).append(div);
                    $(div).append(span);
                    $(div).append(p);

                    $(p).append(a);
                    $(div).append('<a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a> <a href="#" class="btn btn-success btn-mini">Unpublish</a>  <a href="#" class="btn btn-success btn-mini">Preview</a> <a href="#" class="btn btn-danger btn-mini">Delete</a>');
                    $('#recent-posts').append(li);
                    // $(div).append(aEdit);
                    // $(div).append(aPublish);
                    // $(div).append(aUnpublish);
                    // $(div).append(aPreview);
                    // $(div).append(aDelete);
                    
                }
            } else {
                console.log('error');
            }
        });
    }
    getADs();
});
