function addUser(){
	var param = {
			userName:$("#userName").val(),
			name:$("#name").val(),
			surname:$("#surname").val(),
			email:$("#email").val(),
			pass:$("#pass").val(),
			pass2:$("#pass2").val()
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type:"POST" , 
		contentType:"application/json; charset=UTF-8",
		url:"addUser",
		data: ser_data,
		success : function(data){
			if(data=='1'){
				alert("passwords don't match");
			}else if(data=='OK'){
				alert("SUCCESFULL");
			}else if(data=='ERROR'){
				alert("AN ERROR OCCURED");
			}
			
		},error:function(data){
			alert(data);
		}
	});
}