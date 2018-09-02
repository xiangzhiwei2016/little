$(function () {
    $("#jqGrid").jqGrid({
        url: '../tevaluate/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{
                label: '客户', name: 'imgUrl', index: 'img_url',  align:"center", width: 80, 
                formatter: function (value,row,index) {
                return transPhoto(index);
            }},
            {label: '手机号', name: 'mobile', index: 'mobile',align:"center",  width: 80},
			{label: '评分', name: 'score', index: 'score', align:"center", width: 80},
			
			{label: '评论详情', name: 'description', index: 'description', align:"center", width: 80},
			{label: '日期', name: 'createTime', index: 'create_time', align:"center", width: 80},
			{label: '操作', name: 'view', index: 'id', align:"center", width: 80,
				formatter: function (value,row,index) {
						var result = "<a href='#' onclick='vm.viewImages("+row.rowId+")'>评论图片查看</a> ";
                    return result;
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

//动态生成div
function genDiv(uplodList){
	 vm.showImages = true;
	 $("#swiper-wrapper-id").empty();
	 // 获取div
 var div = document.getElementById("swiper-wrapper-id");  

 for(var i =0;i< uplodList.length;i++ ){
// 	var imgUrl = "../statics/img/Group.png";
 	var imgUrl =  imgUrl = uplodList[i].imgUrl;
 	 // 添加 div
     var test = document.createElement("div");
 	// 设置 div 属性，如 id
 	test.setAttribute("class", "swiper-slide");
 	test.innerHTML = '<img src="'+imgUrl+'"/>';
     div.appendChild(test); 
 }
 var swiper = new Swiper('.swiper-container', {
		navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
		},
	});
	openWindow({
     title: "图片详情",
     area: ['600px', '350px'],
     btn: ['确定'],
     content: jQuery("#imageDiv"),
     btn1: function (index) {
     	 $("#swiper-wrapper-id").empty();
     	 vm.showImages = false;
     	 layer.close(index);
     }
 });
}

function gen(id){
	$.get("../tevaluate/queryImages?id=" + id, function (r) {
		var array = r.list;
		console.log(array);
		genDiv(array);
 });
}

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        showImages:false,
        title: null,
		tEvaluate: {},
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
			vm.tEvaluate = {};
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
            let url = vm.tEvaluate.id == null ? "../tevaluate/save" : "../tevaluate/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tEvaluate),
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
				    url: "../tevaluate/delete",
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
		viewImages: function (id) {
            gen(id);
        },
		getInfo: function(id){
			$.get("../tevaluate/info/"+id, function (r) {
                vm.tEvaluate = r.tEvaluate;
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