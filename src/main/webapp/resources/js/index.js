$(function() {

	$.extend($.jgrid.defaults, {
		datatype : 'json',
		jsonReader : {
			repeatitems : false,
			total : function(result) {
				// Total number of pages
				return Math.ceil(result.total / result.max);
			},
			records : function(result) {
				// Total number of records
				return result.total;
			}
		},
		prmNames : {
			page : "page.page",
			rows : "page.size",
			sort : "page.sort",
			order : "page.sort.dir"
		},
		sortname : 'wordName',
		sortorder : 'asc',
		height : 'auto',
		viewrecords : true,
		rowList : [ 10, 20, 50, 100 ],
		altRows : true,
		loadError : function(xhr, status, error) {
			alert(error);
		}
	});

	$.extend($.jgrid.edit, {
		ajaxEditOptions : {
			contentType : "application/json"
		},
		mtype : 'PUT',
		serializeEditData : function(data) {
			delete data.oper;
			delete data.id;
			return JSON.stringify(data);
		}
	});
	$.extend($.jgrid.del, {
		mtype : 'DELETE',
		serializeDelData : function() {
			return "";
		}
	});

	var URL = 'rest/userWord';
	var options = {
		url : URL,
		editurl : URL,
		colModel : [ {
			name : 'wordId',
			label : 'ID',
			index : 'wordId',
			formatter : 'integer',
			width : 40,
			editable : true,
			hidden : true,
			edithidden : true,
			editoptions : {
				disabled : true,
				size : 5
			},
			key : true
		}, {
			name : 'wordName',
			label : 'English Word',
			index : 'wordName',
			width : 180,
			editable : true,
			editrules : {
				required : true
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, {
			name : 'wordTranslation',
			label : 'Translation',
			index : 'wordTranslation',
			width : 180,
			editable : true,
			editrules : {
				required : true
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, {
			name : 'usageExample',
			label : 'Usage Example',
			index : 'usageExample',
			width : 500,
			editable : true,
			edittype : 'textarea',
			editoptions:{"style":"width:300px", rows:5},
			editrules : {
				edithidden : true
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, ],
		caption : "Words",
		pager : '#wordpager',
		height : 'auto',
		ondblClickRow : function(id) {
			jQuery(this).jqGrid('editGridRow', id, editOptions);
		}
	};

	var editOptions = {
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata.wordId;
		},
		height : 280,
		width : 500,
		closeAfterEdit : true
	};

	var addOptions = {
		mtype : "POST",
		height : 280,
		width : 500,
		closeAfterAdd : true
	};

	var delOptions = {
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata;
		},
	};
	var searchOptions = {
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata;
		},
		closeOnEscape : true,
		closeAfterSearch : true,
		caption : "Search",
		multipleSearch : true,
	};
	$("#wordgrid").jqGrid(options).navGrid('#wordpager', {}, // options
	editOptions, addOptions, delOptions, searchOptions // search options
	);

	$("#wordgrid").jqGrid(options).navGrid('#wordpager', {
		edit : true,
		add : true,
		del : true,
		search : true,
		refresh : true
	});

	//textsgrid
$(function() {
		$.extend($.jgrid.defaults, {
			datatype : 'json',
			jsonReader : {
				repeatitems : false,
				total : function(result) {
					// Total number of pages
					return Math.ceil(result.total / result.max);
				},
				records : function(result) {
					// Total number of records
					return result.total;
				}
			},
			prmNames : {
				page : "page.page",
				rows : "page.size",
				sort : "page.sort",
				order : "page.sort.dir"
			},
			sortname : 'title',
			sortorder : 'asc',
			height : 'auto',
			viewrecords : true,
			rowList : [ 10, 20, 50, 100 ],
			altRows : true,
			loadError : function(xhr, status, error) {
				alert(error);
			}
		});

		$.extend($.jgrid.del, {
			mtype : 'DELETE',
			serializeDelData : function() {
				return "";
			}
		});

		var URL = 'rest/userTexts';
		var options = {
			url : URL,
			editurl : URL,
			colModel : [ {
				name : 'textId',
				label : 'ID',
				index : 'textId',
				formatter : 'integer',
				width : 40,
				editable : true,
				hidden : true,
				edithidden : true,
				editoptions : {
					disabled : true,
					size : 5
				},
				key : true
			}, {
				name : 'title',
				label : 'Title',
				index : 'title',
				width : 400,
				editable : true,
				edittype: 'text',
				editoptions:{"style":"width:300px"}
			}, {
				name : 'text',
				label : 'Text',
				index : 'text',
				width : 800,
				editable : true,
				edittype: 'textarea',
				editoptions:{"style":"width:300px", rows:15}
			},  {
				name : 'userName',
				label : 'UserName',
				index : 'userName',
				width : 40,
				editable : true,
				hidden : true,
				edithidden : true,
				editoptions : {
					disabled : true,
					size : 5
				},
			}, ],
			caption : "Texts",
			pager : '#textpager',
			height : 'auto',
			rowNum: 10,
			ondblClickRow : function(id) {
				jQuery(this).jqGrid('editGridRow', id, editOptions);
			}
		};

		var addOptions = {
				mtype : "POST",
				height : 280,
				width : 500
			};
		
		var editOptions = {
				onclickSubmit : function(params, postdata) {
					params.url = URL + '/' + postdata.textgrid_id;
				},
				height : 280,
				width : 500,
				closeAfterEdit : true
			};
		
		var delOptions = {
			onclickSubmit : function(params, postdata) {
				params.url = URL + '/' + postdata;
			},
		};

	    $("#textgrid").jqGrid(options).navGrid('#textpager', {search:false}, // options
	    		editOptions, addOptions, delOptions
		);
	});	

	// for list of texts
	/*$
			.ajax({
				type : 'GET',
				url : 'rest/userTexts',
				async : false,
				beforeSend : function() { loading 
				},
				dataType : 'json',
				success : function(result) {
					var buffer = "";
					var i = 0;
					$
							.each(
									result,
									function(index, val) {
										buffer += "<div class=\"showtext\">"
												+ val.title
												+ "</div><div class=\"text1\" style=\"display: none;\">"
												+ val.text 
												+ "</div>"
												+"<hr />";
										i++;
										$("#usertexts").html(buffer);
									});
				}
			});
	// show or hide text div
	$('.showtext').click(function() {
		$(this).next('.text1').slideToggle("fast");
	});
});

// add new text
function deselect(e) {
	$('.pop').slideFadeToggle(function() {
		e.removeClass('selected');
	});
}

//show pop up
$(function() {
	$('#contact').on('click', function() {
		if ($(this).hasClass('selected')) {
			deselect($(this));
		} else {
			$(this).addClass('selected');
			$('.pop').slideFadeToggle();
		}
		return false;
	});

	$('.close').on('click', function() {
		deselect($('#contact'));
		return false;
	});
});

$.fn.slideFadeToggle = function(easing, callback) {
	return this.animate({
		opacity : 'toggle',
		height : 'toggle'
	}, 'fast', easing, callback);
};

//add and delete text
$(function() {
	$('#new_text')
			.submit(
					function() { // bind function to submit event of form
						var formData = JSON.stringify($("#new_text")
								.serializeArray());
						var json = JSON.parse(formData);
						var mydata = "{" + json[0].name + ":\"" + json[0].value
								+ "\", " + json[1].name + ":\"" + json[1].value
								+ "\"}";
						$.ajax({
							type : $(this).attr('method'),
							url : $(this).attr('action'),
							dataType : 'json',
							data : mydata,
							contentType : "application/json",
							success : function(responseText) {
								deselect($('#contact'));
								location.reload();
							}
						});
						return false; // prevents the form from submitting
					});*/
	//TODO
	/*$('.texts-remove').on('click', function() {
		var title = $(this).closest("div.showtext").text();
		$(this).closest("div.showtext").remove();
		return false;
	});*/
	/*$('#remove_text').click(function() {
		//var title = $(this).closest("div.showtext").text();
		//alert(title);
		$.ajax({
			type : 'DELETE',
			url : 'rest/userTexts',
			async : false,
			data: data,
			contentType : "application/json",
			success : function(responseText) {
				deselect($('#contact'));
				location.reload();
			}
		});
		$(this).closest("div.row").remove();
		return false;
	});		*/
});

