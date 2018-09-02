$(function () {
    $("#jqGrid").jqGrid({
        url: '../tmerchant/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
		/*	{label: '小程序ID', name: 'appId', index: 'app_id', align:"center", width: 80},*/
			{label: '商户名称', name: 'merchantName', index: 'merchant_name', align:"center", width: 80},
			{label: '用户名', name: 'mcLoginName', index: 'mc_login_name', align:"center", width: 80},
			{label: '密码', name: 'passWord', index: 'pass_word', align:"center", width: 80},
			{label: '状态', name: 'status', index: 'status', align:"center", width: 80,
				formatter: function (value) {
                    return transferStatus(value);
                }
			},
			{label: '接入时间', name: 'createTime', index: 'create_time', width: 80},
			{label: '操作', name: 'status', index: 'operate',align:"center",  width: 80,
				 formatter: function (value,row,index) {
	                    return doOperate(value,row.rowId);
	                }
			}
//			,
//			{label: '修改时间', name: 'modifyTime', index: 'modify_time', width: 80},
//			{label: '创建者', name: 'creator', index: 'creator', width: 80},
//			{label: '修改者', name: 'modifier', index: 'modifier', width: 80}
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

//商户状态
var merchantStatus = [ {
	"key" : "0",
	"value" : "已暂停"
}, {
	"key" : "1",
	"value" : "运营中"
} ];
// 商户状态
function transferStatus(value){
	for(var x in merchantStatus){
		if(merchantStatus[x].key == value){
			return merchantStatus[x].value;
		}
	}
	return "-";
};

//操作
function doOperate(value,id){
	var result = "-";
	if(1 == value){
		result = "<a href='#' onclick='updateStatus("+id+",0)'>暂停服务</a> "
	}else{
		result = "<a href='#' onclick='updateStatus("+id+",1)'>恢复服务</a> "
	}
	return result;
}

//修改订单状态
function updateStatus(id,status){
	var msg = "确认恢复该商户的服务？";
	if(status ==0){
		msg = "确认暂停该商户的服务？"
	}
	confirm(msg, function () {
		var param = {"id":id,"status":status};
		$.ajax({
			type: "POST",
		    url: "../tmerchant/updateStatus",
		    contentType: "application/json",
		    data: JSON.stringify(param),
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
}

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		tMerchant: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.tMerchant = {};
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
            let url = vm.tMerchant.id == null ? "../tmerchant/save" : "../tmerchant/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tMerchant),
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
				    url: "../tmerchant/delete",
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
		getInfo: function(id){
			$.get("../tmerchant/info/"+id, function (r) {
                vm.tMerchant = r.tMerchant;
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
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