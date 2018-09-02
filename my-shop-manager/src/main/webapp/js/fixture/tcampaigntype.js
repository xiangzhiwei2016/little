$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcampaigntype/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '活动类型', name: 'name', index: 'name',align:"center",  width: 80},
			{label: '活动说明', name: 'rule', index: 'rule',align:"center",  width: 80},
			{label: '参与活动人数', name: 'count', index: 'count',align:"center",  width: 80},
			{label: '状态', name: 'status', index: 'status', align:"center", width: 80,
				 formatter: function (value) {
	                    return transferCompaginStatus(value);
	                }
			},
//			{label: '描述', name: 'description', index: 'description',align:"center",  width: 80},
//			{label: '生效时间', name: 'effectTime', index: 'effect_time', width: 80},
//			{label: '过期时间', name: 'expiryTime', index: 'expiry_time', width: 80},
//			{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
//			{label: '修改时间', name: 'modifyTime', index: 'modify_time', width: 80},
//			{label: '创建者', name: 'creator', index: 'creator', width: 80},
//			{label: '修改者', name: 'modifier', index: 'modifier', width: 80},
			{label: '操作', name: 'status', index: 'operate',align:"center",  width: 80,
				 formatter: function (value,row,index) {
	                    return doOperate(index);
	                }
			}],
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

// 状态字典
var compaginStatus = [ {
	"key" : "0",
	"value" : "不可用"
}, {
	"key" : "1",
	"value" : "可用"
} ];
// 营销活动类型
function transferCompaginStatus(value){
	for(var x in compaginStatus){
		if(compaginStatus[x].key == value){
			return compaginStatus[x].value;
		}
	}
};
// 营销活动类型1.定金折扣2.组团返现3.赠送礼品
var compaginTypes = [ {
	"key" : "1",
	"value" : "定金享折扣"
}, {
	"key" : "2",
	"value" : "组团返现金"
} , {
	"key" : "3",
	"value" : "点评送礼品"
} ];

//营销活动类型
function transferCompaginTypes(value){
	for(var x in compaginTypes){
		if(compaginTypes[x].key == value){
			return compaginTypes[x].value;
		}
	}
};
//操作
function doOperate(index){
	var result = "";
	var value = index.status;
	var id = index.id;
	result = "<a href='#' onclick='vm.modify("+id+")'>编辑</a> &nbsp;&nbsp;<a href='#' onclick='vm.detail("+id+")'>查看详情</a>"
	if(1 == value){
		result += "&nbsp;&nbsp;<a href='#' onclick='updateStatus("+id+")'>撤销活动</a> "
	}
	return result;
}

//修改订单状态
function updateStatus(id){
	confirm('确认撤销该活动', function () {
		$.ajax({
			type: "POST",
		    url: "../tcampaigntype/updateStatus",
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
	});
}


let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        ishowDjxzk:false,
        ishowDpslp:false,
        ishowZtfxj:false,
        title: null,
		tCampaignType: {},
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
			vm.tCampaignType = {};
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
		modify: function (id) {
			$.get("../tcampaigntype/info/"+id, function (r) {
                vm.tCampaignType = r.tCampaignType;
                var type = vm.tCampaignType.type;
                if(1 == type){
                	vm.modifyDjxzk(vm.tCampaignType);
                }if(2 == type){
                	vm.modifyZtfxj(vm.tCampaignType);
                }if(3 == type){
                	vm.modifyDpslp(vm.tCampaignType);
                }
            });
		},
		detail: function (id) {
			openWindow({
                type: 2,
                title: '活动详情',
                content: '../fixture/campaignDetails.html?id=' + id
            });
		},
		createDjxzk:function () {
			vm.ishowDjxzk = true;
			vm.tCampaignType = {
					type:"1",
					status:"1"
			};
			openWindow({
		        title: "创建定金享折扣",
		        btn: ['保存'],
		        area: ['400px', '300px'],
		        content: jQuery("#Djxzk"),
		        btn1: function (index) {
		        	 vm.ishowDjxzk = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		modifyDjxzk:function (index) {
			vm.ishowDjxzk = true;
			vm.tCampaignType = index;
			openWindow({
		        title: "编辑定金享折扣",
		        btn: ['保存'],
		        area: ['400px', '300px'],
		        content: jQuery("#Djxzk"),
		        btn1: function (index) {
		        	 vm.ishowDjxzk = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		createZtfxj:function () {
			vm.ishowZtfxj = true;
			vm.tCampaignType = {
					type:"2",
					status:"1"
			};
			openWindow({
		        title: "创建组团返现金",
		        btn: ['保存'],
		        area: ['500px', '400px'],
		        content: jQuery("#Ztfxj"),
		        btn1: function (index) {
		        	 vm.ishowZtfxj = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		modifyZtfxj:function (index) {
			vm.ishowZtfxj = true;
			vm.tCampaignType = index;
			openWindow({
		        title: "编辑组团返现金",
		        btn: ['保存'],
		        area: ['500px', '400px'],
		        content: jQuery("#Ztfxj"),
		        btn1: function (index) {
		        	 vm.ishowZtfxj = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		createDpslp:function () {
			vm.ishowDpslp = true;
			vm.tCampaignType = {
					type:"3",
					status:"1"
			};
			openWindow({
		        title: "创建点评送礼品",
		        btn: ['保存'],
		        area: ['500px', '400px'],
		        content: jQuery("#Dpslp"),
		        btn1: function (index) {
		        	 vm.ishowDpslp = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		modifyDpslp:function (index) {
			vm.ishowDpslp = true;
			vm.tCampaignType = index;
			openWindow({
		        title: "编辑点评送礼品",
		        btn: ['保存'],
		        area: ['500px', '400px'],
		        content: jQuery("#Dpslp"),
		        btn1: function (index) {
		        	 vm.ishowDpslp = false;
		        	 layer.close(index);
		        	 vm.saveOrUpdate();
		        }
		    });
		},
		saveOrUpdate: function (event) {
            let url = vm.tCampaignType.id == null ? "../tcampaigntype/save" : "../tcampaigntype/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tCampaignType),
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
				    url: "../tcampaigntype/delete",
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
			$.get("../tcampaigntype/info/"+id, function (r) {
                vm.tCampaignType = r.tCampaignType;
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