$(function () {
    let clientId = getQueryString("clientId");
    let url = '../tclient/list';
    if (clientId) {
        url += '?upClientId=' + clientId;
    }
    $("#jqGrid").jqGrid({
        url: url,
        datatype: "json",
        colModel: [
       			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
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
				 {label: '关注小程序时间', name: 'registerTime', index: 'register_time', align:"center", width: 80}
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
	for(var x in clientTypes){
		if(clientTypes[x].key == value){
			return clientTypes[x].value;
		}
	}
};

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        tclient: {}
    },
    methods: {
        getInfo: function () {
        	let clientId = getQueryString("clientId");
            $.get("../tclient/queryByClientId/" + clientId, function (r) {
                vm.tclient = r.tClient;
            });
        }
    },
    created: function () {
        this.getInfo();
    }
});