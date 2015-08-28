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

	var URL = 'rest/adminwordservise';
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
			width : 200,
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
			width : 200,
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
			width : 600,
			editable : true,
			edittype : 'textarea',
			editrules : {
				edithidden : true
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, {
			name : 'userName',
			label : 'User Name',
			index : 'userName',
			width : 195,
			editable : true,
			edittype : 'select',
			editoptions: {dataUrl: "rest/adminwordservise/selectusernames"},
			editrules : {
				required : true
				
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, ],
		caption : "Words",
		pager : '#pager',
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
	$("#grid").jqGrid(options).navGrid('#pager', {}, // options
			 editOptions, addOptions, delOptions, searchOptions // search options
	);
	$("#grid")
	.jqGrid(options)
	.navGrid('#pager', {edit:true, add:true, del:true, search: true, refresh: true});
});

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
		sortname : 'userName',
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

	var URL = 'rest/adminuserservise';
	var options = {
		url : URL,
		editurl : URL,
		colModel : [ {
			name : 'userId',
			label : 'ID',
			index : 'userId',
			formatter : 'integer',
			width : 40,
			editable : false,
			hidden : true,
			edithidden : true,
			editoptions : {
				disabled : true,
				size : 5
			},
			key : true
		}, {
			name : 'userName',
			label : 'User Name',
			index : 'userName',
			width : 200,
			editable : false,
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, {
			name : 'userEmail',
			label : 'Email',
			index : 'userEmail',
			width : 200,
			editable : false,
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, {
			name : 'access',
			label : 'Access Level',
			index : 'access',
			width : 100,
			editable : true,
			edittype : 'select',
			editoptions: {value: "1:Admin; 2:User"},
			editrules : {
				required : true
			},
			searchoptions : {
				sopt : [ 'eq', 'cn' ]
			}
		}, ],
		caption : "Users",
		pager : '#pager2',
		height : 'auto',
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
				params.url = URL + '/' + postdata.grid2_id;
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
	var searchOptions = {
		onclickSubmit : function(params, postdata) {
			params.url = URL + '/' + postdata;
		},
		closeOnEscape : true,
		closeAfterSearch : true,
		caption : "Search",
		multipleSearch : true,
	};
    $("#grid2").jqGrid(options).navGrid('#pager2', {}, // options
    		editOptions, addOptions, delOptions, searchOptions // search options
	);
    $("#grid2")
	.jqGrid(options)
	.navGrid('#pager2', {edit:true, add:false, del:true, search: true, refresh: true});
});
