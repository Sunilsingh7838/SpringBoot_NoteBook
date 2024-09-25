<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cards</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
	<h1>Cards Table</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>User Name</th>
				<th>Card Number</th>
				<th>Card Array</th>
				<th>Client Name</th>
				<th>Email</th>
				<th>Country</th>
				<th>Choose Gender</th>

			</tr>
			<tr>
				<th><input type="text" id="fullNameId"
					placeholder="Search Name"></th>
				<th><input type="text" id="usernameId"
					placeholder="Search User Name"></th>
				<th><input type="text" id="cardNumberId"
					placeholder="Search Card Number"></th>

				<th><label for="options"></label> <select
					id="cardArrayOptionsId" name="selectedOptionForCardArray">
						<option value="selectAll">Select All</option>
						<option value="visa">Visa</option>
						<option value="master-card">Master-Card</option>
						<option value="rupay">Rupay</option>
				</select></th>

				<th><input type="text" id="clientNameId"
					placeholder="Search Client Name"></th>
				<th><input type="text" id="emailId" placeholder="Search Email"></th>
				<th><input type="text" id="countryId"
					placeholder="Search Country"></th>

				<th><label for="options"></label> <select id="genderOptionsId"
					name="selectedOptionForGender">
						<option value="selectAll">Select All</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
				</select></th>
			</tr>
		</thead>
		<tbody id="table-body">

		</tbody>
	</table>

	<script type="text/javascript">
	
	$(document).ready(function() {
		
		// on page view get all data in table
		fetchAllCards();
		
	    // Trigger the filtering when the dropdowns change
	    $('#cardArrayOptionsId, #genderOptionsId').on('change', function() {
	    	filterCardArrayAndGenderColumn();  
	    });
		
	    // Filter Rows of text input
	    $('#fullNameId,#usernameId,#cardNumberId,#clientNameId,#emailId,#countryId').on('keyup', function(){
	    	//filterAllColumns();
	    	filterCardArrayAndGenderColumn();
	    })
});
	
	function fetchAllCards(){
		$.ajax({
            url: "/cards",  
            method: "GET",
            dataType: "json",
    		contentType: "application/json",
            success: function(response) {
            	console.log(response);
                if(response.status === 'Success') {
                    var rows = '';
                    response.data.forEach(function(cards) {
                        rows += '<tr>' +
                                '<td>' + cards.firstName +" " +cards.lastName+ '</td>' +
                                '<td>' + cards.username+ '</td>' +
                                '<td>' + cards.cardNumber + '</td>' +
                                '<td>' + cards.cardArray + '</td>' +
                                '<td>' + cards.clientName + '</td>' +
                                '<td>' + cards.email + '</td>' +
                                '<td>' + cards.country + '</td>' +
                                '<td>' + cards.gender + '</td>' +
                                '</tr>';
                    });
                    $('#table-body').html(rows);
                }
            },
            error: function(xhr, status, error) {
    			console.error('Error:', error);
    			console.error('xhr:', xhr);
    			console.error('status:', status);
    		}
        });
	}
	
	function filterCardArrayAndGenderColumn(){
		const filters = {
		        searchRequestDto: [],
		       firstName: $('#fullNameId').val(),
		       username: $('#usernameId').val(),
		       cardNumber: $('#cardNumberId').val(),
		       clientName: $('#clientNameId').val(),
		       email: $('#emailId').val(),
		       country: $('#countryId').val()
		    };
		var CardArray = $('#cardArrayOptionsId').val();
		var Gender = $('#genderOptionsId').val();
		
		if(CardArray !=='selectAll'){
			filters.searchRequestDto.push({
				column:'cardArray',
				value: CardArray
			});
		}
		
		
		
		if(Gender !=='selectAll'){
			filters.searchRequestDto.push({
				column:'gender',
				value: Gender
			});
		}
		
		  $.ajax({
		        url: '/specs',  
		        type: 'POST',   
		        contentType: 'application/json',
		        data: JSON.stringify(filters),
		        success: function(data) {
		        	console.log(data);
		            $('#table-body').empty();
		            var rows ='';
		            data.forEach(cards => {
		                rows += '<tr>' +
                        '<td>' + cards.firstName +" " +cards.lastName+ '</td>' +
                        '<td>' + cards.username+ '</td>' +
                        '<td>' + cards.cardNumber + '</td>' +
                        '<td>' + cards.cardArray + '</td>' +
                        '<td>' + cards.clientName + '</td>' +
                        '<td>' + cards.email + '</td>' +
                        '<td>' + cards.country + '</td>' +
                        '<td>' + cards.gender + '</td>' +
                        '</tr>';
            });
            $('#table-body').html(rows);
		        },
		        error: function(xhr, status, error) {
		            console.error('Error:', error);
		            console.error('Status:', status);
		        }
		    });
	}
	
	function filterAllColumns(){
		const fullName = $('#fullNameId').val().toLowerCase();
		const userName = $('#usernameId').val().toLowerCase();
		const cardNumber = $('#cardNumberId').val();
		const clientName = $('#clientNameId').val().toLowerCase();
		const email = $('#emailId').val().toLowerCase();
		const country = $('#countryId').val().toLowerCase();
		
	     $("#table-body tr").filter(function() {
	    	 
	    	  const fullNameText = $(this).find("td:eq(0)").text().toLowerCase();
	          const isFullNameMatch = fullNameText.startsWith(fullName); 
	          
	    	  const userText = $(this).find("td:eq(1)").text().toLowerCase();
	          const isUserMatch = userText.startsWith(userName); 
	          
	    	  const cardNum = $(this).find("td:eq(2)").text().toLowerCase();
	          const isCardNum = cardNum.startsWith(cardNumber); 
	          
	    	  const clName = $(this).find("td:eq(4)").text().toLowerCase();
	          const isClName = clName.startsWith(clientName); 
	          
	    	  const emailField = $(this).find("td:eq(5)").text().toLowerCase();
	          const isEmailField = emailField.startsWith(email); 
	          
	    	  const countryField = $(this).find("td:eq(6)").text().toLowerCase();
	          const isCountryField = countryField.startsWith(country); 

	            $(this).toggle(
	            	isUserMatch && isCardNum && isFullNameMatch && isClName && isEmailField && isCountryField  
	                //($(this).find("td:eq(0)").text().toLowerCase().indexOf(fullName) > -1) &&
	                //($(this).find("td:eq(2)").text().indexOf(cardNumber) > -1) &&
	               // ($(this).find("td:eq(4)").text().toLowerCase().indexOf(clientName) > -1) &&
	               // ($(this).find("td:eq(5)").text().toLowerCase().indexOf(email) > -1) &&
	                //($(this).find("td:eq(6)").text().toLowerCase().indexOf(country) > -1)
	            );
	        });
	}
    </script>
</body>
</html>