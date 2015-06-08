$(function() {
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
                    console.log(longitude + "  " + latitude);
                } else {
                    $('#location').parent().addClass('has-error');
                    $('#error-url').show();
                }
            }
        });
    });


    $('#start').click(function() {
        var industry = '';
        $('#industry option:selected').each(function() {
            industry += $(this).val() + ',';
        });
        if (industry) {
            industry = industry.substr(0, industry.length - 1);
        }
        var data = {
            loginname: $('#user-name').val(),
            password:  $('#Password').val(),
            tel: $('#tel').val(),
            name: $('#name').val(),
            address:  $('#location').val(),
            ssid: $('#ssid').val(),
            wifiPassword: $('#wifiPassword').val(),
            longitude: longitude,
            latitude: latitude,
            icon: $('#blah').attr('src'),
            business: industry,
        }
        $.ajax({
            url: '/freewifiserver/register',
            method: 'post',
            data: data
        }).done(function(data) {
            document.location.href = '/freewifiserver/login'
        }).fail(function(){
            document.location.href = '/freewifiserver/login'
            console.log('error');
        });
        
        
    });

    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }
    $('#blah').hide();
    $("#imgInp").change(function(){
        readURL(this);
        $('#blah').width(42);
        $('#blah').height(42);
        $('#blah').show();
    });


    $('#industry').multiselect({
        nonSelectedText: 'industry'
    });
    
    $('.multiselect').removeClass('btn-default');
    $('.multiselect').addClass('btn-primary btn-file');
});
