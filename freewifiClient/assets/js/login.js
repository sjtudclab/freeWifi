
var inputAccount = document.getElementById("inputAccount");
var inputPassword = document.getElementById("inputPassword");

function sendLoginInfo(){
	//获取表单数据值
	var account = inputAccount.value.trim();
	var password = inputPassword.value.trim();

//js调用java方法，调用格式window.objectName.functionName()
	window.javaSender.sendLoginInfo(account, password);
	return true;
}

function register(){
	window.javaSender.callRegister();
}
