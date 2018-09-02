$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcase/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '案例名称', name: 'caseName', index: 'case_name',  align:"center",width: 80},
			{label: '地址', name: 'address', index: 'address',  align:"center",width: 80},
			{label: '面积', name: 'area', index: 'area', align:"center", width: 80},
			{label: '风格', name: 'style', index: 'style', align:"center", width: 80},
			{label: '采购清单', name: 'products', index: 'products', align:"center",width: 80},
	/*		{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},*/
			{
                label: '案例主图', name: 'imgUrl', index: 'img_url', align:"center", width: 80, formatter: function (value) {
                return transImg(value);
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

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        visible: false,
        tCaseDetail0Flag: false,
        tCaseDetail1Flag: false,
        tCaseDetail2Flag: false,
		tCase: {
			imgList:[],
			caseDetailList:[]
		},
		tCaseDetail0: {
			detailTitle:"",
			description:"",
			uploadDetailList:[],
			imgList:[]
		},
		tCaseDetail1: {
			detailTitle:"",
			description:"",
			uploadDetailList:[],
			imgList:[]
		},
		tCaseDetail2: {
			detailTitle:"",
			description:"",
			uploadDetailList:[],
			imgList:[]
		},
		uploadDetailList:[],
		caseDetailList:[],
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
			vm.tCase = {};
			vm.tCaseDetail0={};
			vm.tCaseDetail0Flag= true;
			vm.tCaseDetail1Flag=false;
	        vm.tCaseDetail2Flag= false;
			vm.uploadList = [];
			vm.caseDetailList=[];
		},
		update: function (event) {
			vm.tCaseDetail0Flag= false;
			vm.tCaseDetail1Flag=false;
	        vm.tCaseDetail2Flag= false;
	        vm.caseDetailList=[];
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getImagesGallery(id);
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.tCase.id == null ? "../tcase/save" : "../tcase/update";
            vm.tCase.imgList = vm.uploadList;
            vm.tCase.caseDetailList = [];
            vm.tCase.caseDetailList.push(vm.tCaseDetail0);
            vm.tCase.caseDetailList.push(vm.tCaseDetail1);
            vm.tCase.caseDetailList.push(vm.tCaseDetail2);
            console.log(vm.tCase);
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tCase),
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
				    url: "../tcase/delete",
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
			$.get("../tcase/info/"+id, function (r) {
                vm.tCase = r.tCase;
                console.log(r.tCase);
                // 案例详情
                for(var i=0;i< r.tCase.caseDetailList.length;i++){
                	if(!vm.tCaseDetail0Flag){
                		vm.tCaseDetail0Flag = true;
                		vm.tCaseDetail0 = r.tCase.caseDetailList[i];
                		console.log(vm.tCaseDetail0);
                	}else if(!vm.tCaseDetail1Flag){
                		vm.tCaseDetail1Flag = true;
                		vm.tCaseDetail1 = r.tCase.caseDetailList[i];
                		console.log(vm.tCaseDetail1);
                	}else if(!vm.tCaseDetail2Flag){
                		vm.tCaseDetail2Flag = true;
                		vm.tCaseDetail2 = r.tCase.caseDetailList[i];
                		console.log(vm.tCaseDetail2);
                	}
                }
            });
		},
		getImagesGallery: function (id) {//获取图片
            $.get("../tcase/queryImages?id=" + id, function (r) {
                vm.uploadList = r.list;
                for(var i = 0;i<vm.uploadList.length;i++){
                	vm.uploadList[i].status = "finished";
                }
            });
        },
        createDiv: function(){
        	var tCaseDetail = {
        			uploadDetailList:[]
        	};
        	vm.tCase.caseDetailList = vm.caseDetailList;
        	vm.tCase.caseDetailList.push(tCaseDetail);
        },
        create: function(){
        	if(!vm.tCaseDetail0Flag){
        		vm.tCaseDetail0Flag = true;
        	}else if(!vm.tCaseDetail1Flag){
        		vm.tCaseDetail1Flag = true;
        	}else if(!vm.tCaseDetail2Flag){
        		vm.tCaseDetail2Flag = true;
        	}else{
        		alert("最多只能上传3个案例详情");
        		return;
        	}
        },
        delDiv:function(index){
        	if(vm.tCase.caseDetailList.length == 1){
        		alert("至少需要上传1个案例详情");
        		return;
        	}
        	vm.tCase.caseDetailList.splice(index,1);
        },
        del0:function(){
        	if(vm.tCaseDetail1Flag == false && vm.tCaseDetail2Flag == false){
        		alert("至少需要上传1个案例详情");
        		return;
        	}
        	vm.tCaseDetail0Flag=false;
        	vm.tCaseDetail0 = {};
        },
        del1:function(){
        	if(vm.tCaseDetail0Flag == false && vm.tCaseDetail2Flag == false){
        		alert("至少需要上传1个案例详情");
        		return;
        	}
        	vm.tCaseDetail1Flag=false;
        	vm.tCaseDetail1 = {};
        },
        del2:function(){
        	if(vm.tCaseDetail1Flag == false && vm.tCaseDetail0Flag == false){
        		alert("至少需要上传1个案例详情");
        		return;
        	}
        	vm.tCaseDetail2Flag=false;
        	vm.tCaseDetail2 = {};
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
      handleRemove2(file) {
          // 从 upload 实例删除数据
          const fileList = this.uploadDetailList;
          this.uploadDetailList.splice(fileList.indexOf(file), 1);
      },
      handleSuccess(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          vm.uploadList.add(file);
      },
      handleSuccess0(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          if(null == vm.tCaseDetail0.uploadDetailList || undefined == vm.tCaseDetail0.uploadDetailList){
        	  vm.tCaseDetail0.uploadDetailList = [];
          }
          vm.tCaseDetail0.uploadDetailList.push(file);
      },
      handleSuccess1(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          if(null == vm.tCaseDetail1.uploadDetailList || undefined == vm.tCaseDetail1.uploadDetailList){
        	  vm.tCaseDetail1.uploadDetailList = [];
          }
          vm.tCaseDetail1.uploadDetailList.push(file);
      },
      handleSuccess2(res, file) {
          // 因为上传过程为实例，这里模拟添加 url
          file.imgUrl = res.url;
          file.name = res.url;
          if(null == vm.tCaseDetail2.uploadDetailList || undefined == vm.tCaseDetail2.uploadDetailList){
        	  vm.tCaseDetail2.uploadDetailList = [];
          }
          vm.tCaseDetail2.uploadDetailList.push(file);
      },
      handleBeforeUpload() {
          const check = this.uploadList.length < 1;
          if (!check) {
              this.$Notice.warning({
                  title: '案例主图，最多只能上传 1 张图片。'
              });
          }
          return check;
      },
      handleBeforeUpload2() {
          const check = this.uploadList.length < 5;
          if (!check) {
              this.$Notice.warning({
                  title: '案例详情图，最多只能上传 5 张图片。'
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
      this.uploadDetailList = this.$refs.uploadDetail.fileList;
  }
});