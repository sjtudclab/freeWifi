$(function() {
    $('#datetimepicker1').datetimepicker({
        language: 'pt-BR'
    });

    $('#datetimepicker2').datetimepicker({
        language: 'pt-BR'
    });

    $('#preview').click(function() {
        $('#modal-body').html( $('#editor').cleanHtml());
    });
    
    $('#adSave').click(function(){
        var data = {
            content: $('#editor').cleanHtml(),
            startDate: $('#startDateTime').val().split(" ")[0],
            startHour: $('#startDateTime').val().split(" ")[1],
            endDate: $('#endDateTime').val().split(" ")[0],
            endHour: $('#endDateTime').val().split(" ")[1],
            name: $('#adName').val()
        }
        $.ajax({
            url: 'ad/save',
            method: 'post',
            data: data
        }).done(function(data) {
            console.log('done');
        }).fail(function(){
            console.log('error');
        })
        
    });
});
