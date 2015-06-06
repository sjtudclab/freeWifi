
var radio_male = document.getElementById("radio_male");
var radio_engage = document.getElementById("radio_engage");
var inputBirthday = document.getElementById("inputBirthday");
var inputCellphone = document.getElementById("inputCellphone");
var inputPassword = document.getElementById("inputPassword");
var btn_education = document.getElementById("btn_education");
var btn_income = document.getElementById("btn_income");
var btn_baby = document.getElementById("btn_baby");
var btn_job = document.getElementById("btn_job");

var warningBirthday = document.getElementById("warningBirthday");
var warningTel = document.getElementById("warningTel");

function sendRegisterInfo(){
	//验证格式
	if((blurBirthday() && blurTel()) != true){
		return false;
	}
	//获取表单数据值
	var gender;
	if(radio_male.checked == true){
		gender = "男";
	}else{
		gender = "女";
	}
	var engage;
	if(radio_engage.checked == true){
		engage = "已婚";
	}else{
		engage = "未婚";
	}
	var birthday = inputBirthday.value.trim();
	var tel = inputCellphone.value.trim();
	var pwd = inputPassword.value.trim();
	var education = btn_education.innerText.trim();
	var income = btn_income.innerText.trim();
	var baby = btn_baby.innerText.trim();
	var job = btn_job.innerText.trim();
	//测试
//	alert("gender:" + gender + " birthday:" + birthday + " tel:" + tel + " education:" + education + " income:" + income);
//	document.body.innerHTML = "提交中..."；//此行代码运行会使整个函数失效
	//js调用java方法，调用格式window.objectName.functionName()
	window.javaSender.sendRegInfo(gender, birthday, tel, pwd, education, income, engage, baby, job);
	return true;
}

function focusBirthday(){
}
function blurBirthday(){
	var reg = /^(\d{4})-(\d{2})-(\d{2})$/
	if (!reg.test(inputBirthday.value.trim())) { 
		warningBirthday.style.display = "inline";
		return false;
	} else{
		warningBirthday.style.display = "none";
		return true;
	}
}

function focusTel(){
}
function blurTel(){
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	if (!reg.test(inputCellphone.value.trim())) { 
		warningTel.style.display = "inline";
		return false;
	} else{
		warningTel.style.display = "none";
		return true;
	}
}

$('.dropdown-toggle').dropdown();

$('#ul_education li').click(function() {
	$('#btn_education').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});

$('#ul_income li').click(function() {
	$('#btn_income').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});

$('#ul_baby li').click(function() {
	$('#btn_baby').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});

$('#ul_job li').click(function() {
	$('#btn_job').html(this.children[0].text+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>');
});
