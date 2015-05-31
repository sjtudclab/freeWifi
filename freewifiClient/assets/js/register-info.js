var radio_male = document.getElementById("radio_male");
var inputBirthday = document.getElementById("inputBirthday");
var inputCellphone = document.getElementById("inputCellphone");
var btn_education = document.getElementById("btn_education");
var btn_income = document.getElementById("btn_income");
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
	var birthday = inputBirthday.value.trim();
	var tel = inputCellphone.value.trim();
	var education = btn_education.innerText.trim();
	var income = btn_income.innerText.trim();
	//测试
//	alert("gender:" + gender + " birthday:" + birthday + " tel:" + tel + " education:" + education + " income:" + income);
//	document.body.innerHTML = "提交中..."；//此行代码运行会使整个函数失效
	//js调用java方法，调用格式window.objectName.functionName()
	window.javaSender.sendRegInfo(gender, birthday, tel, education, income);
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