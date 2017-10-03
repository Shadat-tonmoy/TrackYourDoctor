$(document).ready(function(){
	alert("SEND?");
    var options = { 
    	    beforeSend: function() 
    	    {
    	    	alert("beforeSend");
    	        $("#progress").show();
    	        //clear everything
    	        $("#bar").width('0%');
    	        $("#message").html("");
    	        $("#percent").html("0%");
    	    },
    	    uploadProgress: function(event, position, total, percentComplete) 
    	    {
    	        $("#bar").width(percentComplete+'%');
    	        $("#percent").html(percentComplete+'%');
    	 
    	    },
    	    success: function() 
    	    {
    	    	alert("Success");
    	        $("#bar").width('100%');
    	        $("#percent").html('100%');
    	 
    	    },
    	    complete: function(response) 
    	    {
    	    	alert("Complete");
    	        $("#message").html("<font color='green'>"+response.responseText+"</font>");
    	    },
    	    error: function()
    	    {
    	    	alert("Error");
    	        $("#message").html("<font color='red'> ERROR: unable to upload files</font>");
    	 
    	    }
    	 
    	}; 
    	 
    	     $("#myForm").ajaxForm(options);
})