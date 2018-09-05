$(function () {
    $("#jqGrid").jqGrid({
        url: '../tclient/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '微信名称', name: 'clientName', index: 'client_name',align:"center",  width: 80,hidden: true},
			{
                label: '用户', name: 'imgUrl', index: 'img_url',  align:"center", width: 80, 
                formatter: function (value,row,index) {
                return transPhoto(index);
            }},
			{label: '手机号', name: 'mobile', index: 'mobile', align:"center", width: 80},
			{label: '用户身份', name: 'clientType', index: 'client_type', align:"center", width: 80,
				 formatter: function (value) {
	                    return transferClientType(value);
	                }
			},
			{label: '关注渠道', name: 'channel', index: 'channel', align:"center", width: 80,
				 formatter: function (value) {
	                    return transferChannels(value);
	                }
			},
			{label: '关注小程序时间', name: 'registerTime', index: 'register_time', align:"center", width: 80},
			{label: '操作', name: 'clientType', index: 'operate',align:"center",  width: 80,
				 formatter: function (value,row,index) {
	                    return doOperate(value,row.rowId);
	                }
			}
			],
		viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});


//第四种  
function exportExcle(tableid){  
    var curTbl = document.getElementById(tableid);  
    var oXL;  
    try{  
        oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel  
    }catch(e){  
        alert("无法启动Excel!\n\n如果您确信您的电脑中已经安装了Excel，"+"那么请调整IE的安全级别。\n\n具体操作：\n\n"+"工具 → Internet选项 → 安全 → 自定义级别 → 对没有标记为安全的ActiveX进行初始化和脚本运行 → 启用");  
        return false;  
    }  
    var oWB = oXL.Workbooks.Add(); //获取workbook对象  
    var oSheet = oWB.ActiveSheet;//激活当前sheet  
    var sel = document.body.createTextRange();  
    sel.moveToElementText(curTbl); //把表格中的内容移到TextRange中  
    sel.select(); //全选TextRange中内容  
    sel.execCommand("Copy");//复制TextRange中内容  
    oSheet.Paste();//粘贴到活动的EXCEL中  
    oXL.Visible = true; //设置excel可见属性  
    var fname = oXL.Application.GetSaveAsFilename("将table导出到excel.xls", "Excel Spreadsheets (*.xls), *.xls");  
    oWB.SaveAs(fname);  
    oWB.Close();  
    oXL.Quit();  
}  


/**
 * 翻译头像
 * @param url
 * @returns {*}
 */
function transPhoto(obj) {
	var url = obj.imgUrl;
	var wechat_name = obj.clientName;
    if (url) {
    	var imgUrl = '<div><img width="50px" height="50px" src="' + url + '"><br/><lable>'+wechat_name+'</lable></div>';
    	var result = "<div>"+imgUrl+"</div>";
        return result;
    } else {
        return '-';
    }
};

//用户类型（0.推广员，1.会员，2潜在用户）
var clientTypes = [ {
	"key" : "0",
	"value" : "推广员"
}, {
	"key" : "1",
	"value" : "会员"
} , {
	"key" : "2",
	"value" : "潜在用户"
} ];
// 用户身份转换
function transferClientType(value){
	if(null == value || undefined == value || ''== value){
		return '-';
	}
	for(var x in clientTypes){
		if(clientTypes[x].key == value){
			return clientTypes[x].value;
		}
	}
	return '-';
};

//0门店扫码、1微信转发
var channels = [ {
	"key" : "0",
	"value" : "门店扫码"
}, {
	"key" : "1",
	"value" : "微信转发"
} ];
// 用户身份转换
function transferChannels(value){
	if(null == value || undefined == value || ''== value){
		return '-';
	}
	for(var x in channels){
		if(channels[x].key == value){
			return channels[x].value;
		}
	}
	return '-';
};
/**
 * 翻译头像
 * @param url
 * @returns {*}
 */
function transPhoto(obj) {
	var url = obj.imgUrl;
	var wechat_no = obj.wechatNo;
	var wechat_name = obj.clientName;
    if (url) {
    	//  style="float:left;margin-left:40%;
    	var imgUrl = '<div><img width="50px" height="50px" src="' + url + '"><br/><lable>'+wechat_name+'</lable></div>';
    	var result = "<div>"+imgUrl+"</div>";
        return result;
    } else {
        return '-';
    }
};

//操作
function doOperate(value,id){
	var result = "";
	if(0 != value){
		result = "<a href='#' onclick='toSpread("+id+")'>找TA推广</a> "
	}else{
		result = "<a href='#' onclick='vm.spreadDetails("+id+")'>推广详情</a> "
	}
	
	return result;
}

// 找TA推广
function toSpread(id){
	$.ajax({
		type: "POST",
	    url: "../tclient/toSpread",
	    contentType: "application/json",
	    data: JSON.stringify(id),
	    success: function (r) {
			if (r.code == 0) {
				alert('操作成功', function (index) {
					$("#jqGrid").trigger("reloadGrid");
				});
			} else {
				alert(r.msg);
			}
		}
	});
};


let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		tClient: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
		    clientType: ''
		}
	},
	methods: {
		exportExcle:function(){
			exportExcle("jqGrid");
		},
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.tClient = {};
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.tClient.id == null ? "../tclient/save" : "../tclient/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tClient),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows();
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
				    url: "../tclient/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		spreadDetails: function (id) {
	            openWindow({
	                type: 2,
	                title: '推广详情',
	                content: '../fixture/spreadDetails.html?clientId=' + id
	            });
	        },
		getInfo: function(id){
			$.get("../tclient/info/"+id, function (r) {
                vm.tClient = r.tClient;
            });
		},
		exportClient: function () {
            exportFile('#rrapp', '../tclient/export', {'name': vm.q.name,'clientType': vm.q.clientType});
        },
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name,'clientType': vm.q.clientType},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
	}
});