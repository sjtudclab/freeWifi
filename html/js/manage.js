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
                    $(a).text(ad.startDate + '--' + ad.endDate + '    ' + ad.startHour + '--' + ad.endHour);
                    var aEdit = document.createElement('a');
                    $(aEdit).addClass('btn btn-primary btn-mini');
                    $(aEdit).text('Edit');
                    var aPublish = document.createElement('a');
                    $(aPublish).addClass('btn btn-success btn-mini');
                    $(aPublish).text('Publish');
                    var aUnpublish = document.createElement('a');
                    $(aUnpublish).addClass('btn btn-success btn-mini');
                    $(aUnpublish).text('Unpublish');
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
