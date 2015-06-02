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
            isLaunch: isLaunch
        }
        $.ajax({
            url: 'save',
            method: 'post',
            data: data
        }).done(function(data) {
            console.log('done');
        }).fail(function(){
            console.log('error');
        })
        
    });
});
