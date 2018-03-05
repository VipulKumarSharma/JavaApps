/*
 * jQuery File Upload Plugin JS Example 6.7
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/*jslint nomen: true, unparam: true, regexp: true */
/*global $, window, document */

$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload();

    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

    if (window.location.hostname === 'localhost:8089') {
        // Demo settings:
        $('#fileupload').fileupload('option', {
            url: '//localhost:8089/jQuery-File-Upload-Java/imgs/',
            maxFileSize: 5000000,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            process: [
                {
                    action: 'load',
                    fileTypes: /^image\/(gif|jpeg|png)$/,
                    maxFileSize: 20000000 // 20MB
                },
                {
                    action: 'resize',
                    maxWidth: 1440,
                    maxHeight: 900
                },
                {
                    action: 'save'
                }
            ]
        });
        // Upload server status check for browsers with CORS support:
        if ($.support.cors) {
            $.ajax({
                url: '//localhost:8089/jQuery-File-Upload-Java/imgs/',
                type: 'HEAD'
            }).fail(function () {
                $('<span class="alert alert-error"/>')
                    .text('Upload server currently unavailable - ' +
                            new Date())
                    .appendTo('#fileupload');
            });
        }
    } else {
        // Load existing files:
        $('#fileupload').each(function () {
            var that = this;
            $.getJSON(this.action, function (result) {
                if (result && result.length) {
                    $(that).fileupload('option', 'done')
                        .call(that, null, {result: result});
                }
            });
        });
    }

	$('#fileupload').fileupload('option', {
	        url							: 'UploadServlet',
	     	dropZone					: $('#fileupload div.upload div.upload-files div#drop div#fileDragDropArea'),
	        autoUpload					: true,
	        multipart					: true,
	     	singleFileUploads			: false,
<<<<<<< HEAD
	     	acceptFileTypes				: /(\.|\/)(gif|jpg|jpeg|png|pdf|doc|docx|xls|xlsx|ppt|pptx|tif|txt)$/i,
=======
	     	acceptFileTypes				: /(\.|\/)(gif|jpg|jpeg|png|pdf|doc|docx|xls|xlsx|ppt|pptx|gif|tif|txt)$/i,
>>>>>>> e91dd32ae3e8ce942577766fd44249be769f55ef
	     	maxFileSize					: 10000000,
	     	maxNumberOfFiles			: 100
	    });
    
    $('#fileupload').fileupload({
        add: function (e, data) {
<<<<<<< HEAD
        	try {
        		$("table#filesListTable tbody").html('');
            	
            	if($("table#docsListTable tbody tr").length == 1 && $("table#docsListTable tbody tr").hasClass("noDataRow")) {
            		$("div#uploadedFilesDiv").css("max-height","218px")
            	} else {
            		$("div#uploadedFilesDiv").css("max-height","110px")
            	}
            	$("div#fileupload-progressbar-row, div#uploadedFilesDiv").show();
            	
            	var jqXHR = data.submit()
            	.success(function (result, textStatus, jqXHR) {})
    	        .error(function (jqXHR, textStatus, errorThrown) {})
    	        .complete(function (result, textStatus, jqXHR) {});
            	
        	} catch (e) {}
=======
        	$("div#fileupload-progressbar-row, div#uploadedFilesDiv").show();
        	
        	var jqXHR = data.submit()
        	.success(function (result, textStatus, jqXHR) {})
	        .error(function (jqXHR, textStatus, errorThrown) {})
	        .complete(function (result, textStatus, jqXHR) {});
>>>>>>> e91dd32ae3e8ce942577766fd44249be769f55ef
        }
    });
    
    $('#fileupload').bind('fileuploadchange', function (e, data) {		
    	//console.log('fileuploadchange');	
    })
    .bind('fileuploadadd', function (e, data) 		{  		
    	//console.log('fileuploadadd');		
    })
    .bind('fileuploadsubmit', function (e, data) 	{
<<<<<<< HEAD
    	try {
    		data.formData = {
	        	reqSiteId		: $("#reqSiteId").val(),
	        	reqDocType		: $("#reqDocType").val(),
	        	reqMstDocId		: $("#reqMstDocId").val(),
	        	reqMstId		: $("#reqMstId").val(),
	        	docReference	: $("#docReference").val()
	       	};
	       	
	        if (!data.formData.docReference) {
	        	/*data.context.find('button').prop('disabled', false);*/
	        	$("div#fileupload-progressbar-row, div#uploadedFilesDiv").hide();
	        	alert('Please Enter document reference first.');
	        	$('#docReference').focus();
	        	return false;
	        }
    	
    	} catch(e) {}
=======
        data.formData = {
        	reqSiteId		: $("#reqSiteId").val(),
        	reqDocType		: $("#reqDocType").val(),
        	reqMstDocId		: $("#reqMstDocId").val(),
        	reqMstId		: $("#reqMstId").val(),
        	docReference	: $("#docReference").val()
       	};
       	
        if (!data.formData.docReference) {
          data.context.find('button').prop('disabled', false);
          alert('Please Enter document reference first.');
          $('#docReference').focus();
          return false;
        }
>>>>>>> e91dd32ae3e8ce942577766fd44249be769f55ef
    })
    .bind('fileuploadstart', function (e) {				
    	//console.log('fileuploadstart');			
    })
    .bind('fileuploadsend', function (e, data) {			
    	//console.log('fileuploadsend');
    	$("div#fileupload-progressbar").addClass("in");
    })
    .bind('fileuploadprogress', function (e, data) {		
    	//console.log('fileuploadprogress');		
    })
    .bind('fileuploadprogressall', function (e, data) {		
    	//console.log('fileuploadprogressall');	
    })
    .bind('fileuploaddone', function (e, data) {			
    	//console.log('fileuploaddone');			
    })
    .bind('fileuploadalways', function (e, data) {		    
    	//console.log('fileuploadalways');
    })
    .bind('fileuploadstop', function (e) {				
    	//console.log('fileuploadstop');
    	$("div#fileupload-progressbar-row").hide();
    })
    .bind('fileuploadfail', function (e, data) {			
    	//console.log('fileuploadfail');			
    })
    .bind('fileuploadpaste', function (e, data) {			
    	//console.log('fileuploadpaste');			
    })
    .bind('fileuploaddrop', function (e, data) {			
    	//console.log('fileuploaddrop');			
    })
    .bind('fileuploaddragover', function (e) {				
    	//console.log('fileuploaddragover');
    });
    
<<<<<<< HEAD
=======
    
    
>>>>>>> e91dd32ae3e8ce942577766fd44249be769f55ef
    /* $('#fileupload').fileupload('add', {
    	files	: $('input[type="file"]').prop('files'), 
    	url		: 'UploadServlet'
    }); */
    
    
    /* $('#fileupload').fileupload('send', {
    	files	: $('input[type="file"]').prop('files')
    }); */
    
    /* var jqXHR = $('#fileupload').fileupload('send', {
    	files	: $('input[type="file"]').prop('files')
	})
    .success(function (result, textStatus, jqXHR) { 
    	console.log('success');
    })
    .error(function (jqXHR, textStatus, errorThrown) {
    	console.log('error');
    })
    .complete(function (result, textStatus, jqXHR) {
    	console.log('complete');
   	}); */
   	
<<<<<<< HEAD
   	
    $("#triggerFile").bind("click", function (event) {
=======
   	$("#triggerFile").bind("click", function (event) {
>>>>>>> e91dd32ae3e8ce942577766fd44249be769f55ef
		event.preventDefault();
		$("input[type=file]").click();
	});
   	
   	$(document).bind('drop dragover', function (e) {
	    e.preventDefault();
	});
});
