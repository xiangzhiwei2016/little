$(function () {
    $("#jqGrid").jqGrid({
        url: '../tproducttest/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '商品名称', name: 'productName', index: 'product_name', width: 80},
			{label: '会员价（精度4位）', name: 'vipPrice', index: 'vip_price', width: 80},
			{label: '原价（精度4位）', name: 'price', index: 'price', width: 80},
			{label: '标签', name: 'tag', index: 'tag', width: 80},
			{label: '描述', name: 'productDesc', index: 'product_desc', width: 80},
			{label: '商品状态', name: 'productStatus', index: 'product_STATUS', width: 80},
			{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
			{label: '修改时间', name: 'modifyTime', index: 'modify_time', width: 80},
			{label: '创建者', name: 'creator', index: 'creator', width: 80},
			{label: '修改者', name: 'modifier', index: 'MODIFIER', width: 80}],
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

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
		tProductTest: {
			primaryPicUrl: '',
            listPicUrl: ''
		},
        uploadList:[],
		ruleValidate: {
			productName: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    productName: ''
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
			vm.tProductTest = {
					primaryPicUrl: '',
	                listPicUrl: ''
			};
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
            let url = vm.tProductTest.id == null ? "../tproducttest/save" : "../tproducttest/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tProductTest),
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
				    url: "../tproducttest/delete",
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
			$.get("../tproducttest/info/"+id, function (r) {
                vm.tProductTest = r.tProductTest;
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'productName': vm.q.productName},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
//        handleSubmit: function (name) {
//            handleSubmitValidate(this, name, function () {
//                vm.saveOrUpdate()
//            });
//        },
//        handleReset: function (name) {
//            handleResetForm(this, name);
//        }
		//==================上传图片用到的====================//
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
            vm.tProductTest.primaryPicUrl = file.response.url;
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.tProductTest.listPicUrl = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.tProductTest.primaryPicUrl;
            eyeImage(url);
        },
        eyeImageListPicUrl: function () {
            var url = vm.tProductTest.listPicUrl;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
	},
	mounted() {
        this.uploadList = this.$refs.upload.fileList;
    }
});