$('.dropdown-toggle').dropdown();

$('#ul_education li').click(function() {
	$('#btn_education').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});

$('#ul_income li').click(function() {
	$('#btn_income').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});