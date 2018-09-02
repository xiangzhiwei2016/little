$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcasedetail/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '案例', name: 'caseName', index: 'case_name', align:"center",width: 80},
			{label: '标题', name: 'detailTitle', index: 'detail_title',align:"center", width: 80},
			{label: '描述', name: 'description', index: 'description', align:"center",width: 80}
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

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
        tCaseDetail: {
			imgList:[],
			caseId :''
		},
		uploadList:[],
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			],
			caseId: [
						{required: true, message: '案例不能为空，请先选择'}
					]
		},
		q: {
		    name: ''
		},
		cases2:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.uploadList = [];
			vm.cases2=[];
			vm.tCaseDetail = {};
			vm.getCases2();
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getCases2();
            vm.getInfo(id);
            vm.getImagesGallery(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.tCaseDetail.id == null ? "../tcasedetail/save" : "../tcasedetail/update";
            vm.tCaseDetail.imgList = vm.uploadList;
            $.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tCaseDetail),
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
				    url: "../tcasedetail/delete",
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
			$.get("../tcasedetail/info/"+id, function (r) {
                vm.tCaseDetail = r.tCaseDetail;
            });
		},
		getCases2: function () {
            $.get("../tcasedetail/queryAllCases", function (r) {
                vm.cases2 = r.list;
            });
        },
		getImagesGallery: function (id) {//获取图片
            $.get("../tcasedetail/queryImages?id=" + id, function (r) {
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
	      eyeImageListPicUrl: function () {
	          var url = vm.tCase.listPicUrl;
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