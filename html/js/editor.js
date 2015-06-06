$(function() {
    $('#sex').multiselect({
        nonSelectedText: 'Sex'
    });

    $('#age').multiselect({
        nonSelectedText: 'Age'
    });

    $('#edu').multiselect({
        nonSelectedText: 'Education'
    });
    
    $('#income').multiselect({
        nonSelectedText: 'Income'
    });

    $('#marry').multiselect({
        nonSelectedText: 'Marry'
    });

    $('#child').multiselect({
        nonSelectedText: 'Child'
    });

    $('#career').multiselect({
        nonSelectedText: 'Career'
    });

    $('#datetimepicker1').datetimepicker({
        language: 'pt-BR',
        pickTime: false
    });

    $('#datetimepicker2').datetimepicker({
        language: 'pt-BR',
        pickTime: false
    });

    $('#datetimepicker3').datetimepicker({
        language: 'pt-BR',
        pickDate: false
    });

    $('#datetimepicker4').datetimepicker({
        language: 'pt-BR',
        pickDate: false
    });

    $('#preview').click(function() {
        $('#modal-body').html( $('#editor').cleanHtml());
    });
    
    $('#adSave').click(function(){
        var sex = '';
        $('#sex option:selected').each(function() {
            sex += $(this).val() + ',';
        });
        if (sex) {
            sex = sex.substr(0, sex.length - 1);
        }

        var age = '';
        $('#age option:selected').each(function() {
            age += $(this).val() + ',';
        });
        if (age) {
            age = age.substr(0, age.length - 1);
        }

        var edu = '';
        $('#edu option:selected').each(function() {
            edu += $(this).val() + ',';
        });
        if (edu) {
            edu = edu.substr(0, edu.length - 1);
        }

        var income = '';
        $('#income option:selected').each(function() {
            income += $(this).val() + ',';
        });
        if (income) {
            income = income.substr(0, income.length - 1);
        }        

        var marry = '';
        $('#marry option:selected').each(function() {
            marry += $(this).val() + ',';
        });
        if (marry) {
            marry = marry.substr(0, marry.length - 1);
        }        

        var child = '';
        $('#child option:selected').each(function() {
            child += $(this).val() + ',';
        });
        if (child) {
            child = child.substr(0, child.length - 1);
        }        

        var career = '';
        $('#career option:selected').each(function() {
            career += $(this).val() + ',';
        });
        if (career) {
            career = career.substr(0, career.length - 1);
        }        

        if ($('#isLaunch').val() == 'on') {
            var isLaunch = true;
        } else {
            var isLaunch = false;
        }
        var data = {
            content: $('#editor').cleanHtml(),
            startDate: $('#startDate').val(),
            startHour: $('#startTime').val(),
            endDate: $('#endDate').val(),
            endHour: $('#endTime').val(),
            name: $('#adName').val(),
            sex: sex,
            age: age,
            education: edu,
            income: income,
            marry: marry,
            child: child,
            career: career,
            isLaunch: isLaunch
        }
        $.ajax({
            url: 'add/save',
            method: 'post',
            data: data
        }).done(function(data) {
            location.href = location.host + '/freewifiserver/home';
            console.log('done');
        }).fail(function(){
            console.log('error');
        })
        
    });
    
    var id = getUrlParameter('id');
    if (id) {
        $.ajax({
            url: 'ad/mobile',
            method: 'get',
            data: id,
        }).done(function(data) {
            console.log('done');
            $('#editor').html(data.data);
        }).fail(function(){
            console.log('error');
        })
    }
});
