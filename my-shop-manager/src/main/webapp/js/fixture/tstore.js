$(function () {
    $("#jqGrid").jqGrid({
        url: '../tstore/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true,  align:"center",hidden: true},
			{label: '商户名称', name: 'merchantName', index: 'merchant_name', align:"center", width: 80},
			{label: '主营业务', name: 'mainBusiness', index: 'main_business', align:"center", width: 80},
			{label: '地址', name: 'address', index: 'address',align:"center",  width: 80},
			{label: '联系电话', name: 'phoneNo', index: 'phone_no', align:"center", width: 80},
			{label: '操作', name: 'view', index: 'id', align:"center", width: 80,
				formatter: function (value,row,index) {
						var result = "<a href='#' onclick='vm.viewImages("+row.rowId+")'>查看</a> ";
                    return result;
                }
			}
//			,
//			{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
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

//获取当前用户的用户名
function getUserName(){
	const currentUser = localStorage.getItem("CURRENT_USER");
	var currentUserName = JSON.parse(currentUser).fullName;
	return currentUserName;
}

// 动态生成div
function genDiv(uplodList){
	 vm.showImages = true;
	 $("#swiper-wrapper-id").empty();
	 // 获取div
    var div = document.getElementById("swiper-wrapper-id");  
    
//    while(div.hasChildNodes()) // 当div下还存在子节点时 循环继续
//    {
//        div.removeChild(div.firstChild);
//    }
   
    for(var i =0;i< uplodList.length;i++ ){
//    	var imgUrl = "../statics/img/Group.png";
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
	$.get("../tstore/queryImages?id=" + id, function (r) {
		var array = r.list;
		genDiv(array);
    });
}
let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
        showImages:false,
        showSaveButton:true,
		tStore: {
			imgList:[],
			primaryPicUrl: '',
            listPicUrl: ''
		},
		uploadList:[],
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
			vm.uploadList = [];
			var userName = getUserName();
			vm.tStore = {
					merchantName:userName
			};
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            vm.tStore.merchantName=getUserName();
            vm.getImagesGallery(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.tStore.id == null ? "../tstore/save" : "../tstore/update";
            vm.tStore.imgList = vm.uploadList;
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tStore),
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
				    url: "../tstore/delete",
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
			$.get("../tstore/info/"+id, function (r) {
                vm.tStore = r.tStore;
            });
		},
		getList: function(){
			$.get("../tstore/queryAll", function (r) {
				if(0 != r.list.length){
					vm.showSaveButton = false;
				}
            });
		},
		getImagesGallery: function (id) {//获取图片
	            $.get("../tstore/queryImages?id=" + id, function (r) {
	                vm.uploadList = r.list;
	                for(var i = 0;i<vm.uploadList.length;i++){
	                	vm.uploadList[i].status = "finished";
	                }
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
		//==================上传图片====================//
		 handleView(name) {
          this.imgName = name;
          this.visible = true;
      },
      handleRemove(file) {
          // 从 upload 实例删除数据
          const fileList = this.uploadList;
          this.uploadList.splice(fileList.indexOf(file), 1);
      },
      handleSuccess(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          vm.uploadList.add(file);
      },
      handleBeforeUpload() {
          const check = this.uploadList.length < 5;
          if (!check) {
              this.$Notice.warning({
                  title: '最多只能上传 5 张图片。'
              });
          }
          return check;
      },
      handleSubmit: function (name) {
          handleSubmitValidate(this, name, function () {
              vm.saveOrUpdate()
          });
      },
      handleFormatError: function (file) {
          this.$Notice.warning({
              title: '文件格式不正确',
              desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
          });
      },
      handleMaxSize: function (file) {
          this.$Notice.warning({
              title: '超出文件大小限制',
              desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
          });
      },
      handleReset: function (name) {
          handleResetForm(this, name);
      },
      handleSuccessPicUrl: function (res, file) {
          vm.tStore.primaryPicUrl = file.response.url;
      },
      handleSuccessListPicUrl: function (res, file) {
          vm.tStore.listPicUrl = file.response.url;
      },
      eyeImagePicUrl: function () {
          var url = vm.tStore.primaryPicUrl;
          eyeImage(url);
      },
      eyeImageListPicUrl: function () {
          var url = vm.tStore.listPicUrl;
          eyeImage(url);
      },
      eyeImage: function (e) {
          eyeImage($(e.target).attr('src'));
      }
	},
	mounted() {
      this.uploadList = this.$refs.upload.fileList;
      this.getList();
  }
});