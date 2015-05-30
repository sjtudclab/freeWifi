
$('#location').blur(function() {
    var address = $('#location').val();
    var url = 'http://api.map.baidu.com/geocoder/v2/?address=' + address
        + '&output=json&ak=fr2k1GxnZbBxXalKYdcQUNBM';
    $.ajax({
        url: url,
        dataType: 'jsonp',
        success: function(data) {
            if (data.status ==  0) {
                address = data.result.location;
                $('#location').parent().removeClass('has-error');
                $('#error-url').hide();
                window.longitude = address.lng;
                window.latitude = address.lat;
                console.log(address.lng + "  " + address.lat);
            } else {
                $('#location').parent().addClass('has-error');
                $('#error-url').show();
            }
        }
    });
});


$('#start').click(function() {
    var data = {
        loginname: $('#user-name').val(),
        password:  $('#Password').val(),
        tel: $('#tel').val(),
        name: $('#name').val(),
        address:  $('#location').val(),
        ssid: $('#ssid').val(),
        wifiPassword: $('#wifiPassword').val(),
        longitude: longitude,
        latitude: latitude
    }
    $.ajax({
        url: 'merchant/register',
        method: 'post',
        data: data
    }).done(function(data) {
        console.log('done');
    }).fail(function(){
        console.log('error');
    })
    
    
});
