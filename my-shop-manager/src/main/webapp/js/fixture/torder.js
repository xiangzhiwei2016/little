$(function () {
    $("#jqGrid").jqGrid({
        url: '../torder/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '订单编号', name: 'orderNo', index: 'order_no',align:"center", width: 80},
//			{label: '商品', name: 'productName', index: 'product_name', width: 80},
/*			{label: '客户', name: 'clientName', index: 'client_name',align:"center",  width: 80},*/
			{
                label: '客户', name: 'imgUrl', index: 'img_url',  align:"center", width: 80, 
                formatter: function (value,row,index) {
                return transPhoto(index);
            }},
			{label: '手机号', name: 'mobile', index: 'mobile',align:"center",  width: 80},
			{label: '订单状态', name: 'orderStatus', index: 'order_status',align:"center",  width: 80,
				 formatter: function (value) {
	                    return transferStatus(value);
	                }
			},
			{label: '提交订单时间', name: 'addTime', index: 'add_time',align:"center",  width: 80},
			{label: '操作', name: 'orderStatus', index: 'operate',align:"center",  width: 80,
				 formatter: function (value,row,index) {
	                    return doOperate(value,row.rowId);
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
// 订单状态
var orderStatus = [ {
	"key" : "0",
	"value" : "已取消"
}, {
	"key" : "1",
	"value" : "已交定金"
} ];
// 订单状态转换
function transferStatus(value){
	if(null == value || undefined == value || ''== value){
		return '-';
	}
	for(var x in orderStatus){
		if(orderStatus[x].key == value){
			return orderStatus[x].value;
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
	var wechat_name = obj.clientName;
    if (url) {
    	var imgUrl = '<div><img width="50px" height="50px" src="' + url + '"><br/><lable>'+wechat_name+'</lable></div>';
    	var result = "<div>"+imgUrl+"</div>";
        return result;
    } else {
        return '-';
    }
};


// 操作
function doOperate(value,id){
	var result = "-";
	if(1 == value){
		result = "<a href='#' onclick='updateStatus("+id+")'>取消订单</a> "
	}
	return result;
}

// 修改订单状态
function updateStatus(id){
	confirm('确认取消该订单？定金别忘记退还给客户哦', function () {
		$.ajax({
			type: "POST",
		    url: "../torder/updateStatus",
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
        title: null,
		tOrder: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    mobile: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.tOrder = {};
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
            let url = vm.tOrder.id == null ? "../torder/save" : "../torder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tOrder),
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
				    url: "../torder/delete",
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
			$.get("../torder/info/"+id, function (r) {
                vm.tOrder = r.tOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'mobile': vm.q.mobile},
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