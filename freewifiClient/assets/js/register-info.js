
function sendRegisterInfo(){
	var radio_male = document.getElementById("radio_male");
	var inputBirthday = document.getElementById("inputBirthday");
	var inputCellphone = document.getElementById("inputCellphone");
	var btn_education = document.getElementById("btn_education");
	var btn_income = document.getElementById("btn_income");

	var gender;
	if(radio_male.checked == true){
		gender = "��";
	}else{
		gender = "Ů";
	}
	var birthday = inputBirthday.value.trim();
	var tel = inputCellphone.value.trim();
	var education = btn_education.innerText.trim();
	var income = btn_income.innerText.trim();
	//����
	alert("gender:" + gender + " birthday:" + birthday + " tel:" + tel + " education:" + education + " income:" + income);
//	document.body.innerHTML = "�ύ��..."��//���д������л�ʹ��������ʧЧ
	//js����java���������ø�ʽwindow.objectName.functionName()
	window.javaSender.sendRegInfo(gender, birthday, tel, education, income);
}

$('.dropdown-toggle').dropdown();

$('#ul_education li').click(function() {
	$('#btn_education').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});

$('#ul_income li').click(function() {
	$('#btn_income').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});