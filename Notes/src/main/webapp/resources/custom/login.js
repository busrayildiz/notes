function loginBtn(){
	var param = {
			userName:$("#username").val(),
			pass:$("#pass").val(),
			
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type:"POST" , 
		contentType:"application/json; charset=UTF-8",
		url:"controlUser",
		data: ser_data,
		success : function(data){
			     if(data=='OK'){
			    	 $(location).attr('href','home')
			     }else if(data=='ERROR'){
				alert("Kullanıcı adı veya şifrenizi kontrol edin");
			}
			
		},error:function(data){
			alert(data);
		}
	});
}