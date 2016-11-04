User = {
	
	clearUserForm : function(){
		$(':input').not(':submit,:button').val('');
	},
	
	hideAllMessage : function(){
		$('#firstNameMissing,#firstNameInvalid,#lastNameInvalid').hide();
		$('#designationMissing,#designationInvalid,#companyNameMissing').hide();
		$('#companyNameInvalid').hide();
	},
	
	validateUser : function(){
		$('#addUser,#updateUser').click(function(){
			// Reading all values
			var firstName 	= $('#firstName').val();
			var lastName 	= $('#lastName').val();
			var designation = $('#designation').val();
			var companyName = $('#companyName').val();
			
			// Regular Expression
			var firstName_regex 	= /^[^\s][a-zA-Z]+([\s]?[a-zA-Z]+)*[^\s]+$/;
			var lastName_regex 		= /^[^\s][a-zA-Z]+([\s]?[a-zA-Z]+)*[^\s]+$/;
			var designation_regex 	= /^[^\s][a-zA-Z]+([\s]?[a-zA-Z]+)*[^\s]+$/;
			var companyName_regex 	= /^[^\s][a-zA-Z]+([\s]?[a-zA-Z]+)*[^\s]+$/;
			
			if(firstName == "" || firstName.length == 0 || firstName == undefined){
				User.hideAllMessage();
				$('#firstNameMissing').show();
				return false;
			}
			else if(!firstName.match(firstName_regex)){
				User.hideAllMessage();
				$('#firstNameInvalid').show();
				return false;
			}
			else if(!lastName.match(lastName_regex) && lastName.length > 0){
				User.hideAllMessage();
				$('#lastNameInvalid').show();
				return false;
			}
			else if(designation == "" || designation.length == 0 || designation == undefined){
				User.hideAllMessage();
				$('#designationMissing').show();
				return false;
			}
			else if(!designation.match(designation_regex)){
				User.hideAllMessage();
				$('#designationInvalid').show();
				return false;
			}
			else if(companyName == "" || companyName.length == 0 || companyName == undefined){
				User.hideAllMessage();
				$('#companyNameMissing').show();
				return false;
			}
			else if(!companyName.match(companyName_regex)){
				User.hideAllMessage();
				$('#companyNameInvalid').show();
				return false;
			}
		});
	},
}